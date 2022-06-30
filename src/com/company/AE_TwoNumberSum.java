package com.company;

import java.util.HashSet;
import java.util.Set;

public class AE_TwoNumberSum {
    public static int[] twoNumberSum(int[] array, int targetSum) {
        // Write your code here.
        Set<Integer> set = new HashSet<>();
        for(int value: array){
            if(set.contains(value)){
                return new int[]{value, targetSum - value};
            }
            set.add(targetSum - value);
        }
        return new int[0];
    }
}
