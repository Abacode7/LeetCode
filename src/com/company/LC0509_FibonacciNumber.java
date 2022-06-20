package com.company;

public class LC0509_FibonacciNumber {
    public static void main(String[] args){
        System.out.println(fibonacci(5));
    }

    public static int fib(int n) {
        if(n == 0) return 0;
        if(n == 1) return 1;

        int f0 = 0, f1 = 1;
        int result = 0;;
        int i = 2;
        while(i <= n){
            result = f0 + f1;
            f0 = f1;
            f1 = result;
            i++;
        }
        return result;
    }

    public static int fibonacci(int n){
        if(n == 0) return 0;
        if(n == 1) return 1;
        return fibonacci(n-1) + fibonacci(n-2);
    }
}
