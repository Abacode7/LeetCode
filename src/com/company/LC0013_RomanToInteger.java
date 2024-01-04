package com.company;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class LC0013_RomanToInteger {

    public static void main(String[] args){
        String input = "III";
        int result = romanToInt(input);
        System.out.println(result);
    }

    public static int romanToInt(String s) {
        Map<Character, Integer> romanToInteger = new HashMap();
        romanToInteger.put('I', 1);
        romanToInteger.put('V', 5);
        romanToInteger.put('X', 10);
        romanToInteger.put('L', 50);
        romanToInteger.put('C', 100);
        romanToInteger.put('D', 500);
        romanToInteger.put('M', 1000);


        Stack<Integer> stack = new Stack<>();

        for(char roman: s.toCharArray()){
            Integer romanInteger = romanToInteger.get(roman);
            if(stack.isEmpty()){
                stack.push(romanInteger);
            }else{
                Integer lastInteger = stack.peek();
                if(lastInteger < romanInteger.intValue()){
                    stack.pop();
                    stack.push(romanInteger - lastInteger);
                }else{
                    stack.push(romanInteger);
                }
            }
        }

        int romanIntegerValue = 0;
        while(!stack.isEmpty()){
            romanIntegerValue += stack.pop();
        }

        return romanIntegerValue;
    }
}
