package com.company;

import java.util.*;

public class LC0472_ConcatenatedWords {
    public static void main(String[] args){
        String[] input = {"cat", "cats", "catsdogcats", "dogcatdog"};
        List<String> result = concatenatedWords(input);

        for (String word: result) {
            System.out.println(word);
        }

        System.out.println(result.size());
    }

    public static List<String> concatenatedWords(String[] words){
        Set<String> set = new HashSet<>(Arrays.asList(words));
        List<String> result = new ArrayList<>();

        for(String word: words){
            for(int i=1; i<word.length(); i++){
                String firstSubstring = word.substring(0, i);
                String lastSubstring = word.substring(i);

                if(set.contains(firstSubstring) && canFind(set, lastSubstring)) {
                    result.add(word);
                    break;
                }
            }
        }

        return result;
    }

    public static boolean canFind(Set<String> set, String word){
        if(set.contains(word)) return true;

        for(int i=1; i<word.length(); i++){
            String firstSubstring = word.substring(0, i);
            String lastSubstring = word.substring(i);

            if(set.contains(firstSubstring) && canFind(set, lastSubstring)) return true;
        }

        return false;
    }
}
