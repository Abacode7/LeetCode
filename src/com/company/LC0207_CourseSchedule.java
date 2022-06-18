package com.company;

import java.util.*;

public class LC0207_CourseSchedule {
    public static void main(String[] args){
        int[][] input = {{1,0}, {0, 1}};
        System.out.println(canFinish(2, input));
    }

    static Set<Integer> explored = new HashSet<>();
    static Set<Integer> exploring = new HashSet<>();

    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        if(numCourses == 1 || prerequisites.length == 0) return true;

        // Build directed graph
        Map<Integer, List<Integer>> adjacencyList = new HashMap<>();
        for(int j=0; j<numCourses; j++)
            adjacencyList.put(j, new ArrayList<Integer>());

        for(int i=0; i<prerequisites.length; i++){
            List<Integer> list = adjacencyList.getOrDefault(prerequisites[i][0], new ArrayList<>());
            list.add(prerequisites[i][1]);
            adjacencyList.put(prerequisites[i][0], list);
        }

        for(int i=0; i<numCourses; i++)
            if(!searchCourses(i, adjacencyList)) return false;

        return true;
    }

    public static boolean searchCourses(int index, Map<Integer, List<Integer>> adjList){
        exploring.add(index);

        boolean noCycle = true;
        for(Integer adjacent: adjList.get(index)){
            if(explored.contains(adjacent)) continue;

            if(exploring.contains(adjacent)) return false;

            noCycle = noCycle & searchCourses(adjacent, adjList);
        }

        exploring.remove(index);
        explored.add(index);

        return noCycle;
    }
}
