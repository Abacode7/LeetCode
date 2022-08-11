package com.company;

import javafx.util.Pair;

import java.util.*;

public class Amazon_Lagos_PrimeNonPrimeOrdering {
    public static void main(String[] args){
        List<String> input = new ArrayList<>();
        input.add("zld 93 12");
        input.add("fp kindle book");
        input.add("10a echo show");
        input.add("17g 12 25 6");
        input.add("ab1 kindle book");
        input.add("125 echo dot");

        List<String> result = order(input);
        for(String res: result){
            System.out.println(res);
        }
    }

    /**
     * 125 echo dot
     * 10a echo show
     * ab1 kindle book
     * fp kindle book
     * zld 93 12
     * 17g 12 25 6
     */

    /**
     * Iterate through input and split input string by the first space occurence
     * Check second substring
     *  if values are digits, save input into non-prime list
     *  if values are letters, save as key value pair into prime list
     *
     * Sort prime values by the value in the pair,
     *  if there is a tie sort by key.
     *
     * Iterate through prime pair list and get sorted values in result list
     *
     * Add the non-prime values to the result list.
     *
     *
     */
    public static List<String> order(List<String> input){
        List<Pair<String, String>> primeStrings = new ArrayList<>();

        List<String> nonPrimeStrings = new ArrayList<>();
        for(String value: input){
            String[] splitValue = value.split(" ", 2);
            if(Character.isDigit(splitValue[1].charAt(0))){
                nonPrimeStrings.add(value);
            }else {
                primeStrings.add(new Pair<>(splitValue[0], splitValue[1]));
            }
        }

        Collections.sort(primeStrings, new Comparator<Pair<String, String>>() {
            @Override
            public int compare(Pair<String, String> o1, Pair<String, String> o2) {
                int value = o1.getValue().compareTo(o2.getValue());
                if(value != 0) return value;
                return o1.getKey().compareTo(o2.getKey());
            }
        });

        List<String> result = new ArrayList<>();
        for(Pair<String, String> value: primeStrings){
            result.add(value.getKey() + " " + value.getValue());
        }
        result.addAll(nonPrimeStrings);
        return result;
    }
}
