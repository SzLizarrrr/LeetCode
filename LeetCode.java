import java.util.List;
import java.util.LinkedList;

public class LeetCode {
    public static void main(String[] args){
        List<Integer> test = new LinkedList<Integer>();
        // test.add(1);
        System.out.println(test.size());
        // String haystack = "ashangesaxdf";
        // String needle = "ang";
        // System.out.println(strStr(haystack, needle));
    }

    public static int searchInsert(int[] nums, int target){
        for(int i=0; i<nums.length; i++){
                if ( target <= nums[i] ) {return i;}
        }
        return nums.length;
    }

    public static int strStr(String haystack, String needle){
        for (int i=0; i<(haystack.length()-needle.length()+1); i++){
            if (needle.equals(haystack.substring(i, i+needle.length()))){
                return i;
            }
        }
        return -1;
    }
}

