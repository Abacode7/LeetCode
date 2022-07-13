package com.company;

import java.util.*;

public class AE_MoveElementToEnd {
    /**
     [3 4 2 5 2 9]
     */
    public static List<Integer> moveElementToEnd(List<Integer> array, int toMove) {
        // Write your code here.
        int i = array.size() - 1;
        int j = i;
        while(j >= 0){
            if(array.get(j) == toMove){
                Integer temp = array.get(i);
                array.set(i, array.get(j));
                array.set(j, temp);
                i--;
            }
            j--;
        }
        return array;
    }
}
