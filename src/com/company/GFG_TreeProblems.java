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

    public static class Node {
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





    /**
     *      12
     *   8     18
     *
     *  5 11
     *
     * More left depth of a binary tree
     * */
    public static int height(Node node){
        if(node == null) return 0;
        if(node.getLeft() == null && node.getRight() == null) return 0;

        int leftSubtreeHeight = node.getLeft() != null ? height(node.getLeft()) : 0;

        int rightSubtreeHeight = node.getRight() != null ? height(node.getRight()) : 0;

        return Math.max(leftSubtreeHeight, rightSubtreeHeight) + 1;
    }




    /**
     * n1 = 5, n = 6
     *          1
     *    2.        3
     * 4.   5.     6.  7
     *      9 8
     *     0
     *
     * 0 [1 2 5 9 0]
     * 8 [1 2 5 8]
     *
     * 4 [1 2 4 ]
     *
     * 5[1 2 5]
     *
     * 6 [1 3 6]
     *
     * Solution: The idea is to use a list to track the parents of a tree
     * On backtracking, remove fellow leaf nodes, since they're not parents
     *
     *
     * */

    Node lca(Node root, int n1, int n2) {
        // Your code here
        if(root == null) return null;

        ArrayList<Node> n1List = new ArrayList<>();
        boolean n1Found = searchTree(root, n1, n1List);

        ArrayList<Node> n2List = new ArrayList<>();
        boolean n2Found = searchTree(root, n2, n2List);

        if(!n1Found || !n2Found) return null;

        // compare list
        Node ancestor = null;
        int i = 0;
        int j = 0;
        while(i < n1List.size()  && j < n2List.size()){
            if(n1List.get(i) != n2List.get(j)) break;

            ancestor = n1List.get(i);

            i++;
            j++;
        }

        return ancestor;
    }

    private boolean searchTree(Node root, int value, List<Node> parents){
        if(root == null) return false;

        parents.add(root);

        if(root.data == value) return true;

        if(root.left != null){
            if(searchTree(root.left, value, parents)) return true;
            if(!parents.isEmpty()) parents.remove(parents.size()-1);
        }

        if(root.right != null){
            if(searchTree(root.right, value, parents)) return true;
            if(!parents.isEmpty()) parents.remove(parents.size()-1);
        }

        return false;
    }





    /**
     * The idea is to:
     * - Collect the left boundary, which is not leaf
     * - Collect the leafs nodes only, left to right
     * - Collect the right boundary, which is not leaf
     *
     *        1
     *    2.    3
     *  4.  5.  6. 7
     *     8 9.      0
     *
     * left [1 2] - To get in ascending order we use a pre order traversal
     * leaf [4 8 9 6 0]
     * right [7 3] - To get in reverse order we use a post order traversal
     *
     * */
    ArrayList<Integer> boundaryTraversal(Node node) {
        // code here
        ArrayList<Integer> boundaryValues = new ArrayList<>();
        if(node == null) return boundaryValues;

        if(!isLeaf(node)) boundaryValues.add(node.data);

        collectLeftBoundary(node.left, boundaryValues);

        collectLeaves(node, boundaryValues);

        collectRightBoundary(node.right, boundaryValues);

        return boundaryValues;
    }

    // Function to collect left boundary nodes
    // (top-down order)
    private void collectLeftBoundary(Node node, List<Integer> list){
        if(node == null || isLeaf(node)) return;

        list.add(node.data);

        if(node.left != null){
            collectLeftBoundary(node.left, list);
        }else if(node.right != null){
            collectLeftBoundary(node.right, list);
        }
    }

    private void collectRightBoundary(Node node, List<Integer> list){
        if(node == null || isLeaf(node)) return;

        if(node.right != null){
            collectRightBoundary(node.right, list);
        }else if(node.left != null){
            collectRightBoundary(node.left, list);
        }

        list.add(node.data);
    }

    private void collectLeaves(Node node, List<Integer> list){
        if(node == null) return;

        if(isLeaf(node)){
            list.add(node.data);
            return;
        }

        collectLeaves(node.left, list);
        collectLeaves(node.right, list);
    }

    /**
     * For Input :
     Case: 1 N 2 3 4 N N N N
     My output: 1 3 4 2
     Correct output: 1 3 4 2


     Case: 1 2 3 N N 4 N 5 N N N
     My output: 1 2 5 3
     Correct output: 1 2 5 4 3

     Case: 1 2 3 N 4 N 5 6
     Correct: 1 2 4 6 5 3
     My output: 1 2 6 5 3
     * */
    private boolean isLeaf(Node node){
        return node.left == null && node.right == null;
    }

}
