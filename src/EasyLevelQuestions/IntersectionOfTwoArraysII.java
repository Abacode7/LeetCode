package EasyLevelQuestions;

import java.util.*;

// LC Question - 350
public class IntersectionOfTwoArraysII {
    public static void main(String[] args){
        int[] nums1 = {4,9,5};
        int[] nums2 = {9,4,9,8,4};
        System.out.println(Arrays.toString(intersectImproved(nums1, nums2)));
    }

    public static int[] intersect(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int[] result = new int[Math.min(nums1.length, nums2.length)];
        int i = 0;
        int j = 0;
        int k = 0;

        while(i<nums1.length && j<nums2.length){
            if(nums1[i] < nums2[j]) i++;
            else if(nums1[i] > nums2[j]) j++;
            else{
                result[k] = nums1[i];
                i++;
                j++;
                k++;
            }
        }

        return Arrays.copyOfRange(result, 0, k);
    }

    // Improved
    // NB: Improved by storing result in any of the input arrays, since
    // both i & j indexes are always ahead of k
    public static int[] intersectImproved(int[] nums1, int[] nums2) {
        Arrays.sort(nums1);
        Arrays.sort(nums2);

        int i = 0, j = 0, k = 0;

        while(i<nums1.length && j<nums2.length){
            if(nums1[i] < nums2[j]) i++;
            else if(nums1[i] > nums2[j]) j++;
            else{
                nums1[k++] = nums1[i++];
                j++;
            }
        }

        return Arrays.copyOfRange(nums1, 0, k);
    }
}
