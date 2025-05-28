package com.company;

import java.util.*;
import java.util.stream.Stream;

@SuppressWarnings("ALL")
public class GFG_TriesProblems {
    /** Refer to Trie Design Learnings for ideal Trie Implementation **/
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
        System.out.println();

        /** Edge Case Test */
//        System.out.println("\nInsert an and ant in Trie; Delete in Trie\n");
//        insert(trieNode, "an");
//        insert(trieNode, "ant");
//
//        System.out.println("Search in trie: an: " + search(trieNode, "an"));
//        System.out.println("Delete in trie: ant: " + delete(trieNode, "ant"));
//        System.out.println("Search in trie: ant: " + search(trieNode, "ant"));
//        System.out.println("Search in trie: an: " + search(trieNode, "an"));

        // Longest Common Prefix
        System.out.println(longestCommonPrefix2(new String[]{ "ambivert", "ambission", "amber", "ambivalent", "ambassy"}));
        System.out.println();

        // Unique Rows
        int[][] a = {{1, 1, 0, 1},{1, 0, 0, 1},{1, 1, 0, 1}};
        System.out.println(uniqueRow2(a, 3, 4));
        System.out.println();
    }




    /**
     * Intuition
     * TrieNode with properties TrieNode[26] children and int wordCount.
     * Where each children index with TrieNode represents the presence of the charater at such index, it the Trie.
     * wordCount marks the end of the last character in the string. We use wordCount int, rather than isEndOfWord boolean
     * because wordCount allows us count the number of times a particular word occurred and in the case of deletion, we still
     * retain correct number of such word remaining.
     *
     * Trie Complexity
     * Insertion: O(n) time, where n is length of word. O(1) space
     * Search: O(n) time, O(1) space
     * Search Prefix: O(n) time, O(1) space
     * Deletion: O(n) time (inner count operations are constant time). O(1) space.
     *
     * Trie Building for list of words
     * Insertion: O(mn) where m is length of longest word, and n is the number of words. O(1) space
     */

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
        TrieNode lastMultiBranchNode = null;
        char lastMultiBranchChar = 'a';

        for(char c: data.toCharArray()){
            if(current.childNodes[c - 'a'] == null) return false;
            else{
                int count = 0;
                for(TrieNode node: current.childNodes){
                    if(node != null) count++;
                }

                if(count > 1){
                    lastMultiBranchNode = current;
                    lastMultiBranchChar = c;
                }
            }

            current = current.childNodes[c - 'a'];
        }

        // Check if current node is a prefix, it should have children
        int count = 0;
        for(TrieNode node: current.childNodes){
            if(node != null) count++;
        }
        if(count > 0) {
            current.wordCount--;
            return true;
        }

        // Check if current node is a suffix, lastMultiBranchNode should not be null
        if(lastMultiBranchNode != null){
            lastMultiBranchNode.childNodes[lastMultiBranchChar - 'a'] = null;
        }else{
            root.childNodes[data.charAt(0) - 'a'] = null;
        }
        return true;
    }

    public static boolean deleteRefactored(TrieNode root, String data){
        TrieNode current = root;
        TrieNode lastMultiBranchNode = root;
        char lastMultiBranchChar = data.charAt(0);

        for(char c: data.toCharArray()){
            if(current.childNodes[c - 'a'] == null) return false;
            else{
                int count = 0;
                for(TrieNode node: current.childNodes){
                    if(node != null) count++;
                    if(count > 1){
                        lastMultiBranchNode = current;
                        lastMultiBranchChar = c;
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
        lastMultiBranchNode.childNodes[lastMultiBranchChar - 'a'] = null;
        return true;
    }



    /**
     * Given string array: [ "ambivert", "ambission", "amber", "ambivalent", "ambassy"]
     * Find the longest common prefix: "amb"
     *
     * Solution 1: Sorting, O(nlogn) time, O(k) where k is the length of the longest common prefix
     * O(1) amortized time (i.e not considering the output length)
     * Intuition: If you sort the words in the list, two farthest words (first & last)
     * will defer the most.
     * Find the longest common prefix amidst them, that tells all the tales
     *
     * Solution 2: Tries
     * O(mn) time, where m is lenght of longest string in array, and n is length of array
     * O(n) amortized time, where we exclude the time taken to build the Trie data structure
     *
     * O(mn) space, worst case where each word in array is not overlapping so every character
     * in every word takes a node.
     */
    public static String longestCommonPrefix1(String arr[]) {
        // code here
        if(arr.length <= 0) return "";
        if(arr.length == 1) return arr[0];

        Arrays.sort(arr);
        int n = arr.length;

        StringBuilder sb = new StringBuilder();
        int minLength = Math.min(arr[0].length(), arr[n-1].length());

        for(int i=0; i<minLength; i++){
            if(arr[0].charAt(i) != arr[n-1].charAt(i)) break;
            sb.append(arr[0].charAt(i));
        }

        if(sb.length() == 0) return "";
        return sb.toString();
    }

    public static String longestCommonPrefix2(String arr[]){
        if(arr.length == 0) return "";
        if(arr.length == 1) return arr[0];

        LCPTrieNode root = new LCPTrieNode();
        for(String word: arr){
            insertIntoTrie(root, word);
        }
        return searchLongCommonPrefix(root, arr[0]);
    }

    static class LCPTrieNode {
        LCPTrieNode[] children;
        int childrenCount;
        int wordCount;

        public LCPTrieNode(){
            this.children = new LCPTrieNode[26];
            childrenCount = 0;
            wordCount = 0;
        }
    }

    private static void insertIntoTrie(LCPTrieNode root, String data){
        LCPTrieNode current = root;
        for(char c: data.toCharArray()){
            if(current.children[c - 'a'] == null){
                current.children[c - 'a'] = new LCPTrieNode();
                current.childrenCount++;
            }
            current =  current.children[c - 'a'];
        }
        current.wordCount++;
    }

    private static String searchLongCommonPrefix(LCPTrieNode root, String data){
        StringBuilder sb = new StringBuilder();

        LCPTrieNode current = root;
        for(char c: data.toCharArray()){
            if(current.childrenCount != 1) break;

            if(current.children[c - 'a'] == null){
                break;
            }

            sb.append(c);
            current = current.children[c - 'a'];
        }
        return sb.toString();
    }




    /**
     * Given:  {{1 1 0 1}, {1 0 0 1}, {1 1 0 1}}
     * Result: {{1 1 0 1}, {1 0 0 1}}
     *
     * Trie:
     *     root
     *      1
     *   1.   0
     *  0.     0
     * 1.       1
     *
     * Solution 1: Using Sets, O(r*c) time, O(m*n) space
     *
     * Solution 2: Using DFS on Tries
     * Result: Solution too long
     * */
    public static ArrayList<ArrayList<Integer>> uniqueRow1(int a[][],int r, int c) {
        //add code here.
        Set<String> set = new HashSet<>();
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();

        for(int i=0; i < r; i++){

            StringBuilder sb = new StringBuilder();
            ArrayList<Integer> row = new ArrayList<>();
            for(int j=0; j < c; j++){
                sb.append(a[i][j]);
                row.add(a[i][j]);
            }

            if(!set.contains(sb.toString())){
                result.add(row);
            }
            set.add(sb.toString());
        }

        return result;
    }

    public static ArrayList<ArrayList<Integer>> uniqueRow2(int a[][],int r, int c){
        //add code here.
        URTrieNode root = new URTrieNode(-1);
        for(int[] matrix: a){
            insertIntoURTrie(root, matrix);
        }

        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        for(URTrieNode node: root.children){
            if(node != null){
                ArrayList<ArrayList<Integer>> dfsResult = dfsTrie(node, new ArrayList<Integer>());
                result.addAll(dfsResult);
            }
        }
        return result;
    }

    private static ArrayList<ArrayList<Integer>> dfsTrie(URTrieNode node, ArrayList<Integer> list){
        list.add(node.value);

        ArrayList<ArrayList<Integer>> result = new ArrayList<>();

        if(node.wordCount > 0) result.add(list);

        for(URTrieNode subNode: node.children){
            if(subNode != null){
                ArrayList<ArrayList<Integer>> dfsResult = dfsTrie(subNode, new ArrayList<Integer>(list));
                result.addAll(dfsResult);
            }
        }
        return result;
    }

    private static class URTrieNode {
        List<URTrieNode> children;
        int value;
        int wordCount;

        public URTrieNode(int value){
            this.value = value;
            children = new ArrayList<>();
            wordCount = 0;
        }
    }

    private static void insertIntoURTrie(URTrieNode root, int[] matrix){
        URTrieNode current = root;

        for(int value: matrix){
            if(subNode(current, value) == null){
                current.children.add(new URTrieNode(value));
            }
            current = subNode(current, value);
        }
        current.wordCount++;
    }

    private static URTrieNode subNode(URTrieNode node, int value){
        for(URTrieNode element: node.children){
            if(element.value == value) return element;
        }
        return null;
    }
}
