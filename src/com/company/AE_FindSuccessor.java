package com.company;

import java.util.*;

public class AE_FindSuccessor {

    /**
     - find inorder traversal of node
     - loop over to find node, then it's successor

     inorder traversal, we visit:
     - Left subtree
     - Parent
     - Right subtree

     **/
    public BinaryTree findSuccessor(BinaryTree tree, BinaryTree node) {
        // Write your code here.
        List<BinaryTree> orderedNodes = new ArrayList<>();
        inorderTraversal(tree, orderedNodes);

        int n = orderedNodes.size();
        for(int i=0; i<n; i++){
            if(orderedNodes.get(i) == node && i < n-1) return orderedNodes.get(i+1);
        }
        return null;
    }

    public void inorderTraversal(BinaryTree tree, List<BinaryTree> list){
        if(tree.left != null) inorderTraversal(tree.left, list);
        list.add(tree);
        if(tree.right != null) inorderTraversal(tree.right, list);
    }

    // This is an input class. Do not edit.
    static class BinaryTree {
        public int value;
        public BinaryTree left = null;
        public BinaryTree right = null;
        public BinaryTree parent = null;

        public BinaryTree(int value) {
            this.value = value;
        }
    }
}
