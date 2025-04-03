package com.company;

import java.util.HashSet;
import java.util.Set;

public class GFG_LinkedListProblems {

    public static void main(String[] args){
        // Todo: test implementation
        System.out.println();
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

        Node prev = null, current = head, next = head.next;
        while(current != null){
            current.next = prev;

            prev = current;
            current = next;
            if(next != null) next = next.next;
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

        if(oddRef != null) oddRef.next = null;

        if(evenRef != null) evenRef.next = oddHead.next;

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

    class Node {
        int data;
        Node next;
        Node(int value) {
            this.data = value;
        }
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
                head = head.next;
            }else{
                prev = head;
                head = head.next;
            }
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
}
