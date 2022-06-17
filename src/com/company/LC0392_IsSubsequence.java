package com.company;

public class LC0392_IsSubsequence {
    public static void main(String[] args){
        System.out.println(isSubsequence("abc", "ahjbhhhcdd"));
    }

    public static boolean isSubsequence(String s, String t) {
        if(s.length() == 0) return true;
        if(t.length() == 0) return false;

        int tIndex=0, sIndex=0;
        while(tIndex < t.length()){
            if(t.charAt(tIndex) == s.charAt(sIndex)){
                sIndex++;
                if(sIndex == s.length()) return true;
            }
            tIndex++;
        }
        return false;
    }
}
