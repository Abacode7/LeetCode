package com.company;

public class AE_KadanesAlgorithm {
    /**

     3 5 -9 1 3 -2 3 4 7  2 -9 6  3   1 -5  4
     3 8 -1 1 4  2 5 9 16 18 9 15 18  19 14 18

     - key is to get the cummulative sum up to that point at each point
     - cumSum[i] = max(cumSum[i-1] + cumSum[i], cumSum[i])
     - max = Max(max, cumSum[i])
     - get a max variable that calculates max at each point too


     **/
    public static int kadanesAlgorithm(int[] array) {
        // Write your code here.
        if(array.length == 1) return array[0];

        int max = array[0];
        int prevSum = array[0];

        for(int i=1; i<array.length; i++){
            int sum = Math.max(prevSum + array[i], array[i]);
            max = Math.max(max, sum);

            prevSum = sum;
        }
        return max;
    }
}
