package EasyLevelQuestions;

public class LC461_HammingDistance {
    public static void main(String[] args){
        int a = 1, b = 4;
        System.out.println(hammingDistance(a, b));
    }

//    The Hamming distance between two integers is the number of positions
//    at which the corresponding bits are different
    // Runtime O(1), Space O(1)
    public static int hammingDistance(int x, int y) {
        // Using exclusiveOr
        int z = x ^ y;
        int count = 0;
        for(int i=0; i<32; i++){
            int mask = 1 << i;
            if((z & mask) != 0) count++;
        }
        return count;
    }
}
