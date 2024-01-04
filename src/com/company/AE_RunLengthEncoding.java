package com.company;

public class AE_RunLengthEncoding {
    /**
     Two pointers:
     i to hold position of current character
     j to explore and check values equal to that at i
     **/
    public String runLengthEncoding(String string) {
        // Write your code here.
        StringBuilder sb = new StringBuilder();
        int i=0;
        while(i < string.length()){
            int count = 1;
            int j = i+1;
            while(j < string.length() && count < 9 && string.charAt(i) == string.charAt(j)) {
                count++;
                j++;
            }
            sb.append(String.valueOf(count));
            sb.append(string.charAt(i));

            i = j;
        }
        return sb.toString();
    }
}
