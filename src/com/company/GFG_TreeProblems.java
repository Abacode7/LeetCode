package com.company;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class GFG_TreeProblems {
    public static void main(String[] args){

        Node node = new Node(1, new Node(2), new Node(3));
        System.out.println(TreeUtils.findDiameter(node));
    }

    static class Node {
        private int data;
        private Node left, right;

        public Node(int item)    {
            data = item;
            left = right = null;
        }

        public Node(int data, Node left, Node right){
            this.data = data;
            this.left = left;
            this.right = right;
        }

        public int getData(){
            return data;
        }

        public Node getLeft(){
            return left;
        }

        public Node getRight(){
            return right;
        }
    }



    /**
     * Preorder traversal goes: root, left, right
     *
     * Solution:
     * O(n) time, where n is number of nodes visited once
     * O(n + h) extra space, where n is the number of nodes in list &
     * h is the recursive stack space, which has a size of the tree height at most
     * (For a balanced tree h = log n, for skewed h = n)
     * */
    static ArrayList<Integer> preorder(Node root) {
        // write code here
        ArrayList<Integer> list = new ArrayList<>();
        preorderTraversal(root, list);
        return list;
    }

    static void preorderTraversal(Node node, ArrayList<Integer> list) {
        // write code here
        if(node == null) return;

        list.add(node.data);
        preorderTraversal(node.left, list);
        preorderTraversal(node.right, list);
    }



    /**
     * Inorder traversal goes: left, root, right
     *
     * Solution:
     * O(n) time, where n is number of nodes visited once
     * O(n + h) extra space, where n is the number of nodes in list &
     * h is the recursive stack space, which has a size of the tree height at most
     * (For a balanced tree h = log n, for skewed h = n)
     * */
    ArrayList<Integer> inOrder(Node root) {
        // Code
        ArrayList<Integer> list = new ArrayList<>();
        inOrderTraversal(root, list);
        return list;
    }

    void inOrderTraversal(Node node, ArrayList<Integer> list){
        if(node == null) return;
        inOrderTraversal(node.left, list);
        list.add(node.data);
        inOrderTraversal(node.right, list);
    }



    /**
     * Question
     * Input:
     *        9
     *         \
     *           10
     * k = 1
     * Output: 10
     * Explanation: 1st Largest element in BST is 10
     *
     * To get the kth largest, we need to traverse inOrder (reverse)
     * to the kth element
     * Solution: Inorder traversal, O(k) time, O(k + h) space
     * where k is the kth largest element and h is the height of the tree
     * */
    public int kthLargest(Node root, int k) {
        // Your code here
        List<Integer> list = new ArrayList<>();
        inOrderTraversal(root, k, list);
        return list.get(k-1);
    }

    private void inOrderTraversal(Node node, int k, List<Integer> list){
        if(node == null) return;
        if(list.size() >= k) return;

        inOrderTraversal(node.right, k, list);
        list.add(node.data);
        inOrderTraversal(node.left, k, list);
    }



    /**
     * Your task is to return the left view of the binary tree
     * The left view of a binary tree is the set of nodes visible
     * when the tree is viewed from the left side.
     *
     * Using an inorderTraversal: root, left, right (skipping the right node)
     * seems to work until you get to lower level children where it doesn't e.g:
     *        9
     *      /         \
     *      4          10
     *                 / \
     *                 1  7
     *  => {9 4 1 7}
     *  There's no way at level 2 to tell if 4 has children
     *
     *  Hence, we use a BFS, which goes level by level, storing the first item from the left in each level.
     *  We note the fixedLevelSize and add the first item, when the levelSize is full (equals fixedLevelSize).
     *
     *  Solution: BFS - OPTIMAL
     *  O(n) time, we iterate each element exactly once (inner loop is to just tell us the level)
     *  O(n) space, we store n in list &
     *  we store at most all elements from the leave nodes at once
     *  (N = 2L - 1 in full binary tree, where N is no. of nodes and L is no. of leave nodes)
     */
    ArrayList<Integer> leftView(Node root) {
        // code here
        ArrayList<Integer> list = new ArrayList<>();
        if(root == null) return list;

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        int level = -1;
        while(!queue.isEmpty()){
            level++;
            int levelSize = queue.size();
            final int fixedLevelSize = levelSize;
            while(levelSize > 0){
                Node node = queue.poll();
                if(levelSize == fixedLevelSize) list.add(node.data);

                if(node.left != null) queue.offer(node.left);
                if(node.right != null) queue.offer(node.right);

                levelSize--;
            }
        }
        return list;
    }



    /**
     * Similar to the above, we use a BFS, which goes level by level, storing the first item
     * from the RIGHT in each level
     *
     * Solution: BFS, O(n) time, O(n) space - OPTIMAL
     * */
    ArrayList<Integer> rightView(Node root) {
        // add code here.
        ArrayList<Integer> list = new ArrayList<>();
        if(root == null) return list;

        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()){
            int levelSize = queue.size();
            final int fixedLevelSize = levelSize;

            while(levelSize > 0){
                Node node = queue.poll();

                if(levelSize == fixedLevelSize) list.add(node.data);

                if(node.right != null) queue.offer(node.right);
                if(node.left != null) queue.offer(node.left);

                levelSize--;
            }
        }

        return list;
    }




    /**
     *   2
     * 1.  3
     *       5
     *
     *    3
     * 1.   4
     *     2  5
     *
     * 1 3 2 4 5
     * 1 2 3 4 5
     *
     * - left subtree
     * - node
     * - right subtree
     *
     * Time: O(n) time, O(n + h)
     * where h is the height of the binary tree, which is space used by the
     * recursive stack
     * */
    public static boolean isBinarySearchTree(Node root){
        if(root == null) return false;

        List<Integer> treeDataList = new ArrayList<>();
        inOrderTraverse(root, treeDataList);

        for(int i=1; i<treeDataList.size(); i++){
            int current = treeDataList.get(i);
            int prev = treeDataList.get(i-1);

            if(prev > current) return false;
        }
        return true;
    }

    private static void inOrderTraverse(Node root, List<Integer> list){
        if(root == null) return;

        inOrderTraverse(root.left, list);
        list.add(root.data);
        inOrderTraverse(root.right, list);
    }





    /**
     * Big Sample
     *      5
     *   8.     6
     * 3.  7
     1.     5
     0      4
           2

     Small Sample
          2
     1       3
     * */
    static class TreeUtils {

        public static int findDiameter(Node root){
            if(root == null) return 0;


            NodeInfo nodeInfo = getNodeInfo(root);
            return nodeInfo.getMaxDiameter();
        }

        private static NodeInfo getNodeInfo(Node root){
            if(root.getLeft() == null && root.getRight() == null) return new NodeInfo(0, 0);

            NodeInfo left = new NodeInfo( 0, 0);
            if(root.getLeft() != null) left = getNodeInfo(root.getLeft());

            NodeInfo right = new NodeInfo( 0, 0);
            if(root.getRight() != null) right = getNodeInfo(root.getRight());

            int maxEdges = Math.max(left.getMaxEdges(), right.getMaxEdges()) + 1;
            int diameter = left.getMaxEdges() +  right.getMaxEdges();
            if(root.getLeft() != null) diameter++;
            if(root.getRight() != null) diameter++;

            int maxDiameter = Math.max(Math.max(left.getMaxDiameter(), right.getMaxDiameter()), diameter);

            return new NodeInfo(maxEdges, maxDiameter);
        }
    }

    static class NodeInfo {
        private int maxEdges;
        private int maxDiameter;

        public NodeInfo(int maxEdges, int maxDiameter){
            this.maxEdges = maxEdges;
            this.maxDiameter = maxDiameter;
        }

        public int getMaxEdges(){
            return maxEdges;
        }

        public int getMaxDiameter(){
            return maxDiameter;
        }
    }

}
