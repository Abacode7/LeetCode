package com.company;

import java.util.HashSet;
import java.util.Set;

public class LC0003_LongestSubstringWithoutRepeatingCharacters {
    public static void main(String[] args){
        String s = "abcabcbb";
        System.out.println(lengthOfLongestSubstring(s));
    }

    public static int lengthOfLongestSubstring(String s) {
        if(s.length() == 0) return 0;

        int maxSubstringLen = Integer.MIN_VALUE;

        for(int i=0; i<s.length(); i++){
            Set<Character> set = new HashSet<>();
            set.add(s.charAt(i));
            int localMax = 1;

            for(int j=i+1; j<s.length(); j++){
                char currentValue = s.charAt(j);
                if(set.contains(currentValue)) break;
                set.add(currentValue);
                localMax++;
            }

            maxSubstringLen = Math.max(maxSubstringLen, localMax);
        }

        return maxSubstringLen;
    }

    /**
     Solution 2
     Runtime: O(N^2), for each element we iterate through it's consecutive elements
     Space: O(N), for the character array created
     Tag: Arrays
     */
    public static int lengthOfLongestSubstring2(String s) {
        char[] sChars = s.toCharArray();
        int sLength = sChars.length;

        int result = 0;
        int i = 0;
        while(i < sLength){
            Set<Character> set = new HashSet<>();
            set.add(sChars[i]);
            int j = i + 1;
            int count = 1;

            while(j < sLength && !set.contains(sChars[j])){
                count++;
                set.add(sChars[j]);
                j++;
            }

            result = Math.max(result, count);
            i++;
        }

        return result;
    }
}
