package com.company;

import java.util.*;

public class LC0994_RottingOranges {
    public static void main(String[] args){
        /**
         * 2 1 1
         * 1 1 0
         * 0 1 1
         * */
        int[][] grid = {{2,1,1},{1,1,0},{0,1,1}};
        System.out.println(orangesRotting(grid));
    }

    public static int orangesRotting(int[][] grid) {
        int fresh = 0;
        int m = grid.length;
        int n = grid[0].length;

        Queue<int[]> queue = new LinkedList<>();
        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(grid[i][j] == 2) {
                    queue.add(new int[]{i, j});
                }else if(grid[i][j] == 1) fresh++;
            }
        }

        int[][] directions = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
        int time = 0;
        while(!queue.isEmpty() && fresh > 0){
            int size = queue.size();
            while(size > 0){
                int[] orange = queue.remove();
                for(int[] direction: directions){
                    int[] neighbour = {orange[0] + direction[0], orange[1] + direction[1]};
                    if(neighbour[0] >= 0 && neighbour[0] < m && neighbour[1] >= 0 && neighbour[1] < n && grid[neighbour[0]][neighbour[1]] == 1) {
                        queue.add(neighbour);
                        fresh--;
                        grid[neighbour[0]][neighbour[1]] = 2;
                    }
                }
                size--;
            }
            time++;
        }

        if(fresh > 0) return -1;

        return time;
    }
}
