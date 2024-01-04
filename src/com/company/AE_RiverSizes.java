package com.company;

import java.util.*;
public class AE_RiverSizes {
    /**

     1 0 0
     0 0 1
     0 0 1

     [1 2]

     i j => 11 [10 01 12 21 ]
     - For all coordinates in the matrix
     - we check if it's a land (1):
     if yes, we explore the land and connected lands to it, then get the size
     -*we mark a land as explored by change it to zero (0)

     *We repeat this process for all coordinates in the matrix
     **/
    public static List<Integer> riverSizes(int[][] matrix) {
        List<Integer> landSizes = new ArrayList<>();
        int m = matrix.length;
        int n = matrix[0].length;

        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(matrix[i][j] == 1){
                    int size = explore(matrix, i, j);
                    landSizes.add(size);
                }
            }
        }
        // Write your code here.
        return landSizes;
    }

    public static int explore(int[][] matrix, int i, int j){
        matrix[i][j] = 0;
        int leftSize = 0, topSize = 0, rightSize = 0, bottomSize = 0;

        if(j-1 >= 0 && matrix[i][j-1] == 1) leftSize = explore(matrix, i, j-1);

        if(j+1 < matrix[0].length && matrix[i][j+1] == 1) rightSize = explore(matrix, i, j+1);

        if(i-1 >= 0 && matrix[i-1][j] == 1) topSize = explore(matrix, i-1, j);

        if(i+1 < matrix.length && matrix[i+1][j] == 1) bottomSize = explore(matrix, i+1, j);

        return leftSize + rightSize + topSize + bottomSize + 1;
    }
}
