package com.company;

import java.util.Stack;

public class AE_BalancedBrackets {

    public static void main(String[] args){
        System.out.println(balancedBrackets("({})[]")); // true
    }
    /**
     ({}) => ( { } )
     **/
    public static boolean balancedBrackets(String str) {
        // Write your code here.
        Stack<Character> stack = new Stack<>();
        for(char bracket: str.toCharArray()){
            if(bracket == '(' || bracket == '{' || bracket == '['){
                stack.push(bracket);
            }else if(bracket == ')' || bracket == '}' || bracket == ']'){
                if(stack.isEmpty()) return false;
                char pairBracket = stack.peek();

                boolean isBalanced = (bracket == ']' && pairBracket == '[')
                        || (bracket == ')' && pairBracket == '(')
                        || (bracket == '}' && pairBracket == '{');

                if(isBalanced) stack.pop();
                else return false;
            }
        }
        return stack.isEmpty();
    }
}
