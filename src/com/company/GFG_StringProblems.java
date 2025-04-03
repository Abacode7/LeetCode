package com.company;

import java.util.*;

public class GFG_StringProblems {
    public static void main(String[] args){
        System.out.println(multiplyStrings("00055", "0"));
    }

    /**
     * Validate an IP Address
     *
     * Solution: O(1) time, O(1) space - OPTIMAL
     * Split string and loop over its address ranges
     */
    public static boolean isValid(String s) {
        // Write your code here - OPTIMAL
        if(s.length() == 0) return false;

        String[] addressRange = s.split("\\.");
        if(addressRange.length != 4) return false;

        // Check for wildcard address (since it defies logic below)
        if(s.equals("0.0.0.0")) return true;

        int counter = 0;
        for(String address: addressRange){
            int number = parseInt(address);
            // Check number in range
            if(number < 0 || number > 255) return false;

            // Check address doesn't lead with zeros
            if(counter == 0 && number == 0) return false;

            // Check for leading zeros
            if(address.charAt(0) == '0' && number != 0) return false;

            // Check even zeros don't have leading zeros
            if(number == 0 && address.length() > 1) return false;

            counter++;
        }

        return true;
    }

    private static int parseInt(String numString){
        try{
            return Integer.parseInt(numString);
        }catch (NumberFormatException e){
            return -1;
        }
    }



    /**
     * Problem Breakdown
     * Multiply two strings
     * 4
     * 9 9
     * 5 5
     * -----
     *   4 9 5
     * 4 9 5
     * ---------
     * 5 4 4 5
     *
     *
     * 5
     * 4
     *  9 9 j
     *  5 5 i
     * -----
     *  4 9 5 first full j iteration
     *5 0 4 5 second full j iteration
     * --------
     * [5 4 4 5]
     *      4 5
     *    4 5
     *    4 5
     *  4 5
     *  ----------
     *  5 4 4 5
     *
     *
     *  Solution: O(m*n) time, O(m+n) space
     *  The key idea is to evaluate the multiplication using tables we learnt in primary
     *  Multiply two digits, add to the current digit in its position (the carry over)
     *  Separate the result into its units
     *  Place the last unit in its correct position
     *  Add the carry over to the position before it.
     *
     *  NB: Be mindful of the indexes, the last index of m & n is (m + n - 1)
     * */
    public static String multiplyStrings(String s1,String s2) {
        //code here. - OPTIMAL
        boolean isNegative = false;
        if(s1.charAt(0) == '-'){
            isNegative = true;
            s1 = s1.substring(1);
        }
        if(s2.charAt(0) == '-'){
            isNegative = !isNegative;
            s2 = s2.substring(1);
        }

        int s1Length = s1.length();
        int s2Length = s2.length();

        int[] result = new int[s1Length + s2Length];
        for(int i=s1Length-1; i >=0; i--){
            for(int j=s2Length-1; j >= 0; j--){
                int product = (s1.charAt(i) - '0') * (s2.charAt(j) - '0');
                int sum = product + result[i + j + 1];

                result[i + j + 1] =  sum % 10;
                result[i + j] += sum / 10;
            }
        }

        StringBuilder resultString = new StringBuilder();
        for(int digit: result){
            if(digit == 0 && resultString.length() == 0) continue;
            resultString.append(digit);
        }

        if(resultString.length() == 0) return "0";

        if(isNegative ) resultString.insert(0, "-");

        return resultString.toString();
    }



    /**
     * Implement Atoi
     *
     * Live Breakdown
     * trim initially
     * check the sign thereafter
     * Iterate from behind
     *  multiple = 1, multiple *= 10
     *  value += multiple
     * reset if you encounter other characters
     *
     * Solution: O(n) time, O(1) space - OPTIMAL
     *
     * */
    public static int myAtoi(String s) {
        // Your code here - OPTIMAL
        s = s.trim();

        boolean isNegative = false;
        if(s.charAt(0) == '-' || s.charAt(0) == '+'){
            if(s.charAt(0) == '-') isNegative = true;
            s = s.substring(1);
        }

        long result = 0;
        long multiple = 1;
        for(int i = s.length()-1; i >= 0; i--){
            char currentValue = s.charAt(i);
            if(Character.isDigit(currentValue)){
                int currentValueInt = currentValue - '0';
                result += (currentValueInt * multiple);

                multiple *= 10;
            }else{
                result = 0;
                multiple = 1;
            }
        }

        if(isNegative) result = -1 * result;

        if(result > Integer.MAX_VALUE || result < Integer.MIN_VALUE){
            if(isNegative) return Integer.MIN_VALUE;
            else return Integer.MAX_VALUE;
        }
        return (int) result;
    }



    /**
     * Live Breakdown
     * a m a z o n => s1
     *
     * s2
     * o n a m a z | right/clockwise rotation
     * a z o n a m | left/anti-clockwise rotation
     *
     * Solution 1: O(n) time, O(n) space
     * Rotate s1 left and right:
     *  Use substring to cut & join at beginning/end based on rotation
     * Store rotated values in SET
     * Check if s2 in SET
     *
     * Solution 2: O(n) time, O(1) space - OPTIMAL
     * Compare rotated indexes in s1 and s2 for left & right based on:
     *  i == (i + d) % n, where i is index, d is number of shifts and n is length of string
     * */
    public static boolean isRotated(String s1, String s2) {
        // Your code here - OPTIMAL
        if(s1.length() != s2.length()) return false;

        if(s1.length() <= 2 && !s1.equals(s2)) return false;

        int sLength = s1.length();
        // Rotating right
        boolean rightRotateValid = true;
        for(int i=0; i<sLength; i++){
            if(s1.charAt(i) != s2.charAt((i+2)%sLength)){
                rightRotateValid = false;
                break;
            }
        }

        // Rotating left
        boolean leftRotateValid = true;
        for(int i=0; i<sLength; i++){
            if(s2.charAt(i) != s1.charAt((i+2)%sLength)){
                leftRotateValid = false;
                break;
            }
        }

        return rightRotateValid || leftRotateValid;
    }



    /**
     * Live breakdown
     *  A B S G
     * See: Backtracking
     * */
    public static ArrayList<String> findPermutation(String s) {
        // Code here
        // Todo: Add implementation
        return new ArrayList<>();
    }

    private static String swap(String s, int i, int j){
        if(i < 0 || i >= s.length() || j < 0 || j >= s.length()) return "";
        char[] sChars = s.toCharArray();
        char temp = sChars[i];
        sChars[i] = sChars[j];
        sChars[j] = temp;
        return new String(sChars);
    }



    /**
     * Live breakdown
     * I = 1, V = 5, X = 10, L = 50, C = 100, D = 500, M = 1000
     *
     * IV, IX, XL, XC, CD, CM
     * 4,  9,  40, 90 400 900
     *
     * CMXCXLIX
     *
     * III => 3
     * MIII=>1000 + 1 + 1 + 1
     * IV => 5 - 1 => 4
     * IX => 10 - 1 => 9
     * CD => 500 - 100 => 400
     *
     * M D C L X V I
     *
     * MMDCCCXCIII
     *
     * Solution 1: O(1) time, O(1) space since the space
     * isn't growing with the input size
     * Key: Anytime the numerals are out of order, we deduct from the result
     * before adding the original value.
     * */
    // Finds decimal value of a given roman numeral
    public int romanToDecimal(String s) {
        // code here - OPTIMAL
        Map<Character, Integer> map = new HashMap<>();
        map.put('M', 1000);
        map.put('D', 500);
        map.put('C', 100);
        map.put('L', 50);
        map.put('X', 10);
        map.put('V', 5);
        map.put('I', 1);

        int result = 0;
        for(int i=0; i<=s.length()-1; i++){
            int numeral = map.get(s.charAt(i));
            int next = (i <= s.length()-2) ? map.get(s.charAt(i+1)) : 0;

            if(numeral < next) result -= numeral;
            else result += numeral;
        }
        return result;
    }

    public int romanToDecimal1(String s) {
        // code here
        int result = 0;
        int i = 0;
        while(i<s.length()){
            char numeral = s.charAt(i);
            if(numeral == 'M'){
                result += 1000;
            }else if(numeral == 'D'){
                result += 500;
            }else if(numeral == 'C'){
                if(i < s.length()-1 && s.charAt(i+1) == 'M'){
                    result += 900;
                    i++;
                }else if(i < s.length()-1 && s.charAt(i+1) == 'D'){
                    result += 400;
                    i++;
                } else result += 100;
            }else if(numeral == 'L'){
                result += 50;
            }else if(numeral == 'X'){
                if(i < s.length()-1 && s.charAt(i+1) == 'C'){
                    result += 90;
                    i++;
                }else if(i < s.length()-1 && s.charAt(i+1) == 'L'){
                    result += 40;
                    i++;
                } else result += 10;
            }else if(numeral == 'V'){
                result += 5;
            }else{
                if(i < s.length()-1 && s.charAt(i+1) == 'X'){
                    result += 9;
                    i++;
                }else if(i < s.length()-1 && s.charAt(i+1) == 'V'){
                    result += 4;
                    i++;
                } else result += 1;
            }
            i++;
        }
        return result;
    }



    /**
     * Live Breakdown
     * geeksforgeeks
     * i, j = i + 1
     *
     * Solution 1: Brute force, O(n^2) time, O(n^2) space
     * Iterate through every index, find the longest substring at each index
     *  Store used variables in a set for each
     * Count the longest substring and compare with the global max longest substring
     * */
    int longestUniqueSubstring(String s) {
        // code here
        int longestSubstring = 0;

        int i=0;
        while(i < s.length()){
            Set<Character> set = new HashSet<>();
            set.add(s.charAt(i));
            int currentCount = 1;
            int j = i + 1;

            while(j < s.length() && !set.contains(s.charAt(j))){
                currentCount++;
                set.add(s.charAt(j));
                j++;
            }

            longestSubstring = Math.max(longestSubstring, currentCount);
            i++;
        }
        return longestSubstring;
    }
}
