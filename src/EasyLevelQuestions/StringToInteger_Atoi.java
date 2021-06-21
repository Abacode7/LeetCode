package EasyLevelQuestions;

// LC Question 8
public class StringToInteger_Atoi {
    public static void main(String[] args){
        String input1 = "42"; //42
        String input2 = "   -42"; //-42
        String input3 = "words and 987"; //0
        String input4 = "-91283472332"; //-91283472332

        System.out.println(myAtoi(input2));
    }

    // Runtime - O(n), Space - O(1)
    public static int myAtoi(String s) {
        s = s.trim();
        if(s.length() == 0) return 0;

        boolean isPositive = true;
        int i = 0;

        if(Character.isLetter(s.charAt(i))) return 0;
        if(s.charAt(i) == '-') {
            isPositive = false;
            i++;
        }else if(s.charAt(i) == '+') i++;

        int result = 0;
        while(i < s.length() && Character.isDigit(s.charAt(i))){
            int intValue = Character.getNumericValue(s.charAt(i));
            if(!isPositive) intValue *= -1; // Make each unit carry the sign

            // Check for overflow
            // Integer max value is [-2....8    2......7]
            if(result > Integer.MAX_VALUE/10 || (result == Integer.MAX_VALUE/10 && intValue > 7)){
                result = Integer.MAX_VALUE;
                break;
            }

            if(result < Integer.MIN_VALUE/10 || (result == Integer.MIN_VALUE/10 && intValue < -8)){
                result = Integer.MIN_VALUE;
                break;
            }

            result = result * 10 + intValue;
            i++;
        }

        return result;
    }
}
