package EasyLevelQuestions;

import java.util.*;

// LC Question 141
public class LinkedListCycle {
    public static void main(String[] args){
        // Test 1
        ListNode twoNode = new ListNode(2);
        ListNode fourNode = new ListNode(-4, twoNode);
        ListNode zeroNode = new ListNode(0, fourNode);
        twoNode.next = zeroNode;
        ListNode chainNode = new ListNode(3, twoNode);

        // Test 2
        ListNode nextNode = new ListNode(3, new ListNode(4, new ListNode(5, new ListNode(6))));

        System.out.println(hasCycle(nextNode));
    }

    // First Solution using Set
    // Runtime O(n), Space O(n)
    public static boolean hasCycle(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        while(head != null){
            if(set.contains(head)) return true;
            set.add(head);
            head = head.next;
        }
        return false;
    }

    // Using Floyd's Cycle Finding Algorithm
    // Runtime O(n), Space O(1)
    public static boolean hasCycleImproved(ListNode head) {
        if(head == null) return false;

        ListNode second = head;
        ListNode first = head.next;

        while(second != first){
            if(first == null || first.next == null) return false;

            second = second.next;
            first = first.next.next;
        }
        return true;
    }
}
