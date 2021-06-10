package EasyLevelQuestions;

import java.util.*;

// NB: LC Question - 17
public class ContainsDuplicate {
    public static void main(String[] args){
        int[] input1 = {1,2,3,1};
        int[] input12 = {1,2,3,4};
        System.out.println(containsDuplicate(input1));
    }

    // Initial Solution
    public static boolean containsDuplicate(int[] nums) {
        Arrays.sort(nums);
        int i = 0;
        while(i < nums.length - 1){
            if(nums[i] == nums[i+1]) return true;
            i++;
        }
        return false;
    }
}
