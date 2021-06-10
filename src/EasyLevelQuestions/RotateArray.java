package EasyLevelQuestions;

import java.util.Arrays;

//NB: LC Question - 189
public class RotateArray {
    public static void main(String[] args){
        int[] input1 = {1,2,3,4,5,6,7};
        int k = 3;
        int[] input2 = {-1,-100,3,99};

        rotateArray(input1, k);
        System.out.println(Arrays.toString(input1));
    }

    /**
     Using Reverse

     [5. 9] k = 3
     => [9 5]

     [5 9 1]
     k = 4 or k = 1 [1 5 9]
     k = 5 or k = 2 [9 1 5]
     We can conclude that if
     k > nums.length then k = k % nums.length

     Steps
     1 - Reverse the whole array
     2 - Reverse the first k values
     3 - Reverse the last k values
     */

    // Runtime - O(n), Space - O(1)
    public static void rotateArray(int[] nums, int k) {
        k = k % nums.length;
        if(k == 0) return;

        reverse(nums, 0, nums.length - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, nums.length - 1);
    }


    public static void reverse(int[] nums, int startIndex, int endIndex){
        while(startIndex < endIndex){
            int temp = nums[startIndex];
            nums[startIndex] = nums[endIndex];
            nums[endIndex] = temp;

            startIndex++;
            endIndex--;
        }
    }

    /**
     Using Extra Array

     Idea is to place each element in their correct
     position in the new array
     */

    // Runtime - O(n), Space - O(n)
    public static void rotateArray2(int[] nums, int k){
        int[] newArray = new int[nums.length];
        for(int i = 0; i < nums.length; i++){
            newArray[(i+k) % nums.length] = nums[i];
        }

        for(int j = 0; j < newArray.length; j++){
            nums[j] = newArray[j];
        }
    }
}
