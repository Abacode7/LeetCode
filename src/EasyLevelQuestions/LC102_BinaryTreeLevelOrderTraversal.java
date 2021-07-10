package EasyLevelQuestions;
import java.util.*;

public class LC102_BinaryTreeLevelOrderTraversal {
    public static void main(String[] args){
        TreeNode treeNode = new TreeNode(3, new TreeNode(9),
                                                new TreeNode(20, new TreeNode(15), new TreeNode(7)));

        System.out.println(levelOrder(treeNode).toString());
    }

    // Using DFS assign depth to each node and add to
    // the appropriate index in a Map

    // Runtime O(N), we go through each node once, also the map entry
    // iteration is at most N
    // Space O(N), for the call stack and map length
    public static List<List<Integer>> levelOrder(TreeNode root) {
        if(root == null) return new ArrayList<>();

        Map<Integer, List<Integer>> map = new HashMap<>();
        dfs(root, 0, map);

        List<List<Integer>> result = new ArrayList<>();
        for(Map.Entry<Integer, List<Integer>> entry: map.entrySet()){
            result.add(entry.getValue());
        }
        return result;
    }

    public static void dfs(TreeNode root, int depth, Map<Integer, List<Integer>> map){
        List<Integer> depthList = map.getOrDefault(depth, new ArrayList<>());
        depthList.add(root.val);
        map.put(depth, depthList);

        if(root.left != null)
            dfs(root.left, depth+1, map);

        if(root.right != null)
            dfs(root.right, depth+1, map);
    }
}
