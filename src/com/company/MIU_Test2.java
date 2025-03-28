package com.company;

public class MIU_Test2 {
    /**
     * Question 2.
     * An array is rapidly increasing if each element (except the first one)
     * is greater than twice the sum of all its preceding elements.
     *
     * Write a method named isRapidlyIncreasing that returns 1 if its array argument is rapidly increasing.
     * Otherwise it returns 0.
     */
    public static void main(String[] args){
        isRapidlyIncreasing(new int[]{1, 3, 9, 27});
        isRapidlyIncreasing(new int[]{1, 3, 200, 500});
        isRapidlyIncreasing(new int[]{1});
        isRapidlyIncreasing(new int[]{1, 3, 9, 26});
        isRapidlyIncreasing(new int[]{1, 3, 7, 26});
        isRapidlyIncreasing(new int[]{1, 3, 8, 26});
    }

    public static int isRapidlyIncreasing(int[] a){
        int arrayLength = a.length;
        if(arrayLength == 0) return 0; // Arrays with no element cannot be rapidly increasing
        if(arrayLength == 1) return 1; // Arrays with one element are rapidly increasing

        int precedingSum = a[0];
        for(int i=1; i<arrayLength; i++){
            if(a[i] <= 2 * precedingSum) return 0;
            precedingSum += a[i];
        }
        return 1;
    }
}
