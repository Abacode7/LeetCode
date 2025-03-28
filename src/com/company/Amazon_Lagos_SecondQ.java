package com.company;

import java.util.ArrayList;
import java.util.List;

public class Amazon_Lagos_SecondQ {
    public static void main(String[] args){
    }

    public static List<List<Integer>> getMaxDistance(int maxDistance, List<List<Integer>> forward,
                                                     List<List<Integer>> backward){

        int i = forward.size() - 1;
        int j = 0;

        int maxDistanceSoFar = Integer.MIN_VALUE;
        List<List<Integer>> result = new ArrayList<>();
        while(i>=0 && j < backward.size()){
            List<Integer> fFlight = forward.get(i);
            List<Integer> bFlight = backward.get(j);
            int distanceSum = fFlight.get(1) + bFlight.get(1);

            if(distanceSum <= maxDistance){
                if(distanceSum >= maxDistanceSoFar){
                    List<Integer> res = new ArrayList<>();
                    res.add(fFlight.get(0));
                    res.add(bFlight.get(0));

                    if(distanceSum == maxDistanceSoFar){
                        result.add(res);
                    }else{
                        maxDistanceSoFar = distanceSum;
                        result = new ArrayList<>();
                        result.add(res);
                    }
                    i--;
                    j++;
                }
            } else if (distanceSum > maxDistance){
                i--;
            }else {
                j++;
            }

        }
        return result;
    }
}
