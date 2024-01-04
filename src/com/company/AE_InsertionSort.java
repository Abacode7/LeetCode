package com.company;

public class AE_InsertionSort {
    /**

     8 5 2 9 5 6 3

     8 | 5 2 9 5 6 3
     5 8 | 2 9 5 6 3
     2 5 8 | 9 5 6 3
     2 5 8 9 | 5 6 3
     2 5 5 8 9 | 6 3
     2 5 5 6 8 9 | 3
     2 5 5 6 8 9 3 |

     - Use an index i to mark the subset of sorted items
     - Use an index j to explore and swap backwards to the right place
       for an item
     - Iterate i, after j's item is correctly positioned.

     Time: O(N^2)
     Space: O(1)
     Sorts in place (uses no extra memory)
     Stable sort (similar elements retain insertion order)
     **/
    public static int[] insertionSort(int[] array) {
        // NB: index i is not needed in this code, only
        // here to mark the index of already sorted items
        int i=0, j=1;
        while(j<array.length){
            int k = j;
            while(k-1>=0 && array[k] < array[k-1]){
                swap(array, k-1, k);
                k--;
            }
            i++;
            j++;
        }
        return array;
    }

    private static void swap(int[] array, int i, int j){
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
