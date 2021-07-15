package EasyLevelQuestions;
import java.util.*;

public class LC384_ShuffleAnArray {
    public static void main(String[] args){
        int[] input = {1, 2, 3, 4, 5};
        Solution solution = new Solution(input);
        System.out.println(Arrays.toString(solution.shuffle()));
    }
}

class Solution {
    private int[] nums;
    private Random random;

    public Solution(int[] nums) {
        this.nums = nums;
        random = new Random();
    }

    /** Resets the array to its original configuration and return it. */
    // Runtime O(1), Space O(1)
    public int[] reset() {
        return nums;
    }

    /** Returns a random shuffling of the array. */
    /*
    Using Knuth Shuffle algorithm
    NB: There are many ways to express this algorithm
    Check out this variation

    for(int i=result.length-1; i>0; i--){
        int randInt = random.nextInt(i);
        swap(result, i, randInt);
     }
     */
    // Runtime O(N), Space O(N)
    public int[] shuffle() {
        int[] result = new int[nums.length];
        for(int i=0; i<nums.length; i++){
            result[i] = nums[i];
        }

        for(int i=0; i<result.length; i++){
            int randInt = i + random.nextInt(result.length-i);
            swap(result, i, randInt);
        }
        return result;
    }

    private void swap(int[] array, int i, int j){
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}