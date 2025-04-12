package com.company;

import java.util.*;

public class GFG_GraphProblems {
    public static void main(String[] args){
        int[][] edges = new int[][]{{3, 0}, {1, 0}, {2,0}};
        System.out.println(topologicalSort(4, edges));
    }

    /**
     * Solution: BFS, O(n) time, where nodes visited exactly once
     * O(n) space for the list & set, queue takes max element in level which is <= n/2 + 1
     */
    public ArrayList<Integer> bfs(ArrayList<ArrayList<Integer>> adj) {
        // code here
        ArrayList<Integer> list = new ArrayList<>();
        if(adj == null || adj.isEmpty()) return list;

        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        queue.offer(0);
        visited.add(0);
        list.add(0);

        while(!queue.isEmpty()){
            int node = queue.poll();
            List<Integer> adjacents = adj.get(node);

            for(int adjacent: adjacents){
                if(!visited.contains(adjacent)){
                    queue.offer(adjacent);
                    visited.add(adjacent);
                    list.add(adjacent);
                }
            }
        }
        return list;
    }



    /**
     * Solution: DFS, O(n) time, we traverse every element in graph
     * O(n) for list and call stack
     */
    public ArrayList<Integer> dfs(ArrayList<ArrayList<Integer>> adj) {
        // Code here
        ArrayList<Integer> list = new ArrayList<Integer>();
        if(adj == null || adj.isEmpty()) return list;

        Set<Integer> visited = new HashSet<>();
        dfsTraverse(adj, 0, visited, list);
        return list;
    }

    private void dfsTraverse(ArrayList<ArrayList<Integer>> adj, int node, Set<Integer> visited, List<Integer> list){
        if(visited.contains(node)) return;
        list.add(node);
        visited.add(node);

        for(int adjacent: adj.get(node)){
            if(!visited.contains(adjacent)){
                dfsTraverse(adj, adjacent, visited, list);
            }
        }
    }



    /**
     * Intuition
     * We need to start from every land and dfs traverse them, turning it to water,
     * so it's not explored again.
     * Then counting the number of such possible traversals
     *
     * From point i, j
     * directions = {{1, -1}, {1, 0}, {1, 1}, {0, -1}, {0, 1}, {-1, -1}, {-1, 0}, {-1, 1}}
     *
     * Solution: DFS
     * O(m*n) time, number of loops, where we dfs traversed lands which are maximally m * n
     * O(m*n) space, where there are all lands and our call stack extends to m*n, & no extra space
     * */
    public int countIslands(char[][] grid) {
        // Code here
        if(grid == null || grid.length == 0) return 0;

        int m = grid.length;
        int n = grid[0].length;

        int numOfIslands = 0;
        for(int i=0; i < m; i++){
            for(int j=0; j < n; j++){
                if(grid[i][j] == 'L'){
                    dfsTraverseLand(grid, i, j);
                    numOfIslands++;
                }
            }
        }

        return numOfIslands;
    }

    private void dfsTraverseLand(char[][] grid, int i, int j){
        grid[i][j] = 'W';

        int m = grid.length;
        int n = grid[0].length;

        int[][] directions = new int[][]{{1, -1}, {1, 0}, {1, 1}, {0, -1}, {0, 1}, {-1, -1}, {-1, 0}, {-1, 1}};

        for(int[] dir: directions){
            int newI = i + dir[0];
            int newJ = j + dir[1];

            if((newI >= 0 && newI < m) && (newJ >= 0 && newJ < n) && grid[newI][newJ] == 'L'){
                dfsTraverseLand(grid, newI, newJ);
            }
        }
    }



    /**
     * [[1, 3], [2, 3], [4, 1], [4, 0], [5, 0], [5,2]]
     * 0:
     * 1: 3
     * 2: 3
     * 3:
     * 4: 1 0
     * 5: 0 2
     *
     * - Iterate adj list:
     * - Explore nodes deeply (and it adjs)
     * - Add them list when completely explored
     * - Reverse list (according to order question demands)
     *  - Given, u -> v, if demand is u before v, then reverse
     *      else, u -> v, v before u, leave as is
     *
     * Solution: DFS
     * O(V+E) time since we explore every vertex, and every edge in adjacency list
     * O(V) auxiliary space, since we store all vertices in space
     * NB: we don't include the space of adjacency list since it's considered the graph
     * NB: complexity for building an adjacecy list is O(V+E) time, O(V+E) space
     * */
    public static ArrayList<Integer> topologicalSort(int V, int[][] edges) {
        // code here
        Set<Integer> visited = new LinkedHashSet<>();
        Map<Integer, List<Integer>> adjacencyList = buildAdjacencyList(V, edges);

        for(int key: adjacencyList.keySet()){
            dfsTraverse(key, adjacencyList, visited, new HashSet<Integer>());
        }

        ArrayList<Integer> list = new ArrayList<>(visited);
        Collections.reverse(list);
        return list;
    }

    private static void dfsTraverse(int node, Map<Integer, List<Integer>> adjacencyList, Set<Integer> visited, Set<Integer> visiting){
        if(visited.contains(node) || visiting.contains(node)) return;
        visiting.add(node);

        for(int adjacent: adjacencyList.get(node)){
            if(!visited.contains(adjacent) && !visiting.contains(adjacent)){
                dfsTraverse(adjacent, adjacencyList, visited, visiting);
            }
        }
        // visiting.remove(node) - necessary if we have to detect cycle
        visited.add(node);
    }

    private static Map<Integer, List<Integer>> buildAdjacencyList(int V, int[][] edges){
        Map<Integer, List<Integer>> adjacencyList = new HashMap<>();
        for(int i=0; i<V; i++){
            adjacencyList.put(i, new ArrayList<>());
        }

        for(int[] edge: edges){
            List<Integer> list = adjacencyList.get(edge[0]);
            list.add(edge[1]);
            adjacencyList.put(edge[0], list);
        }
        return adjacencyList;
    }



    /**
     * Live Breakdown
     * Knights directions from i, j
     * directions: {-2, 1}, {-2, -1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}
     * Intuition
     * There are 8 directions the knight can go using the long L from the night position:
     * 2North 1East, 2North 1West, 2South 1East, 2South 1West, 2East 1North,
     * 2East 1South, 2West 1North, 2West 1South
     * Others like 1North 2East, lands in the same position as 2East 1North, so use LONG Ls to get the indexes
     *
     * With the above knowledge, we can do a Breadth First Search, counting the LEVEL DEPTH as we iterate
     * till we find the target position.
     * The loop stops if:
     *  - We find the target
     *  - We exhaust the valid indexes to be explored
     *
     * The level depth at which we find the target is the minimum distance from the initial position
     *
     * Solution: BFS - The Shortest Path Algorithm
     * O(n^2) time, since that's the max number of cells in the chess board
     * O(n^2) space, if all gets visited at some point.
     * */
    public int minStepToReachTarget(int[] knightPos, int[] targetPos, int n) {
        // Code here
        // Accounting for extreme edge cases
        if(n == 1){
            if(targetPos[0] != knightPos[0]) return -1;
            else return 0;
        }
        if(knightPos.length != 2 || targetPos.length != 2) return -1;

        Set<String> visited = new HashSet<>();
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(knightPos);
        visited.add(knightPos[0] + "-" + knightPos[1]);

        final int[][] directions = new int[][]{{-2, 1}, {-2, -1}, {-1, 2}, {1, 2}, {2, 1}, {2, -1}, {1, -2}, {-1, -2}};

        int minDistance = -1;
        while(!queue.isEmpty()){
            minDistance++;

            int levelSize = queue.size();
            while(levelSize > 0){
                int[] node = queue.poll();
                if(node[0] == targetPos[0] && node[1] == targetPos[1]) return minDistance;

                for(int[] dir: directions){
                    int newX = node[0] + dir[0];
                    int newY = node[1] + dir[1];
                    String position = newX + "-" + newY;

                    if(newX >= 1 && newX <= n && newY >= 1 && newY <= n && !visited.contains(position)){
                        queue.offer(new int[]{newX, newY});
                        visited.add(position);
                    }
                }
                levelSize--;
            }
        }
        return -1;
    }


}
