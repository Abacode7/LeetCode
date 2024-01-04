package com.company;

import java.util.*;
public class AE_SingleCycleCheck {
    /**
     Index -> 0 2 3 5 1 4 0

     0 1 2  3  4 5
     2 3 1 -4 -4 2

     - start at initial index: 0
     - visit all elements accordingly
     - store visited indexes
     - if index visited twice, end operation

     then check:
     - if end index == start index
     if false, return false
     - if true, check all elements have been visited.


     Index: 0, step -1 = 5
     0 -1 = 5
     0 -2 = 4
     0 -3 = 3
     0 -4 = 2
     0 -5 = 1
     0 -6 = 0
     0 -7 = 5
     0 -8 = 4
     -9 = 3
     -10 = 2
     -11 = 1
     -12 = 0
     -13 = 5
     -14 = 4

     -6 -12 => 0
     -1 -7 -13 => 5
     -2 -8 -14 => 4



     - Follow steps
     - Every element is visited once
     - The beginning and ending index must be the same

     Rollovers
     - N: sum >= N: Index = sum % N
     sum < 0: if(sum >= -1 * N): Index = N + sum
     else: Index = N +  -1*(abs(sum) % N)
     **/
    public static boolean hasSingleCycle(int[] array) {
        Set<Integer> set = new HashSet<>();
        int arrayLength = array.length;

        int currentIndex = 0;
        while(!set.contains(currentIndex)){
            set.add(currentIndex);
            int currentValue = array[currentIndex];

            int sum = currentIndex + currentValue;
            if(sum >= arrayLength) currentIndex = sum % arrayLength;
            else if(sum >= 0) currentIndex = sum;
            else{
                if(sum >= -1 * arrayLength){
                    currentIndex = arrayLength + sum;
                }else{
                    currentIndex = arrayLength + -1 * (Math.abs(sum) % arrayLength);
                    if(currentIndex == arrayLength) currentIndex = 0;
                }
            }
        }

        if(currentIndex != 0) return false;

        for(int i=0; i<array.length; i++) if(!set.contains(i)) return false;

        // Write your code here.
        return true;
    }
}
