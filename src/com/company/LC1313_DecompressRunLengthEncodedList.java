package com.company;

import java.util.*;

public class LC1313_DecompressRunLengthEncodedList {
    public static void main(String[] args){
        int[] nums = {1,1,2,3};
        System.out.println(decompressRLElist(nums));
    }

    public static int[] decompressRLElist(int[] nums) {
        List<Integer> list = new ArrayList<>();

        for(int i=0; i<nums.length; i+=2){
            int value = nums[i+1];
            int j = 1;
            while(j <= nums[i]){
                list.add(value);
                j++;
            }
        }
        return list.stream().mapToInt(i -> i).toArray();
    }
}
