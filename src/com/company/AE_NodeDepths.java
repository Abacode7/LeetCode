package com.company;

public class AE_NodeDepths {
    /**

     - Get node depth of root
     - Get total node depth of left subtree using that of root
     - Get total node depth of right subtree using that of root
     -* Repeat process

     **/
    public static int nodeDepths(BinaryTree root) {
        // Write your code here.
        return nodeDepths(root, -1);
    }

    public static int nodeDepths(BinaryTree root, int depthSoFar){
        int parentDepth = depthSoFar + 1;

        int totalLeftNodeDepth = 0;
        if(root.left != null) totalLeftNodeDepth = nodeDepths(root.left, parentDepth);

        int totalRightNodeDepth = 0;
        if(root.right != null) totalRightNodeDepth = nodeDepths(root.right, parentDepth);

        return parentDepth + totalLeftNodeDepth + totalRightNodeDepth;
    }

    static class BinaryTree {
        int value;
        BinaryTree left;
        BinaryTree right;

        public BinaryTree(int value) {
            this.value = value;
            left = null;
            right = null;
        }
    }
}
