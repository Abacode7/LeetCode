package com.company;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class GFG_LinkedListProblems {

    public static void main(String[] args){
        // Todo: test implementation
        LinkedListUtils.Node node =
                new LinkedListUtils.Node(4,
                new LinkedListUtils.Node(2,
                        new LinkedListUtils.Node(1), new LinkedListUtils.Node(3)),
                new LinkedListUtils.Node(5));

        System.out.println("Before Flatten: ");
        LinkedListUtils.printNodes(node);
        System.out.println();

        LinkedListUtils.flatten(node);
        System.out.println("After Flatten: ");
        LinkedListUtils.printNodes(node);
    }



    /**
     * Iterate through each nodes
     * Solution 1: O(n) time, O(n) new space
     * Create a node from each node, make it the head of the old node.
     *
     * Solution 2: O(n) time, O(1) extra space - OPTIMAL
     * Using three pointers: prev, current, and next
     *
     *        1 -> 2 -> 3
     * prev curr next
     * **/
    Node reverseList(Node head) {
        // code here
        if(head == null) return head;

        Node newNode = null;

        while(head != null){
            Node newHead = new Node(head.data);
            newHead.next = newNode;
            newNode = newHead;
            head = head.next;
        }
        return newNode;
    }

    Node reverseList2(Node head){
        // code here - OPTIMAL
        if(head == null) return head;

        Node prev = null, current = head, next;
        while(current != null){
            next = current.next;

            current.next = prev;

            prev = current;
            current = next;
        }
        return prev;
    }



    /**
     * We should have oddHead and oddRef
     * where oddHead keeps references to head of oddNumbers
     * and oddRef keeps the latest ref to oddNumbers
     *
     * same thing for even, evenHead and evenRef
     *
     * Solution: O(n) time, O(1) space - OPTIMAL
     * */
    Node divide(Node head) {
        // code here - OPTIMAL
        if(head == null) return head;

        Node oddHead = new Node(-1);
        Node oddRef = oddHead;

        Node evenHead = new Node(-2);
        Node evenRef = evenHead;


        while(head != null){
            if(head.data % 2 == 0){
                evenRef.next = head;
                evenRef = evenRef.next;
            }else{
                oddRef.next = head;
                oddRef = oddRef.next;
            }
            head = head.next;
        }

        oddRef.next = null;

        evenRef.next = oddHead.next;

        return evenHead.next;
    }



    /**
     * Solution 1: O(n) time, O(1) space
     * Using Floyd's Cycle Finding Algorithm
     * The use of slow && fast pointers
     */
    public static boolean detectLoop(Node head) {
        // Add code here - OPTIMAL
        if(head == null) return false;

        Node slow = head;
        Node fast = head.next;

        while(fast != null){
            if(slow == fast) return true;

            slow = slow.next;

            if(fast.next != null) fast = fast.next.next;
            else fast = null;
        }
        return false;
    }



    /**
     * 2->2->1->4->4
     *
     * if(head.data == x && head == headRef)
     *  headRef = head.next
     *
     * if head.data == x
     *  prev.next = head.next
     *
     * Solution: O(n) time, O(1) space - OPTIMAL
     * Using two pointers: prev and head
     * */
    public static Node deleteAllOccurances(Node head, int x) {
        // Your code here - OPTIMAL
        if(head == null) return head;
        Node headRef = head;
        Node prev = null;

        while(head != null){
            if(head.data == x){
                if(head == headRef){
                    headRef = head.next;
                }else{
                    prev.next = head.next;
                }
            }else{
                prev = head;
            }
            head = head.next;
        }
        return headRef;
    }


    /**
     *       f s fs
     * s f   s       f
     * 1 2 3 4 5 6 7 8 3
     *
     * Solution: O(n) time, O(n) space
     * Using a set to store existing nodes
    **/
    public static void removeLoop(Node head) {
        // code here
        Node current = head;
        Node prev = null;
        Set<Node> set = new HashSet<>();

        while(current != null){
            if(set.contains(current)){
                prev.next = null;
                return;
            }

            set.add(current);

            prev = current;
            current = current.next;
        }
    }




    /**
     * Given:
      s.   f
      1.   null
     * 1    2   3   4   5  null
     * 3rd from the end
     *
     * counter = 1 to <=k
     *
     * edge cases:
     * - head is null OR k < 0: return -1
     *
     * Solution: Two Pointers (fast & slow)
     *      Time: O(n) time
     *      Space: O(1) space
     * */
    int getKthFromLast(Node head, int k) {
        // Your code here
        if(head == null || k <= 0) return -1;

        Node fast = head;
        int fastCounter = 1;
        while(fastCounter <= k){
            if(fast == null) return -1; // If fast is null before we get to k, return -1;

            fast = fast.next;
            fastCounter++;
        }

        Node slow = head;
        while(fast != null){
            fast = fast.next;
            slow = slow.next;
        }
        return slow.data;
    }



    /**
     * Given;
     * [1->3, 2->4, 5->6]
     *
     * Find min head and its index:
     *  headRef -> minHead(1)
     * Store minHead.next at its index
     *
     *  0.  1.   2
     * [null, null, 5 -> 6]
     *
     * headRef -> 1 -> 2 -> 3 -> 4 -> 5 -> 6
     * At index 0: node = node.next
     *
     * */
    static Node mergeKSortedList(List<Node> nodeList) {
        if(nodeList == null || nodeList.isEmpty()) return null;

        int nodeListSize = nodeList.size();

        Node head = new Node(-1);
        Node headRef = head;

        while(true){
            Node minNode = null;
            int minNodeIndex = -1;

            for(int i=0; i<nodeListSize; i++){
                Node currentNode = nodeList.get(i);

                if(currentNode == null) continue;

                if(minNode == null || minNode.getData() > currentNode.getData()){
                    minNode = currentNode;
                    minNodeIndex = i;
                }
            }

            if(minNode == null) break;

            head.setNext(minNode);
            nodeList.set(minNodeIndex, minNode.getNext());

            head = head.getNext();
        }

        return headRef.getNext();
    }




    /**
     * Example:
          4
        /   \
       2     5
     1  3
     Result: 4 2 1 3  5

     4
      \
       2
        1
         3
     * */
    static void flatten(LinkedListUtils.Node node){
        LinkedListUtils.flatten(node);
    }

}


/** Linked List Node Structure */
class Node {
    int data;
    Node next;

    public Node(int value) {
        this.data = value;
    }

    public int getData(){
        return data;
    }

    public Node getNext(){
        return next;
    }

    public void setNext(Node next){
        this.next = next;
    }
}



class LinkedListUtils {
    /**
     * Example:
          4
        /   \
       2     5
     1  3
     Preorder Traverse: 4 2 1 3  5

     Result
      4
       \
        2
         1
          3
     * */
    public static void flatten(Node node){
        if(node == null) return;
        traverseAndFlatten(node);
    }

    private static void traverseAndFlatten(Node node){
        if(node.getLeft() == null && node.getRight() == null) return;

        Node left = node.getLeft();
        Node right = node.getRight();

        if(left != null){
            traverseAndFlatten(left);

            Node leftRightMostNode = left;
            while(leftRightMostNode.getRight() != null){
                leftRightMostNode = leftRightMostNode.getRight();
            }

            node.setRight(left);
            node.setLeft(null);
            leftRightMostNode.setRight(right);
        }

        if(right != null) traverseAndFlatten(right);
    }

    static class Node {
        private int data;
        private Node left;
        private Node right;

        public Node(int data){
            this.data = data;
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

        public void setLeft(Node left){
            this.left = left;
        }

        public void setRight(Node right){
            this.right = right;
        }
    }


    /** Function to print nodes in structure **/
    public static void printNodes(Node node){
        if(node == null){
            System.out.print(" null ");
            return;
        }

        System.out.print(node.getData() + " -> ");
        printNodes(node.left);
        printNodes(node.right);
    }

}

