package com.company;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

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
        if(head == null) return null;

        Node prev = null, current = head;
        while(current != null){
            Node next = current.next;

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



    /**
     * 1 -> 9 ->  0 => 100 + 90 + 0
     *
     * 2 -> 5 => 20 + 5
     *
     * 2 1 5
     *
     * Two Options - (Take memory extra m+n)
     * - Iterate, get values as string, convert to int then sum
     * - Reverse, compute units, get int value then sum
     *
     *
     * Alternatively, - (No extra memory)
     * - Reverse, sum units and store in one of nodes, return node.
     * */
    public static Node addNodes(Node firstNode, Node secondNode){
        // code here - LIMITED
        if(firstNode == null && secondNode == null) return null;
        if(firstNode == null) return secondNode;
        if(secondNode == null) return firstNode;

        int firstValue = getNodeValue(firstNode);
        int secondValue = getNodeValue(secondNode);

        int sum = firstValue + secondValue;

        Node sumNode = buildNodeOfValue(sum);
        return sumNode;
    }

    /**
     * LIMITED: CHECK CASE BELOW
     *
     * For Input :
     * 8 5 1 6 7 0 9 2 4 4 0 9
     * 7 4 7 8 1 0 5 9 0 8 3 0
     * Your Code's output is:
     * 1 7 5 3 6 8 1 1 2 7
     * It's Correct output is:
     * 1 5 9 9 4 8 1 5 1 5 2 3 9
     */

    private static Node buildNodeOfValue(int value){
        if(value < 10) return new Node(value);

        /**
         * 257 % 10 = 7 - unit
         * 257 / 10 = 25
         *
         * 25 % 10 = 5 - unit
         * 25 / 10 = 2
         *
         * 2 % 10 = 2 - unit
         * 2 / 10 = 0
         *
         * */
        Node prev = null;
        while(value != 0){
            int unit = value % 10;

            prev = new Node(unit, prev);

            value = value / 10;
        }
        return prev;
    }

    private static int getNodeValue(Node head){
        if(head == null) return 0;
        Node newHead = reverseNode(head);

        Node current = newHead;

        int sum = 0;
        int unit = 1;
        while(current != null){
            sum += current.getData() * unit;

            unit *= 10;
            current = current.getNext();
        }

        reverseNode(newHead); // restore to original form
        return sum;
    }

    private static Node reverseNode(Node node){
        if(node == null) return null;
        Node prev = null;
        Node current = node;
        Node next;

        while(current != null){
            next = current.getNext();

            current.setNext(prev);

            prev = current;
            current = next;
        }

        return prev;
    }



    /**
     *            s.  s.next
     *                      f
     *  1 -> 2 -> 3 -> 3 -> 2 -> 1
     *                 1 -> 2 -> 3
     *  H
     * [3 2 1]
     * - NB: While fast has next next
     *
     *           s     s.next
     *                     f
     * 1 -> 2 -> 3 -> 2 -> 1
     * H
     * [2 1]
     *
     * Edge cases:
     * 0 item, return false; 1 item, return true; 2 item, algorithm works
     *
     * Solution 1: Stack O(n) time, O(n) space
     *
     * Solution 2: Reverse O(n) time, O(1) space
     * */
    public static boolean isPalindrome(Node head){
        if(head == null) return false;
        if(head.getNext() == null) return true;

        // Get first node in second part of list
        Node middle = findMiddleNode(head);

        // Iterate and store elements of second part of list
        Stack<Integer> stack = buildStackFromNode(middle.getNext());

        // Compare data in second part of list to head
        Node newCurrent = head;
        while(!stack.isEmpty()){
            if(newCurrent.getData() != stack.pop()) return false;

            newCurrent = newCurrent.getNext();
        }

        return true;
    }

    private static Node findMiddleNode(Node head){
        Node slow = head;
        Node fast = head;
        while(fast.getNext() != null && fast.getNext().getNext() != null){
            slow = slow.getNext();
            fast = fast.getNext().getNext();
        }
        return slow;
    }

    private static Stack<Integer> buildStackFromNode(Node start){
        Stack<Integer> stack = new Stack<>();
        Node current = start.getNext();
        while(current != null){
            stack.push(current.getData());

            current = current.getNext();
        }
        return stack;
    }

}


/** Linked List Node Structure */
class Node {
    int data;
    Node next;

    public Node(int value) {
        this.data = value;
    }

    public Node(int value, Node next){
        this.data = value;
        this.next = next;
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

