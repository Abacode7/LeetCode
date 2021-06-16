package EasyLevelQuestions;

// LC Question 7
public class ReverseInteger {
    public static void main(String[] args){
        int input = -123;
        int input2 = 54321;
        System.out.println(reverse(input));
    }

    public static int reverse(int x) {
        int unit = 0;
        int result = 0;

        while(x != 0){
            unit = x % 10;
            x = x / 10;

            /*
            -2.....48       -2.....40       2.....40        2.....47
            [MinValue       MinValue/10     MaxValue/10     MaxValue]
            -2....40   if unit < -8

            */

            // Possible overflow condition
            if(result > Integer.MAX_VALUE/10 ||(result == Integer.MAX_VALUE/10 && unit > 7) ||
                    result < Integer.MIN_VALUE/10 ||(result == Integer.MIN_VALUE/10 && unit < -8)) return 0;

            result = (result * 10) + unit;
        }

        return result;
    }

    // String solution
    public static int reverseIntegerWithString(int x){
        String reversed = new StringBuilder().append(Math.abs(x)).reverse().toString();
        try {
            return (x < 0) ? Integer.parseInt(reversed) * -1 : Integer.parseInt(reversed);
        } catch (NumberFormatException e) {
            return 0;
        }
    }
}
