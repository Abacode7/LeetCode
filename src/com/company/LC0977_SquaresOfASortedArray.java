package com.company;

import static com.company.Main.printArray;

public class LC0977_SquaresOfASortedArray {
    public static void main(String[] args){
        int[] nums = {-4,-1,0,3,10};

        printArray(sortedSquares(nums));
    }

    public static int[] sortedSquares(int[] nums) {
        int n = nums.length;
        int[] result = new int[n];
        int i=0, j=n-1;

        for(int k=n-1; k>=0; k--){
            if(Math.abs(nums[i]) > Math.abs(nums[j])){
                result[k] = nums[i] * nums[i];
                i++;
            }else {
                result[k] = nums[j] * nums[j];
                j--;
            }
        }
        return result;
    }
}
