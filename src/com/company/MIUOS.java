package com.company;

import java.util.Arrays;

public class MIUOS {

    public static void main(String[] args){
        int[] input1 = {1, 2, 3, 4};
        int[] input2 = {4, 1, 2, 3};
        int[] input3 = {1, 1, 2, 2,};
        int[] input4 = {1, 1};
        int[] input5 = {};

        System.out.println(f(input5));
    }

    public static int f(int[] a){
        int arrayLength = a.length;
        if(arrayLength <= 1) return -1;

        Arrays.sort(a);
        int largestInteger = a[arrayLength - 1];

        for(int i = arrayLength - 2; i >= 0; i--){
            if(a[i] < largestInteger) return a[i];
        }
        return -1;
    }
}
