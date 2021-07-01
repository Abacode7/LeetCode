package EasyLevelQuestions;

// LC Question 21
public class MergeTwoSortedList {
    public static void main(String[] args){
        ListNode l1 = new ListNode(1, new ListNode(3, new ListNode(5)));
        ListNode l2 = new ListNode(2, new ListNode(4, new ListNode(6)));

        ListNode result = mergeTwoLists(l1, l2);

        // Print result
        while(result != null){
            System.out.println(result.val);
            result = result.next;
        }
    }
    // First Solution
    // Runtime O(m + n), Space (n + m)
    public static ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if(l1 == null) return l2;
        if(l2 == null) return l1;

        ListNode newList = new ListNode(0);
        ListNode headRef = newList;

        while(l1 != null || l2 != null){
            if(l1 == null){
                newList.next = new ListNode(l2.val);
                l2 = l2.next;
                newList = newList.next;
                continue;
            }
            if(l2 == null){
                newList.next = new ListNode(l1.val);
                l1 = l1.next;
                newList = newList.next;
                continue;
            }

            if(l1.val <= l2.val){
                newList.next = new ListNode(l1.val);
                l1 = l1.next;
            }else{
                newList.next = new ListNode(l2.val);
                l2 = l2.next;
            }
            newList = newList.next;
        }

        return headRef.next;
    }

    // Runtime O(m+n), Space O(1)
    public static ListNode mergeTwoListsImproved(ListNode l1, ListNode l2) {
        // maintain an unchanging reference to node ahead of the return node.
        ListNode prehead = new ListNode(-1);

        ListNode prev = prehead;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                prev.next = l1;
                l1 = l1.next;
            } else {
                prev.next = l2;
                l2 = l2.next;
            }
            prev = prev.next;
        }

        // At least one of l1 and l2 can still have nodes at this point, so connect
        // the non-null list to the end of the merged list.
        prev.next = l1 == null ? l2 : l1;

        return prehead.next;
    }
}
