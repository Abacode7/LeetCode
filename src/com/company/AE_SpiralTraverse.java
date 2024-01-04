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

    // Todo: Rework to cover all test cases (8/12)
    /**

     1  2  3  4 0
     10 11 12 5 0
     9   8  7 6 0
     2   45 0 8 3

     increase j = startJ - endJ
     increase i = startI+1 - endI
     decrease j = endJ-1 - startJ
     decrease i = endI-1 - start-1

     // stopping condition, if any index is repeated - stop and return

     [[1 2 3 4 5]

     ]

     **/
    public static List<Integer> spiralTraverse(int[][] array) {
        List<Integer> result = new ArrayList<>();
        Set<String> set = new HashSet<>();

        int startI = 0, endI = array.length-1, startJ = 0, endJ = array[0].length-1;
        while(allInBound(array, startI, endI, startJ, endJ) && !set.contains(startI + "" + startJ)){
            spiral(array, result, set, startI, endI, startJ, endJ);
            startI++;
            startJ++;
            endI--;
            endJ--;
        }
        // Write your code here.
        return result;
    }

    public static boolean allInBound(int[][] array, int startI, int endI, int startJ, int endJ){
        if(startI < 0 || startI >= array.length) return false;

        if(endI < 0 || endI >= array.length) return false;

        if(startJ < 0 || startJ >= array.length) return false;

        if(endJ < 0 || endJ >= array.length) return false;

        return true;
    }

    public static void spiral(int[][] array, List<Integer> result, Set<String> set, int startI, int endI, int startJ, int endJ){
        for(int i=startI, j=startJ; j<=endJ; j++){
            if(set.contains(i + "" + j)) return;
            result.add(array[i][j]);
            set.add(i + "" + j);
        }

        for(int j=endJ, i=startI+1; i<=endI; i++){
            if(set.contains(i + "" + j)) return;
            result.add(array[i][j]);
            set.add(i + "" + j);
        }

        for(int i=endI, j=endJ-1; j>=startJ; j--){
            if(set.contains(i + "" + j)) return;
            result.add(array[i][j]);
            set.add(i + "" + j);
        }

        for(int j=startJ, i=endI-1; i>=startI-1; i--){
            if(set.contains(i + "" + j)) return;
            result.add(array[i][j]);
            set.add(i + "" + j);
        }
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
    public static List<Integer> spiralTraverse1(int[][] array) {
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
