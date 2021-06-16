package EasyLevelQuestions;

import java.util.*;

// LC Question 36
public class ValidSudoku {

    public static void main(String[] args){
        String[][] board = {{"5","3",".",".","7",".",".",".","."}
                        ,{"6",".",".","1","9","5",".",".","."}
                        ,{".","9","8",".",".",".",".","6","."}
                        ,{"8",".",".",".","6",".",".",".","3"}
                        ,{"4",".",".","8",".","3",".",".","1"}
                        ,{"7",".",".",".","2",".",".",".","6"}
                        ,{".","6",".",".",".",".","2","8","."}
                        ,{".",".",".","4","1","9",".",".","5"}
                        ,{".",".",".",".","8",".",".","7","9"}};
        // To return true

        // Convert to character array
        char[][] charBoard = new char[board.length][board.length];
        for(int i=0; i<board.length; i++){
            for(int j=0; j<board.length; j++){
                charBoard[i][j] = board[i][j].charAt(0);
            }
        }

        // Run method
        System.out.println(isValidSudoku(charBoard));
    }

    // Runtime & Space O(1) since it a 9x9 array
    public static boolean isValidSudokuImproved(char[][] board) {
        Set<String> set = new HashSet<>();
        for(int i=0; i<9; i++){
            for(int j=0; j<9; j++){
                char currentValue = board[i][j];
                if(currentValue != '.'){
                    if( !set.add(currentValue + "in row" + i) ||
                            !set.add(currentValue + "in column" + j) ||
                            !set.add(currentValue + "in box" + i/3 + "-" + j/3)) return false;
                }
            }
        }

        return true;
    }

    // Initial Solution
    // Todo: Merge loops of board element traversal
    public static boolean isValidSudoku(char[][] board) {
        // Validation the rows
        for(int i=0; i<board.length; i++){
            Set<Character> set = new HashSet<>();
            for(int j=0; j<board[0].length; j++){
                if(board[i][j] != '.'){
                    if(set.contains(board[i][j])) return false;
                    set.add(board[i][j]);
                }
            }
        }

        // Validation the columns
        for(int i=0; i<board[0].length; i++){
            Set<Character> set = new HashSet<>();
            for(int j=0; j<board.length; j++){
                if(board[j][i] != '.'){
                    if(set.contains(board[j][i])) return false;
                    set.add(board[j][i]);
                }
            }
        }

        // Validation the sections
        for(int x=0; x<3; x++){
            for(int y=0; y<3; y++){

                Set<Character> set = new HashSet<>();
                for(int i = 0 + x*3; i < 3 + x*3; i++){
                    for(int j = 0 + y*3; j < 3 + y*3; j++){
                        if(board[i][j] != '.'){
                            if(set.contains(board[i][j])) return false;
                            set.add(board[i][j]);
                        }
                    }
                }
            }
        }

        return true;
    }
}
