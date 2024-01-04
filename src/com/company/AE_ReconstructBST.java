package com.company;

import java.util.*;

public class AE_ReconstructBST {
    /**
     [1 3 5 7 9 11 13]

           7
        3     11
     1   5  9    13

     [7 3 1 5 11 9 13]

     Key: Preorder traversal format list gives the format for input to BSt
     to get the same structure.
     i.e BST's to be reconstructed can be stored in their Preorder traversal format.

     Pre order traversal goes:
     - Visit parent
     - Visit left subtree
     - Visit right subtree

     **/

    // This is an input class. Do not edit.
    static class BST {
        public int value;
        public BST left = null;
        public BST right = null;

        public BST(int value) {
            this.value = value;
        }
    }

    private BST insert(BST bst, int value) {
        if(bst == null) return new BST(value);
        if(bst.value > value){
            bst.left = insert(bst.left, value);
        }else{
            bst.right = insert(bst.right, value);
        }
        return bst;
    }

    public BST reconstructBst(ArrayList<Integer> preOrderTraversalValues) {
        BST bst = new BST(preOrderTraversalValues.get(0));
        for(int i=1; i<preOrderTraversalValues.size(); i++){
            insert(bst, preOrderTraversalValues.get(i));
        }
        // Write your code here.
        return bst;
    }
}
