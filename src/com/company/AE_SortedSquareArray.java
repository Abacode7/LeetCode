package com.company;

import java.util.ArrayList;
import java.util.List;

public class AE_SortedSquareArray {
    public int[] sortedSquaredArray(int[] array) {
        // Write your code here.
        int i = 0, j = array.length - 1;
        List<Integer> result = new ArrayList<>();

        while(i <= j){
            if(Math.abs(array[i]) > Math.abs(array[j])){
                result.add(array[i] * array[i]);
                i++;
            }else{
                result.add(array[j] * array[j]);
                j--;
            }
        }

        int[] arrayResult = new int[result.size()];
        int k = result.size() - 1;
        for(int value: result){
            arrayResult[k] = value;
            k--;
        }

        return arrayResult;
    }
}
