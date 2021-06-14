package EasyLevelQuestions;

import java.util.*;

// LC Question 1
public class TwoSums {
    public static void main(String[] args){
        int[] input = {2,7,11,15};
        System.out.printf("Solution: %s\n", Arrays.toString(twoSumImproved(input, 9)));
    }

    // First solution
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> result = new ArrayList<>();
        for(int i=0; i<nums.length; i++){
            if(map.keySet().contains(target-nums[i])){
                result.add(map.get(target-nums[i]));
                result.add(i);
                break;
            }
            map.put(nums[i], i);
        }

        int[] res = new int[result.size()];
        for(int i=0; i<result.size(); i++){
            res[i] = result.get(i);
        }
        return res;
    }

    // Improved solution
    // Runtime O(n), Space O(n)
    public static int[] twoSumImproved(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i=0; i<nums.length; i++){
            if(map.keySet().contains(target-nums[i])){
                return new int[]{map.get(target-nums[i]), i};
            }
            map.put(nums[i], i);
        }
        throw new IllegalArgumentException("No two sum solution");
    }
}
