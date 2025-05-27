package com.company;

import java.util.*;

public class GFG_GraphProblems {
    public static void main(String[] args){
        int[][] edges = new int[][]{{3, 0}, {1, 0}, {2,0}};
        System.out.println(topologicalSort(4, edges));


        String[] words = new String[]{"baa", "abcd", "abca", "cab", "cad"};
        String[] words2 = new String[]{"abc", "abc"};
        System.out.println(AlienSolution.findOrder(words2));
    }

    /**
     * Solution: BFS, O(n + e) time, where nodes visited exactly once, where n is the number of vertices
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
     * Solution: DFS, O(n + e) time, we traverse every element in graph, where n is vertices and e is edges
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

    static class Board {
        private int boardSize;

        public Board(int boardSize){
            if(boardSize < 2) throw new IllegalArgumentException("Board should be greater than 2");
            this.boardSize = boardSize;
        }

        private boolean isCellValid(Cell cell){
            return cell.getRow() >= 1 && cell.getRow() <= boardSize && cell.getColumn() >= 1 && cell.getColumn() < boardSize;
        }


        static class Cell {
            private int row;
            private int column;

            public Cell(int row, int column){
                this.row = row;
                this.column = column;
            }

            public int getRow(){
                return row;
            }

            public int getColumn(){
                return column;
            }

            /**
             * 2North 1East, 2North 1West, 2South 1East, 2South 1West, 2East 1North,
             *      * 2East 1South, 2West 1North, 2West 1South
             *
             * North: - row, South: + row, West: - column, East: + column
             */
            public List<Cell> getAdjacentCells(){
                List<Cell> cells = new ArrayList<>();
                int[][] directions = new int[][]{{-2, 1}, {-2, -1}, {2,1}, {2, -1}, {-1, 2}, {1,2}, {-1, -2}, {1, -2}};

                for(int[] dir: directions){
                    int newRow = row + dir[0];
                    int newCol = column + dir[1];
                    cells.add(new Cell(newRow, newCol));
                }
                return cells;
            }

            public boolean equals(Object otherCell){
                if(this == otherCell) return true;
                if(!(otherCell instanceof Cell)) return false;
                Cell newOtherCell = (Cell) otherCell;
                return this.row == newOtherCell.getRow() && this.column == newOtherCell.getColumn();
            }

            public int hashCode(){
                return Objects.hash(row, column);
            }

            public String toString(){
                return row + "-" + column;
            }
        }
    }





    /**
     * Detect Cycle: Directed Graph
     * 0: 1 2
     * 1: 2
     * 2: 0 3
     *
     * Intuition
     * There's a cycle in an undirected graph if we revisit a node while it's still in visiting
     * i.e while it's visiting is true
     *
     *
     * Implementation Notes
     * graph allows us track the adjacency list of the graph.
     * List<List<Integer> supports constant time checks for the adjacents
     *
     * visited ensures we dont fully explore a node multiple times
     * because each node dfsExporation, completely explores and finishes that node.
     *
     * visiting helps track the nodes in current exploration
     * ensure to unmark it when done visiting the node.
     *
     * */
    public boolean isCyclic(int V, int[][] edges) {
        // code here
        List<List<Integer>> graph = buildGraph(V, edges);
        boolean[] visited = new boolean[V];
        boolean[] visiting = new boolean[V];


        for(int i=0; i<V; i++){
            if(!visited[i]){
                boolean isCyclic = dfsHelper(graph, i, visited, visiting);
                if(isCyclic) return true;
            }
        }

        return false;
    }


    private boolean dfsHelper(List<List<Integer>> graph, int node, boolean[] visited, boolean[] visiting){
        visited[node] = true;
        visiting[node] = true;

        List<Integer> adjacents = graph.get(node);
        for(int adjacent: adjacents){
            if(visiting[adjacent]) return true;

            if(!visited[adjacent]){
                boolean isCyclic = dfsHelper(graph, adjacent, visited, visiting);
                if(isCyclic) return true;
            }
        }

        visiting[node] = false;
        return false;
    }

    private List<List<Integer>> buildGraph(int V, int[][] edges){
        List<List<Integer>> graph = new ArrayList<>();

        for(int i=0; i<V; i++){
            graph.add(new ArrayList<>());
        }

        for(int[] edge: edges){
            graph.get(edge[0]).add(edge[1]);
        }
        return graph;
    }





    /**
     * Detect Cycle: Undirected Graph
     * 0: 1 2
     * 1: 0 2
     * 2: 0 1 3
     * 3: 2
     *
     * 0    1   2   3
     *[t    t   t       ] visited
     *[t    t   t       ] visiting
     *
     * Intuition
     * There's a cycle if you revisit a node while it's still in visiting.
     *
     * NB: For undirected graphs, we must ensure to exclude exploring a parent in
     * the adjacency list of the child, because that could seem like a cycle.
     * */
    public boolean isCycle(int vertices, int[][] edges) {
        // Code here
        List<List<Integer>> graph = buildUndirectedGraph(vertices, edges);
        boolean[] visited = new boolean[vertices];
        boolean[] visiting = new boolean[vertices];

        final int falseParent = -1;

        for(int i=0; i<vertices; i++){
            if(!visited[i]){
                boolean isCycle = dfsHelper(graph, i, falseParent, visiting, visited);
                if(isCycle) return true;
            }
        }

        return false;
    }

    private boolean dfsHelper(List<List<Integer>> graph, int vertex, int parent, boolean[] visited, boolean[] visiting){
        visited[vertex] = true;
        visiting[vertex] = true;


        List<Integer> adjacents = graph.get(vertex);
        for(int adjacent: adjacents){
            if(adjacent == parent) continue;

            if(visiting[adjacent]) return true;

            if(!visited[adjacent]){
                if(dfsHelper(graph, adjacent, vertex, visited, visiting)) return true;
            }
        }

        visiting[vertex] = false;
        return false;
    }

    private List<List<Integer>> buildUndirectedGraph(int vertices, int[][] edges){
        List<List<Integer>> graph = new ArrayList<>();

        for(int i=0; i<vertices; i++){
            graph.add(new ArrayList<>());
        }

        for(int[] edge: edges){
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }
        return graph;
    }



    /**
     * Dijkstras Algorithm (for shortest path in weighted graph)
     * 0: 1(1) 2(6)
     * 1: 0(1) 2(3)
     * 2: 1(3) 0(6)
     *
     * Brute force is to explore all possible paths between 0 and the src
     * 2 as destination.
     *
     * Using the Dijstras Algorithm (for Shortest path on weighted graph)
     * Its a BFS with a minHeap which allows us to evaluate the node with smallest weights first
     * (a form of Greedy algorithm)
     * **/
    static class GraphDijkstraUtils {

        public static int[] shortestPath(int vertices, int[][] edges, int src){
            List<List<Edge>> graph = buildGraph(vertices, edges);

            int[] distanceToSource = new int[vertices];
            Arrays.fill(distanceToSource, Integer.MAX_VALUE);
            PriorityQueue<Vertex> queue = new PriorityQueue<>();

            distanceToSource[src] = 0;
            queue.offer(new Vertex(src, 0));


            while(!queue.isEmpty()){

                Vertex vertex = queue.poll();
                List<Edge> adjacentEdges = graph.get(vertex.getData());

                for(Edge edge: adjacentEdges){
                    int source = vertex.getData();
                    int destination = edge.getDestination();

                    if(distanceToSource[destination] > distanceToSource[source] + edge.getWeight()){
                        distanceToSource[destination] = distanceToSource[source] + edge.getWeight();
                        queue.offer(new Vertex(destination, distanceToSource[destination]));
                    }
                }
            }

            return distanceToSource;
        }

        private static List<List<Edge>> buildGraph(int vertices, int[][] edges){
            List<List<Edge>> graph = new ArrayList<>();

            for(int i=0; i<vertices; i++){
                graph.add(new ArrayList<>());
            }

            for(int[] edge: edges){
                int u = edge[0];
                int v = edge[1];
                int weight = edge[2];

                graph.get(u).add(new Edge(v, weight));
                graph.get(v).add(new Edge(u, weight));
            }
            return graph;
        }

        static class Edge {
            private int destination;
            private int weight;


            public Edge(int destination, int weight){
                this.destination = destination;
                this.weight = weight;
            }

            public int getDestination(){
                return destination;
            }

            public int getWeight(){
                return weight;
            }
        }



        static class Vertex implements Comparable<Vertex>{
            private int data;
            private int weight;

            public Vertex(int data, int weight){
                this.data = data;
                this.weight = weight;
            }

            public int getData(){
                return data;
            }

            public int getWeight(){
                return weight;
            }

            public void setWeight(int weight){
                this.weight = weight;
            }

            public int compareTo(Vertex otherVertex){
                if(weight < otherVertex.getWeight()) return -1;
                else if(weight == otherVertex.getWeight()) return 0;
                return 1;
            }
        }
    }


}



class AlienSolution {

    /**
     * "caa", "aaa", "aab"
     * caa | aaa => c -> a
     * caa | aab => c -> a
     * aaa | aab => a -> b
     *
     * c -> a -> b
     * Find the graph associations between characters
     * Build graph from associations
     * Find the topological sort of the graph
     *
     * */
    public static String findOrder(String[] words) {
        // code here
        if(words == null || words.length == 0 || words.length == 1) return "";

        Set<Edge> charRelations = findCharacterRelations(words);

        Map<Character, List<Character>> graph = buildGraph(charRelations, words);
        Set<Character> visited = new HashSet<>();
        Set<Character> visiting = new HashSet<>();

        StringBuilder result = new StringBuilder();

        for(Map.Entry<Character, List<Character>> entry: graph.entrySet()){
            if(!visited.contains(entry.getKey())){
                boolean isSortable = topologicalSort(graph, entry.getKey(), result, visited, visiting);
                if(!isSortable) return "";
            }
        }

        // todo: Add every other
        return result.reverse().toString();
    }

    private static boolean topologicalSort(Map<Character, List<Character>> graph, char wordChar, StringBuilder result, Set<Character> visited, Set<Character> visiting){
        visited.add(wordChar);
        visiting.add(wordChar);

        List<Character> adjacents = graph.get(wordChar);

        for(char adjacent: adjacents){
            if(visiting.contains(adjacent)) return false;

            if(!visited.contains(adjacent)){
                boolean isSortable = topologicalSort(graph, adjacent, result, visited, visiting);
                if(!isSortable) return false;
            }
        }

        visiting.remove(wordChar);
        result.append(wordChar);
        return true;
    }

    private static Map<Character, List<Character>> buildGraph(Set<Edge> edges, String[] words){
        Map<Character, List<Character>> graph = new HashMap<>();
        for(Edge edge: edges){
            char from = edge.getFrom();
            char to = edge.getTo();

            graph.putIfAbsent(from, new ArrayList<>());
            graph.putIfAbsent(to, new ArrayList<>());

            graph.get(from).add(to);
        }

        for(String word: words){
            for(char wordChar: word.toCharArray()){
                graph.putIfAbsent(wordChar, new ArrayList<>());
            }
        }
        return graph;
    }

    private static Set<Edge> findCharacterRelations(String[] words){
        Set<Edge> edges = new HashSet<>();

        for(int i=1; i<words.length; i++){
            String firstWord = words[i-1];
            String secondWord = words[i];

            Edge edge = findCharacterRelation(firstWord, secondWord);

            if(edge != null) edges.add(edge);
        }
        return edges;
    }

    private static Edge findCharacterRelation(String firstWord, String secondWord){

        int i = 0;
        int j = 0;

        while(i < firstWord.length() && j < secondWord.length()){
            char firstChar = firstWord.charAt(i);
            char secondChar = secondWord.charAt(j);

            if(firstChar != secondChar) {
                return new Edge(firstChar, secondChar);
            }

            i++;
            j++;
        }
        return null;
    }

    static class Edge {
        private char from;
        private char to;

        public Edge(char from, char to){
            this.from = from;
            this.to = to;
        }

        public char getFrom(){
            return from;
        }

        public char getTo(){
            return to;
        }

        public boolean equals(Object object){
            if(this == object) return true;
            if(!(object instanceof Edge)) return false;
            Edge otherEdge = (Edge) object;
            return this.from == otherEdge.getFrom() && this.to == otherEdge.getTo();
        }

        public int hashCode(){
            return Objects.hash(from, to);
        }
    }

    /**
     * For Input :
     * dddc a ad ab b be cd cded
     * Your Code's output is:
     * false
     * It's Correct output is:
     * true
     */
}

