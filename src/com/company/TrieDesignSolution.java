package com.company;

public class TrieDesignSolution {
    public static void main(String[] args){
        Trie trie = new Trie();

        // Dictionary
        String[] words = new String[]{"and", "ant", "ball", "cup"};
        for(String word: words){
            System.out.println("Insert word: " + word);
            trie.insert(word);
        }

        System.out.println("Search word: an: " + trie.search("an"));
        System.out.println("Search prefix: an: " + trie.searchPrefix("an"));
        System.out.println("Search word: ant: " + trie.search("ant"));
        System.out.println("Search word: and: " + trie.search("and"));
        System.out.println("Search word: ball: " + trie.search("ball"));

        System.out.println("Delete word: an: " + trie.delete("an"));
        System.out.println("Delete word: ant: " + trie.delete("ant"));
        System.out.println("Delete word: ant again: " + trie.delete("ant"));

        System.out.println("Insert word: an" );
        trie.insert("an");

        System.out.println("Search word: an: " + trie.search("an"));
        System.out.println("Delete word: and: " + trie.delete("and"));
        System.out.println("Search word: and: " + trie.search("and"));
        System.out.println("Search word: an: " + trie.search("an"));


        System.out.println("Test delete apple, does not erase app");
        Trie trie1 = new Trie();
        trie1.insert("app");
        trie1.insert("apple");
        System.out.println("Delete apple: " + trie1.delete("apple"));
        System.out.println("Search app: " + trie1.search("app"));

        System.out.println("Test delete anderson, does not erase ant");
        Trie trie2 = new Trie();
        trie2.insert("anderson");
        trie2.insert("ant");
        System.out.println("Delete anderson: " + trie2.delete("anderson"));
        System.out.println("Search anderson: " + trie2.search("anderson"));
        System.out.println("Search ant: " + trie2.search("ant"));

        System.out.println("Test delete anderson, does not erase ant");
        Trie trie3 = new Trie();
        trie3.insert("anderson");
        trie3.insert("anderson");
        System.out.println("Delete anderson: " + trie3.delete("anderson"));
        System.out.println("Search anderson: " + trie3.search("anderson"));
    }
}

class Trie {

    private TrieNode root;

    public Trie(){
        this.root = new TrieNode();
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
    public void insert(String word){
        TrieNode current = root;

        for(char c: word.toCharArray()){
            if(current.children[c - 'a'] == null){
                current.children[c - 'a'] = new TrieNode();
                current.childrenCount++;
            }
            current = current.children[c - 'a'];
        }
        current.wordCount++;
    }

    /**
     * General Note for Tries:
     * While we are operating at character c, the current TrieNode is the parent of c.
     * Keep this in mind
     * */
    public boolean search(String word){
        TrieNode current = root;

        for(char c: word.toCharArray()){
            if(current.children[c - 'a'] == null){
                return false;
            }
            current = current.children[c - 'a'];
        }
        return current.wordCount > 0;
    }

    public boolean searchPrefix(String word){
        TrieNode current = root;

        for(char c: word.toCharArray()){
            if(current.children[c - 'a'] == null){
                return false;
            }
            current = current.children[c - 'a'];
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
     * Code Intuition
     * lastMultiBranch is the node of the last character with multiple child nodes
     * Given ant & anderson in trie, deleting anderson, lastCurrentNode is n & lastCurrentChar is d
     * */
    public boolean delete(String word){
        TrieNode current = root;

        TrieNode lastMultiBranchNode = root;
        char lastMultiBranchChar = word.charAt(0);

        for(char c: word.toCharArray()){
            if(current.children[c - 'a'] == null) return false;
            else{
                if(current.childrenCount > 1 || current.wordCount > 0){
                    lastMultiBranchNode = current;
                    lastMultiBranchChar = c;
                }
            }

            current = current.children[c - 'a'];
        }

        if(current.wordCount < 1) return false; // word not in Trie

        // If word is prefix, decrease word count only
        if(current.childrenCount > 0){ // Add (OR current.wordCount > 1) if we want deletion of duplicates
            current.wordCount--;       // to only affect 1 of the duplicates
            return true;
        }

        // is suffix, delete up to the last node with multiple children
        // OR delete up to root
        lastMultiBranchNode.children[lastMultiBranchChar - 'a'] = null;
        lastMultiBranchNode.childrenCount--;
        return true;
    }


    static class TrieNode {
        TrieNode[] children;
        int childrenCount;
        int wordCount;


        public TrieNode(){
            this.children = new TrieNode[26];
            this.childrenCount = 0;
            this.wordCount = 0;
        }
    }
}