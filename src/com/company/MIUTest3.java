package com.company;

public class MIUTest3 {
    /**
     * Question 1.
     * Write a function named hasSingleMaximum that takes an array argument and returns 1
     * if the maximum value in its array argument occurs exactly once in the array, otherwise it returns 0.
     */
    public static void main(String[] args){
        hasSingleMaximum(new int[]{1, 2, 3, 1, 0});
        hasSingleMaximum(new int[]{18});
        hasSingleMaximum(new int[]{1, 2, 3, 0, 1, 3});
        hasSingleMaximum(new int[]{13, 1, 13, 2, 13, 0, 13, 1, 13});
        hasSingleMaximum(new int[]{});
        hasSingleMaximum(new int[]{-6, -6, -6, -6, -6, -6, -6});
    }

    public static int hasSingleMaximum(int[] a){
        if(a.length == 0) return 0;

        int maxValue = Integer.MIN_VALUE;
        int maxValueOccurrence = 0;

        for(int arrayValue: a){
            if(arrayValue == maxValue) maxValueOccurrence++;

            if(arrayValue > maxValue){
                maxValue = arrayValue;
                maxValueOccurrence = 1;
            }
        }

        if(maxValueOccurrence > 1) return 0;
        return 1;
    }
}
