package com.company;

public class LC0053_MaximumSubarraySum {
    public static void main(String[] args){
        System.out.println(maxSubArray(new int[]{-2,1,-3,4,-1,2,1,-5,4})); //6
    }

    public static int maxSubArray(int[] nums) {
        if(nums.length == 0) return 0;

        int maxSubarraySum = nums[0];
        int[] maxSumTillIndexArray = new int[nums.length];
        maxSumTillIndexArray[0] = nums[0];

        for(int i=1; i<nums.length; i++){
            maxSumTillIndexArray[i] = Math.max(nums[i], nums[i] + maxSumTillIndexArray[i-1]);
            maxSubarraySum = Math.max(maxSubarraySum, maxSumTillIndexArray[i]);
        }

        return maxSubarraySum;
    }
}
