package com.company;

import java.util.*;

public class AE_ContinuousMedian {
    /**

     3 7 8 9 1

     lower (maxHeap) [3]
     greater (minHeap) [789]

     Aim is to keep track of the two median values

     Using two heaps
     lowerHalf: maxHeap
     greaterHalf: minHeap

     Steps
     - Insertion: We insert values into the lower heap:
     if value <= root, else, into the greaterHalf
     - Rebalancing:
     if heaps size difference > 2: remove from one and add to the other
     - Calculate the median

     **/
    static class ContinuousMedianHandler {
        double median = 0;
        PriorityQueue<Integer> lowerHalf = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> greaterHalf = new PriorityQueue<>();

        public void insert(int number) {
            if(lowerHalf.size() == 0 && greaterHalf.size() == 0) {
                lowerHalf.offer(number);
                median = number;
                return;
            }

            if(number <= lowerHalf.peek()){
                lowerHalf.offer(number);
            }else{
                greaterHalf.offer(number);
            }

            // rebalance
            rebalance(lowerHalf, greaterHalf);

            // calculate median
            median = calculateMedian(lowerHalf, greaterHalf);
        }

        private double calculateMedian(PriorityQueue<Integer> lowerHalf, PriorityQueue<Integer> greaterHalf){
            int lowerSize = lowerHalf.size();
            int greaterSize = greaterHalf.size();
            int total = lowerSize + greaterSize;

            if(total % 2 == 0){
                return (lowerHalf.peek() + greaterHalf.peek()) / 2.0;
            }else{
                if(lowerSize > greaterSize) return lowerHalf.peek();
                else return greaterHalf.peek();
            }
        }

        private void rebalance(PriorityQueue<Integer> lowerHalf, PriorityQueue<Integer> greaterHalf){
            int lowerSize = lowerHalf.size();
            int greaterSize = greaterHalf.size();
            if(lowerSize > greaterSize){
                if(lowerSize - greaterSize >= 2) greaterHalf.offer(lowerHalf.poll());
            }else{
                if(greaterSize - lowerSize >= 2) lowerHalf.offer(greaterHalf.poll());
            }
        }

        public double getMedian() {
            return median;
        }
    }
}
