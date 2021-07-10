package EasyLevelQuestions;
import java.util.*;

public class LC101_SymmetricTree {
    public static void main(String[] args){
        TreeNode treeNode = new TreeNode(1,
                new TreeNode(2, new TreeNode(3), new TreeNode(4)),
                new TreeNode(2, new TreeNode(4), new TreeNode(3)));

        TreeNode treeNode2 = new TreeNode(1,
                new TreeNode(2, null, new TreeNode(3)),
                new TreeNode(2, null, new TreeNode(3)));

        System.out.println(isSymmetricDFS(treeNode));
    }

    // DFS
    // A tree is symmetric around its center, if
    // for every node in the tree it's left subtree equals
    // it's right subtree

    // Runtime O(N), we through each node exactly once
    // Space O(N), we have a call max call stack of N
    public static boolean isSymmetricDFS(TreeNode root) {
        return isReflection(root, root);
    }

    public static boolean isReflection(TreeNode tree1, TreeNode tree2){
        if(tree1 == null && tree2 == null) return true;
        if(tree1 == null || tree2 == null) return false;
        if(tree1.val != tree2.val) return false;

        return isReflection(tree1.left, tree2.right) && isReflection(tree1.right, tree2.left);
    }

    // BFS Solution
    // The idea is to traverse over the same tree with two cues
    // taking left value from one and right from the other and vice versa
    // then comparing their values;

    // Runtime O(N), we move through each tree node exactly once
    // Space the queue consumes less than max of 2N, so we say an upper
    // bound of O(N)
    public boolean isSymmetric(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        queue.add(root);
        while(!queue.isEmpty()){
            TreeNode node1 = queue.poll();
            TreeNode node2 = queue.poll();

            if(node1 == null && node2 == null) continue;
            if(node1 == null || node2 == null) return false;
            if(node1.val != node2.val) return false;

            queue.add(node1.left);
            queue.add(node2.right);
            queue.add(node1.right);
            queue.add(node2.left);
        }

        return true;
    }
}
