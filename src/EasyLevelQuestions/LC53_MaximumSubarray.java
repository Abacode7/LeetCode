package EasyLevelQuestions;

public class LC53_MaximumSubarray {
    public static void main(String[] args){
        int[] input = {-2,1,-3,4,-1,2,1,-5,4}; //ans 6
        System.out.println(maxSubArray(input));
    }

    /*
    Idea is each value (which on it's own is a sub array), with
    the sum of itself and the maxContinuousSumSoFar to get the new maxContinuousSumSoFar.
    We then keep a variable to store the max Sums of all maxContinuousSumSoFar we've got.
     */
    // Runtime O(N), Space O(1)
    public static int maxSubArray(int[] nums) {
        int maxSum = Integer.MIN_VALUE;
        int maxSumSoFar = 0;

        for(int i=0; i<nums.length; i++){
            maxSumSoFar = Math.max(nums[i], nums[i] + maxSumSoFar);
            maxSum = Math.max(maxSum, maxSumSoFar);
        }

        return maxSum;
    }
}
