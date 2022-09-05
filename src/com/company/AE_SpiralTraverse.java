package com.company;

import java.util.*;

public class AE_SpiralTraverse {
    public static void main(String[] args){
        int[][] input = {
                {1, 2, 3, 4, 5},
                {12, 13, 14, 5},
                {11, 16, 15, 6},
                {10, 9, 8, 7}
        };

        List<Integer> result = spiralTraverse(input);
        Main.printArray(result);
    }
    /**
     1 2 3 4 5
     10  9  8  7  6

     00 01 02 03 04
     10 11 12 13 14
     20 21 22 23 24
     30 31 32 33 34

     column to max
     row to max
     column to min
     row to min-1 if(indexes exist then stop)

     change max & min values
     then repeat proces
     **/

    // Todo: Timeout
    public static List<Integer> spiralTraverse(int[][] array) {
        if(array == null || array.length == 0) return new ArrayList<>();

        List<Integer> result = new ArrayList<>();
        int n = array.length-1;
        int m = array[0].length-1;

        Set<String> set = new HashSet<>();
        int i = 0, j = 0;
        while(!set.contains(i + "" + j)){
            int[] index = traverse(array, result, set, 0, n, 0, m);
            i = index[0];
            j = index[1];
        }
        // Write your code here.
        return result;
    }

    public static int[] traverse(int[][] array, List<Integer> result, Set<String> set,
                                 int nMin, int nMax, int mMin, int mMax){

        int i = nMin;
        int j = mMin;

        while(j <= mMax){
            result.add(array[i][j]);
            set.add(i + "" + j);
            j++;
        }
        j--;

        while(i <= nMax){
            result.add(array[i][j]);
            set.add(i + "" + j);
            i++;
        }
        i--;

        while(j >= mMin){
            result.add(array[i][j]);
            set.add(i + "" + j);
            j--;
        }
        j++;

        while(i > nMin){
            result.add(array[i][j]);
            set.add(i + "" + j);
            i--;
        }
        i++;

        return new int[]{i, j+1};
    }
}
