package com.company;

import java.util.*;

public class AE_MinHeapConstruction {
      /**

0 1 2 3 4 5 6
A B C D E F G

      A0
  B1        C2
D3   E4    F5    G6

parent: floor(i-1/2)
leftChild: 2 * i + 1
rightChild: 2 * i + 2

Miin Heap
Sift Up
- If currentNode < parent: swap
- *repeat process on new currentNode

SiftDown
- From currentNode: get min between children nodes
- If currentNode > minChildNode: swap
- *repeat process on new currentNode

Remove: (removal of head/root node)
- Swap root with last node
- Remove last node
- Sift down from head

Insertion
- Insert into last position
- Sift up from there

Peek
- Get element at first index of heap array

Build Heap
- From last parent index to root index:
    - *Get last parent index by computing  parent index of last value
- Sift down each element.
    */
    static class MinHeap {
        List<Integer> heap = new ArrayList<Integer>();

        public MinHeap(List<Integer> array) {
            heap = buildHeap(array);
        }

        public List<Integer> buildHeap(List<Integer> array) {
            // Write your code here.
            heap = array;

            int lastIdx = heap.size() - 1;
            int lastParentIdx = (lastIdx - 1)/2;

            for(int i = lastParentIdx; i >= 0; i--){
                siftDown(i, lastIdx, heap);
            }
            return heap;
        }

        public void siftDown(int currentIdx, int endIdx, List<Integer> heap) {
            int leftChildIdx = 2 * currentIdx + 1;
            int rightChildIdx = 2 * currentIdx + 2;

            while(leftChildIdx <= endIdx){
                int minIdx = leftChildIdx;
                if(rightChildIdx <= endIdx && heap.get(rightChildIdx) < heap.get(leftChildIdx)) minIdx = rightChildIdx;

                if(heap.get(currentIdx) > heap.get(minIdx)) swap(currentIdx, minIdx, heap);

                currentIdx = minIdx;
                leftChildIdx = 2 * currentIdx + 1;
                rightChildIdx = 2 * currentIdx + 2;
            }
            return;
        }

        public void siftUp(int currentIdx, List<Integer> heap) {
            int parentIdx = (currentIdx - 1)/2;
            while(parentIdx >= 0 && heap.get(currentIdx) < heap.get(parentIdx)){
                swap(parentIdx, currentIdx, heap);
                currentIdx = parentIdx;
                parentIdx = (currentIdx - 1)/2;
            }
            return;
        }

        private void swap(int i, int j, List<Integer> heap){
            int temp = heap.get(i);
            heap.set(i, heap.get(j));
            heap.set(j, temp);
        }

        public int peek() {
            // Write your code here.
            return heap.get(0);
        }

        public int remove() {
            // Write your code here.
            int lastIdx = heap.size() - 1;
            swap(0, lastIdx, heap);
            int value = heap.remove(lastIdx);

            siftDown(0, lastIdx-1, heap);
            return value;
        }

        public void insert(int value) {
            // Write your code here.
            heap.add(value);
            int lastIdx = heap.size() - 1;
            siftUp(lastIdx, heap);
            return;
        }
    }

    /**
     * Rough Idea
     *
     * Todo: Not fully functional
     */
    /**
     *
     5 3 8 4 1 9

     1
     3     4
     5   4  6

     Sift Up
     - If element < parent, swap
     *repeat this instruction till false

     Sift Down
     - Get min between children
     - If element (parent) > min, swap with that child
     *repeat whole procedure again

     Insert
     - Add element to last position (size())
     - Sift Up

     Removal
     - Swap root node with last element
     - Sift Down

     Peek
     - Retrieve top element

     1
     9      4
     5  8   9

     0 1 2 3 4 5
     1 3 4 5 8 9

     0: 1, 2
     1: 3, 4
     2: 5, 6

     Min Heap: Parent node <= child node
     Parent: i
     Left child: 2i + 1
     Right child: 2i + 2

     Child to parent: floor(i-1/2)

     **/
    static class RoughMinHeap {
        List<Integer> heap = new ArrayList<Integer>();

        public RoughMinHeap(List<Integer> array) {
            heap = buildHeap(array);
        }

        public List<Integer> buildHeap(List<Integer> array) {
            for(Integer value: array){
                heap.add(value);
                siftUp(heap.size() - 1, heap);
            }
            return heap;
        }

        public void siftDown(int currentIdx, int endIdx, List<Integer> heap) {
            int leftChildIdx = 2 * currentIdx + 1;
            int rightChildIdx = 2 * currentIdx + 2;

            if(leftChildIdx > endIdx && rightChildIdx > endIdx) return;

            int rightChild = rightChildIdx <= endIdx ? heap.get(rightChildIdx) : Integer.MAX_VALUE;
            int leftChild = heap.get(leftChildIdx);

            boolean moveLeft = leftChild <= rightChild;

            if(moveLeft){
                int temp = heap.get(currentIdx);
                heap.set(currentIdx, leftChildIdx);
                heap.set(leftChildIdx, temp);
                siftDown(leftChildIdx, endIdx, heap);
            }else{
                int temp = heap.get(currentIdx);
                heap.set(currentIdx, rightChildIdx);
                heap.set(rightChildIdx, temp);
                siftDown(rightChildIdx, endIdx, heap);
            }
        }

        public void siftUp(int currentIdx, List<Integer> heap) {
            int parentIdx = (currentIdx - 1)/2;
            while(parentIdx >= 0 && heap.get(currentIdx) < heap.get(parentIdx)){
                int temp = heap.get(parentIdx);
                heap.set(parentIdx, heap.get(currentIdx));
                heap.set(currentIdx, temp);

                currentIdx = parentIdx;
                parentIdx = (currentIdx - 1) / 2;
            }
        }

        public int peek() {
            return heap.get(0);
        }

        public int remove() {
            int lastIdx = heap.size()-1;
            int temp = heap.get(0);
            heap.set(0, heap.get(lastIdx));
            heap.set(lastIdx, temp);

            heap.remove(lastIdx);

            siftDown(0, heap.size()-1, heap);

            return temp;
        }

        public void insert(int value) {
            heap.add(value);
            siftUp(heap.size() - 1, heap);
        }
    }
}
