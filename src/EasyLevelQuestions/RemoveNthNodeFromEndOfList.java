package EasyLevelQuestions;

// LC Question 19
public class RemoveNthNodeFromEndOfList {
    public static void main(String[] args){
        // 2 - 3 - 4 - 5 - 6
        ListNode nextNode = new ListNode(3, new ListNode(4, new ListNode(5, new ListNode(6))));
        ListNode node = new ListNode(2, nextNode);

        ListNode result = removeNthFromEndOptimised(node, 1);

        // Print result
        while(result != null){
            System.out.println(result.val);
            result = result.next;
        }
    }

    // Using two pointers first and second which we make n + 1 apart, so
    // when first reaches the end (becomes null), second.next node is the value
    // to be deleted
    // Runtime O(L) where L is length of linked list
    // Space O(1), no extra space is used
    public static ListNode removeNthFromEndOptimised(ListNode head, int n) {
        ListNode dummy = new ListNode(-1, head);
        ListNode first = dummy;
        ListNode second = dummy;

        for(int i=1; i<=n+1; i++){
            first = first.next;
        }

        while(first != null){
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;

        return dummy.next;
    }

    // First solution
    // Runtime O(L) where L is length of linked list
    // Space O(1), no extra space is used
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        int totalNodes = 0;
        ListNode headRef = head;

        while(head != null){
            totalNodes++;
            head = head.next;
        }
        // Add one extra to totalNodes
        totalNodes++;

        head = headRef;
        ListNode headPrev = null;
        int count = 0;
        while(head != null){
            count++;
            if(totalNodes - count == n){
                // Means we deleting the head
                if(headPrev == null) return head.next;

                // Means we deleting a child node
                headPrev.next = head.next;
                return headRef;
            }
            headPrev = head;
            head = head.next;
        }
        return headRef;
    }
}
