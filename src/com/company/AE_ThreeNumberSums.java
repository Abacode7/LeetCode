package com.company;

import java.util.*;
//import javafx.util.*;

public class AE_ThreeNumberSums {
    public static void main(String[] args){
        int[] array = {12, 3, 1, 2, -6, 5, -8, 6};
        int target = 0;

        List<Integer[]> result = threeNumberSum(array, target);
        for (Integer[]v: result) {
            System.out.println(v[0] + "  " + v[1] + "  " + v[2]);
        }
    }

    /**
     [12 3 1 2 -6 5 -8 6]

     Map(-8, -6)
     Set 2 [-14 -5 -7 3 -4 -6 5 4 -3 -5]
     [-8 -6 1 2 3 5 6 12]

     Loop over array
        Check if: target - value is key in Pair Map
            Yes: Get pairs and add them to result list with value
            No: Continue

        Loop back from value:
            Add pairs with value for all values behind it.
     */
    public static List<Integer[]> threeNumberSum(int[] array, int targetSum) {
        // Write your code here.
        Arrays.sort(array);
        Map<Integer, List<Pair<Integer>>> map = new HashMap<>();
        List<Integer[]> result = new ArrayList<>();

        // First Leg
        for(int i=1; i<array.length; i++){
            // Step 1
            if(map.keySet().contains(targetSum - array[i])){
                List<Pair<Integer>> pairs = map.get(targetSum - array[i]);
                for(Pair<Integer> pair: pairs){
                    result.add(new Integer[]{pair.getKey(), pair.getValue(), array[i]});
                }
            }

            // Step 2
            for(int j=i-1; j>=0; j--){
                int sum = array[i] + array[j];
                map.putIfAbsent(sum, new ArrayList<>());

                List<Pair<Integer>> pairList = map.get(sum);
                pairList.add(new Pair(array[j], array[i]));
                map.put(sum, pairList);
            }
        }

        // Second Leg
        Collections.sort(result, new Comparator<Integer[]>(){
            public int compare(Integer[] a, Integer[] b){
                int result = a[0] - b[0];
                if(result != 0) return result;
                else{
                    int res = a[1] - b[1];
                    if(res != 0) return res;
                    else{
                        return a[2] - b[2];
                    }
                }
            }
        });

        return result;
    }

    private static class Pair<T>{
        T key;
        T value;

        Pair(T key, T value){
            this.key = key;
            this.value = value;
        }

        public T getKey(){
            return this.key;
        }

        public T getValue(){
            return this.value;
        }
    }
}
