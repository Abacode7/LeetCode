package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class LC0056_MergeIntervals {
    public static void main(String[] args){
        int[][] input = {{1,3},{2,6},{8,10},{15,18}};
        System.out.println(merge(input));
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
        result.add(subset);

        return result.toArray(new int[result.size()][2]);
    }
}
