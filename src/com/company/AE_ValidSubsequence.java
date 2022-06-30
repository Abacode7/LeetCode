package com.company;

import java.util.List;

public class AE_ValidSubsequence {
    public static boolean isValidSubsequence(List<Integer> array, List<Integer> sequence) {
        // Write your code here.
        int i = 0;
        int j = 0;
        while(i < array.size()){
            if(array.get(i) == sequence.get(j)) j++;

            if(j == sequence.size()) return true;

            i++;
        }
        return false;
    }
}
