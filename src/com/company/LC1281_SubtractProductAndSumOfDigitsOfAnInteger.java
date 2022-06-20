package com.company;

public class LC1281_SubtractProductAndSumOfDigitsOfAnInteger {
    public static void main(String[] args){
        int n = 234;
        System.out.println(subtractProductAndSum(n));
    }
    public static int subtractProductAndSum(int n) {
        int product = 1;
        int sum = 0;
        while(n != 0){
            int unit = n % 10;
            sum += unit;
            product *= unit;
            n /= 10;
        }
        return product - sum;
    }
}
