package EasyLevelQuestions;

import java.util.*;

// LC Question 234
public class PalindromeLinkedList {
    public static void main(String[] args){
        ListNode next = new ListNode(0, new ListNode(1));
        ListNode head = new ListNode(1, next);
        System.out.println(isPalindrome(head));
    }

    // Runtime O(n), Space O(1)
    public static boolean isPalindromeImproved(ListNode head) {
        ListNode second = head;
        ListNode first = head;

        while(first != null && first.next != null){
            second = second.next;
            first = first.next.next;
        }

        second = reverseLinkedList(second);
        first = head;

        while(second != null){
            if(first.val != second.val) return false;
            first = first.next;
            second = second.next;
        }

        return true;
    }

    public static ListNode reverseLinkedList(ListNode head){
        ListNode prev = null;
        while(head != null){
            ListNode nextReference = head.next;
            head.next = prev;

            prev = head;
            head = nextReference;
        }
        return prev;
    }

    // First solution
    // We could also copy List into array and use the
    // two pointer approach
    // Runtime O(n), Space O(n)
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
