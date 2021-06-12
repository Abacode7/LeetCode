package EasyLevelQuestions;

// LC Question 136
public class SingleNumber {

    public static void main(String[] args){
        int[] nums = {4,1,2,1,2};
        System.out.println(singleNumber(nums));
    }

    /*
    We use the properties of XOR here
    a ^ a = 0, a ^ 0 = a

    Using this an XOR operation of all values with
    duplicates in the array yields 0, leaving the
    singular value
     */
    public static int singleNumber(int[] nums) {
        int result = 0;
        for(int value: nums){
            result ^= value;
        }
        return result;
    }
}
