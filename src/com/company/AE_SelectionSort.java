package com.company;

public class AE_SelectionSort {
    /**
     8 5 2 9 5 6 3

     2 | 5 8 9 5 6 3

     2 3 | 8 9 5 6 5

     2 3 5 | 9 5 6 8

     2 3 5 5 | 9 6 8

     2 3 5 5 6 | 9 8

     2 3 5 5 6 8 9

     - Start with the whole array as an unsorted array
     - Find the smallest item
     - Swap with the first element
     - Reduce the unsorted array to exclude the smallest element

     Time: O(N^2)
     Space: O(1)
     Sorts in place (uses no extra memory)
     Not stable sort (similar elements don't retain insertion order)
     **/
    public static int[] selectionSort(int[] array) {
        // Write your code here.
        int n = array.length;
        for(int i=0; i<n-1; i++){
            // Get the smallest value index as j
            int j = i;
            int k = j+1;
            while(k < n){
                if(array[k] < array[j]) j = k;
                k++;
            }

            // Swap current value with smallest
            swap(array, i, j);
        }
        return array;
    }

    private static void swap(int[] array, int i, int j){
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
