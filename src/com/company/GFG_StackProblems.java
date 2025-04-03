package com.company;

import java.util.*;

public class GFG_StackProblems {

    public static void main(String[] args){
        Stack<Integer> stack = new Stack<>();
        stack.add(5);
        stack.add(4);
        stack.add(3);
        stack.add(2);
        stack.add(1);

        Stack<Integer> result = sortStack(stack);
        Stack<Integer> result1 = sortStack2(stack);

        while(!result.isEmpty()){
            System.out.println(result.pop());
        }
        System.out.println();

        while(!result1.isEmpty()){
            System.out.println(result1.pop());
        }

        /**
         * 41 88 58 69 93 42 44 25 12 47 41 88 58 69 93 42 44 25 12 47 - Input
         * 88 93 69 93 93 44 47 47 47 88 88 93 69 93 -1 44 47 47 47 -1 - Mine
         * 88 93 69 93 -1 44 47 47 47 88 88 93 69 93 -1 44 47 47 47 -1 - Correct
         */
        System.out.println(nextLargerElement(new int[]{6, 8, 0, 1, 3})); //[8, -1, 1, 3, -1]
        System.out.println(validParenthesis("[()()]{}"));
    }



    /**
     * Live Breakdown
     * Input: s = “[{()}]”
     * Output: true
     *
     * Input: s = “[()()]{}”
     * Output: true
     *
     * Solution: O(n) time, O(n) space
     * Using Stacks to store pairs
     */
    public static boolean validParenthesis(String s){
        if(Objects.isNull(s) || s.equals("") || s.length() % 2 != 0) return false;
        Stack<Character> stack = new Stack<>();
        for(char currentChar: s.toCharArray()){
            if(currentChar == '[' || currentChar == '{' || currentChar == '('){
                stack.push(currentChar);
            }else{
                if(currentChar == ']' && stack.peek() != '[') return false;
                if(currentChar == '}' && stack.peek() != '{') return false;
                if(currentChar == ')' && stack.peek() != '(') return false;
                stack.pop();
            }
        }
        return stack.isEmpty();
    }



    /**
     * Solution: O(nlogn) time, O(n) space
     * With sort method
     *
     * Solution 2: O(n^2) time, O(n) space
     * Without sort method
     * */
    public static Stack<Integer> sortStack(Stack<Integer> s) {
        // add code here.
        if(s == null || s.isEmpty()) return s;

        List<Integer> stackList = new ArrayList<>(s);
        Collections.sort(stackList);

        Stack<Integer> newStack = new Stack<>();
        for(int value: stackList){
            newStack.push(value);
        }
        return newStack;
    }

    public static Stack<Integer> sortStack2(Stack<Integer> s) {
        // add code here.
        if(s == null || s.isEmpty()) return s;

        List<Integer> list = new ArrayList<>(s.size());
        while(!s.isEmpty()){
            int value = s.pop();

            int i = 0;
            while(i < list.size() && list.get(i) < value){
                i++;
            }

            if(list.isEmpty()) list.add(value);
            else {
                list.add(i, value);
            }
        }

        for(int value: list){
            s.push(value);
        }
        return s;
    }



    /**
     * Live Breakdown
     *  i/j 0 1 2
     *  0   1 1 0
     *  1.  0 1 0
     *  2   0 1 1
     *
     * Steps
     * Find the suspect celebrity: Person who knows no one
     * If suspect != 1: Then no celeb, return -1
     * else: check that every one knows suspect celeb
     *
     * Solution: O(n^2) time, O(1) space
     * Iterating the adjacency matrix
     *
     * Solution 2: O(n) time, O(1) space
     * Using two pointers
     * i = 0, j = n-1
     * if i knows j:
     *  then i isn't a celeb, j may be  a celeb, increment i
     * else:
     *  j isn't a celeb, i may be a celeb, decrement j
     *
     *  Get the potential celeb
     *  Then check if potential celeb is celeb
     * */
    public int celebrity(int mat[][]) {
        // code here
        int suspectCelebCount = 0;
        int suspectCeleb = -1;

        for(int i=0; i < mat.length; i++){
            int countPersonKnown = 0;
            for(int j=0; j < mat[0].length; j++){
                if(i == j) continue;

                if(mat[i][j] == 1) countPersonKnown++;

                if(countPersonKnown > 1) break;
            }

            if(countPersonKnown == 0){
                suspectCelebCount++;
                suspectCeleb = i;
            }
        }

        if(suspectCelebCount != 1) return -1;

        // Check if everyone knows suspect celeb
        for(int i=0; i < mat.length; i++){
            if(mat[i][suspectCeleb] != 1) return -1;
        }
        return suspectCeleb;
    }

    public int celebrity2(int mat[][]) {
        // code here
        int i = 0, j = mat.length - 1;
        while(i < j){
            if(mat[i][j] == 1){
                i++;
            }else{
                j--;
            }
        }

        int celeb = i;

        for(int k=0; k < mat.length; k++){
            if(k == celeb) continue;

            if(mat[celeb][k] == 1 || mat[k][celeb] == 0) return -1;
        }
        return celeb;
    }



    /**
     * 1 3 2 4
     * 4 3 2 1
     *
     * Solution: O(n^2) time, O(1) space
     * Using brute force to find the next larger element
     *
     * Solution 1: O(n) `amortized` time, O(n) space
     * Using stack to store and delete next large elements
     * */
    public static ArrayList<Integer> nextLargerElement(int[] arr) {
        // code here
        ArrayList<Integer> list = new ArrayList<>();

        int i = 0;
        while(i < arr.length-1){
            int j = i+1;

            while(j < arr.length && arr[j] <= arr[i]){
                j++;
            }

            if(j >= arr.length) list.add(-1);
            else{
                list.add(arr[j]);
            }

            i++;
        }
        list.add(-1);

        return list;
    }

    public static ArrayList<Integer> nextLargerElement2(int[] arr) {
        // code here
        ArrayList<Integer> list = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();

        int n = arr.length;
        list.add(-1);
        stack.push(arr[n-1]);
        for(int i=n-2; i >= 0; i--){

            while(!stack.isEmpty() && stack.peek() <= arr[i]){
                stack.pop();
            }

            if(stack.isEmpty()) {
                list.add(-1);
            } else{
                list.add(stack.peek());
            }
            stack.push(arr[i]);
        }

        Collections.reverse(list);
        return list;
    }



    /**
     * Implement queue using stacks
     * Idea is simple
     * For Enqueue: Push into stack 1
     * For Dequeue: Pop only from stack 2 following the below:
     * - Move data from stack 1 to stack 2, if stack 2 is empty
     * - If stack 2 is not empty, simply pop from stack 2
     * (the order will have reversed from LIFO to FIFO)
     *
     * Solution:
     * Enqueue: O(1) time, O(n) space
     * Dequeue: O(n) time, O(n) space
     */
    Stack<Integer> stack1 = new Stack<>();
    Stack<Integer> stack2 = new Stack<>();
    public void pushQueue(int data){
        stack1.push(data);
    }

    public int popQueue(){
        if(stack1.isEmpty() && stack2.isEmpty()) return -1;

        if(!stack2.isEmpty()){
            while(!stack1.isEmpty()){
                stack2.push(stack1.pop());
            }
        }
        return stack2.pop();
    }
}
