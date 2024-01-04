package com.company;

import java.util.HashMap;
import java.util.Map;

public class AE_GenerateDocument {
    /**

     Iterate through characters and documents at the same time
     - For each char in characters: increment count
     - For each char in document: decrement count

     - Iterate through char to count map and ensure no char has < 0 count

     **/
    public boolean generateDocument(String characters, String document) {
        // Write your code here.
        Map<Character, Integer> map = new HashMap<>();
        int i=0;
        int n = Math.max(characters.length(), document.length());
        while(i < n){
            if(i < characters.length()){
                char charValue = characters.charAt(i);
                map.putIfAbsent(charValue, 0);

                Integer count = map.get(charValue);
                count++;
                map.put(charValue, count);
            }
            if(i < document.length()){
                char docValue = document.charAt(i);
                map.putIfAbsent(docValue, 0);

                Integer count = map.get(docValue);
                count--;
                map.put(docValue, count);
            }
            i++;
        }

        for(Map.Entry<Character, Integer> entry: map.entrySet()){
            if(entry.getValue() < 0) return false;
        }
        return true;
    }
}
