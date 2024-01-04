package com.company;

import java.util.ArrayList;
import java.util.List;

public class DP_TargetSum {
    public static void main(String[] args){
        int target = 100; // 8 | 100
        int[] values = {1, 2, 5, 25}; // {2, 3, 5} | {1, 2, 5, 25}
        System.out.println(bestSum(target, values));
    }

    public static boolean canSum(int target, int[] values){
        int m = target;
        boolean[] subProblems = new boolean[m+1];
        subProblems[0] = true;
        for(int i=0; i<=m; i++){
            if(subProblems[i]){
                for(int value: values){
                    if(value + i <= m) subProblems[value + i] = true;
                }
            }
        }
        return subProblems[m];
    }


    public static List<Integer> howSum(int target, int[] numbers){
        List<List<Integer>> table = new ArrayList<List<Integer>>(target+1);
        for(int i=0; i<=target; i++) table.add(null);
        table.set(0, new ArrayList<>());

        for(int i=0; i<=target; i++){
            if(table.get(i) != null){
                for(int num: numbers){
                    if(num + i <= target){
                        List<Integer> values = new ArrayList<>(table.get(i));
                        values.add(num);
                        table.set(num + i, values);
                    }
                }
            }
        }
        return table.get(target);
    }


    public static List<Integer> bestSum(int target, int[] numbers){
        List<List<Integer>> table = new ArrayList<List<Integer>>(target+1);
        for(int i=0; i<=target; i++) table.add(null);
        table.set(0, new ArrayList<>());

        for(int i=0; i<=target; i++){
            if(table.get(i) != null){
                for(int num: numbers){
                    if(num + i <= target){
                        List<Integer> values = new ArrayList<>(table.get(i));
                        values.add(num);
                        if(table.get(num + i) == null) table.set(num + i, values);
                        else{
                            if(values.size() < table.get(num + i).size()) table.set(num + i, values);
                        }
                    }
                }
            }
        }
        return table.get(target);
    }
}
