package com.company;

public class LC2181_MergeNodesInBetweenZeros {
    public static void main(String[] args){
        /**
         * Input: 0 2 3 0 7 0
         * Output: 5 7
         */
        ListNode node = new ListNode(0, new ListNode(2, new ListNode(3, new ListNode(0, new ListNode(7, new ListNode(0))))));
        ListNode result = mergeNodes(node);

        while(result != null){
            System.out.print(result.val + " ");
            result = result.next;
        }
        System.out.println();
    }

    /**
     * Runtime: O(N), where N is the size of the list node
     * Space: O(N), a new list node is created for the output
     * Tags: Linked List
     */
    public static ListNode mergeNodes(ListNode head) {
        ListNode result = new ListNode(0);
        ListNode resultRef = result;

        while(head != null){
            if(head.val == 0){
                if(head.next == null) break; // Exit initialization of list node for last zero
                result.next = new ListNode(0);
                result = result.next;
            }else{
                result.val += head.val;
            }

            head = head.next;
        }

        return resultRef.next;
    }
}
