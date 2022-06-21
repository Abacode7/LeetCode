package com.company;

public class LC0125_ValidPalindrome {
    public static void main(String[] args){
        String input = "race a car";
        String input2 = "A man, a plan, a canal: Panama";
        System.out.println(isPalindrome(input2));
    }
    public static boolean isPalindrome(String s) {
        String lowercase = s.toLowerCase();

        int i = 0, j = lowercase.length() - 1;
        while(i < j) {
            char iChar = lowercase.charAt(i);
            char jChar = lowercase.charAt(j);

            if(!Character.isLetterOrDigit(iChar)){
                i++;
                continue;
            }
            if(!Character.isLetterOrDigit(jChar)){
                j--;
                continue;
            }

            if(iChar != jChar) return false;
            i++;
            j--;
        }

        return true;
    }
}
