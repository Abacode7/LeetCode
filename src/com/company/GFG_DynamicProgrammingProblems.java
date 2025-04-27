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
     *
     *
     */
    static int knapsack(int W, int val[], int wt[]) {
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
     * This allows for Selection, which comes with infinite supply
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
}
