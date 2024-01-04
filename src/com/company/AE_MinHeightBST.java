package com.company;

import java.util.*;

public class AE_MinHeightBST {
    /**
     Odd Length Case:
     [1 3 5 7 9 11 13]

            7
        3     11
     1  5   9   13

     Even Length Case:
     [1 3 5 7 9 11]

            7
        3    11
     1  5    9

     **/
    public static BST minHeightBst(List<Integer> array) {
        // Write your code here.
        int i = 0;
        int j = array.size() - 1;

        return minHeightBst(array, i, j);
    }

    public static BST minHeightBst(List<Integer> array, int lowIndex, int highIndex){
        if(lowIndex > highIndex) return null;
        int totalLength = lowIndex + highIndex;
        int midIndex = totalLength % 2 == 0 ? totalLength / 2 : totalLength / 2 + 1;

        BST bst = new BST(array.get(midIndex));
        bst.left = minHeightBst(array, lowIndex, midIndex-1);
        bst.right = minHeightBst(array, midIndex+1, highIndex);
        return bst;
    }

    static class BST {
        public int value;
        public BST left;
        public BST right;

        public BST(int value) {
            this.value = value;
            left = null;
            right = null;
        }

        public void insert(int value) {
            if (value < this.value) {
                if (left == null) {
                    left = new BST(value);
                } else {
                    left.insert(value);
                }
            } else {
                if (right == null) {
                    right = new BST(value);
                } else {
                    right.insert(value);
                }
            }
        }
    }
}
