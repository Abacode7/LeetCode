package com.company;

import java.util.*;

public class AE_SuffixTrieConstruction {
    /**
     A Suffix Trie is a Trie datastructure that stores all possible
     suffixes of a string in a Trie.

     Given: babc
     The trie structure will store: babc, abc, bc, c

     b: {
     a: {b: {c: {*}}}
     c : {*}
     }
     a: {b: {c: *}}
     c: {*}

     **/
    static class TrieNode {
        Map<Character, TrieNode> children = new HashMap<Character, TrieNode>();
    }

    static class SuffixTrie {
        TrieNode root = new TrieNode();
        char endSymbol = '*';

        public SuffixTrie(String str) {
            populateSuffixTrieFrom(str);
        }

        public void populateSuffixTrieFrom(String str) {
            for(int i=0; i < str.length(); i++){
                String subString = str.substring(i);
                insertString(subString);
            }
        }

        public void insertString(String str){
            TrieNode node = root;
            for(char value: str.toCharArray()){
                if(!node.children.containsKey(value)){
                    node.children.put(value, new TrieNode());
                }
                node = node.children.get(value);
            }
            node.children.put('*', null);
        }

        public boolean contains(String str) {
            // Write your code here.
            TrieNode node = root;
            for(char value: str.toCharArray()){
                if(!node.children.containsKey(value)) return false;
                node = node.children.get(value);
            }

            return node.children.containsKey('*');
        }
    }
}
