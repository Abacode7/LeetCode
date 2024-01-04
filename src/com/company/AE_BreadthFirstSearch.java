package com.company;

import java.util.*;
public class AE_BreadthFirstSearch {
    /**
     name: A
     children: B C D

     A
     B  C   D
     E  F    G  H

     [A B C D E F G H]

     Queue => []
     Output => [A B C D E F G H]

     - Add element to queue
     - while queue is not empty:
     - remove node, add to aoutput array
     - add children to queue

     *repeat process till queue is empty.
     **/
    static class Node {
        String name;
        List<Node> children = new ArrayList<Node>();

        public Node(String name) {
            this.name = name;
        }

        public List<String> breadthFirstSearch(List<String> array) {
            Queue<Node> queue = new LinkedList<>();
            queue.add(this);

            while(!queue.isEmpty()){
                Node currentNode = queue.remove();
                array.add(currentNode.name);

                if(currentNode.children != null){
                    queue.addAll(currentNode.children);
                }
            }
            // Write your code here.
            return array;
        }

        public Node addChild(String name) {
            Node child = new Node(name);
            children.add(child);
            return this;
        }
    }
}
