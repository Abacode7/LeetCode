package com.company;

public class AE_HeapSort {
    /**
     Given
     8 5 2 9 5 6 3

     Default heap
     8
     5      2
     9   5   6  3

     Build heap
     9
     8      6
     5   5   2  3

     Given new heap: 9 8 6 5 5 2 3
     swap: 2 8 6 5 5 3 | 9

     reduce and repeat step on: 2 8 6 5 5 3

     - Build a max heap out of the input array
     - Swap the root (largest) with the last element of the
     array*
     - Reduce the array to exclude the last element (which is already sorted)
     - Max heapify the array again

     *repeat process

     NB: For heaps
     parent: floor((i -1)/2)
     left: 2 * i + 1
     right: 2 * i + 2

     Time: O(nlogn): Doing Sift down O(logn), n times. Note optimised build max heap is O(n)
     Space: O(1)
     Sorts in place (uses no extra memory)
     Not stable sort (similar elements don't retain insertion order)
     **/
    public static int[] heapSort(int[] array) {
        // Write your code here.
        if(array.length == 0) return array;

        buildMaxHeap(array);
        int heapSize = array.length;

        while(heapSize > 1){
            swap(array, 0, heapSize - 1);
            siftDown(array, 0, heapSize - 2);
            heapSize--;
        }

        return array;
    }

    private static void siftDown(int[] array, int index, int endIdx){
        int leftChildIdx = 2 * index + 1;
        while(leftChildIdx <= endIdx){
            int swapIdx = leftChildIdx;
            if(leftChildIdx + 1 <= endIdx && array[leftChildIdx+1] > array[leftChildIdx])
                swapIdx = leftChildIdx + 1;

            if(array[index] > array[swapIdx]) return;

            // else
            swap(array, index, swapIdx);

            index = swapIdx;
            leftChildIdx = 2 * index + 1;
        }
        return;
    }

    private static void buildMaxHeap(int[] array){
        int endIdx = array.length - 1;
        int parentIdxOfEnd = (endIdx - 1) / 2;

        while(parentIdxOfEnd >= 0){
            siftDown(array, parentIdxOfEnd, endIdx);
            parentIdxOfEnd--;
        }
    }

    private static void swap(int[] array, int i, int j){
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}
