package com.company;

import java.util.ArrayList;
import java.util.List;

public class GFG_TrieTest {

    public static void main(String[] args){
        int[][] a = {{1, 1, 0, 1},{1, 0, 0, 1},{1, 1, 0, 1}};
        System.out.println(uniqueRow(a, 3, 4));

        int[][] b = {{0, 0, 0, 1}, {0, 0, 0, 1}};
        System.out.println(uniqueRow(b, 2, 4));

        int[][] c = {{1, 0, 0, 1, 0, 1}, {1, 0, 0, 1, 0, 1}, {0, 0, 0, 1, 0, 0}, {0, 0, 0, 0, 0, 0}, {0, 0, 0, 1, 0, 0}, {0, 0, 0, 1, 0, 0}, {0, 0, 0, 1, 0, 0}, {0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0}, {0, 0, 0, 0, 0, 0}};
        System.out.println(uniqueRow(c, 10, 6));
    }

    public static ArrayList<ArrayList<Integer>> uniqueRow(int a[][],int r, int c){
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
