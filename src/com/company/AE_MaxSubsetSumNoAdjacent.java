package com.company;

public class AE_MaxSubsetSumNoAdjacent {
    /**

     6 2 8 1 7 3
     even: 21
     odd: 6

     6 2 8 1 7 3 12 100
     even: 33
     odd: 106

     even: 29
     odd: 116
     valid: 6 21 100 => 127

     6 9 21 7  2  100
     6 9 27 27 29 127

     we need a maxSum array which stores the maximum sum up until and including
     that index
     maxSum[i] = max of (maxSum[i-1], maxSum[i-2] + array[i])

     we need maxSum[0] and maxSum[1]

     **/
    public static int maxSubsetSumNoAdjacent(int[] array) {
        // Write your code here.
        int n = array.length;
        if(n == 0) return 0;
        if(n == 1) return array[0];

        int[] maxSum = new int[n];
        maxSum[0] = array[0];
        maxSum[1] = Math.max(array[0], array[1]);

        for(int i=2; i<n; i++){
            maxSum[i] = Math.max(maxSum[i-1], maxSum[i-2] + array[i]);
        }
        return maxSum[n-1];
    }
}
