package com.company;

import java.util.Arrays;

public class MIU_ReverseArray {

    public static void main(String[] args){
        System.out.println(Arrays.toString(reverseArray(new int[]{1, 2, 3, 4, 5})));
        System.out.println(Arrays.toString(reverseArray(new int[]{1, 2, 3, 4})));
    }

    public static int[] reverseArray(int[] numbers){
        int numLength = numbers.length;
        for(int i = 0; i<numLength/2; i++){
            int temp = numbers[i];
            numbers[i] = numbers[numLength-1-i];
            numbers[numLength-1-i] = temp;
        }
        return numbers;
    }
}
