package com.company;

import java.util.*;

public class AE_LaptopRentals {
    /**
     0 2
     0 4
     1 4
     3 10
     4 6
     7 8
     9 11

     Problem can be rephrased to finding the max number of
     simultaneous laptop rentals usage.

     - Sort time slots by order of start time
     - Before adding a time slot:
     - Check and remove time slots, with endtime <= current time slot start time
     - Add time slot
     - Get size of all simultaneous usage
     - Compute max of such usage


     NB: Using array: O(n*2), but heaps give us O(n)
     **/

    public int laptopRentals(ArrayList<ArrayList<Integer>> times) {
        // Write your code here.
        Collections.sort(times, (a, b) -> a.get(0) - b.get(0));

        PriorityQueue<ArrayList<Integer>> minHeap = new PriorityQueue<>(new Comparator<ArrayList<Integer>>(){
            public int compare(ArrayList<Integer> a, ArrayList<Integer> b){
                return a.get(1) - b.get(1);
            }
        });

        int maxUsage = 0;
        for(ArrayList<Integer> time: times){
            while(!minHeap.isEmpty() && minHeap.peek().get(1) <= time.get(0)) minHeap.poll();
            minHeap.offer(time);

            maxUsage = Math.max(maxUsage, minHeap.size());
        }
        return maxUsage;
    }
}
