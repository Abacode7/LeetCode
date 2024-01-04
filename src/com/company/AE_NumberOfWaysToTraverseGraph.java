package com.company;

import java.util.*;

public class AE_NumberOfWaysToTraverseGraph {
    /**
     0 1 2
     | | | | 0
     | | | | 1

     Given 2 * 3 grid
     To go from 00 to 12
     the number of ways is:
     numWays(00) = numWays(01) + numWays(10)
     numWays(01) = numWays(02) + numWays(11)

     This means each problem can be broken into sub problems:

     The base problem is at index (m-1)(n-1), if going from 0, 0:
     NB: base is 1,1 if reducing from row,column
     and the num of ways to go from m-1)(n-1) to m-1)(n-1) is 1

     We can solve this both recursively (top-bottom) and iteratively(bottom-up):

     Edge cases: Going from row,column to 1,1
     if row or column < 1: numWays is 0

     Optimisation: We might get to a point were we find repeated sub-problems,
     here we use memoization to hold their solution.

     Complexity: Given m and n
     The tree at from every node has two egdes - a binary tree
     whose height is m + n (because the longest path from root to leaf is going to take m + n)
     and the branching factor is 2 as a binary tree, hence
     Time: O(2^(m+n))
     Space: O(m+n)

     With memoization: We ask what are the possible unique combinations we'll do for our recursive solution and that is
     m = {0, 1, 2, 3, 4 ... m}
     n = {0, 1, 2, 3, ... n}
     which is m * n possible unique computation hence:
     Time: O(m*n)
     Space: O(m+n)

     3,2
     2,2    3,1
     1,2  2,1  2,1
     1,1    1,1   1,1

     **/

    Map<String, Integer> map = new HashMap<>();
    public int numberOfWaysToTraverseGraph(int width, int height) {
        // Write your code here.
        String key = String.valueOf(width) + String.valueOf(height);
        if(map.containsKey(key)) return map.get(key);

        if(width < 1 || height < 1) return 0;
        if(width == 1 && height == 1) return 1;

        int result = numberOfWaysToTraverseGraph(width-1, height) + numberOfWaysToTraverseGraph(width, height-1);
        map.put(key, result);
        return result;
    }

    /**

     Using tables

     We go from 0,0 to (m-1)(n-1)
     Base case at (0,0) = 1
     We sum towards the right & downwards each
     Cell at i,j => Cell at i-1,j and Cell at i, j-1

     Time Complexity: O(m * n)
     Space Complexity: O(m * N)
     */
    public int numberOfWaysToTraverseGraph2(int width, int height) {
        // Write your code here.
        int[][] table = new int[width][height];
        table[0][0] = 1;

        for(int i=0; i<width; i++){
            for(int j=0; j<height; j++){
                if(i-1 >= 0) table[i][j] += table[i-1][j];
                if(j-1 >=0) table[i][j] += table[i][j-1];
            }
        }
        return table[width-1][height-1];
    }
}
