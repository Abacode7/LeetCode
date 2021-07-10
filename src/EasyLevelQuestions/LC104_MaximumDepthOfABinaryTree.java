package EasyLevelQuestions;

public class LC104_MaximumDepthOfABinaryTree {
    public static void main(String[] args){
        TreeNode treeNode = new TreeNode(3, new TreeNode(9), new TreeNode(20, new TreeNode(15), new TreeNode(7)));
        System.out.println(maxDepth(treeNode));
    }

    // First solution
    // Runtime O(N), we go over each not exactly once
    // Space O(N), at worst case we have a skewed binary tree,
    // there the storage to keep the call stack would be O(N)
    // at best case the tree is balanced hence the height of the
    // tree is O(logN)...same with call stack
    public static int maxDepth(TreeNode root) {
        // We use depth first search
        // For each node we assign it a depth
        // We then compare this depth against the max
        // depth to get the max depth
        if(root == null) return 0;

        return dfs(root, 1);
    }

    public static int dfs(TreeNode root, int depth){
        if(root.left == null && root.right == null) return depth;

        int leftTree = Integer.MIN_VALUE;
        if(root.left != null)
            leftTree = dfs(root.left, depth+1);

        int rightTree = Integer.MIN_VALUE;
        if(root.right != null)
            rightTree = dfs(root.right, depth+1);

        return Math.max(leftTree, rightTree);
    }

    // Other solution
    public static int maxDepthImp(TreeNode root) {
        if (root == null) {
            return 0;
        } else {
            int left_height = maxDepthImp(root.left);
            int right_height = maxDepthImp(root.right);
            return java.lang.Math.max(left_height, right_height) + 1;
        }
    }
}
