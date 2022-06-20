package com.company;

public class LC0026_RemoveDuplicatesFromSortedArray {
    public static void main(String[] args){
        int[] nums = {0,0,1,1,1,2,2,3,3,4};
        System.out.println(removeDuplicates(nums));
    }

    public static int removeDuplicates(int[] nums) {
        if(nums.length == 1) return 1;

        int i = 0;
        int j = 1;
        while(j < nums.length){
            if(nums[j] != nums[i]){
                i++;
                nums[i] = nums[j];
            }
            j++;
        }
        return i+1;
    }
}
