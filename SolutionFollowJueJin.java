
public class SolutionFollowJueJin {
    /**7. Reverse Integer */
    public int reverse(int x) {
        long result = 0;
        while ( x != 10 ) {
            result = (result * 10) + (x%10);
            if (result > Integer.MAX_VALUE) return 0;
            if (result < Integer.MIN_VALUE) return 0;
            x = x/10;
        }

        return (int)result;
    }

    public int reverseInDiscuss(int x) {
        int result = 0;
        while(x != 0) {
            int tail = x%10;
            int tmp = result * 10 + tail;
            if ((tmp - tail)/10 != result) return 0; // if (tmp - tail)/10 not equals tp result, that means Integer range effect the mod.
            result = tmp;
            x = x/10;
        }
        return result;
    }
}
