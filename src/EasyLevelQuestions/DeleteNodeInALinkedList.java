package EasyLevelQuestions;

// LC Question - 237
public class DeleteNodeInALinkedList {
    public static void main(String[] args){
        ListNode nextNode = new ListNode(3, new ListNode(4, new ListNode(5, new ListNode(6))));
        ListNode node = new ListNode(2, nextNode);

        // Run
        deleteNode(nextNode);

        while(node != null){
            System.out.println(node.val);
            node = node.next;
        }
    }

    // Best solution
    // Runtime - O(1), Space - O(1)
    // We basically move the consecutive node chain
    // to the current node
    public static void deleteNode2(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }

    // Since we are given the node to deleted
    // directly, the idea is to adjust consecutive
    // node values to the current position

    // Runtime - O(n), Space - O(1)
    public static void deleteNode(ListNode node) {
        if(node == null) return;

        // We iterate till we get to the second
        // to the last node, which we the move the
        // next value to and assign its next as null
        while(node.next.next != null){
            node.val = node.next.val;
            node = node.next;
        }
        node.val = node.next.val;
        node.next = null;
    }
}

