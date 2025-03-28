package com.company;

public class MIU_Test1 {

    /**
     * Question 3.
     * Define the weighted sum of an array to be 2 * the sum of the even valued elements + 3 * the sum
     * of the odd valued elements.
     *
     * Write a method named computeWeightedSum that computes the weighted sum of its argument.
     */
    public static void main(String[] args){
        computeWeightedSum(new int[]{1, 2, 3, 4, 5});
        computeWeightedSum(new int[]{1, 3, 5});
        computeWeightedSum(new int[]{2, 4, 6});
        computeWeightedSum(new int[]{1});
        computeWeightedSum(new int[]{2});
        computeWeightedSum(new int[]{0, 0, 0, 0, 0});
    }

    public static int computeWeightedSum(int[] a){
        if(a.length == 0) return 0;

        int evenSum = 0, oddSum = 0;
        for(int arrayValue: a){
            if(arrayValue % 2 == 0){
                evenSum += arrayValue;
            }else {
                oddSum += arrayValue;
            }
        }

        return 2 * evenSum + 3 * oddSum;
    }
}
