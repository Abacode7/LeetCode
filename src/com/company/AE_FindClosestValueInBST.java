package com.company;

import java.util.*;

public class AE_FindClosestValueInBST {
    public static int findClosestValueInBst(BST tree, int target) {
        // Write your code here.
        Map<Integer, Integer> map = new HashMap<>();

        // Leg 1
        dfs(tree, target, map);

        // Leg 2
        int minValue = -1;
        int minDiff = Integer.MAX_VALUE;
        for(Map.Entry<Integer, Integer> entry: map.entrySet()){
            if(entry.getValue() < minDiff){
                minDiff = entry.getValue();
                minValue = entry.getKey();
            }
        }

        return minValue;
    }

    public static void dfs(BST tree, int target, Map<Integer, Integer> map){
        int diff = Math.abs(tree.value - target);
        map.put(tree.value, diff);
        if(tree.value == target) return;

        if(target < tree.value){
            if(tree.left != null) dfs(tree.left, target, map);
        }else{
            if(tree.right != null) dfs(tree.right, target, map);
        }
    }

    static class BST {
        public int value;
        public BST left;
        public BST right;

        public BST(int value) {
            this.value = value;
        }
    }
}
