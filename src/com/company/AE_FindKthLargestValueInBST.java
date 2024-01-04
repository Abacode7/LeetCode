package com.company;

import java.util.*;

public class AE_FindKthLargestValueInBST {
    // This is an input class. Do not edit.

    /**
     k = 2
     [1 3 5 7 9 11 13]

           7
        3     11
     1   5  9    13

     - Do an inorder traversal of bst
     - Find the kth largest value from the end of the list.

     Inorder traversal
     - Visit the left subtree
     - Visit the parent
     - Visti the right subtree

     **/
    static class BST {
        public int value;
        public BST left = null;
        public BST right = null;

        public BST(int value) {
            this.value = value;
        }
    }

    public int findKthLargestValueInBst(BST tree, int k) {
        // Write your code here.
        if(k < 1) return -1;
        List<Integer> orderedList = new ArrayList<>();
        inorderTraversal(tree, orderedList);
        int n = orderedList.size();
        return orderedList.get(n - k);
    }

    public void inorderTraversal(BST tree, List<Integer> list){
        if(tree.left != null) inorderTraversal(tree.left, list);
        list.add(tree.value);
        if(tree.right != null) inorderTraversal(tree.right, list);
    }
}
