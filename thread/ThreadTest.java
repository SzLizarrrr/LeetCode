package thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.StampedLock;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;
import java.util.concurrent.TimeUnit;

public class ThreadTest {
    static int count = 0;
    ExecutorService executor = Executors.newFixedThreadPool(2);

    public static void main(String[] args) {
        ThreadTest threadTest = new ThreadTest();
        threadTest.stampedLockTest();
    }

    void nomalTest() {
        IntStream.range(0, 10000).forEach(i -> executor.submit(this::nomalIncrement));

        stop(executor);

        System.out.println(count);
    }

    void reentrantlockTest() {
        ReentrantLock lock = new ReentrantLock();
        executor.submit(() -> {
            lock.lock();
            try {
                sleep(1);
            } finally {
                lock.unlock();
            }
        });

        executor.submit(() -> {
            System.out.println("Locked: " + lock.isLocked());
            System.out.println("Held by me: " + lock.isHeldByCurrentThread());
            boolean locked = lock.tryLock();
            System.out.println("Lock acquired: " + locked);
        });
    }

    void readWriteLockTest() {
        ReadWriteLock lock = new ReentrantReadWriteLock();
        Map<String, String> map = new HashMap<>();
        lock.writeLock().lock();
        try {
            sleep(1);
            map.put("foo", "bar");
        } finally {
            lock.writeLock().unlock();
        }

        Runnable readTask = () -> {
            lock.readLock().lock();
            try {
                System.out.println(map.get("foo"));
                sleep(1);
            } finally {
                lock.readLock().unlock();
            }
        };

        executor.submit(readTask);
        executor.submit(readTask);

        stop(executor);
    }

    void stampedLockTest() {
        Map<String, String> map = new HashMap<>();
        StampedLock lock = new StampedLock();

        executor.submit(() -> {
            long stamp = lock.writeLock();
            try {
                sleep(1);
                map.put("foo", "bar");
            } finally {
                lock.unlockWrite(stamp);
            }
        });

        Runnable readTask = () -> {
            long stamp = lock.readLock();
            try {
                System.out.println(map.get("foo"));
                sleep(1);
            } finally {
                lock.unlockRead(stamp);
            }
        };

        executor.submit(readTask);
        executor.submit(readTask);

        stop(executor);
    }

    void nomalIncrement() {
        synchronized (this) {
            count += 1;
        }
    }

    void increment() {
        ReentrantLock lock = new ReentrantLock();
        lock.lock();
        try {
            count++;
        } finally {
            lock.unlock();
        }

    }

    void stop(ExecutorService executor) {
        try {
            executor.shutdown();
            executor.awaitTermination(60, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            System.err.println("termination interrupted");
        } finally {
            if (!executor.isTerminated()) {
                System.err.println("killing non-finished tasks");
            }
            executor.shutdownNow();
        }
    }

    void sleep(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            throw new IllegalStateException(e);
        }
    }
}