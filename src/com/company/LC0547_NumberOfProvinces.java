package com.company;

import java.util.HashSet;
import java.util.Set;

public class LC0547_NumberOfProvinces {
    public static void main(String[] args){
        int[][] input = {{1,1,0},{1,1,0},{0,0,1}};
        System.out.println(findCircleNum(input));
    }

    public static int findCircleNum(int[][] isConnected) {
        Set<Integer> isFound = new HashSet<>();
        int provinceCount = 0;
        for(int i=0; i < isConnected.length; i++){
            if(!isFound.contains(i)){
                isFound.add(i);
                findProvinceFrom(i, isConnected, isFound);
                provinceCount++;
            }
        }

        return provinceCount;
    }

    private static void findProvinceFrom(int i, int[][] isConnected, Set<Integer> isFound){
        for(int j=0; j < isConnected[i].length; j++){
            if(j == i) continue;
            if(!isFound.contains(j) && isConnected[i][j] == 1){
                isFound.add(j);
                findProvinceFrom(j, isConnected, isFound);
            }
        }
    }
}
