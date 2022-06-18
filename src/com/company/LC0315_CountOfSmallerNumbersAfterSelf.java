package com.company;

import java.util.ArrayList;
import java.util.List;

public class LC0315_CountOfSmallerNumbersAfterSelf {
    public static void main(String[] args){
        int[] input = {5,2,6,1};
        // expected output: [2,1,1,0]
        System.out.println(countSmaller(input));
    }

    // Todo: Improve time complexity
    public static List<Integer> countSmaller(int[] nums) {
        List<Integer> result = new ArrayList<>();
        for(int i=0; i<nums.length-1; i++){
            int count = 0;
            for(int j=i+1; j<nums.length; j++){
                if(nums[j] < nums[i]) count++;
            }
            result.add(count);
        }
        result.add(0);

        return result;
    }
}
