package com.company;

import java.util.*;

public class AE_SortKSortedArrays {
    /**

     k = 3
     0 1 2 3
     3 1 2 2
     1 2 2 3


     k = 3
     0 1 2 3 4 5

     4 1 7 3 9 5
     1 3 4 5 7 9

     i = 0
     j = i + k

     Problem Solving
     - Taking a que from Selection Sort, where we find min value in the
     sub array and insert in the next postion
     - Sorting is optimised using the k-sorted info, in that we only consider the
     next adjacent k values to get the min for a position
     - This will give us O(n * k)
     - We then optimise further using MinHeap

     - Build priority queue out first k items
     - Define two pointers:
     - first one (i): hold insertion position for next sorted input
     - second one (j): index of the next value to be added to the heap
     - Poll min heap for smallest value
     - Add value to correct position
     - Increment pointers to next postion
     - if second pointer < heap length, add value

     **/
    public int[] sortKSortedArray(int[] array, int k) {
        // Write your code here.
        if(k >= array.length) k = array.length - 1;
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        // add first k values to heap
        for(int i=0; i<=k; i++) minHeap.offer(array[i]);

        int i = 0;
        int j = i + k;
        while(i < array.length){
            int value = minHeap.poll();
            array[i] = value;

            i++;
            j = i + k;
            if(j < array.length) minHeap.offer(array[j]);
        }

        return array;
    }
}
