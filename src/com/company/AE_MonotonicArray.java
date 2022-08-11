package com.company;

public class AE_MonotonicArray {

    // Solution 1
    public static boolean isMonotonic(int[] array) {
        boolean nonIncreasing = true;
        boolean nonDecreasing = true;
        int i = 1;
        while(i < array.length){
            if(array[i-1] < array[i]) nonIncreasing = false;

            if(array[i-1] > array[i]) nonDecreasing = false;
            i++;
        }
        return nonIncreasing || nonDecreasing;
    }

    // Solution 2
    public static boolean isMonotonic2(int[] array) {
        // Write your code here.
        return isNonIncreasing(array) || isNonDecreasing(array);
    }

    private static boolean isNonIncreasing(int[] array){
        boolean nonIncreasing = true;
        int i = 1;
        while(i < array.length){
            if(array[i-1] < array[i]) {
                nonIncreasing = false;
                break;
            }
            i++;
        }
        return nonIncreasing;
    }

    private static boolean isNonDecreasing(int[] array){
        boolean nonDecreasing = true;
        int i = 1;
        while(i < array.length){
            if(array[i-1] > array[i]) {
                nonDecreasing = false;
                break;
            }
            i++;
        }
        return nonDecreasing;
    }
}
