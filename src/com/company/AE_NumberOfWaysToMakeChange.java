package com.company;

public class AE_NumberOfWaysToMakeChange {
    /**

     The key is the ordering
     For Permutation solution:
     - Let the sub problem amount be the outer loop
     - The denoms be the inner loop
     * This will propagate all number of ways from each sub-problem to the eventual problem

     For the Combination solution:
     - Let the denoms be the outer loop
     - The sub problem amounts be the inner loop
     * This will propagate ways for a particular denom unto the target amount early.

     Time: O(n*m), Space: O(n)
     **/

    /** Merely switching the iterations, that of denoms before amounts **/
    public static int numberOfWaysToMakeChange(int n, int[] denoms) {
        // Write your code here.
        int[] ways = new int[n+1];
        ways[0] = 1;

        for(int denom: denoms){
            for(int i=0; i<n; i++){
                int futureAmount = i + denom;
                if(futureAmount > n) break;
                ways[futureAmount] += ways[i];
            }
        }
        return ways[n];
    }

    // Todo: Solution uses permutation. Doesn't work
    /** Permutation Solution **/
     public static int numberOfWaysToMakeChange1(int n, int[] denoms) {
       // Write your code here.
       int[] ways = new int[n+1];
       ways[0] = 1;
       for(int i=0; i<n; i++){
         for(int denom: denoms){
           int futureAmount = i + denom;
           if(futureAmount <= n) ways[futureAmount] += ways[i];
         }
       }
       return ways[n];
     }

    /**
     Given: target = 6, denoms = [1, 5]
     To get this, we do:
     - subtract amount 1 on the left
     - subtract amount 5 on the right
            6
         5       1
       4   0   0    -4
     3  -1

     this gives:
     1 1 1 1 1 1 => 6
     1 5 => 6
     5 1 => 6

     this tree basically explores all permutation of the denominations,
     but that's not our desired output.
     We aim to count their combinations instead: hence we have to tweak our tree to enforce
     an order i.e instead of picking (1, 5) and (5, 1) we need it to pick only (1, 5).

     To enforce this, we say a tree cannot deduct a denomination lower than the first deduction.
     That is, if I deduct 5 as the initial amount, I must continue to deduct denominations 5 and above.

     Put simply:
     Deduct the caveat that whatsoever denomination we start deducting with down
     a path, we do not deduct less than that denomination down that path.

     So we have:
                    6
                5      1
              4   0       -4
            3  -1
          2 -2
        1 -3
      0 -4

     In this case: we get (1, 1, 1, 1, 1, 1) and (1, 5)

     Complexity Analysis:
     Given, target = m and length of denoms = n
     Tentatively the complexity is O(n ^ m), Space O(m)


     Dynamic Programming
     **/
    public static int numberOfWaysToMakeChange2(int n, int[] denoms) {
        // Write your code here.
        return numberOfWaysToMakeChange(n, denoms, 0);
    }

    public static int numberOfWaysToMakeChange(int n, int[] denoms, int dm) {
        // Write your code here.
        if(n < 0) return 0;
        if(n == 0) return 1;
        int numberOfWays = 0;
        for(int denom: denoms){
            if(denom < dm) continue;
            int target = n - denom;
            numberOfWays += numberOfWaysToMakeChange(target, denoms, denom);
        }
        return numberOfWays;
    }

    // Todo: Implementation does a Permutation.
    //  Does not work for this use case.
    /**

     Using Top-bottom approach
     n = 6
     denoms = 1 5

     left: we deduct 1
     right: we deduct 5

     base case: 0 target amount, return 1
     base case: negative target amount, return 0

                        6
                5           1
            4      0     0    -4
         3  -1
        2  -2
      1  -3
     0 -4

     Positive Cases:
     - 1 1 1 1 1 1
     - 1 5
     - 5 1


     Using Bottom-top approach
     Steps:
     - Create your sub-problem to problem array
        - Array from index 0 to target amount
     - Initialize the number of ways at amount 0 to 1
        - Since that's numberOfWays for the base case amount 0
     - Iterate through each amount:
        - Iterate through each denom in that amount
            - Compute Array[current amount + denom] += Array[current amount]
     - Return value of Array[target amount]

     demons [1, 5]
     At 6, the 1 values are gotten by:
     1 5
     5 1
     111111
     0    1    2    3    4    5    6 <- change (which are sub problems)
     1    1    1   1     1    2   1

     */
    public static int noOfWaysToMakeChange(int n, int[] denoms) {
        // Write your code here.
        if(n < 0) return 0;
        if(n == 0) return 1;
        int numberOfWays = 0;
        for(int denom: denoms){
            int target = n - denom;
            numberOfWays += numberOfWaysToMakeChange(target, denoms);
        }
        return numberOfWays;
    }
}
