package com.company;

import java.util.ArrayList;
import java.util.List;

public class AE_ValidateBST {
    /**
     *       10
     *    5       15
     *  1    10       20
     *
     *  value < max and value >= min
     */

    public static boolean validateBST(BST tree) {
        // Write your code here.
        return validate(tree, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    public static boolean validate(BST tree, Integer min, Integer max){
        if(tree == null) return true;
        if(tree.value < min || tree.value >= max) return false;
        boolean left = validate(tree.left, min, tree.value);
        boolean right = validate(tree.right, tree.value, max);
        return left && right;
    }

    /**
     * Using Inorder Traversal
     * Fails at checking node in left subtree is strictly
     * less than a parent node
     */
    public static boolean validateBst(BST tree) {
        // Write your code here.
        List<Integer> treeNodes = new ArrayList<>();
        inOrderTraversal(tree, treeNodes);

        for(int i=1; i<treeNodes.size(); i++){
            if(treeNodes.get(i-1) > treeNodes.get(i)) return false;
        }
        return true;
    }

    public static void inOrderTraversal(BST tree, List<Integer> treeNodes){
        if(tree.left != null)
            inOrderTraversal(tree.left, treeNodes);

        treeNodes.add(tree.value);

        if(tree.right != null)
            inOrderTraversal(tree.right, treeNodes);
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
