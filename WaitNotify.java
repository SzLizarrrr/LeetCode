import java.util.concurrent.TimeUnit;

public class WaitNotify {
  static boolean flag = true;
  static Object lock = new Object();

  public static void main(String[] args) throws Exception {
    Thread waitThread = new Thread(new Wait(), "WaitThread");
    waitThread.start();
    TimeUnit.SECONDS.sleep(1);
    Thread notifyThread = new Thread(new Notify(), "NotifyThread");
    notifyThread.start();
  }

  static class Wait implements Runnable {
    public void run() {
      //加锁
      synchronized (lock) {
        //当条件不满足的时候，进入WAITTING状态，同时释放lock锁
        try {
          while (flag) {
            System.out.println("flag is true ");
            lock.wait();
          }
        } catch (Exception e) {
          System.out.println(e);
        }
        //条件满足
        System.out.println("doSomething");
      }
    }
  }

  static class Notify implements Runnable {
    public void run() {
      //加锁
      synchronized (lock) {
        //获取lock的锁，然后进行通知，通知不会释放lock锁
        //直到发出通知的线程执行完毕释放了lock锁，WaitThread线程才能从wait方法返回
        lock.notifyAll();
        System.out.println("flag is false now");
        flag = false;
      }
    }
  }
}
