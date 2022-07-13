package com.company;

import java.util.*;

public class AE_SmallDifference {
    /**
     [-1 3 5 10 20 28]
     [15 17 26 134 135]

     Rather than brute force, which compares every
     element against each other

     we use sliding window, which compares the absolutely
     necessary elements against themselves
     **/
    public static int[] smallestDifference(int[] arrayOne, int[] arrayTwo) {
        // Write your code here.
        Arrays.sort(arrayOne);
        Arrays.sort(arrayTwo);

        int i = 0, j = 0;
        int minDiff = Integer.MAX_VALUE;
        int[] result = new int[2];
        while(i < arrayOne.length && j < arrayTwo.length){
            // Check difference
            if(Math.abs(arrayOne[i] - arrayTwo[j]) < minDiff){
                result[0] = arrayOne[i];
                result[1] = arrayTwo[j];

                minDiff = Math.abs(arrayOne[i] - arrayTwo[j]);
            }

            // Slide window
            if(arrayOne[i] < arrayTwo[j]) i++;
            else j++;
        }
        return result;
    }
}
