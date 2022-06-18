package com.company;

public class LC0002_AddTwoNumbers {
    public static void main(String[] args){
        ListNode input1 = makeListNode(9);
        ListNode input2 = makeListNode(9999999991L);

        System.out.println(addTwoNumbers(input1, input2));
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode result = dummy;

        int carryOver = 0;
        while(l1 != null || l2 != null){
            int l1Value = l1 != null ? l1.val : 0;
            int l2Value = l2 != null ? l2.val : 0;

            int sum = l1Value + l2Value + carryOver;

            int nodeValue = sum % 10;
            carryOver = sum / 10;

            result.next = new ListNode(nodeValue);

            if(l1 != null) l1 = l1.next;
            if(l2 != null) l2 = l2.next;

            result = result.next;
        }

        if(carryOver > 0){
            result.next = new ListNode(1);
        }

        return dummy.next;
    }

    //Todo: Rework to pass all test cases
    public static ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        long num1 = 0;
        long num2 = 0;
        int i = 0;
        while(l1 != null || l2 != null){
            if(l1 != null){
                num1 += l1.val * Math.pow(10, i);
                l1 = l1.next;
            }

            if(l2 != null){
                num2 += l2.val * Math.pow(10, i);
                l2 = l2.next;
            }
            i++;
        }

        long result = num1 + num2;
        if(result == 0) return new ListNode(0);

        ListNode resNode = new ListNode(-1);
        ListNode resNodeRef = resNode;
        while(result != 0){
            int unit = (int) result % 10;
            resNode.next = new ListNode(unit);

            result = result / 10;
            resNode = resNode.next;
            System.out.println(unit);

        }

        return resNodeRef.next;
    }


    //NB: The head is the last digit
    public static ListNode makeListNode(long value){
        ListNode result = new ListNode(-1);
        ListNode ref = result;

        while(value > 0){
            int unit = (int) value % 10;
            result.next = new ListNode(unit);

            value = value / 10;
            result = result.next;
        }
        return ref.next;
    }

    private static class ListNode{
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) {this.val = val;}
        ListNode(int val, ListNode next) {this.val = val; this.next = next;}
    }
}
