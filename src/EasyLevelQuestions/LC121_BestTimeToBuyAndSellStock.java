package EasyLevelQuestions;

public class LC121_BestTimeToBuyAndSellStock {
    public static void main(String[] args){
        int[]  prices = {7,1,5,3,6,4};
        System.out.println(maxProfit(prices));
    }
    /*
    Idea is at every price point, we must request for the
    minPriceSoFar. If less than that, reassign the minPriceSoFar
    if greater than that compare the difference with that of the
    maxProfit
     */

    // Runtime O(N), Space O(1)
    public static int maxProfit(int[] prices) {
        int minPriceSoFar = Integer.MAX_VALUE;
        int maxProfit = 0;

        for(int i=0; i<prices.length; i++){
            if(prices[i] < minPriceSoFar) minPriceSoFar = prices[i];
            else {
                maxProfit = Math.max(maxProfit, prices[i] - minPriceSoFar);
            }
        }

        return maxProfit;
    }

    // Linear solution - Time limit exceeded
    public int maxProfitOld(int[] prices) {
        int maxProfit = 0;
        for(int i=0; i<prices.length; i++){
            int j = i+1;
            while(j < prices.length){
                if(prices[j] > prices[i]){
                    maxProfit = Math.max(maxProfit, prices[j] - prices[i]);
                }
                j++;
            }
        }
        return maxProfit;
    }
}
