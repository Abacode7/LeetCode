package com.company;

import java.util.*;

public class Amazon_OS {

    public static void main(String[] args){
        char[][] board = new char[][]{
                {'A', 'B', 'R', 'Z'},
                {'R', 'U', 'E', 'A'},
                {'G', 'E', 'D', 'D'},
                {'S', 'R', 'L', 'T'},
                {'O', 'M', 'A', 'K'},
        };

        List<String> words = new ArrayList<>(Arrays.asList("APPLE", "BURGER", "NOTE", "MALT", "PAPER", "READ", "RADAR", "SORT"));
        System.out.println("Find words in board: " + searchWordsInBoard(board, words));


        int[] regionStart = new int[]{1, 3, 4, 6, 9};
        int[] regionEnd = new int[]{2, 8, 5, 7, 10};
        System.out.println("Minimum retailer to relocate: " + minRetailersToRelocate(regionStart, regionEnd));


        int[] ratings = new int[]{2, 1, 2, 4, 1, 5, 1, 4, 6, 7, 4, 500};
        System.out.println("Ratings to remove: " + maximumIncreasingRating(ratings));


        System.out.println("Is substring: " + isSubstring("tpabcfhjkabcdzy", "abcd"));
        System.out.println("Is substring: " + isSubstring("aaaaaaaaaaaaaaba", "aaaba"));
        System.out.println("Is substring: " + isSubstring("tpabcfhjkabcdzy", "abce"));
        System.out.println("Is substring: " + isSubstring("aaaaaaaaaaaaaaba", "abcd"));
    }


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

     Direction: i,j => {-1, 0} {}-1,0 1,0 0,-1 {0,1}
     **/

    public static List<String> searchWordsInBoard(char[][] board, List<String> words){
        List<String> wordsInBoard = new ArrayList<>();
        for(String word: words){
            if(searchWordInBoard(board, word)) wordsInBoard.add(word);
        }
        return wordsInBoard;
    }

    private static boolean searchWordInBoard(char[][] board, String word){
        for(int i=0; i<board.length; i++){
            for(int j=0; j<board[0].length; j++){
                if(word.charAt(0) == board[i][j]){
                    if(searchWordAtIndex(board, i, j, word, 0, new HashSet<>())) return true;
                }
            }
        }
        return false;
    }

    private static boolean searchWordAtIndex(char[][] board, int rowIndex, int colIndex, String word, int wordIndex, Set<String> visited){
        String boardPosition = rowIndex + "-" + colIndex;
        visited.add(boardPosition);

        if(wordIndex >= word.length()) return false;
        if(board[rowIndex][colIndex] != word.charAt(wordIndex)) return false;
        if(board[rowIndex][colIndex] == word.charAt(wordIndex) && wordIndex == word.length()-1) return true;

        int[][] directions = new int[][]{{-1,0}, {1,0}, {0,-1}, {0,1}};

        for(int[] direction: directions){
            int newRow = rowIndex + direction[0];
            int newCol = colIndex + direction[1];
            String newBoardPosition = newRow + "-" + newCol;

            if(isValidIndex(board, newRow, newCol) && !visited.contains(newBoardPosition)){
                boolean wordFound = searchWordAtIndex(board, newRow, newCol, word, wordIndex+1, visited);
                if(wordFound) return true;
            }
        }

        return false;
    }

    private static boolean isValidIndex(char[][] board, int rowIndex, int colIndex){
        return (rowIndex >=0 && rowIndex < board.length) && (colIndex >= 0 && colIndex < board[0].length);
    }




    /**
     * 2022 SDE I Onsite
     *
     * Given two strings, big & small: check if small is substring of big
     * Examples:
     * big: tpabcfhjkabcdzy
     * small: abcd
     *
     * big: aaaaaaaaaaaaaaba
     * small: aaaba
     *
     * big: abcde
     * small: de
     *
     * Brute force Solution: O(mn) time, O(mn) space
     */
    public static boolean isSubstring(String bigString, String smallString){
        // Brute force
        int smallStringLen = smallString.length();
        int smallStringEndIndex = smallStringLen - 1;
        int bigStringLen = bigString.length();

        for(int i=0; i <= bigStringLen - smallStringLen; i++){
            int substringEndIndex = i + smallStringLen-1;
            if(substringEndIndex >= bigStringLen) continue;

            if(bigString.charAt(i) == smallString.charAt(0) &&
                    bigString.charAt(substringEndIndex) == smallString.charAt(smallStringEndIndex)){
                String substring = bigString.substring(i, substringEndIndex+1); // n operation
                if(substring.equals(smallString)) return true;
            }
        }
        return false;
    }




    /**
     * PK SDE II Onsite
     * Reference:
     * https://www.reddit.com/r/leetcode/comments/1kcm20w/amazon_sde_ii/
     *
     * Question 2
     * Amazons online shopping apa has n retailers on board, Retailer operates in the region regionstart[il to regionEnd[i.
     * A set of k retailers is said to be inclusive if there exists at least one retailer such
     * that their operating region intersects with (or touches) all the remaining (k-1) retailers' operating regions.
     * Amazon wants to shift some retailers to a different location.
     * Find the minimum number of retailers to relocate such that the remaining retailers form an inclusive set.
     *
     * Example
     * regionStart [1, 3, 4, 6, 9]
     * regionEnd = [2, 8, 5, 7, 10]
     *
     *
     * Retailer 1 ranges from 1 to 2.
     * Retailer 5 ranges from 9 to 10.
     * Retailers 2, 3, and 4 already are inclusive.
     *
     * Move retailers 1 and 5 to intersect with Retailer 2's region. The minimum number of retailers to relocate is 2.

     * Function Description
     * Complete the function minRetailersToRelocate in the editor below.
     * minRetailers ToRelocate has the following parameter(s):
     * int regionStart/n); the left ends of the operating regions int reglonEnd/n): the right ends of the operating regions
     *
     * Returns
     * int: the minimum number of retailers to relocate
     *
     *
     *
     * Problem Breakdown
     *      s1      s2      e1      s3      e2      s4      e3       e4
     * 1    2       3       4       5       6       7       8         9
     *
     *  s1 - s2
     *  s2 - s3
     *  s3 - s4
     *
     *  Our task is to find the guy with the most intersections
     *  then:
     *   - Get the total retailers in that connection => maxIntersectionCount + 1
     *   - Return total numbers - total
     *               0  1  2  3  4
     *  regionStart [1, 3, 4, 6, 9]
     *  regionEnd = [2, 8, 5, 7, 10]
     *               0  2  1  1  0
     *
     *               3 Retailers in the largest connection
     *               Result = total - 3
     *
     *  Runtime: O(n^2) time, O(n) space
     */
    public static int minRetailersToRelocate(int[] regionStart, int[] regionEnd){
        int regionStartLength = regionStart.length;
        int regionEndLength = regionEnd.length;

        if(regionStartLength != regionEndLength) return -1;
        if(regionStartLength <= 1) return 0;

        int[] retailerIntersections = new int[regionStartLength];
        int maxIntersections = Integer.MIN_VALUE;

        for(int i=0; i<regionStartLength-1; i++){
            for(int j=i+1; j < regionStartLength; j++){
                if(isRegionIntersect(regionStart[i], regionEnd[i], regionStart[j], regionEnd[j])){
                    retailerIntersections[i]++;
                    retailerIntersections[j]++;

                    if(retailerIntersections[i] > maxIntersections){
                        maxIntersections = retailerIntersections[i];
                    }

                    if(retailerIntersections[j] > maxIntersections){
                        maxIntersections = retailerIntersections[j];
                    }
                }
            }
        }

        int totalRetailersInMaxIntersection = maxIntersections + 1;
        int totalRetailers = retailerIntersections.length;

        return totalRetailers - totalRetailersInMaxIntersection;
    }

    private static boolean isRegionIntersect(int regionOneStart, int regionOneEnd, int regionTwoStart, int regionTwoEnd){
        return !(regionTwoStart > regionOneEnd || regionTwoEnd < regionOneStart);
    }




    /**
     * PK SDE II Onsite
     * Reference:
     * https://www.reddit.com/r/leetcode/comments/1kcm20w/amazon_sde_ii/
     *
     * Question 1:
     * Given a list of integer ratings, what is the minimum number of ratings you must remove
     * from the list such that all the remaining ratings are identical?
     *
     * def maximum_increasing_ratings(ratings: list[int]) -> int
     * n = len(ratings) if n == 0: return 0
     *
     * [2 1 2 4 1 5 1 4 6 7 4 500]=
     * 1: 3
     * 2: 2
     * 4: 2
     * 5: 1
     * 6: 1
     * 7: 1
     * 500: 1
     *
     * Min Rating Removal = Total Number of Ratings - (Freq of most frequent rating)
     *
     * Complexity: O(n) time, O(n) space
     *
     * Test Example: [7, 3, 4, 2, 3]
     * max = 2
     *
     * 7: 1
     * 3: 2
     * 4: 1
     * 2: 1
     */
    public static int maximumIncreasingRating(int[] ratings){
        int numOfRatings = ratings.length;
        if(numOfRatings == 0) return 0;

        Map<Integer, Integer> ratingsFrequency = new HashMap<>();
        int maxRatingFrequency = Integer.MIN_VALUE;

        for(int rating: ratings){
            int ratingFrequency = ratingsFrequency.getOrDefault(rating, 0);
            ratingFrequency++;
            ratingsFrequency.put(rating, ratingFrequency);

            if(ratingFrequency > maxRatingFrequency) {
                maxRatingFrequency = ratingFrequency;
            }
        }

        return numOfRatings - maxRatingFrequency;
    }

}
