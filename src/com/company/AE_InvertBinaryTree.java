package com.company;

public class AE_InvertBinaryTree {
    /**

     if left & right subtree == null, return

     else:
     For each root node, left & right
     - swap left sub tree, if non null
     - swap right sub tree, if non null
     - swap left node with right node

     **/

    public static void invertBinaryTree(BinaryTree tree) {
        // Write your code here.
        if(tree.left == null & tree.right == null) return;

        if(tree.left != null) invertBinaryTree(tree.left);

        if(tree.right != null) invertBinaryTree(tree.right);

        BinaryTree temp = tree.left;
        tree.left = tree.right;
        tree.right = temp;
    }

    static class BinaryTree {
        public int value;
        public BinaryTree left;
        public BinaryTree right;

        public BinaryTree(int value) {
            this.value = value;
        }
    }
}
