package com.company;

import java.util.*;
public class AE_DepthFirstSearch {
    /**
     A
     B     C    D
     E   F       G  H


     [A B E F C D G H]

     name: A
     children: B C D

     name: B
     children: E F

     - Get it's name, store in the output array
     - Repeat the process for it's children array from left to right.

     Input Parameter: Node, Output array
     **/
    static class Node {
        String name;
        List<Node> children = new ArrayList<Node>();

        public Node(String name) {
            this.name = name;
        }

        public List<String> depthFirstSearch(List<String> array) {
            // Write your code here.
            depthFirstSearch(this, array);
            return array;
        }

        private void depthFirstSearch(Node node, List<String> array){
            array.add(node.name);
            if(node.children != null){
                for(Node childNode: node.children){
                    depthFirstSearch(childNode, array);
                }
            }
        }

        public Node addChild(String name) {
            Node child = new Node(name);
            children.add(child);
            return this;
        }
    }
}
