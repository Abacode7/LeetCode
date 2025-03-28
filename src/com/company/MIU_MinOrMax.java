package com.company;

public class MIU_MinOrMax {

    public static void main(String[] args){
        System.out.println(power(5, 2));
        System.out.println(power(3, 3));
        System.out.println(power(2, 5));
    }

    public static int power(int a, int b){
        int product = 1;
        for(int i=0; i<b; i++) product *= a;
        return product;
    }

    /**
     * Given 2 ^ 0.25
     * => 2 ^ (25/100)
     * => 2 ^ (25 * 1/100)
     * => Math.sqrt(2^25, 100)
     *
     * Given 2 ^ 2.25
     * => 2 ^ (2 + 0.25)
     * => (2 ^ 2) * (2 ^ 0.25)
     */
    public static int power(double a, double b){
        return 0;
    }
}
