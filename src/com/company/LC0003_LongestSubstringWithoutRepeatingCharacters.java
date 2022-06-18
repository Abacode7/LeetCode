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
}
