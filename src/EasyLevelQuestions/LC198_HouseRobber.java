package EasyLevelQuestions;

public class LC198_HouseRobber {

    public static void main(String[] args){
        int[] input = {1, 2, 3, 1, 1, 100};
        System.out.println(rob(input));
    }

    // At each point we ask "Whats the maximum gain we have so far?"
    // Its a greedy algorithm. To check we find Max(nums[i], nums[i] + nums[i-2])
    // knowing that the value of nums[i-2] is the max gain at that point
    /*
    0   1  2  3  4  5
    {1, 2, 3, 1, 1, 100};
     */

    public static int rob(int[] nums) {
        if(nums.length == 0) return 0;

        int[] dp = new int[nums.length+1];
        dp[0] = 0;
        dp[1] = nums[0];
        for(int i=1; i<nums.length; i++){
            dp[i+1] = Math.max(dp[i], nums[i] + dp[i-1]);
        }
        return dp[nums.length];
    }

    // Wrong solution
    // Doesn't work for this case [1 2 3 1 1 100 ] - 104
    public static int rob1(int[] nums) {
        int firstPhase = 0;
        int secondPhase = 0;

        int i = 0;
        int j = 1;
        while(i < nums.length || j < nums.length){
            if(i < nums.length ){
                firstPhase += nums[i];
            }
            if(j < nums.length ){
                secondPhase += nums[j];
            }
            i += 2;
            j += 2;
        }
        return Math.max(firstPhase, secondPhase);
    }
}