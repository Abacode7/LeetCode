package com.company;

public class LC0004_MedianOfTwoSortedArrays {
    public static void main(String[] args){
        int[] input1 = {1, 2}, input2 = {3, 4};
        System.out.println(medianOfTwoSortedArrays(input1, input2));
    }

    // Todo: Check solution for logarithmic time
    // Runtime: O(n)
    public static double medianOfTwoSortedArrays(int[] nums1, int[] nums2){
        int m = nums1.length;
        int n = nums2.length;

        if(m + n == 0) return -1;

        int midPoint = (m+n)/2 + 1;
        int count = 1;

        int i=0, j=0;

        int curr = m > 0 ? nums1[0] : nums2[0];
        int prev = curr;

        while((i < m || j < n) && count <= midPoint){
            prev = curr;
            if(i >= m){
                curr = nums2[j++];
                count++;
                continue;
            }
            if(j >= n){
                curr = nums1[i++];
                count++;
                continue;
            }

            if(nums1[i] <= nums2[j]){
                curr = nums1[i++];
            }else{
                curr = nums2[j++];
            }
            count++;
        }

        if((m+n) % 2 == 0){
            return (double) (prev + curr)/2;
        }

        return curr;
    }
}
