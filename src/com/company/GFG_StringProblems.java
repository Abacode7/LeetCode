package com.company;

import java.util.*;

public class GFG_StringProblems {
    public static void main(String[] args){
        System.out.println(longestUniqueSubstring2("abcbadbd"));
    }

    /**
     * Validate an IP Address
     *
     * - Split on \\.
     * - Parse & validate number between 0 & 255
     * - Validate leading zeros
     *      - If number is non-zero, it should have no leading zero
     *      - If number is zero, same. It should contain only one zero
     * - Validate no ip starts with zero 0
     *      NB: The only exception to this condition is 0.0.0.0
     *      which should be checked and returned early.
     * */
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
     * Solution: O(n) time, O(1) space - OPTIMAL
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
     * Is Rotated (Simpler)
     *      * amazon | azonam | onamaz
     *      * Value  |Left Rotate| Right Rotate
     *
     * Given s1 is length n & s2 is length m
     * Solution:
     *      * Rotate s1 left and right:
     *      * Use substring to cut & join at beginning/end based on rotation
     *      * Creating new strings of s1, take n space
     *
     * Complexity:
     *      Time: O(n) time
     *      Space:O(n) space
     * */
    public static boolean isRotatedSimpler(String s1, String s2) {
        // Your code here
        if(s1.length() != s2.length()) return false;
        if(s1.length() <= 2 && !s1.equals(s2)) return false;


        int s1Length = s1.length();
        String s1LeftRotate = s1.substring(2, s1Length) + s1.substring(0, 2);

        if(s1LeftRotate.equals(s2)) return true;

        String s1RightRotate = s1.substring(s1Length-2, s1Length) + s1.substring(0, s1Length-2);

        if(s1RightRotate.equals(s2)) return true;

        return false;
    }




    /**
     * Live breakdown
     *                ABC - 3 POSITIONS
     *       ABC      BAC      CBA - 2 POSITIONS
     *   ABC  ACB    BAC BCA   CBA CAB  - 1 POSITION
     *
     *
     *                  ABC  - 3 POSITIONS
     *       ABC        ACB         CBA  - 2 POSITIONS
     *   ABC   BAC      ACB CAB     CBA  BCA  -  1 POSITION
     *   NB: This does the same implementation, fixing the positions from the last value
     *   The code implementation uses this.
     *
     *   At each level, full length (3 positions), we swap the first position with all positions
     *   Now the first position is fixed, we reduce the length swap across the levels, till we have 1 free position
     *   NOTE:
     *   We have to back-track the characters in the string, when swap across a single level
     *
     *   Complexity: It's arrangement meaning we'll have
     * */
    public ArrayList<String> findPermutation(String s) {
        // Code here
        StringBuilder word = new StringBuilder(s);

        Set<String> wordPermutations = findPermutation(word, s.length());
        return new ArrayList<>(wordPermutations);
    }

    private Set<String> findPermutation(StringBuilder word, int wordLength) {
        // Code here
        Set<String> wordPermutations = new HashSet<>();
        if(wordLength == 1){
            wordPermutations.add(word.toString());
            return wordPermutations;
        }

        int lastLetterIndex = wordLength-1;
        for(int j=lastLetterIndex; j>=0; j--){
            swap(word, j, lastLetterIndex);
            Set<String> localPermutations = findPermutation(word, wordLength-1);
            wordPermutations.addAll(localPermutations);
            swap(word, j, lastLetterIndex);
        }
        return wordPermutations;
    }

    private void swap(StringBuilder word, int i, int j){
        char temp = word.charAt(i);
        word.setCharAt(i, word.charAt(j));
        word.setCharAt(j, temp);
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
     * abcbadbd
     * i, j = i + 1
     *
     * Solution 1: Brute force, O(n^2) time, O(n^2) space
     * Iterate through every index, find the longest substring at each index
     *  Store used variables in a set for each
     * Count the longest substring and compare with the global max longest substring
     *
     * Solution 2: Sliding Window, O(n) time, O(1) space since it grows wrt to n until
     * the 26th alphabet
     *
     * left | right
     * right is exploratory:
     * Iterate right, finding unique elements, calculating the longest substring
     * When right encounters a duplicate:
     *  increment left till the prior duplicate
     *  remove character occurrences while iterating the left
     *  increment both left and right
`     * */
    static int longestUniqueSubstring(String s) {
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

    static int longestUniqueSubstring2(String s) {
        // code here - OPTIMAL
        int left = 0, right = 0;
        int maxSubstring = 0;
        Set<Character> set = new HashSet<>();

        while(right < s.length()){
            if(!set.contains(s.charAt(right))){
                maxSubstring = Math.max((right - left + 1), maxSubstring);
                set.add(s.charAt(right));
            }else{
                while(s.charAt(left) != s.charAt(right)){
                    set.remove(s.charAt(left));
                    left++;
                }
                left++;
            }
            right++;
        }
        return maxSubstring;
    }



    /**
     * Are Anagrams
     *
     * geeks | kseeg
     * Using Sorting:
     * Time: O(nlogn + mlogm)
     * Space: O(n + m)
     *
     * Using Sorting Via Fixed Character Array
     * ASCII | Alphabets | Numbers are Fixed
     * We can sort them in linear time Using a Fixed Array
     *
     * Time: O(n + m)
     * Space: O(n + m)
     * */
    public static boolean areAnagrams(String s1, String s2) {
        // Your code here
        if(s1.length() != s2.length()) return false;

        String sortedS1 = sortString(s1);
        String sortedS2 = sortString(s2);

        return sortedS1.equals(sortedS2);
    }

    private static String sortString(String word){
        int[] charCounts = new int[26];

        for(char wordChar: word.toCharArray()){
            charCounts[wordChar - 'a']++;
        }

        StringBuilder stringBuilder = new StringBuilder();
        for(int i=0; i<26; i++){
            int charCount = charCounts[i];

            while(charCount > 0){
                char wordChar = (char) ('a' + i);
                stringBuilder.append(wordChar);
                charCount--;
            }
        }

        return stringBuilder.toString();
    }



    /**
     * Look & Say Pattern
     *
     * Intuition:
     * Like the problem statement, you have to look and say the previouse value
     * to get the next value.
     *
     * Complexity
     * Say the count is n, each term at most doubles in size 1 -> 11 -> 21 -> 1211 -> ... n times
     * The behaviour is tree like, with the height of the tree being n, hence we have:
     *      Time: O(2^n)
     *      Space: O(2^n), at each level we use store all values in a string builder which grows the higher we go
     *      the sum/max of all such space takes 2^n
     * */
    public String countAndSay(int n) {
        // code here
        String word = "1";
        for(int i=1; i<n; i++){
            word = lookAndSay(word);
        }
        return word;
    }

    private String lookAndSay(String word){
        int wordLength = word.length();
        StringBuilder stringBuilder = new StringBuilder();

        int i = 0;
        while(i < wordLength){
            int charCount = 1;
            int j = i;
            while(j < wordLength-1 && word.charAt(j) == word.charAt(j+1)){
                charCount++;
                j++;
            }

            stringBuilder.append(String.valueOf(charCount));
            stringBuilder.append(word.charAt(i));

            i = j+1;
        }

        return stringBuilder.toString();
    }



    /**
     * Remove To Form Anagram
     * c d d g k | g c d, k k, x
     * c d g k | g c d
     * 0 1 0 1
     *
     * c d g k | k k
     * 1 2 1 1.  1 1
     *
     * Intuition
     * NB: Removals can be from any/both strings
     * Goal is to count the letters at each index for both strings
     * - Remove the excess letters at each index
     * - Sum of the left overs is what needs to be removed.
     *
     * Time: O(n) time
     * Space: O(1) space
     *
     *
     * */
    public int remAnagrams(String s1, String s2) {
        // add code here.

        int[] s1Count = new int[26];
        int[] s2Count = new int[26];

        for(int i=0; i<s1.length(); i++) s1Count[s1.charAt(i) - 'a']++;

        for(int i=0; i<s2.length(); i++) s2Count[s2.charAt(i) - 'a']++;

        int numOfRemovals = 0;
        for(int i=0; i<26; i++){
            numOfRemovals += Math.abs(s1Count[i] - s2Count[i]);
        }

        return numOfRemovals;
    }




    /**
     * Longest Unique Substring
     *
     * [abcdef]
     * i=0
     * j=0
     * abcdefabcbb
     *
     * Intuiton: Using Two Pointers
     * - One to add unique values to the set
     * - The other to track the earliest unique value in the substring
     *  - If we find a duplicate:
     *      - Remove earliest unique value
     *      - Iterate earliest pointer
     *
     * NB: Operates like sliding window
     * Complexity:
     *      Time: O(n) time
     *      Space: O(n) space
     * */
    public int longestUniqueSubstr(String s) {
        // code here
        HashSet<Character> set = new HashSet<>();

        int i = 0;
        int j = 0;

        int longestSubstringLength = Integer.MIN_VALUE;
        while(j < s.length()){

            while(j < s.length() && !set.contains(s.charAt(j))){
                set.add(s.charAt(j));
                j++;
            }

            int substringLength = j - i;
            longestSubstringLength = Math.max(substringLength, longestSubstringLength);

            set.remove(s.charAt(i));
            i++;
        }
        return longestSubstringLength;
    }

}
