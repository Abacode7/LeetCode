package EasyLevelQuestions;

import java.util.Arrays;

public class LC88_MergeSortedArray {
    public static void main(String[] args){
        int[] nums1 = {1,2,3,0,0,0};
        int m = 3, n = 3;
        int[] nums2 = {2,5,6};

        merge(nums1, m, nums2, n);
        System.out.println(Arrays.toString(nums1));
    }

    // Runtime O(m+n), going through the length of array nums1
    // Space O(1) no extra space used.
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        // Let p1 and p2 denote indexes of nums1 and nums2
        // Starting from their last values
        // if nums[p2] > nums[p1] then nums[p2] is the largest
        // number and vice versa
        // We also use pointer i as the pointer to the writing position

        // Notice the edge case where m = 0, i.e array nums1 has no presorted values
        // we make sure p1 is always greater than 0

        int p1 = m-1;
        int p2 = n-1;
        int i = m+n-1;

        while(p2 >= 0){
            if(p1 >= 0 && nums1[p1] > nums2[p2]){
                nums1[i--] = nums1[p1--];
            }else {
                nums1[i--] = nums2[p2--];
            }
        }
        return;
    }
}
