package EasyLevelQuestions;

import java.util.*;

// NB: LC Question - 217
public class ContainsDuplicate {
    public static void main(String[] args){
        int[] input1 = {1,2,3,1};
        int[] input2 = {1,2,3,4};
        System.out.println(containsDuplicate2(input1));
    }

    // Initial Solution
    // Runtime - O(nlogn), Space - O(1)
    public static boolean containsDuplicate(int[] nums) {
        Arrays.sort(nums);
        int i = 0;
        while(i < nums.length - 1){
            if(nums[i] == nums[i+1]) return true;
            i++;
        }
        return false;
    }

    // Improved Solution
    // Runtime - O(n), Space - O(n)
    public static boolean containsDuplicate2(int[] nums){
        Set<Integer> set = new HashSet<>();
        int i = 0;
        while(i < nums.length){
            if(set.contains(nums[i])) return true;
            set.add(nums[i]);
            i++;
        }
        return false;
    }
}
