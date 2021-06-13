package EasyLevelQuestions;

import java.util.Arrays;

// LC Question 66
public class PlusOne {
    public static void main(String[] args){
        int[] input = {4, 2, 1, 7};
        int[] input2 = {9, 9, 9};
        System.out.println(Arrays.toString(plusOne(input)));
    }

    // Runtime O(n), Space O(n)
    public static int[] plusOne(int[] digits) {
        int i = digits.length - 1;

        while(i >= 0){
            if(digits[i] != 9){
                digits[i]++;
                return digits;
            }else{
                digits[i] = 0;
            }
            i--;
        }

        int[] result = new int[digits.length + 1];
        result[0] = 1;
        return result;
    }
}
