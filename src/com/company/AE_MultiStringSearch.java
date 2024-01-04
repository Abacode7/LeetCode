package com.company;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

public class AE_MultiStringSearch {
    public static void main(String[] args){
        String bigString = "this is a big string";
        String[] smallStrings = {"this", "yo", "is", "a", "bigger", "string", "kappa"};

        Timestamp start = Timestamp.valueOf(LocalDateTime.now());
        List<Boolean> result = multiStringSearch(bigString, smallStrings);
        Timestamp end = Timestamp.valueOf(LocalDateTime.now());
        System.out.println(result);
        System.out.println(end.getTime() - start.getTime());
    }

    /**
     *
     where b = length of big string, s = the longest small string,
     n length of small string list

     Suggestion One - Using Set:
     - Total: O(b^2 + n), Building/populating: O(b2), Searching: O(n) | Space O(b2 + n)
     - Find all permutations of the substring of bigString, put in a set
     - Then check if word present in set

     Suggestion Two - Naive Check:
     - O(nbs), isSubstring() - O(bs)
     - For each word check if it's in bigString
     - Check by finding a match for their first letters and checking further.

     Suggestion Three - Using Tries (populating all possible bigString values):
     - Total: O(b^3 + ns), Building/Populating Trie: O(b2 s), Searching O(ns)
     - Using Tries

     Suggestion Four - Using Suffix Trie:
     - Total: O(b^2 + ns), Building/Populating Trie: O(b2), Searching: O(ns) | Space O(b2 + n)

     Suggestion Five - Using Trie (populating small strings values)
     - Total: O(bs + ns), Building/Populating Trie: O(ns), Searching (for bigString): O(bs)

     abcabcd
     abcd
     Example
     bigString: abcde | string size: b
     smallString: abcd bxd cde abcde | list size: n, the longest small string size: s

     a ab abc abcd abcde
     b bc bcd bcde
     c cd cde
     d de
     e

     **/

    /** Solution 3 - Using Trie **/
    public static List<Boolean> multiStringSearch(String bigString, String[] smallStrings) {
        Trie trie = new Trie();
        for(int i=0; i<bigString.length(); i++){
            for(int j=i+1; j<=bigString.length(); j++){
                trie.populate(bigString.substring(i, j));
            }
        }

        List<Boolean> result = new ArrayList<>();
        for(String smallString: smallStrings){
            result.add(trie.isPresent(smallString));
        }

        return result;
    }

    static class Trie {
        TrieNode root = new TrieNode();

        public void populate(String str){
            TrieNode node = root;
            for(char value: str.toCharArray()){
                if(!node.children.containsKey(value)){
                    node.children.put(value, new TrieNode());
                }
                node = node.children.get(value);
            }
            node.children.put('*', null);
        }

        public boolean isPresent(String str){
            TrieNode node = root;
            for(char value: str.toCharArray()){
                if(!node.children.containsKey(value)) return false;
                node = node.children.get(value);
            }
            return node.children.containsKey('*');
        }

        private class TrieNode{
            Map<Character, TrieNode> children = new HashMap<>();
        }
    }

    /** Solution 2 - Using isSubstring (naive check) **/
    public static List<Boolean> multiStringSearch2(String bigString, String[] smallStrings) {
        // Write your code here
        List<Boolean> result = new ArrayList<>(smallStrings.length);
        for(int i=0; i<smallStrings.length; i++){
            result.add(isSubstring(smallStrings[i], bigString));
        }
        return result;
    }

    public static boolean isSubstring(String subStr, String str) {
        if(subStr.length() > str.length()) return false;

        for(int i=0; i<str.length(); i++){
            if(str.charAt(i) != subStr.charAt(0)) continue;

            int j = 0;
            int k = i;
            while(j < subStr.length() && k < str.length() && str.charAt(k) == subStr.charAt(j)){
                k++;
                j++;
            }
            if(j == subStr.length()) return true;
        }
        return false;
    }

    /** Solution 1 - Using set **/
    public static List<Boolean> multiStringSearch1(String bigString, String[] smallStrings) {
        Set<String> set = new HashSet<>();
        for(int i=0; i<bigString.length(); i++){
            for(int j=i+1; j<=bigString.length(); j++){
                set.add(bigString.substring(i, j));
            }
        }

        List<Boolean> result = new ArrayList<>();
        for(String smallString: smallStrings){
            result.add(set.contains(smallString));
        }
        return result;
    }
}
