package com.company;

public class LC0083_RemoveDuplicatesFromSortedList {
    public static void main(String[] args){
    }

    public ListNode deleteDuplicates(ListNode head) {
        ListNode headRef = head;
        if(head == null) return head;

        ListNode prev = head;
        head = head.next;
        while(head != null){
            if(head.val == prev.val){
                prev.next = head.next;
            }else{
                prev = head;
            }

            head = head.next;
        }
        return headRef;
    }
}
