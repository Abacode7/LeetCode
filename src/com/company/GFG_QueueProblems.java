package com.company;

import javafx.util.Pair;

import java.util.*;

@SuppressWarnings("DuplicatedCode")
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

        // Test rotten orange
        System.out.println(orangesRotting(new int[][]{{0, 1, 2}, {0, 1, 2}, {2, 1, 1}}));
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



    /**
     * Live breakdown
     * k = 4
     *
     * [-8]
     * i         j<i+k
     * -8, 1, 5, 2, 3, -6, 10
     *     i.  j. (remove -8, add 3) [1, 5, 2, 3]
     *
     * Intuition
     * Iterate initially from i=0 to j < i + K
     *  - Store all negative values only
     *
     * Iterate i and j together
     * - remove x[i-1], add x[j] if x[j] is negative
     * - if queue is empty store 0, else store the peeked element
     *
     * Solution: O(n) time, O(k) space
     * Using two pointers and a Queue
     */
    static List<Integer> firstNegInt(int[] arr, int k) {
        // write code here
        Queue<Integer> queue = new LinkedList<>();
        for(int i=0; i<k; i++){
            if(arr[i] < 0) queue.offer(arr[i]);
        }

        List<Integer> result = new ArrayList<>();
        if(queue.isEmpty()) result.add(0);
        else result.add(queue.peek());

        int i = 1, j = k;
        while(j < arr.length){
            if(arr[i-1] < 0) queue.remove(arr[i-1]);
            if(arr[j] < 0) queue.offer(arr[j]);

            if(queue.isEmpty()){
                result.add(0);
            }else{
                result.add(queue.peek());
            }
            i++;
            j++;
        }
        return result;
    }



    /**
     * Live Breakdown
     * i/j 0 1 2
     * ------------
     * 0.  0 1 2
     * 1.  0 1 2
     * 2.  2 1 1
     *
     *     2 2 2
     *     0 2 0
     *
     *   2 2 0 1
     *
     * Trial Solution
     * (Store the indexes of all fresh oranges
     *  - If none, return -1) -- Preliminary checks
     *
     * 1. From the indexes of the corrupt oranges,
     * - try to infect the other oranges using bfs
     * - store the min time it took to corrupt these oranges
     *  - use new m X n matrix
     *
     * 2. Review if all oranges are infected
     * - if not, return -1
     * - if true,
     *  - check max of min times in time m X n matrix
     *
     * Trial solution won't work because it requires cloning of the mat[][]
     * from each corrupt orange because using similar mat[][] will erase points
     * where we had fresh oranges
     * - In technical terms, this traverses in a breadth first traversal way from
     * each rotten orange till it fully explores its children before restarting the
     * BFS traversal from another rotten orange
     *
     * - What we need? We need to traverse all rotten oranges in a BFS way at the same
     * time, so we get the minimum time it takes to corrupt the fresh oranges early.
     * Thereby ensuring other rotten orange don't have to corrupt it again.
     *
     *
     * Solution: Breadth First Search, O(m*n) time, O(m*n) space with m*n extra space
     * We can find all rotten oranges and explore their adjacent(s) simultaneously
     * and count the time for each node (using an adjacency matrix)
     *
     * Solution 2: Breadth First Search, O(m*n) time, O(m*n) space - OPTIMAL
     * We need to find all rotten oranges then explore their
     * adjacent(s) simultaneously to corrupt them AND
     * count the time in BFS Level SEGMENT
     * */
    public int orangesRottingTrial(int[][] mat) {
        // Code here
        int m = mat.length, n = mat[0].length;
        int[][] time = new int[m][n];


        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(mat[i][j] == 2){ // start from a rotten orange
                    corruptOranges(mat.clone(), i, j, time);
                }
            }
        }

        /** Todo
         *  Find max time amidst to corrupt in the time graph
         */
        return 0;
    }

    private void corruptOranges(int[][]mat, int i, int j, int[][] time){
        int m = mat.length, n = mat[0].length;
        if(i < 0 || i >= m || j < 0 || j >= n) return;

        time[i][j] = 0;

        int[] x = {-1, 0, 1, 0};
        int[] y = {0, 1, 0, -1};

        Queue<Pair<Integer, Integer>> queue = new LinkedList<>();
        queue.offer(new Pair<>(i, j));

        while(!queue.isEmpty()){
            Pair<Integer, Integer> pair = queue.poll();
            int p = pair.getKey(), q = pair.getValue();

            for(int k=0; k<x.length; k++){
                int newX = p + x[k];
                int newY = q + y[k];

                if(newX < 0 || newX >= m || newY < 0 || newY >= n) continue;

                if(mat[newX][newY] == 1){
                    mat[newX][newY] = 2;
                    time[newX][newY] = Math.min(time[p][q]+1, time[newX][newY]);
                    queue.offer(new Pair<>(newX, newY));
                }
            }
        }
    }

    public static int orangesRotting(int[][] mat) {
        // Code here
        int m = mat.length, n = mat[0].length;
        Queue<int[]> queue = new LinkedList<>();
        // Find all rotten oranges
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(mat[i][j] == 2) queue.offer(new int[]{i, j});
            }
        }
        if(queue.isEmpty()) return -1;

        // Explore their adjacent(s) to corrupt them
        // Store the min time to corrupt them
        int[][] time = new int[m][n];
        int[][] direction = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

        while(!queue.isEmpty()){

            int[] rottenIndex = queue.poll();
            int x = rottenIndex[0];
            int y = rottenIndex[1];

            for(int[] dir: direction){
                int newX = x + dir[0];
                int newY = y + dir[1];

                if(newX < 0 || newX >= m || newY < 0 || newY >= n) continue;

                if(mat[newX][newY] == 1){
                    mat[newX][newY] = 2;

                    if(time[newX][newY] == 0) time[newX][newY] = time[x][y]+1;
                    else time[newX][newY] = Math.min(time[x][y]+1, time[newX][newY]);

                    queue.offer(new int[]{newX, newY});
                }
            }
        }

        // Find if there are still fresh oranges, return -1 if true
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(mat[i][j] == 1) return -1;
            }
        }

        // Find the max of the min times it took to corrupt any orange
        int minTime = 0;
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                minTime = Math.max(minTime, time[i][j]);
            }
        }
        return minTime;
    }

    public int orangesRotting2(int[][] mat) {
        // Code here - OPTIMAL
        int m = mat.length, n = mat[0].length;
        // Find all rotten oranges
        Queue<int[]> queue = new LinkedList<>();
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(mat[i][j] == 2) queue.offer(new int[]{i, j});
            }
        }

        // Explore their adjacent to corrupt and store the time
        int[][] directions = new int[][]{{-1 ,0}, {0, 1}, {1, 0}, {0, -1}};
        int minTime = 0;
        while(!queue.isEmpty()){

            minTime++;
            int queueLevelSize = queue.size();
            while(queueLevelSize > 0){
                int[] rottenOrange = queue.poll();
                int x = rottenOrange[0];
                int y = rottenOrange[1];

                for(int[] dir: directions){
                    int newX = x + dir[0];
                    int newY = y + dir[1];

                    if(newX < 0 || newX >= m || newY < 0 || newY >= n) continue;

                    if(mat[newX][newY] == 1){
                        mat[newX][newY] = 2;
                        queue.offer(new int[]{newX, newY});
                    }
                }
                queueLevelSize--;
            }
        }

        // Check if there are still fresh oranges, return -1 if true
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(mat[i][j] == 1) return -1;
            }
        }

        // Return min time to corrupt all oranges
        return Math.max(0, minTime - 1);
    }


    /**
     * Live Breakdown
     * 4 3 1 10 2 6
     *
     * [4 3 1 10 2 6  queue
     * 6 2 10 1 3 4]  stack
     *
     * Solution: Using Stack, O(n) time, O(n) extra space - OPTIMAL
     * */
    public Queue<Integer> reverseQueue(Queue<Integer> queue) {
        // code here. - OPTIMAL
        Stack<Integer> stack = new Stack<>();
        while(!queue.isEmpty()){
            stack.push(queue.poll());
        }

        while(!stack.isEmpty()){
            queue.offer(stack.pop());
        }
        return queue;
    }

    public Queue<Integer> reverseQueue2(Queue<Integer> queue) {
        // code here. - OPTIMAL
        List<Integer> list = new ArrayList<>(queue);
        int i=0, j = list.size() - 1;
        while(i <= j){
            int temp = list.get(j);
            list.set(j, list.get(i));
            list.set(i, temp);

            i++;
            j--;
        }
        return new LinkedList<>(list);
    }
}
