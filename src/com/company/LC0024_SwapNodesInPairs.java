package com.company;

public class LC0024_SwapNodesInPairs {
    public static void main(String[] args){
        ListNode node = new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4))));
        ListNode result = swapPairs(node);

        while(result != null){
            System.out.print(result.val + " ");
            result = result.next;
        }
        System.out.println();
    }

    /**
     Pseudocode
     1 2 3 4
     2 1 3 4

     nodePair = node.next
     node.next = nodePair.next
     nodePair.next = node

     do this while: node.next != null
     iterate using: node = node.next;

     Runtime: O(N), where N is the size of List Node
     Space: O(1), it is done in place hence no extra space required
     Tag: Linked List
     */
    public static ListNode swapPairs(ListNode head) {
        if(head == null || head.next == null) return head;

        ListNode newHead = head.next;

        ListNode currentNode = head;
        ListNode prevNode = null;
        while(currentNode != null && currentNode.next != null){
            ListNode currentNodePair = currentNode.next;
            currentNode.next = currentNodePair.next;
            currentNodePair.next = currentNode;

            // Check to join adjacent PAIRS
            if(prevNode != null){
                prevNode.next = currentNodePair;
            }
            prevNode = currentNode;

            currentNode = currentNode.next;
        }

        return newHead;
    }
}
