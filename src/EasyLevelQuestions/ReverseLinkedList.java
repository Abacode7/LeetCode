package EasyLevelQuestions;

// LC Question 206
public class ReverseLinkedList {
    public static void main(String[] args){
        // 2 - 3 - 4 - 5 - 6
        ListNode nextNode = new ListNode(3, new ListNode(4, new ListNode(5, new ListNode(6))));
        ListNode node = new ListNode(2, nextNode);

        ListNode result = reverseList(node);

        // Print result
        while(result != null){
            System.out.println(result.val);
            result = result.next;
        }
    }

    // Runtime O(n), Space O(1)
    public static ListNode reverseListImproved(ListNode head) {
        ListNode prev = null;
        ListNode currentNode = head;

        while(currentNode != null){
            ListNode nextNodeRef = currentNode.next;
            currentNode.next = prev;

            prev = currentNode;
            currentNode = nextNodeRef;
        }
        return prev;
    }

    // First Solution
    // Runtime O(n), Space O(n)
    public static ListNode reverseList(ListNode head) {
        ListNode headPrev = null;
        ListNode headNew = null;
        while(head != null){
            headNew = new ListNode(head.val);
            headNew.next = headPrev;

            // holds access to subsequent node chains
            headPrev = headNew;

            // iterate
            head = head.next;

        }
        return headNew;
    }
}
