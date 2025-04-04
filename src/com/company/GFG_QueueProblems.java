package com.company;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class GFG_QueueProblems {
    public static void main(String[] args){
        // Test queue stack
        pushStack(1);
        pushStack(3);
        pushStack(5);
        pushStack(7);
        pushStack(9);
        while(true){
            int value = popStack();
            if(value == -1) break;
            System.out.println(value);
        }
        System.out.println();
    }

    /**
     * Implement Stack with Queues
     * [1 2 3 4] => 4 3 2 1
     * [4     q1 => 4
     * [1 2 3 q2
     * swap q1 and q2
     *
     * For Push: We simply push into queue1
     * For Pop:
     * We copy all but the last item from queue1 into queue2
     * Pop the last item from queue1
     * Swap queue1 and queue2, so queue1 has the data left
     *
     * Solution:
     * Push: O(1)time, O(n) space
     * Pop: O(n) time, O(n) space
     */
    static Queue<Integer> queue1 = new LinkedList<>();
    static Queue<Integer> queue2 = new LinkedList<>();
    public static void pushStack(int data){
        queue1.offer(data);
    }

    public static int popStack(){
        if(queue1.isEmpty()) return -1;

        while(queue1.size() > 1){
            queue2.offer(queue1.poll());
        }
        int value = queue1.poll();

        //Swap queues
        Queue<Integer> tempQueue = queue2;
        queue2 = queue1;
        queue1 = tempQueue;

        return value;
    }


    /**
     * Live Breakdown
     * 4, 3, 2, 6
     * 6 4 3 2 -> 10
     * 10 3 2 -> 13
     * 13 2 -> 15
     * => 10 + 13 + 15 => 38
     *
     * 2, 3, 4, 6 -> 5
     * 5, 4, 6 -> 4, 5, 6 -> 14
     * 9, 6 -> 6, 9 -> 15 -> 29
     *
     * Intuition:
     * Using greedy algorithm, it makes sense to tie smallest
     * knots at every given time, so we must operate with a sorted
     * queue, which maintains a MIN HEAP property -> Priority Queue
     *
     * Solution: Using Heap with min heap property
     * O(nlogn) time, where log n is for single insertion into heap, done n times
     * O(n) space
     * */
    public static int minCost(int[] arr) {
        // code here
        Queue<Integer> queue = new PriorityQueue<>();
        for(int value: arr){
            queue.offer(value);
        }

        int minCost = 0;
        while(queue.size() > 1){
            int sum = queue.poll() + queue.poll();
            minCost += sum;
            queue.offer(sum);
        }
        return minCost;
    }
}
