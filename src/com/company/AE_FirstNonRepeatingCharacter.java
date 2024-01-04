package com.company;

public class AE_FirstNonRepeatingCharacter {
    /**
     0 1 2 3 4 5 6
     a b c d c a f

     a  b  c   d   e   f  ...
     65 66 67  68  69  70
     0  1   2   3  4    5
     counts

     - Create array of length 26 size
     - Store counts of each char at index in the 26-length-array
     - the index could be calculated using: (character - 'a')
     i.e the index relative to ascii character 'a'
     - Iterate again to get the first index with counter 1

     - Time O(n), Space: O(1) because it does not grow with respect to the
     input size
     **/
    public int firstNonRepeatingCharacter(String string) {
        // Write your code here.
        int[] count = new int[26];
        for(char c: string.toCharArray()){
            count[c - 'a'] += 1;
        }

        for(int i=0; i<string.length(); i++){
            char c = string.charAt(i);
            if(count[c - 'a'] == 1) return i;
        }
        return -1;
    }
}
