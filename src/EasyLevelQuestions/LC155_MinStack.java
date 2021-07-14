package EasyLevelQuestions;

import java.util.*;

public class LC155_MinStack {
    public static void main(String[] args){
        MinStack minStack = new MinStack();
        minStack.push(12);
        minStack.push(30);
        minStack.push(7);
        minStack.push(6);
        minStack.push(45);
        minStack.push(6);
        minStack.push(6);
        minStack.push(14);
        minStack.push(6);

        for(int i=0; i<9; i++){
            System.out.println("Current value: " + minStack.top() + ", min: " + minStack.getMin());
            minStack.pop();
        }
    }
}

/*
 We store the current min value along with the stack.
 We could also use to stacks, one to store the input values, the other to
 store the current min value.
 */
class MinStack {
    /** initialize your data structure here. */
    private Stack<int[]> stack;

    public MinStack() {
        stack = new Stack<>();
    }

    public void push(int val) {
        if(!stack.isEmpty()){
            int minSoFar = stack.peek()[1];
            stack.push(new int[]{val, Math.min(val, minSoFar)});
            return;
        }
        stack.push(new int[]{val, val});
    }

    public void pop() {
        stack.pop();
    }

    public int top() {
        return stack.peek()[0];
    }

    public int getMin() {
        return stack.peek()[1];
    }
}
