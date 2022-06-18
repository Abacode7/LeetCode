package com.company;

import java.util.Arrays;

public class LC1465_MaximumAreaOfAPieceOfCakeAfterHorizontalAndVerticalCuts {
    public static void main(String[] args){
        int h = 5, w = 4;
        int[] horizontalCuts = {1,2,4}, verticalCuts = {1,3};
        System.out.println(maxArea(h, w, horizontalCuts, verticalCuts));
    }

    public static int maxArea(int h, int w, int[] horizontalCuts, int[] verticalCuts) {
        // the max area is the max difference length in horizontal
        // multiplied by the max difference length in vertical

        Arrays.sort(horizontalCuts);
        Arrays.sort(verticalCuts);

        long hMax = horizontalCuts[0];
        int m = horizontalCuts.length;
        for(int i=1; i<m; i++){
            hMax = Math.max(hMax, horizontalCuts[i] - horizontalCuts[i-1]);
        }
        hMax = Math.max(hMax, h - horizontalCuts[m-1]);


        long wMax = verticalCuts[0];
        int n = verticalCuts.length;
        for(int i=1; i<n; i++){
            wMax = Math.max(wMax, verticalCuts[i] - verticalCuts[i-1]);

        }
        wMax = Math.max(wMax, w - verticalCuts[n-1]);

        long maxArea = (hMax * wMax) % 1000000007;
        return (int) maxArea;
    }
}
