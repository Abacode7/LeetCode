package com.company;

import java.util.*;

public class AE_TopologicalSort {
    /**
     [1 2]
     1 ->
     2 -> 1 3 4
     3 -> 1 4
     4 ->

     1 4 3 2

     - create adjacency list
     - Use a dfs for topological sort
     - Aim is to explore deeper till we find a node that:
     - Is not a prerequisite or
     - has been explored

     *Edge Case:
     - A Cycle: Cycle has occured if we find it in exploring set while exploring
     - return empty list in this case
     **/

    public static List<Integer> topologicalSort(List<Integer> jobs, List<Integer[]> deps) {
        // Write your code here.
        Map<Integer, List<Integer>> adjacencyList = createAdjacencyList(jobs, deps);
        List<Integer> sortedList = new ArrayList<>();

        Set<Integer> explored = new HashSet<>();


        for(Integer job: jobs){
            if(!explored.contains(job)){
                boolean isCycle = dfs(adjacencyList, sortedList, explored, new HashSet<>(), job);
                if(isCycle) return new ArrayList<>();
            }
        }
        return sortedList;
    }

    public static boolean dfs(Map<Integer, List<Integer>> adjList, List<Integer> result, Set<Integer> explored, Set<Integer> exploring, int job){
        exploring.add(job);

        for(Integer value: adjList.get(job)){
            if(exploring.contains(value)) return true;

            if(!explored.contains(value)){
                boolean isCycle = dfs(adjList, result, explored, exploring, value);
                if(isCycle) return true;
            }
        }
        result.add(job);

        exploring.remove(job);
        explored.add(job);
        return false;
    }

    public static Map<Integer, List<Integer>> createAdjacencyList(List<Integer> jobs, List<Integer[]> deps){
        Map<Integer, List<Integer>> map = new HashMap<>();
        for(Integer job: jobs){
            map.put(job, new ArrayList<>());
        }

        for(Integer[] value: deps){
            Integer prerequisite = value[0];
            Integer job = value[1];

            List<Integer> list = map.get(job);
            list.add(prerequisite);
            map.put(job, list);
        }

        return map;
    }
}
