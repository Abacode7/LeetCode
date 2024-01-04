package com.company;

import java.util.*;

public class Main {

    public static void main(String[] args) {
	// write your code here
        int[][] array = {{1,1,0}, {1,1,0}, {0,0,1}};
        int[][] array2 = {{1,0,0}, {0,1,0}, {0,0,1}};
        System.out.println(findCircleNum(array2));


        int[][] intervals = {{1,3},{2,6},{8,10},{15,18}};
        System.out.println(merge(intervals));
    }


    public static int findCircleNum(int[][] isConnected) {
        Set<Integer> isFound = new HashSet<>();
        int provinceCount = 0;
        for(int i=0; i < isConnected.length; i++){
            if(!isFound.contains(i)){
                isFound.add(i);
                findProvinceFrom(i, isConnected, isFound);
                provinceCount++;
            }
        }

        return provinceCount;
    }

    private static void findProvinceFrom(int i, int[][] isConnected, Set<Integer> isFound){
        for(int j=0; j < isConnected[i].length; j++){
            if(j == i) continue;
            if(!isFound.contains(j) && isConnected[i][j] == 1){
                isFound.add(j);
                findProvinceFrom(j, isConnected, isFound);
            }
        }
    }

    public static int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>(){
            public int compare(int[] a, int[] b){
                return a[0] - b[0];
            }
        });

        List<int[]> result = new ArrayList<>();
        int[] subset = intervals[0];

        for(int i=1; i < intervals.length; i++){
            int[] currInterval = intervals[i];

            if(subset[1] >= currInterval[0]){
                subset[1] = Math.max(subset[1], currInterval[1]);
            }else{
                result.add(subset);
                subset = currInterval;
            }
        }

        for (int[] val: result){
            System.out.println(val[0] + " " +  val[1]);
        }
        return result.toArray(new int[result.size()][2]);
    }

    /**
     * Utility methods
     * @param array
     */

    public static <T> void printArray(T[] array){
        for (T v: array) {
            System.out.println(v);
        }
    }

    public static void printArray(int[] array){
        for (int v: array) {
            System.out.println(v);
        }
    }

    public static void printArray(double[] array){
        for (double v: array) {
            System.out.println(v);
        }
    }

    public static <T> void printArray(List<T> array){
        for (T t: array) {
            System.out.println(t);
        }
    }

    /** Quick Notes **/

    // 270 Closest value in BST
    // 112 Path sum
    // 104 Maximum depth of Binary tree
    // 111 Minimum depth of BT
    // 1701 average waiting time

    /**
     *
     * Questions from live coding interviews
     *
     * Amazon final stage:
     * - Question on chess game with focus on
     * the movement of the knight piece.
     *
     * - Multi String Search
     *
     * Google final stage:
     * - Question on Google document, to count lines words will fit in.
     * - Follow up on how you can adjust the table divider to fit words on
     * the min number of lines.
     *
     * - Multi String Search:
     *  Question on list of words and a dictionary to find something... (can't fully recall)
     *
     * - Given a list of integer, find max length of adjacent similar elements.
     * - Follow up, if you're allowed to switch 3 element values, what is the max length still.
     *
     * - Given people (with choice of rooms) and rooms (single or multipartner rooms)
     *
     */
}
