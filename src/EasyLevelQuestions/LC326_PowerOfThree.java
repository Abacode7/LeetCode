package EasyLevelQuestions;

public class LC326_PowerOfThree {
    public static void main(String[] args){
        int input = 243;
        System.out.println(isPowerOfThree(input));
    }


//    public static boolean isPowerOfThree(int n) {
//        double result = Math.log(n) / Math.log(3);
//        return result % 1 == 0;
//    }

    // Using base10 instead cos there might be precision errors
    // when using ln (natural logarithm)
    // Runtime O(n), Space O(n)
    public static boolean isPowerOfThree(int n) {
        double result = Math.log10(n) / Math.log10(3);
        return result % 1 == 0;
    }
}
