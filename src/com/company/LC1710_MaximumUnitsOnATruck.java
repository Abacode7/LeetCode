package com.company;

import java.util.Arrays;

public class LC1710_MaximumUnitsOnATruck {
    public static void main(String[] args){
        int[][] boxTypes = {{1,3},{2,2},{3,1}}; // 8
        int truckSize = 4;
        System.out.println(maximumUnits(boxTypes, truckSize));
    }

    public static int maximumUnits(int[][] boxTypes, int truckSize) {
        if(boxTypes.length == 0) return 0;

        Arrays.sort(boxTypes, (a, b) -> b[1] - a[1]);
        int maxUnits = 0;

        for(int[] boxType: boxTypes){
            if(truckSize > boxType[0]){
                truckSize -= boxType[0];
                maxUnits += boxType[0] * boxType[1];
            }else{
                maxUnits += truckSize * boxType[1];
                break;
            }
        }

        return maxUnits;
    }
}
