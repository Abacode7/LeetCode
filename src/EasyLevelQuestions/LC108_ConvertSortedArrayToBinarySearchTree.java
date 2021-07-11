package EasyLevelQuestions;

public class LC108_ConvertSortedArrayToBinarySearchTree {
    public static void main(String[] args){
        int[] nums = {-10,-3,0,5,9};
        System.out.println(sortedArrayToBST(nums));
    }
    /*
        Time complexity: O(N) since we visit each node exactly once.
        Space complexity: O(N). O(N) to keep the output, and O(logN) for the recursion stack.
     */

    public static TreeNode sortedArrayToBST(int[] nums) {
        // Locate the middle element and make it the
        // head
        // Repeat this process recursively for both the left
        // and right subtree

        return makeBST(nums, 0, nums.length - 1);
    }

    public static TreeNode makeBST(int[] nums, int left, int right){
        if(left > right) return null;
        int middle = left + (right - left) / 2;
        TreeNode node = new TreeNode(nums[middle]);
        node.left = makeBST(nums, left, middle-1);
        node.right = makeBST(nums, middle+1, right);
        return node;
    }
}
