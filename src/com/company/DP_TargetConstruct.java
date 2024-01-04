package com.company;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DP_TargetConstruct {
    public static void main(String[] args){
        String target = "abcdef";
        String[] wordBank = {"ab", "abc", "cd", "def", "abcd", "ef", "c"};
        System.out.println(myAllConstruct(target, wordBank));
    }

    // Time 0:42 0:52 1:00
    /**
     * 0123456
     * abcdef
     *
     *  ""  "a" "ab"  "abc"  "abcd"  "abcde"  "abcdef" Key
     *  0    1    2    3       4       5         6
     *  T   F     T    T       T       F         T      Values
     *
     *
     * Complexity: target = m, wordBank = n
     * Time: O(mn)
     * Space: O(m)
     */
    public static boolean myCanConstruct(String target, String[] wordBank){
        Map<String, Boolean> map = new HashMap<>();
        for(int i=0; i<=target.length(); i++){
            map.put(target.substring(0, i), false);
        }
        map.put("", true);

        for(Map.Entry<String, Boolean> entry: map.entrySet()){
            String subTarget = entry.getKey();
            if(entry.getValue()){
                for(String word: wordBank){
                    String futureTarget = subTarget + word;
                    if(map.containsKey(futureTarget)) map.put(futureTarget, true);
                }
            }
        }
        return map.get(target);
    }

    /**
     * 1:00 1:08 1:15
     *
     * Alternatively we can also do:
     * 0 1 2 3 4 5
     * a b c d e f
     * ["ab", "abc", "cd", "def", "abcd", "ef", "c"]
     *
     *
     *    "a"   "abc"   "abcde"
     * ""    "ab"   "abcd"    "abcdef"
     * 0  1   2   3   4   5   6 <- represent subTarget, specifically the end index of the subTarget in target (i.e 0 represent the end index of "" in abcdef)
     * T  F   F   F   F   F   F
     * a  b   c   d   e   f    <- also represents the start index of the substring following our subTarget
     *
     */
    public static boolean canConstruct(String target, String[] wordBank){
        boolean[] table = new boolean[target.length() + 1];
        table[0] = true;

        for(int i=0; i<target.length(); i++){
            if(table[i]){
                for(String word: wordBank){
                    int subTargetEndIdx = i + word.length();
                    if(subTargetEndIdx <= target.length() && word.equals(target.substring(i, i + word.length()))){
                        table[i + word.length()] = true;
                    }
                }
            }
        }
        return table[target.length()];
    }


    public static int myCountConstruct(String target, String[] wordBank){
        Map<String, Integer> map = new HashMap<>();
        for(int i=0; i<=target.length(); i++){
            map.put(target.substring(0, i), 0);
        }
        map.put("", 1);

        for(Map.Entry<String, Integer> entry: map.entrySet()){
            String subTarget = entry.getKey();
            if(entry.getValue() != 0){
                for(String word: wordBank){
                    String futureTarget = subTarget + word;
                    if(map.containsKey(futureTarget)){
                        int count = map.get(futureTarget) + map.get(subTarget);
                        map.put(futureTarget, count);
                    }
                }
            }
        }
        return map.get(target);
    }

    public static int countConstruct(String target, String[] wordBank){
        int[] table = new int[target.length() + 1];
        table[0] = 1;

        for(int i=0; i < target.length(); i++){
            if(table[i] != 0){
                for(String word: wordBank){
                    int subTargetEndIdx = i + word.length();
                    if(subTargetEndIdx <= target.length() && word.equals(target.substring(i, subTargetEndIdx))){
                        table[subTargetEndIdx] += table[i];
                    }
                }
            }
        }
        return table[target.length()];
    }

    /**
     * 012345
     * abcdef
     * ["ab", "abc", "cd", "def", "abcd", "ef", "c"]
     *
     *
     *      "a"      "abc"     "abcde"
     * ""        "ab"     "abcd"     "abcdef"
     *  0    1    2    3    4    5    6
     *  a    b    c    d    e    f
     *
     */
    public static List<List<String>> allConstruct(String target, String[] wordBank){
        int targetLength = target.length();
        List<List<List<String>>> table = new ArrayList<>(targetLength + 1);
        for(int i=0; i<=targetLength; i++){
            table.add(null);
        }
        List<List<String>> arrayList = new ArrayList<>();
        arrayList.add(new ArrayList<>());
        table.set(0, arrayList);

        for(int i=0; i<=targetLength; i++){
            if(table.get(i) != null){
                for(String word: wordBank){
                    int subTargetIdx = i + word.length();
                    if(subTargetIdx <= targetLength && word.equals(target.substring(i, subTargetIdx))){
                        List<List<String>> wordGroup = table.get(subTargetIdx);
                        if(wordGroup == null) wordGroup = new ArrayList<>();

                        List<List<String>> baseWordGroup = table.get(i);
                        for(List<String> base: baseWordGroup){
                            List<String> newWordGroup = new ArrayList<>(base);
                            newWordGroup.add(word);
                            wordGroup.add(newWordGroup);
                        }

                        table.set(subTargetIdx, wordGroup);
                    }
                }
            }
        }
        return table.get(targetLength);
    }

    /**
     * 2:42
     * 012345
     * abcdef
     * ["ab", "abc", "cd", "def", "abcd", "ef", "c"]
     *
     * ""    "ab"    "abc"    "abcd"    "abcde"    "abcdef"
     * [[]]  [[ab]]
     *
     */
    public static List<List<String>> myAllConstruct(String target, String[] wordBank){
        int targetLength = target.length();
        Map<String, List<List<String>>> map = new HashMap<>();
        for(int i=0; i<=targetLength; i++){
            map.put(target.substring(0, i), null);
        }
        List<List<String>> words = new ArrayList<>();
        words.add(new ArrayList<>());
        map.put("", words);

        for(Map.Entry<String, List<List<String>>> entry: map.entrySet()){
            String subTarget = entry.getKey();
            List<List<String>> subTargetWordLists = entry.getValue();
            if(subTargetWordLists != null){
                for(String word: wordBank){
                    String potentialTarget = subTarget + word;
                    if(map.containsKey(potentialTarget)){
                        List<List<String>> ptWordList = map.get(potentialTarget);
                        if(ptWordList == null) ptWordList = new ArrayList<>();

                        for(List<String> subTargetWordList: subTargetWordLists){
                            List<String> newWordList = new ArrayList<>(subTargetWordList);
                            newWordList.add(word);
                            ptWordList.add(newWordList);
                        }

                        map.put(potentialTarget, ptWordList);
                    }
                }
            }
        }

        return map.get(target);
    }
}
