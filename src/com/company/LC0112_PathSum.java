package com.company;

public class LC0112_PathSum {
    public static void main(String[] args){
    }

    public boolean hasPathSum(TreeNode root, int targetSum) {
        if(root == null) return false;
        return dfs(root, targetSum, 0);
    }

    public boolean dfs(TreeNode root, int targetSum, int sumSoFar){
        if(root.left == null && root.right == null && sumSoFar + root.val == targetSum) return true;

        boolean leftSubtreeTrue = false;
        if(root.left != null){
            leftSubtreeTrue = dfs(root.left, targetSum, sumSoFar + root.val);
        }

        boolean rightSubtreeTrue = false;
        if(root.right != null){
            rightSubtreeTrue = dfs(root.right, targetSum, sumSoFar + root.val);
        }

        return leftSubtreeTrue || rightSubtreeTrue;
    }
}
