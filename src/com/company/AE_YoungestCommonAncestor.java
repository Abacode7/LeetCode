package com.company;

import java.util.*;
public class AE_YoungestCommonAncestor {
    /**

     I -> D -> B -> A
     E -> B -> A

     - Aim is to get the Ancestral tree of both Nodes
     - Iterate from the top most Ancestor of both Ancestral tree
     to the point where they differ in Ancestors.

     *Use Set to hold both ancestor trees.
     *Aim is to find first repeated ancestor in the list.
     *For each Descendant:
     - ensure not null
     - if present in set, return it (means it's been discovered by other descendant tree)
     -* Iterate descendant to next ancestor
     **/


    public static AncestralTree getYoungestCommonAncestor(
            AncestralTree topAncestor, AncestralTree descendantOne, AncestralTree descendantTwo) {

        Set<AncestralTree> set = new HashSet<>();
        while(descendantOne != null || descendantTwo != null){
            if(descendantOne != null){
                if(set.contains(descendantOne)) return descendantOne;
                set.add(descendantOne);
                descendantOne = descendantOne.ancestor;
            }

            if(descendantTwo != null){
                if(set.contains(descendantTwo)) return descendantTwo;
                set.add(descendantTwo);
                descendantTwo = descendantTwo.ancestor;
            }
        }

        // Write your code here.
        return topAncestor; // Replace this line
    }

    static class AncestralTree {
        public char name;
        public AncestralTree ancestor;

        AncestralTree(char name) {
            this.name = name;
            this.ancestor = null;
        }

        @Override
        public boolean equals(Object obj){
            if(obj == this) return true;
            if(!(obj instanceof AncestralTree)) return false;

            AncestralTree ant = (AncestralTree) obj;
            if(name == ant.name && ancestor.equals(ant.ancestor)) return true;
            return false;
        }

        @Override
        public int hashCode(){
            return Objects.hash(name, ancestor);
        }

        // This method is for testing only.
        void addAsAncestor(AncestralTree[] descendants) {
            for (AncestralTree descendant : descendants) {
                descendant.ancestor = this;
            }
        }
    }
}
