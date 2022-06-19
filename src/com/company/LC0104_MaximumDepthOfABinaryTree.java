package com.company;

public class LC0104_MaximumDepthOfABinaryTree {
    public static void main(String[] args){
    }

    public int maxDepth(TreeNode root) {
        if(root == null) return 0;
        return dfs(root, 0);
    }

    public int dfs(TreeNode root, int counter){
        counter++;
        if(root.left == null && root.right == null) return counter;

        int leftSubtree = Integer.MIN_VALUE;
        if(root.left != null){
            leftSubtree = dfs(root.left, counter);
        }

        int rightSubtree = Integer.MIN_VALUE;
        if(root.right != null){
            rightSubtree = dfs(root.right, counter);
        }

        return Math.max(leftSubtree, rightSubtree);
    }
}
