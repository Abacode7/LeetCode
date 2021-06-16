package EasyLevelQuestions;

import java.util.Arrays;

// LC Question 344
public class ReverseString {
    public static void main(String[] args){
        char[] input  = {'a', 'b', 'a', 'c', 'u', 's'};
        reverseString(input);
        System.out.println(Arrays.toString(input));
    }

    // Runtime O(n), Space O(1)
    public static void reverseString(char[] s) {
        int len = s.length;
        for(int i=0; i<len/2; i++){
            char temp = s[i];
            s[i] = s[len-1-i];
            s[len-1-i] = temp;
        }
    }
}
