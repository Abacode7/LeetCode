package com.company;

import java.util.*;

public class AE_BranchSum {
    /**
     NB: Backtracking is done when left and right subtrees have
     been explored.

     - Start at root, add SumSoFar to current value
     - if node has no children: add sum to list
     - else: pass sum to left subtree and right subtree

     **/
    // This is the class of the input root. Do not edit it.
    public static class BinaryTree {
        int value;
        BinaryTree left;
        BinaryTree right;

        BinaryTree(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    public static List<Integer> branchSums(BinaryTree root) {
        List<Integer> branchSums = new ArrayList<>();
        // Write your code here.
        branchSums(root, branchSums, 0);
        return branchSums;
    }

    public static void branchSums(BinaryTree root, List<Integer> branchSums, int sumSoFar){
        int sum = root.value + sumSoFar;
        if(root.left == null && root.right == null){
            branchSums.add(sum);
            return;
        }

        if(root.left != null) branchSums(root.left, branchSums, sum);
        if(root.right != null) branchSums(root.right, branchSums, sum);
    }
}
