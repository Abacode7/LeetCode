package com.company;

import java.util.Arrays;
import java.util.stream.Stream;

@SuppressWarnings("ALL")
public class GFG_TriesProblems {
    public static void main(String[] args){
        System.out.println("Yay Tries!");
        TrieNode trieNode = new TrieNode();

        // Insert Trie
        System.out.println("\nInsert into Trie\n");
        String[] dictionary = new String[]{"spoon", "cup", "pencil", "capacity", "plate", "tv", "ant"};
        for(String words: dictionary){
            insert(trieNode, words);
        }

        String[] outSideOfDictionary = new String[]{"sickle", "raft", "rope", "docket", "pine"};
        String[] combinedDictionary = Stream.concat(Arrays.stream(dictionary), Arrays.stream(outSideOfDictionary))
                .toArray(String[]::new);

        // Search Trie
        System.out.println("\nSearch in Trie\n");
        for(String word: combinedDictionary){
            System.out.printf("This word: %s is present in trie: %s\n", word, search(trieNode, word));
        }

        // Search Prefix in Trie
        System.out.println("\nSearch Prefix int Trie\n");
        String[] prefixes = new String[]{"cap", "an", "pallette"};
        for(String word: prefixes){
            System.out.printf("This prefix: %s is present in trie: %s\n", word, searchPrefix(trieNode, word));
        }

        // Delete in Trie
        System.out.println("\nDelete in Trie\n");
        delete(trieNode, "capacity");
        delete(trieNode, "spoon");
        for(String word: dictionary){
            System.out.printf("This word: %s is present in trie: %s\n", word, search(trieNode, word));
        }
    }

    public static class TrieNode {
        TrieNode[] childNodes;
        int wordCount;

        public TrieNode(){
            this.childNodes = new TrieNode[26];
            wordCount = 0;
        }
    }

    public static void insert(TrieNode root, String data){
        TrieNode current = root;

        for(char c: data.toCharArray()){
            if(current.childNodes[c - 'a'] == null){
                current.childNodes[c - 'a'] = new TrieNode();
            }
            current = current.childNodes[c - 'a'];
        }
        current.wordCount++;
    }

    public static boolean search(TrieNode root, String data){
        TrieNode current = root;

        for(char c: data.toCharArray()){
            if(current.childNodes[c - 'a'] == null) return false;

            current = current.childNodes[c - 'a'];
        }
        return current.wordCount > 0;
    }

    public static boolean searchPrefix(TrieNode root, String dataPrefix){
        TrieNode current = root;

        for(char c: dataPrefix.toCharArray()){
            if(current.childNodes[c - 'a'] == null) return false;

            current = current.childNodes[c - 'a'];
        }
        return true;
    }

    /**
     * For trie delete, there are 3 cases
     * is absent: return
     * is present:
     * - The key is a prefix of another string in trie:
     *      - Decrease count of prefix
     * - The key is a suffix of another string in trie
     *      - We delete up to the prefix
     * - The key is neither of the above
     *      - We delete up to the root
     *
     *  Code Intuition
     * lastCurrentNode is the node of the last character with multiple child nodes
     * Given ant & anderson in trie, deleting anderson, lastCurrentNode is n & lastCurrentChar is d
     * */
    public static boolean delete(TrieNode root, String data){
        TrieNode current = root;
        TrieNode lastCurrentNode = null;
        char lastCurrentChar = 'a';

        for(char c: data.toCharArray()){
            if(current.childNodes[c - 'a'] == null) return false;
            else{
                int count = 0;
                for(TrieNode node: current.childNodes){
                    if(node != null) count++;
                }

                if(count > 1){
                    lastCurrentNode = current;
                    lastCurrentChar = c;
                }
            }

            current = current.childNodes[c - 'a'];
        }

        // Check if current node is a prefix, it should have children
        int count = 0;
        for(TrieNode node: current.childNodes){
            if(node != null) count++;
        }
        if(count > 1) {
            current.wordCount--;
            return true;
        }

        // Check if current node is a suffix, lastCurrentNode should not be null
        if(lastCurrentNode != null){
            lastCurrentNode.childNodes[lastCurrentChar - 'a'] = null;
        }else{
            root.childNodes[data.charAt(0) - 'a'] = null;
        }
        return true;
    }

    public static boolean deleteRefactored(TrieNode root, String data){
        TrieNode current = root;
        TrieNode lastCurrentNode = root;
        char lastCurrentChar = data.charAt(0);

        for(char c: data.toCharArray()){
            if(current.childNodes[c - 'a'] == null) return false;
            else{
                int count = 0;
                for(TrieNode node: current.childNodes){
                    if(node != null) count++;
                    if(count > 1){
                        lastCurrentNode = current;
                        lastCurrentChar = c;
                        break;
                    }
                }
            }
            current = current.childNodes[c - 'a'];
        }

        // Check if current node is a prefix, it should have children
        int count = 0;
        for(TrieNode node: current.childNodes){
            if(node != null) count++;
            if(count > 1) {
                current.wordCount--;
                return true;
            }
        }

        // Set the last multi-children node (with last character) to null
        lastCurrentNode.childNodes[lastCurrentChar - 'a'] = null;
        return true;
    }
}
