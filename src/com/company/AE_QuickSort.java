package com.company;

public class AE_QuickSort {
    /**
     P   L          R
     8 | 5 2 9 5 6 3

     - Hold pointers L R & P
     - if both L > P && R < P:
     swap item in L & R
     *repeat operation till L > R: at this point, we know that item R < P

     - swap item P and item R. *item P is now in it's correct postiton

     *repeat this step for sub sets initial L - P-1 & P+1 to initial R


     Time: O(N^2), average, best case O(NlogN)
     Space: O(logN) given we compute the small sub arrays first,
     so we can take out computations from the call stack
     **/
    public static int[] quickSort(int[] array) {
        // Write your code here.
        int n = array.length;
        quickSort(array, 0, n-1);
        return array;
    }

    public static void quickSort(int[] array, int start, int end){
        if(start >= end) return;

        int P = start;
        int L = start+1, R = end;

        while(L <= R){
            if(array[L] > array[P] && array[R] <= array[P]){
                int temp = array[L];
                array[L] = array[R];
                array[R] = temp;

                L++;
                R--;
                continue;
            }

            if(array[L] <= array[P]) L++;

            if(array[R] > array[P]) R--;
        }

        int temp = array[R];
        array[R] = array[P];
        array[P] = temp;

        quickSort(array, start, R-1);
        quickSort(array, R+1, end);
    }
}
