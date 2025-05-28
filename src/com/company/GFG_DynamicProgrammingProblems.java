package com.company;

import java.util.*;

@SuppressWarnings("ALL")
public class GFG_DynamicProgrammingProblems {
    public static void main(String[] args){
        System.out.printf("Knapsack Problem: %s\n", knapsack(4, new int[]{1, 2, 3}, new int[]{4, 5, 1}));

        System.out.printf("Knapsack Problem: %s\n", knapsack(3, new int[]{1, 2, 3}, new int[]{4, 5, 6}));

        System.out.printf("Knapsack Problem: %s\n", knapsack(5, new int[]{10, 40, 30, 50}, new int[]{5, 4, 2, 3}));

        System.out.printf("Possible Weight Combinations: %s\n", findPossibleWeightCombinations(11, new int[]{1, 5, 6}, 3));


        System.out.printf("Equal Partition Problem: %s\n", equalPartition(new int[]{5, 1, 5, 11}));

        System.out.printf("Can Construct: %s\n", canConstruct(4, new int[]{1,2,3}, 3));
    }

    /**
     * - Find the different weights combinations <= W
     * - Find the value for which it is maximised
     *
     *  W = 4 and wt = [4, 5, 1]
     *           val = [1, 2, 3]
     *
     *
     *                          4{4, 5, 1}
     *            4{4, 5}()                     3{4,5}(1)
     *     4{4}()                                      3{4}()
     *
     * 4{}()   0{}(4)                                  3{}()
     *
     *
     * dp(W, n) = dp(W, n-1) OR dp(W-arr[n-1], n-1)
     *
     * Converting Forward (to be used in tabular format)
     * Making dp(W, n-1) be dp(W, n), then it contributes to:  dp(W, n+1)
     * Making dp(W-arr[n-1], n-1) be dp(W,n), then it contributes to:  dp(W+arr[n-1],n+1)
     * dp(W, n) => dp(W, n+1) + dp(W+arr[n-1,n+1)
     *
     *
     * Tag: Selection, Finite
     */

    static int knapsack(int W, int val[], int wt[]) {
        // code here
        int wtLength = wt.length;
        int[][] memo = new int[W+1][wt.length+1];

        for(int[] row: memo){
            Arrays.fill(row, -1);
        }
        return knapsack(W, val, wt, wtLength, memo);
    }

    static int knapsack(int W, int val[], int wt[], int wtLength, int[][] memo) {
        // code here
        if(W == 0 || wtLength == 0) return 0;
        if(memo[W][wtLength] != -1) return memo[W][wtLength];

        int valueWithoutWeight = knapsack(W, val, wt, wtLength-1, memo);

        int valueWithWeight = 0;
        if(wt[wtLength-1] <= W){
            valueWithWeight = knapsack(W-wt[wtLength-1], val, wt, wtLength-1, memo);
            valueWithWeight += val[wtLength-1];
        }

        memo[W][wtLength] = Math.max(valueWithoutWeight, valueWithWeight);
        return memo[W][wtLength];
    }

    /**
     * - Find the different weights combinations <= 4
     * - Find the value for which it is maximised
     *
     *  W = 4 and wt = [4, 5, 1]
     *
     *              4{1, 4, 5}
     *              4{1, 4}
     *      4{1}              0{1}
     *  4{}    3{}
     *
     * This solution works, but takes extra memory in saving all possible paths first before finding the
     */
    static int knapsackOne(int W, int val[], int wt[]) {
        // code here
        List<List<Integer>> weightCombinations = findPossibleWeightCombinations(W, wt, wt.length);
        if(weightCombinations.isEmpty()) return 0;

        Map<Integer, Integer> weightToValue = mapWeightToValue(val, wt);

        int maxWeightValue = Integer.MIN_VALUE;

        for(List<Integer> weightCombination: weightCombinations){
            int valueSum = 0;
            for(int weight: weightCombination){
                valueSum += weightToValue.get(weight);
            }
            maxWeightValue = Math.max(maxWeightValue, valueSum);
        }

        return maxWeightValue;
    }

    /** Find all possible selection for a sum of value <= target */
    static List<List<Integer>> findPossibleWeightCombinations(int W, int[] wt, int wtLength){
        List<List<Integer>> weightCombinations = new ArrayList<>();
        if(W == 0 || wtLength == 0) {
            weightCombinations.add(new ArrayList<>());
            return weightCombinations;
        }

        List<List<Integer>> firstSubWeights = findPossibleWeightCombinations(W, wt, wtLength-1);
        weightCombinations.addAll(firstSubWeights);

        if(wt[wtLength-1] <= W){
            List<List<Integer>> secondSubWeights = findPossibleWeightCombinations(W-wt[wtLength-1], wt, wtLength-1);

            for(List<Integer> secondSubWeight: secondSubWeights){
                secondSubWeight.add(wt[wtLength-1]);
                weightCombinations.add(secondSubWeight);
            }
        }

        return weightCombinations;
    }

    private static Map<Integer, Integer> mapWeightToValue(int[] val, int[] wt){
        Map<Integer, Integer> weightToValue = new HashMap<>();

        for(int i=0; i<wt.length; i++){
            weightToValue.put(wt[i], val[i]);
        }

        return weightToValue;
    }




    /**
     *
     * Intuition,
     * Since we're given whole numbers, if the total sum of the array is odd, we cant have equal sum partitions
     * If total sum is even, each partition has to be exactly totalSum/2
     *
     * The goal is to find we can make the target = totalSum/2, from the array of numbers
     *
     *  1, 5, 11, 5 => 22/2 => 11 in this case
     *          [1, 5, 11, 5]
     *              11
     *     10(-1) 6(-5) 0(-11) 6(-5)
     *
     *    dp(target,n) = dp(target,n-1) OR dp(target-arr[n-1],n-1)
     *
     * Tag: Selection, Finite
     * */
    static boolean equalPartition(int arr[]) {
        // code here
        List<Integer> arrayList = new ArrayList<>();

        int arraySum = 0;
        for(int num: arr) {
            arraySum += num;
            arrayList.add(num);
        }

        if(arraySum % 2 != 0) return false;

        int arrayTarget = arraySum / 2;

        return canConstruct(arrayTarget, arr, arr.length);
    }

    static boolean canConstruct(int target, int[] arr, int arrLength){
        if(target == 0) return true;
        if(arrLength == 0) return false;

        if(arr[arrLength-1] > target){
            return canConstruct(target, arr, arrLength-1);
        }

        return canConstruct(target-arr[arrLength-1], arr, arrLength-1) || canConstruct(target, arr, arrLength-1);
    }

    private static boolean canConstructMemo(int target, int[] arr, int arrLength, int[][] memo){
        if(target == 0) return true;
        if(arrLength == 0) return false;

        if(memo[target][arrLength] != -1) return memo[target][arrLength] == 1;

        if(arr[arrLength-1] > target){
            boolean canConstruct = canConstructMemo(target, arr, arrLength-1, memo);
            memo[target][arrLength] = canConstruct ? 1 : 0;
            return canConstruct;
        }

        boolean canConstruct = canConstructMemo(target-arr[arrLength-1], arr, arrLength-1, memo) || canConstructMemo(target, arr, arrLength-1, memo);
        memo[target][arrLength] = canConstruct ? 1 : 0;
        return canConstruct;
    }




    /**
     * This allows for Selection, which comes with infinite supply.
     * Tag: Selection, Infinite
     */
    public int countCoinChange(int coins[], int sum) {
        // code here.
        int[][] memo = new int[sum+1][coins.length+1];
        for(int[] row: memo){
            Arrays.fill(row, -1);
        }
        return countCoinChange(coins, coins.length, sum, memo);
    }

    private int countCoinChange(int[] coins, int coinsLength, int sum, int[][] memo){
        if(sum == 0) return 1;
        if(coinsLength == 0) return 0;

        if(memo[sum][coinsLength] != -1) return memo[sum][coinsLength];

        int totalWays = countCoinChange(coins, coinsLength-1, sum, memo);
        if(coins[coinsLength-1] <= sum){
            totalWays += countCoinChange(coins, coinsLength, sum-coins[coinsLength-1], memo);
        }

        memo[sum][coinsLength] = totalWays;
        return totalWays;
    }




    /**
     *               [100, 180, 260, 310, 40, 535, 695]
     *
     *                              root[100, 180, 260, 310, 40, 535, 695]
     *
     *  100[180, 260, 310, 40, 535, 695]           [180, 260, 310, 40, 535, 695]
     *
     * where n is the lenght of stock prices:
     *   dp(maxTotal,n) = d(+/-arr[i], n-1) OR d(n-1)
     *
     * The base case is when the arrays array is empty {}
     * We need to explore all paths, and at each problem we might either BUY/SELL a stock or IGNORE it
     *
     * We start from the front, given we must buy first before we sell.
     * At each point, we choose to buy/sell OR ignore.
     *      If we buy, the subproblem that follows it says sell
     *      If we ignore, the subproblem that follows that still holds on to the command (to buy in this case)
     *
     * Tag: Selection, Finite
     * */
    // Function to find the days of buying and selling stock for max profit.
    int stockBuySell(int arr[]) {
        // code here
        int arrLength = arr.length;

        int[][] memo = new int[arrLength+1][2];
        for(int[] row: memo){
            Arrays.fill(row, -1);
        }
        return stockBuySell(arr, 0, true, memo);
    }

    private int stockBuySell(int[] arr, int buyIndex, boolean toBuy, int[][] memo){
        if(buyIndex == arr.length) return 0;

        int toBuyNum = toBuy? 1 : 0;
        if(memo[buyIndex][toBuyNum] != -1) return memo[buyIndex][toBuyNum];


        int withoutStock = stockBuySell(arr, buyIndex+1, toBuy, memo);

        int withStock = stockBuySell(arr, buyIndex+1, !toBuy, memo);
        withStock += toBuy? -1 * arr[buyIndex] : arr[buyIndex];


        memo[buyIndex][toBuyNum] = Math.max(withoutStock, withStock);
        return memo[buyIndex][toBuyNum];
    }





    /**
     *  AAP | ACT => ACTAAP
     *  Every last (or first) character in the bigger string must equal the last char in one of the smaller strings
     *         In such cases we
     *  The case where we might have last char equal to the last char of both string1 and string2 then we need to explore
     *  both paths
     *
     *  The base case is if the bigger string is exhausted, since s1 + s2 = s3 then it means we've fully exhausted s1 and s2
     *
     *  NB: Check for indexes as you process them
     * */
    public boolean isInterLeave(String s1, String s2, String s3) {
        // code here
        if(s1.length() + s2.length() != s3.length()) return false;

        int[][] memo = new int[s1.length()+1][s2.length()+1];

        for(int[] row: memo) Arrays.fill(row, -1);

        return isInterLeave(s1, s2, s3, s1.length(), s2.length(), s3.length(), memo);
    }

    private boolean isInterLeave(String s1, String s2, String s3, int m, int n, int k, int[][] memo){
        if(k == 0) return true;
        if(memo[m][n] != -1) return memo[m][n] == 1;

        boolean isInterLeaved = false;
        if((m > 0 && s3.charAt(k-1) == s1.charAt(m-1)) &&
                (n > 0 && s3.charAt(k-1) == s2.charAt(n-1))){
            isInterLeaved = isInterLeave(s1, s2, s3, m-1, n, k-1, memo) || isInterLeave(s1, s2, s3, m, n-1, k-1, memo);
        }else if(m > 0 && s3.charAt(k-1) == s1.charAt(m-1)){
            isInterLeaved = isInterLeave(s1, s2, s3, m-1, n, k-1, memo);
        }else if(n > 0 && s3.charAt(k-1) == s2.charAt(n-1)){
            isInterLeaved = isInterLeave(s1, s2, s3, m, n-1, k-1, memo);
        }

        memo[m][n] = isInterLeaved ? 1 : 0;
        return isInterLeaved;
    }




    /**
     *  Given: ABCDE and ABE
     *
     * LCS(ABCDE, ABE) = 1 + LCS(ABCD, AB)
     *
     * LCS(ABCD, AB) = LCS(ABC, AB) OR LCS(ABCD, AB)
     *
     * if s1[i] == s2[j]: 1 + LCS(s1[m-1], s2[n-1])
     * else: LCS(s1[m-1], s2[n]) OR LCS(s1[m], s2[n-1])
     *
     * */
    static int lcs(String s1, String s2) {
        // code here
        int s1Length = s1.length();
        int s2Length = s2.length();

        int[][] memo = new int[s1Length+1][s2Length+1];
        for(int[] row: memo){
            Arrays.fill(row, -1);
        }

        return lcs(s1, s2, s1Length, s2Length, memo);
    }

    private static int lcs(String s1, String s2, int s1Length, int s2Length, int[][] memo){
        if(s1Length == 0 || s2Length == 0) return 0;

        if(memo[s1Length][s2Length] != -1) return memo[s1Length][s2Length];

        int lcsValue = 0;
        if(s1.charAt(s1Length-1) == s2.charAt(s2Length-1)){
            lcsValue = 1 + lcs(s1, s2, s1Length-1, s2Length-1, memo);
        }else{
            lcsValue = Math.max(lcs(s1, s2, s1Length, s2Length-1, memo), lcs(s1, s2, s1Length-1, s2Length, memo));
        }

        memo[s1Length][s2Length] = lcsValue;
        return lcsValue;
    }




    /**
     * coins [25, 10, 5], sum = 30
     *
     *                  30{25,10,5}
     *          30{25,10}.        25{25,10,5}
     *   30{25}.   20{25}.      25{25,10}   15{25,10,5}
     *
     *  *                  30{10,5,25}
     *          30{10,5}.          5{10,5,25}
     *                            5{10,5}.
     *                      5{10}.    0{10,5}
     *
     *      * 0.....30
     *      * [0...n-1] where n is the lenght of coins
     *      *
     *      * dp(sum,n) = dp(sum,n-1) OR dp(sum-arr[n-1], n)
     *
     *      Tag: Selection, Infinite
     * */
    public int minCoins(int coins[], int sum) {
        // code here
        int[][] memo = new int[sum+1][coins.length+1];
        for(int[] row: memo){
            Arrays.fill(row, -2);
        }
        return minCoins(coins, coins.length, sum, memo);
    }

    private int minCoins(int coins[], int coinsLength, int sum, int[][] memo) {
        // code here
        if(sum == 0) return 0;
        if(coinsLength == 0) return -1;

        if(memo[sum][coinsLength] != -2) return memo[sum][coinsLength];


        int minCoinsWithout = minCoins(coins, coinsLength-1, sum, memo);
        int minCoinsWith = -1;
        if(coins[coinsLength-1] <= sum){
            minCoinsWith = minCoins(coins, coinsLength, sum-coins[coinsLength-1], memo);
            if(minCoinsWith != -1) minCoinsWith += 1;
        }

        int minCoin = -1;
        if(minCoinsWith != -1 && minCoinsWithout != -1){
            minCoin = Math.min(minCoinsWith, minCoinsWithout);
        }else if(minCoinsWithout == -1){
            minCoin = minCoinsWith;
        }else{
            minCoin = minCoinsWithout;
        }

        memo[sum][coinsLength] = minCoin;
        return minCoin;
    }



    /**
     * Given and arr A with size n, we want to create a new array B by removing n/3 elements from A.
     * We want to get the maximum value possible from B if we say sum of first half of B - sum of second half of B,
     * without changing the order of items in A and B.
     *
     * Given: 1, 3, 4, 7, 5, 2
     * a solution is removing 1 and 3, so we have (4 + 7) - (5+2) = 4 as the answer.
     */


}
