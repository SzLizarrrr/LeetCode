import java.util.*;

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
        while (x != 0) {
            int tail = x%10;
            int tmp = result * 10 + tail;
            if ((tmp - tail)/10 != result) return 0; // if (tmp - tail)/10 not equals tp result, that means Integer range effect the mod.
            result = tmp;
            x = x/10;
        }
        return result;
    }

    /**9. Palindrome Number */
    public boolean isPalindrome(int x) {
        if (x < 0 || (x % 10 == 0 && x != 0)) return false;
        int tmp = x, reverse = 0;

        while (tmp != 0) {
            reverse = reverse * 10 + tmp % 10;
            tmp = tmp / 10;
        }

        return reverse == x;
    }

    public boolean isPalindromeInDisucss(int x) { // the only difference between my soluction and this soluction is here only reversed half number and cut have number from original number. Mine resultervsed whold number.
        if ( x < 0 || (x % 10 == 0 && x != 0) ) return false;
        int halfReverse = 0;

        while (halfReverse < x) {
            halfReverse = halfReverse * 10 + x % 10;
            x = x / 10;
        }

        return x == halfReverse || x == halfReverse / 10;
    }

    /**13. Roman to Integer */
    public int romanToInt(String s) {
        int result = 0;
        int preValue = 0;
        Map<Character, Integer> dict = new HashMap<Character, Integer>();
        dict.put('M', 1000);
        dict.put('D', 500);
        dict.put('C', 100);
        dict.put('L', 50);
        dict.put('X', 10);
        dict.put('V', 5);
        dict.put('I', 1);

        for (int i = 0; i < s.length(); i++) {
            int point = dict.get(s.charAt(i));
            result += point > preValue ? point - 2 * preValue : point;
            preValue = point;
        }

        return result;
    }

    public int romanToIntInDiscuss(String s) {
        int result = 0;
        for (int i = s.length(); i > 0; i --) {
            switch (s.charAt(i-1)) {
                case 'I':
                    result += (result >= 5 ? -1 : 1);
                    break;
                case 'V':
                    result += 5;
                    break;
                case 'X':
                    result += 10 * (result >= 50 ? -1 : 1);
                    break;
                case 'L':
                    result += 50;
                    break;
                case 'C':
                    result += 100 * (result >= 500 ? -1 : 1);
                    break;
                case 'D':
                    result += 500;
                    break;
                case 'M':
                    result += 1000;
                    break;
            }
        }
        return result;
    }

    /**14. Longest Common Prefix */
    /*
     * I don't have my answer for this question, I got all the idea from solutions.
     * Here I left a note for my self. If want to solute this question, need to fully
     * understand what string.indexOf() is
     */

    /**20. Valid Parentheses */
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<Character>();
        stack.push('A');
        for (char element : s.toCharArray()) {
            switch (element) {
                case '}':
                    if (stack.pop() == '{')
                        break;
                    else
                        return false;
                case ']':
                    if (stack.pop() == '[')
                        break;
                    else
                        return false;
                case ')':
                    if (stack.pop() == '(')
                        break;
                    else
                        return false;
                default :
                    stack.push(element);
            }
        }
        return stack.peek() == 'A';
    }

    public boolean isValidInDiscuss(String s) {
        Stack<Character> stack = new Stack<Character>();
        for (char element : s.toCharArray()) {
            if (element == '{')
                stack.push('}');
            else if (element == '[')
                stack.push(']');
            else if (element == '(')
                stack.push(')');
            else if (stack.empty() || stack.pop() != element)
                return false;
        }
        return stack.empty();
    }

    /**21. Merge Two Sorted Lists */
    /*
     * Please check SolutionPartOne.java about this question's answer
     */

    /**26. Remove Duplicates from Sorted Array */
    /**27. Remove Element */
    /*
     * Once have time, please review these questions
     */

    /**28. Implement strStr() */
    public int strStr(String haystack, String needle) {
        for (int i = 0; i < haystack.length()-needle.length()+1; i++) {
            if (needle.equals(haystack.substring(i, i+needle.length())))
                return i;
        }
        return -1;
    }

    public int strStrIntDiscuss(String haystack, String needle) {
        for (int i = 0; ; i++) {
            for (int j = 0; ; j++) {
                if (j == needle.length()) return i;
                if (i + j == haystack.length()) return -1;
                if (needle.charAt(j) != haystack.charAt(i + j)) break;
            }
        }
    }

    /**35.Search Insert Position */
    public int searchInsert(int[] nums, int target) {
        if (target > nums[nums.length-1]) return nums.length;
        if (target < nums[0]) return 0;
        if (nums.length == 0) return 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= target) return i;
        }
        return nums.length;
    }

    public int searchInsertInDiscuss(int[] nums, int target) {
        int start = 0, end = nums.length-1;

        while (start <= end) {
            int mid = (start + end) / 2;
            if (nums[mid] < target) start = mid + 1;
            else if (nums[mid] > target) end = mid - 1;
            else return mid;
        }

        return start;
    }
}
