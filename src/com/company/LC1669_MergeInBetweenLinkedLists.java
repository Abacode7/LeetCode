package com.company;

public class LC1669_MergeInBetweenLinkedLists {
    public static void main(String[] args){
        ListNode list1 = new ListNode(0, new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5))))));
        ListNode list2 = new ListNode(100, new ListNode(101, new ListNode(102)));
        ListNode result = mergeInBetween(list1, 3, 4, list2);

        /**
         * Expected output: 0 1 2 100 101 102 5
         */
        while(result != null){
            System.out.print(result.val + " ");
            result = result.next;
        }
        System.out.println();
    }

    /**
     * Runtime: O(n + m) where n and m are lengths of linked list respectively
     * Space: O(1), cos no extra space is used
     *
     * Tags: Linked List
     */
    public static ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {
        ListNode list2Tail = list2;
        while(list2Tail.next != null){
            list2Tail = list2Tail.next;
        }

        ListNode list1Head = list1;
        ListNode prev = null;
        ListNode nodeBeforeA = null, nodeAfterB = null;
        int i = 0;
        while(list1 != null){
            if(i == a){
                nodeBeforeA = prev;
            }
            if(i == b){
                nodeAfterB = list1.next;
                break;
            }
            prev = list1;
            list1 = list1.next;
            i++;
        }

        list2Tail.next = nodeAfterB;
        if(nodeBeforeA == null) return list2;
        nodeBeforeA.next = list2;

        return list1Head;
    }
}
