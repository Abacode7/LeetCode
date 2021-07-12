package EasyLevelQuestions;

public class LC70_ClimbingStairs {
    public static void main(String[] args){
        int input = 5;
        System.out.println(climbStairs(input));
    }

    // The no of steps it takes to climb N steps is simply
    // the no. of steps to climb N-1 plus that of N-2

    // Using a Bottom Top DP Approach
    // Runtime O(n), Space O(n)
    public static int climbStairs(int n) {
        // n+1 since the indexes represent
        // the no. of steps
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;

        for(int i=2; i<n+1; i++){
            dp[i] = dp[i-1] + dp[i-2];
        }

        return dp[n];
    }
}
