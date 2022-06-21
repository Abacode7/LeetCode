package com.company;

public class LC0704_BinarySearch {
    public static void main(String[] args){
        int[] nums = {-1,0,3,5,9,12};
        System.out.println(search(nums, 9));
    }

    public static int search(int[] nums, int target) {
        return binarySearch(nums, 0, nums.length-1, target);
    }
    public static int binarySearch(int[] nums, int startIndex, int endIndex, int target){
        if(startIndex > endIndex) return -1;
        int midIndex = (startIndex + endIndex)/2;

        if(nums[midIndex] == target) return midIndex;
        else if(nums[midIndex] < target) return binarySearch(nums, midIndex + 1, endIndex, target);
        else return binarySearch(nums, startIndex, midIndex-1, target);
    }
}
