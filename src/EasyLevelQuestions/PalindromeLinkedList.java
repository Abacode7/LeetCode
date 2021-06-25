package EasyLevelQuestions;

import java.util.*;

public class PalindromeLinkedList {
    public static void main(String[] args){
        ListNode next = new ListNode(0, new ListNode(1));
        ListNode head = new ListNode(1, next);
        System.out.println(isPalindrome(head));
    }


    // First solution
    // We could also copy List into array and use the
    // two pointer approach
    public static boolean isPalindrome(ListNode head) {
        if(head.next == null) return true;

        ListNode headRef = head;
        int count = 0;
        while(head != null){
            count++;
            head = head.next;
        }

        Stack<Integer> stack = new Stack<>();
        head = headRef;
        int i = 0;
        while(i < count){
            if(count % 2 != 0 && i == count/2){
                i++;
                head = head.next;
                continue;
            }

            if(i < count/2){
                stack.push(head.val);
            }else{
                if(head.val != stack.pop().intValue()) return false;
            }
            head = head.next;
            i++;
        }
        return true;
    }
}


class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
