package com.company;

public class AE_PalindromeCheck {
    public static boolean isPalindrome(String str) {
        // Write your code here.
        int n = str.length();
        int i=0, j=n-1;
        while(i < j){
            if(str.charAt(i) != str.charAt(j)) return false;
            i++;
            j--;
        }
        return true;
    }
}
