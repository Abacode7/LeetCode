package com.company;

public class LC0023_MergeKSortedList {
    public ListNode mergeKLists(ListNode[] lists) {
        ListNode result = new ListNode(0);
        ListNode resultHeadRef = result;

        while(true){
            int minValue = Integer.MAX_VALUE;
            int minIndex = -1;

            for(int i=0; i<lists.length; i++){
                ListNode current = lists[i];
                if(current != null && current.val < minValue){
                    minValue = current.val;
                    minIndex = i;
                }
            }

            if(minIndex == -1) break;

            result.next = new ListNode(minValue);
            lists[minIndex] = lists[minIndex].next;
            result = result.next;
        }

        return resultHeadRef.next;
    }

    private class ListNode{
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) {this.val = val;}
        ListNode(int val, ListNode next) {this.val = val; this.next = next;}
    }
}
