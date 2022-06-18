package com.company;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Amazon_OS_2 {
    /**
    You are given a boggle board, ie a rectangular table filled with letters, like the following:

    E.g. Board =
            +-------------------+
            |  A |  B |  R |  Z | APPLE
            |----+----+----+----|
            |  R |  U |  E |  A |
            |----+----+----+----|
            |  G |  E |  D |  D |
            |----+----+----+----|
            |  S |  R |  L |  T | //BURGER
            |----+----+----+----|
            |  O |  M |  A |  K |
            +-------------------+

    And you are also provided a dictionary of valid words.
    For example the list [APPLE, BURGER, NOTE, MALT, PAPER, READ, RADAR, SORT]

    The task is to write an algorithm to find all the valid words from the given list, hidden in the board. Starting from any letter you are allowed to move in horizontal and vertical directions with the only constraint that you can use each board cell only once for a particular word.

    In the above example the correct output would be the list [BURGER, MALT, READ]

    O(hmn)
    where h is

     **/
    public List<String> findPresentWords(List<String> words, char[][] board) {
        List<String> result = new ArrayList<>();
        for(String word: words){
            if(searchWordInBoard(word, board)) result.add(word);
        }
        return result;
    }


    public boolean searchWordInBoard(String word, char[][] board) {
        Set<int[]> indexes = new HashSet<>();

        for(int i=0; i<board.length; i++){
            for(int j=0; j<board[0].length; j++){
                if(board[i][j] == word.charAt(i)) indexes.add(new int[]{i, j});
            }
        }

        for(int[] index: indexes){
            if(searchFromIndex(index, word, 0, new HashSet<>(), board)) return true;
        }

        return false;
    }

    public boolean searchFromIndex(int[] index, String word, int position, Set<int[]> explored, char[][] board) {
        explored.add(index);
        if(word.charAt(position) != board[index[0]][index[1]]) return false;

        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for(int[] direction: directions){
            if(!explored.contains(direction)){
                int currentX = index[0] + direction[0];
                int currentY = index[1] + direction[1];

                if(currentX >=0 && currentX < board.length && currentY >= 0 && currentY < board[0].length){
                    // Todo: Do something
                }
            }

        }

        return true;
    }
}
