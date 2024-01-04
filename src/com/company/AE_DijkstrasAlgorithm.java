package com.company;

import java.util.*;

public class AE_DijkstrasAlgorithm {
    /**
     0 -> 1(7)
     1 -> 2(6) 3(30) 4(3)
     2 -> 3(14)
     3 -> 4(2)
     4 ->
     5 ->

     Start: 0

     0 1  2  3  4
     7     27
     **/
    public int[] dijkstrasAlgorithm(int start, int[][][] edges) {
        // Write your code here.
        int[] result = new int[edges.length];
        Arrays.fill(result, -1);

        Queue<Integer> queue = new LinkedList<>();
        Map<Integer, Integer> map = new HashMap<>();

        queue.add(start);
        map.put(start, 0);

        while(!queue.isEmpty()){
            Integer node = queue.remove();
            for(int[] edge: edges[node]){
                if(map.containsKey(edge[0]) && edge[1] + map.get(node) > map.get(edge[0])) continue;
                queue.offer(edge[0]);
                map.put(edge[0], edge[1] + map.get(node));
            }
            result[node] = map.get(node);
        }

        return result;
    }

    // Todo: rework: time limit exceeded
    public int[] djk(int start, int[][][] edges) {
        // Write your code here.
        int[] result = new int[edges.length];

        Queue<Node> queue = new LinkedList<>();
        Set<Integer> set = new HashSet<>();

        queue.add(new Node(start, 0));
        set.add(start);

        while(!queue.isEmpty()){
            Node node = queue.remove();
            for(int[] edge: edges[node.vertice]){
                // Todo: Add condition edge[1] + node.path > edge[0].current value
                // Reasons why DS was changed to a Map
                if(set.contains(edge[0])) continue;
                queue.offer(new Node(edge[0], edge[1] + node.path));
                set.add(edge[0]);
            }
            result[node.vertice] = node.path;
        }
        return result;
    }

    private static class Node{
        int vertice;
        int path;

        public Node(int vertice, int path){
            this.vertice = vertice;
            this.path = path;
        }
    }
}
