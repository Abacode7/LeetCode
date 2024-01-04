package com.company;

public class AE_BubbleSort {
    /**
     8 5 2 9 5 6 3

     first pass
     8 5 2 9 5 6 3
     5 8 2 9 5 6 3
     5 2 8 9 5 6 3
     5 2 8 5 9 6 3
     5 2 8 5 6 9 3
     5 2 8 5 6 3 9

     second pass
     5 2 8 5 6 3 9
     2 5 8 5 6 3 9
     2 5 5 8 6 3 9
     2 5 5 6 8 3 9
     2 5 5 6 3 8 9

     third pass
     2 5 5 6 3 8 9

     NB: There's always n-1 pass
     Since at the end of every pass the largest value is always in place.
     We can optimise by shifting the last index to be checked after every pass.

     Time: O(N^2)
     Space: O(1)
     Sorts in place (uses no extra memory)
     Stable sort (similar elements retain insertion order)
     **/
    public static int[] bubbleSort(int[] array) {
        // Write your code here.
        int n = array.length;
        for(int j=n-1; j>0; j--){
            for(int i=1; i<=j; i++){
                if(array[i] < array[i-1]){
                    int temp = array[i];
                    array[i] = array[i-1];
                    array[i-1] = temp;
                }
            }
        }
        return array;
    }
}

