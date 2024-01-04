package com.company;

import java.util.ArrayList;
import java.util.List;

public class AE_LongestPeak {
    /**

     - Find all peak indexes
        - Peaks are tipping points, which are greater than their left and right adj values

     - Check each peaks for their lengths and return the max.

     **/
    public static int longestPeak(int[] array) {
        // Write your code here.
        List<Integer> peakIndexes = new ArrayList<>();
        for(int i=1; i < array.length-1; i++){
            int possiblePeak = array[i];
            if(possiblePeak > array[i-1] && possiblePeak > array[i+1]) peakIndexes.add(i);
        }

        Integer longestPeak = Integer.MIN_VALUE;
        for(Integer peakIndex: peakIndexes){
            int peakLength = findPeakLength(array, peakIndex);
            longestPeak = Math.max(longestPeak, peakLength);
        }
        return  longestPeak >= 3 ? longestPeak : 0;
    }

    public static int findPeakLength(int[] array, int index){
        int n = array.length;
        int i=index-1;
        int j=index+1;

        boolean hasNext = (j+1 < n && array[j] > array[j+1]) || (i-1 >= 0 && array[i] > array[i-1]);
        while(hasNext){
            if(j+1 < n && array[j] > array[j+1]) j++;
            if(i >= 0 && array[i] > array[i-1]) i--;

            hasNext = (j+1 < n && array[j] > array[j+1]) || (i-1 >= 0 && array[i] > array[i-1]);
        }

        return j - i + 1;
    }
}
