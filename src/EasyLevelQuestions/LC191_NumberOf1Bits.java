package EasyLevelQuestions;

public class LC191_NumberOf1Bits {
    public static void main(String[] args){
        int input = 11; // ans = 3
        System.out.println(hammingWeight(input));
    }

    // Runtime O(1), Space O(1)
    // Using bit mask and shifting
    public static int hammingWeight(int n) {
        int count = 0;
        int one = 1;
        for(int i=0; i<32; i++){
            int mask = one << i;
            if((n & mask) != 0) count++;
        }
        return count;
    }
}
