package com.company;

public class AE_MinNumberOfCoinChange {
    public static void main(String[] args){
        System.out.println(minNumberOfCoinsForChange(3, new int[]{1, 2}));
    }
    /**

     n = 7
     1 5 10

     7
     6         2     -3
     5 1 -4

     0{1}

     Test case
     3 [1, 2]
     1 1 1
     1 2 {2 1}

     Example
     n  currentDenom
     3, 0 [1, 2]
     2, 1
     1, 1
     0, 1 return 1;


     **/
    public static int minNumberOfCoinsForChange(int n, int[] denoms) {
        // Write your code here.
        if(n == 0) return 0;
        return minNumberOfCoinsForChange(n, denoms, 0);
    }

    public static int minNumberOfCoinsForChange(int n, int[] denoms, int currentDenom){
        if(n < 0) return -1;
        if(n == 0) return 1;

        int min = Integer.MAX_VALUE;
        for(int denom: denoms){
            if(denom < currentDenom || denom > n) continue;
            int minCoinChange = minNumberOfCoinsForChange(n - denom, denoms, denom);
            if(minCoinChange > 0) min = Math.min(min, minCoinChange);
        }

        if(min == Integer.MAX_VALUE){
            return -1;
        }

        // We do not want to count the case for currentDenom at zero
        return currentDenom == 0 ? min : min + 1;
    }
}
