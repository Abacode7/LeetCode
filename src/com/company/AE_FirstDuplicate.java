package com.company;
import java.util.*;

public class AE_FirstDuplicate {
    /**
     Use a hashtable
     **/
    public int firstDuplicateValue(int[] array) {
        // Write your code here.
        Set<Integer> set = new HashSet<>();
        for(int value: array){
            if(set.contains(value)) return value;
            set.add(value);
        }
        return -1;
    }
}
