package com.company;

import java.util.Arrays;

public class AE_NonConstructibleChange {
    /**
     1 2 5
     1 3

     The cumulative at each point represents the max change which can be created
     decreasing downwards i.e 4 means 4, 3, 2, 1 can be created

     1 1 2 3 5  7  22
     1 2 4 7 12 19 41

     taking 7 (19) 22 (41) - cumulativies in bracket
     there are values between 7 and 22, if the max change so far (at 7) plus 1
     is less than 22 then that's the min denomination that cant be created

     furthermore, 7   19   22   41
     between 22 and 41: using 22 plus all denominations in 19, we can fill this space
     between 7 and 22: 19 denominations suffices for it up until 19, now
     between 19 and 22: no denominations suffice for them, so 19 + 1 is the min non constructible one
     **/

    public int nonConstructibleChange(int[] coins) {
        // Write your code here.
        Arrays.sort(coins);

        int change = 0;
        for(int coin: coins){
            if(change + 1 < coin) return change + 1;
            change += coin;
        }
        return change + 1;
    }
}
