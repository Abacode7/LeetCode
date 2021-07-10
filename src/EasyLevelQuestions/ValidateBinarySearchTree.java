package EasyLevelQuestions;

import java.util.*;

// LC Question98
public class ValidateBinarySearchTree {
    public static void main(String[] args){
        TreeNode treeNode = new TreeNode(5, new TreeNode(1), new TreeNode(4, new TreeNode(3), new TreeNode(6)));
        System.out.println(isValidBST(treeNode));
    }

    // First solution
    // For a binary search tree, if we perform an
    // inorder traversal on it, it values must be sorted.
    // To check validity, we store it values inorder, if
    // any value breaks the order then it is not a BST

    // Runtime O(n) since we go through each node once
    // Space O(n), since we store values of the entire tree in the stack
    public static boolean isValidBST(TreeNode root) {
        Stack<Integer> stack = new Stack<>();

        return inorderTraversal(root, stack);
    }

    public static boolean inorderTraversal(TreeNode root, Stack<Integer> stack){
        boolean leftSubtree = true;
        if(root.left != null){
            leftSubtree = inorderTraversal(root.left, stack);
            if(!leftSubtree) return false; // add this to avoid computing the right subtree
        }

        if(!stack.isEmpty() && stack.peek() >= root.val) return false;
        else{
            stack.add(root.val);
        }

        boolean rightSubtree = true;
        if(root.right != null){
            rightSubtree = inorderTraversal(root.right, stack);
        }

        return leftSubtree && rightSubtree;
    }

    // Using the two pointer approach
    // One pointer keeps the lowLimit for each node, while the other keeps
    // the upper Limit for the node

    // Runtime O(n) since we go through each node once
    // Space O(n), since we could store values of the entire tree in the internal stack
    public static boolean validate(TreeNode root, Integer low, Integer high) {
        // Empty trees are valid BSTs.
        if (root == null) {
            return true;
        }
        // The current node's value must be between low and high.
        if ((low != null && root.val <= low) || (high != null && root.val >= high)) {
            return false;
        }
        // The left and right subtree must also be valid.
        return validate(root.left, low, root.val) && validate(root.right, root.val, high);
    }

    public static boolean isValidBSTImproved(TreeNode root) {
        return validate(root, null, null);
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    public TreeNode(){}
    public TreeNode(int val){this.val = val;}
    public TreeNode(int val, TreeNode left, TreeNode right){
        this.val = val;
        this.left = left;
        this.right = right;
    }
}
