package com.company;

import java.util.*;

public class GFG_ArrayProblems {

    public static void main(String[] args){
        int[][] input  = {{1,2,3}, {4,5,6}, {7,8,9}};
        System.out.println(matSearch(input, 4));

        System.out.println(least_average2(new int[]{30, 20, 10}, 1));

        System.out.println(hasTripletSum(new int[]{84, 24, 14, 97, 93, 92, 83}, 181));


        int[] inputArray = new int[]{1, 2, 3, 4, 5};
        System.out.println("Input Array: " + Arrays.toString(inputArray));

        leftRotateArray(inputArray, 2);
        System.out.println("Left Rotate: " + Arrays.toString(inputArray));

        int[] inputArray2 = new int[]{1, 2, 3, 4, 5};
        rightRotateArray(inputArray2, 2);
        System.out.println("Right Rotate: " + Arrays.toString(inputArray2));
    }

    /**
     * Maximum Subarray Sum - Kadanes Algorithm
     *
     * Given: [2, 3, -8, 7, -1, 2, 3]
     * Solution 1: Brute Force, O(n^2) time, O(1) space
     * Use two nested loops to iterate all subarrays
     * Find the max sub array sum
     *
     * Solution 2: Kadane's Algorithm, O(n) time, O(1) space - OPTIMAL
     * The max sum of subarray to index i, is the max of subarray sum to index i-1 plus arr[i] and arr[i] alone
     * Compare this max to the overall of all max sum of subarrays
     */
    int maxSubarraySum(int[] arr) {
        // Your code here - OPTIMAL
        int maxSumAtPostion = arr[0];
        int maxSumOfAnySubArray = arr[0];

        for(int i=1; i<arr.length; i++){
            maxSumAtPostion = Math.max(maxSumAtPostion + arr[i], arr[i]);

            maxSumOfAnySubArray = Math.max(maxSumOfAnySubArray, maxSumAtPostion);
        }
        return maxSumOfAnySubArray;
    }



    /**
     * Search in a Row-Column sorted matrix
     *
     * 3 30 38
     * 20 52 54
     * 35 60 69
     * Find: 35
     *
     * Solution 1: Linear Search, Brute force, O(n^2) time, O(1) space
     * Solution 2: Binary Search each row O(mlogn) time, O(1) space
     * Solution 3: O(m + n) time, O(1) space - OPTIMAL
     * Rule out rows or columns by searching from the corners
     * of the minor diagonal
     * */
    public static boolean matSearch(int[][] mat, int x) {
        // your code here - OPTIMAL
        int m = mat.length;
        int n = mat[0].length;

        int i = 0;
        int j = n-1;

        while(i < m && j >= 0){
            if(mat[i][j] == x) return true;
            else if(mat[i][j] > x) j--;
            else i++;
        }
        return false;
    }

    public static boolean matSearch2(int[][] mat, int x) {
        // your code here
        for(int i = 0; i < mat.length; i++){
            if(binarySearch(mat[i], x)) return true;
        }
        return false;
    }

    public static boolean binarySearch(int[] mat, int x){
        int low = 0;
        int high = mat.length - 1;

        while(low <= high){
            int mid = (low + high) / 2;
            if(mat[mid] == x) return true;
            else if (mat[mid] > x) high = mid - 1;
            else low = mid + 1;
        }
        return false;
    }



    /**
     * Left Array Rotation Using Reversal
     * 1, 2, 3, 4, 5 | n elements
     * 0  1. 2. 3. 4 |  4 3 2 1 0
     *
     * 2  3. 4. 0. 1
     *
     * Note:
     * n elements = first d elements & (n-d) elements
     * n = d & (n-d)
     * rotated n = (n-d) & d
     *
     * Also Note:
     * If d >= n: rotation by d = rotation by d % n
     * For example 6 % 5 => 1, rotation by 1 == rotation by 6
     *
     * Using New Temp Array:
     * - Iterate from d...<n in arr:
     *      new_array[i-d] = arr[i]
     *
     * - Iterate from 0...<d in arr:
     *      new_array[n-d + i] = arr[i]
     *
     *
     * Using Reversal In Place:
     * How can I take: 0  1. 2. 3. 4  => 2  3. 4. 0. 1
     * Knowing n = d & (n-d)
     * Reverse 0...<n: 4 3 2 1 0
     * Reverse 0...<(n-d): 2 3 4 1 0
     * Reverse n-d...<n: 2 3 4 0 1
     *
     * */
    static void leftRotateArray(int arr[], int d) {
        // add your code here
        if(arr == null || arr.length == 0) return;
        int arrLength = arr.length;

        d = d % arrLength;

        reverseArray(arr, 0, arrLength-1);
        reverseArray(arr, 0, arrLength-d-1);
        reverseArray(arr, arrLength-d, arrLength-1);
    }

    static void reverseArray(int[] arr, int startIndex, int endIndex){
        int arrLength = arr.length;
        if(!isValidIndex(startIndex, arrLength)) return;
        if(!isValidIndex(endIndex, arrLength)) return;

        while(startIndex < endIndex){
            int temp = arr[startIndex];
            arr[startIndex] = arr[endIndex];
            arr[endIndex] = temp;

            startIndex++;
            endIndex--;
        }
    }

    static boolean isValidIndex(int index, int arrLength){
        return index >= 0 && index < arrLength;
    }

    /**
     * Left Array Rotation (Using Modulo)
     * 1, 2, 3, 4, 5
     * ---------------
     * 0  1. 2. 3. 4
     *
     * 2  3. 4. 0. 1
     *
     * i = 0, iprime = 2
     * i = 1, iprime = 3
     * ....
     * Say n is array length
     * d = 2, n = 5
     * iprime = (i + d) % n
     *
     * i = 0: (0 + 2) % 5 = 2
     * i = 3: (3 + 2) % 5 = 0
     * i = 4: (4 + 2) % 5 = 1
     *
     * arr[iprime] = a[(i + d) % n]
     * */
    static void leftRotateArray1(int arr[], int d) {
        // add your code here
        int arrLength = arr.length;
        int[] result = new int[arrLength];

        for(int i=0; i<arrLength; i++){
            result[i] = arr[(i + d) % arrLength];
        }

        for(int i=0; i<arrLength; i++){
            arr[i] = result[i];
        }
    }

    /**
     * Right Array Rotation (Using Reversal)
     * 1 2 3 4 5 where d = 2
     * 0 1 2 3 4
     * Index becomes:
     * 3 4 0 1 2
     *
     * n = (n-d) & d
     * n = [0...n-d-1] & [n-d...n-1]
     *
     * Reverse 0...n-1: 4 3 2 1 0
     * Reverse 0...d-1: 3 4 2 1 0
     * Reverse d...n-1: 3 4 0 1 2
     */
    static void rightRotateArray(int[] arr, int d){
        if(arr == null || arr.length == 0) return;
        int arrLength = arr.length;

        d = d % arrLength;

        reverseArray(arr, 0, arrLength-1);
        reverseArray(arr, 0, d-1);
        reverseArray(arr, d, arrLength-1);
    }


    /**
     * Spirally traversing a matrix
     *
     * 1  2  3  4
     * 5  6  7  8
     * 9  10 11 12
     * 13 14 15 16
     *
     * Solution: O(m * n) time, O(m*n) space - OPTIMAL
     * Add labels top, bottom for rows, and left, right for columns
     * Traverse row top from left to right and increment top
     * Traverse col right from top to bottom and decrement right
     * Traverse row bottom from right to left and decrement bottom
     * Traverse col left from bottom to top and increment left
     * All while top <= bottom and left <= right
     *
     * */
    // Function to return a list of integers denoting spiral traversal of matrix.
    public ArrayList<Integer> spirallyTraverse(int[][] mat) {
        // code here - OPTIMAL
        int m = mat.length;
        int n = mat[0].length;

        int top = 0;
        int bottom = m - 1;
        int left = 0;
        int right = n - 1;

        ArrayList<Integer> result = new ArrayList<>();
        while(top <= bottom && left <= right){

            for(int i = left; i <= right; i++){
                result.add(mat[top][i]);
            }
            top++;

            for(int i = top; i <= bottom; i++){
                result.add(mat[i][right]);
            }
            right--;

            if(top <= bottom){
                for(int i = right; i >= left; i--){
                    result.add(mat[bottom][i]);
                }
                bottom--;
            }

            if(left <= right){
                for(int i = bottom; i >= top; i--){
                    result.add(mat[i][left]);
                }
                left++;
            }
        }

        return result;
    }



    /**
     * Trapping Rain Water
     * 3 0 1 0 4 0 2
     *
     * General Idea
     * Water is trapped by finding higher bars on left and right of
     * each index
     * The water trapped at each index is:
     *  min(lMax, rMax) - height[index] if min > height[index]
     *
     * Solution 1: O(n) time, O(n) space
     * Precalculate the leftMaxes and rightMaxes arrays
     *
     * Solution 2: O(n) time, O(1) space - OPTIMAL
     * Use Two Pointers
     * **/
    public int maxWater(int[] arr) {
        // code here
        int n = arr.length;
        int[] leftMax = new int[n];
        int[] rightMax = new int[n];

        // Fill left max
        leftMax[0] = arr[0];
        for(int i = 1; i < n; i++){
            leftMax[i] = Math.max(leftMax[i-1], arr[i]);
        }

        // Fill right max
        rightMax[n-1] = arr[n-1];
        for(int i = n-2; i >= 0; i--){
            rightMax[i] = Math.max(rightMax[i+1], arr[i]);
        }

        int res = 0;
        for(int i=1; i<=n-2; i++){
            int minValue = Math.min(leftMax[i-1], rightMax[i+1]);
            if(minValue > arr[i]){
                res += minValue - arr[i];
            }
        }

        return res;
    }



    /**
     * Two sum - Pairs with 0 Sum
     *
     * -4 -1 -1 0 1 2
     *
     * Solution 1: With Sorting First, O(nlogn) time, O(n) space
     * sort list
     * Make map of {value} to {occurrence}
     * check if complement of value exist in map or put value in map
     *
     * */
    public static ArrayList<ArrayList<Integer>> getPairs(int[] arr) {
        // code here
        Arrays.sort(arr);
        Set<Integer> set = new HashSet<>();
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();

        for(int value: arr){
            int target = 0 - value;
            if(set.contains(target)){
                ArrayList<Integer> res = new ArrayList<>();
                res.add(target);
                res.add(value);
                result.add(res);
                set.remove(target);
            }else{
                set.add(value);
            }
        }

        Collections.sort(result, (a, b) -> {
            return a.get(0) - b.get(0);
        });
        return result;
    }




    /**
     * Subarray with least average
     *
     * 30 20 10 5
     * k = 2
     *
     * Solution: O(n) time, O(1) space - OPTIMAL
     * recent_average = (30 + 20)/2 = 25
     * min-average = recent_avarage
     * second_average =  ((recent-average * k) - val[i-1] + val[i+k])/k
     * */
    public static int least_average(int[] nums, int k){
        // code here - OPTIMAL
        double recentAverage = 0;
        for(int i=0; i<k; i++){
            recentAverage += nums[i];
        }
        recentAverage /= k;

        double leastAverage = recentAverage;
        int leastAverageIndex = 0;

        for(int i=1; i < nums.length-k+1; i++){
            recentAverage = ((recentAverage * k) - nums[i-1] + nums[i+k-1])/k;
            if(recentAverage < leastAverage){
                leastAverage = recentAverage;
                leastAverageIndex = i;
            }
        }
        // Add one, since the question demands one-based index
        return leastAverageIndex+1;
    }

    /** Using cumulative instead of average - OPTIMAL **/
    public static int least_average2(int[] nums, int k){
        // code here - OPTIMAL
        double recentCumulative = 0;
        for(int i=0; i<k; i++){
            recentCumulative += nums[0];
        }

        double leastAverage = recentCumulative / k;
        int leastAverageIndex = 0;

        for(int i=1; i < nums.length-k+1; i++){
            recentCumulative = recentCumulative - nums[i-1] + nums[i+k-1];
            double recentAverage = recentCumulative / k;
            if(recentAverage < leastAverage){
                leastAverage = recentAverage;
                leastAverageIndex = i;
            }
        }
        return leastAverageIndex+1;
    }



    /**
     * Convert array into Zig-Zag fashion
     *
     * 4 3 7 8 6 2 1
     * 1 2 3 4 6 7 8
     * 1 3 2 6 4 8 7
     *
     * 1 2 3 4
     * 1 3 2 4
     *
     * Solution 1: Using Sorting, O(nlogn) time, O(1) space
     * Sort the array
     * Start from the 2nd and 3rd elements, and flip them
     *  This ensures the first 4 elements are in zigzag
     * Skip over to remaining sorted array and repeat
     *
     * 4 3 7 8 6 2 1
     * 3 7 4 8 6 2 1
     * 3 7 4 8 6 2 1
     * 3 7 4 8 2 6 1

     * */
    public static void zigZag(int[] arr) {
        // code here
        Arrays.sort(arr);

        int i = 1;
        while(i <= arr.length-2){
            int temp = arr[i];
            arr[i] = arr[i+1];
            arr[i+1] = temp;

            i += 2;
        }
    }




    /**
     * Duplicates in Array
     *
     * 0 1 2 1 500 | [0 1 2 500]
     * [0.....500]
     *
     * Solution: Using Set to store present numbers
     *      Time: O(n)
     *      Space: O(n)
     * */
    public List<Integer> findDuplicates(int[] arr) {
        // code here
        List<Integer> duplicates = new ArrayList<>();

        if(arr == null || arr.length == 0) return duplicates;

        Set<Integer> set = new HashSet<>();

        for(int number: arr){
            if(set.contains(number)){
                duplicates.add(number);
            }
            set.add(number);
        }
        return duplicates;
    }




    /**
     * Has Triplet Sum
     * Solution: Perform Two sum on each index
     *  Time: O(n^2)
     *  Space: O(n)
     */
    public static boolean hasTripletSum2(int[] arr, int target) {
        // Your code Here
        if(arr == null || arr.length < 3) return false;

        for(int i=0; i<arr.length-2; i++){
            int newTarget = target - arr[i];

            boolean hasTwoSum = hasTwoSum(arr, i+1, newTarget);
            if(hasTwoSum) return true;
        }
        return false;
    }

    private static boolean hasTwoSum(int[] arr, int startIndex, int target){
        Set<Integer> set = new HashSet<>();

        for(int i=startIndex; i < arr.length; i++){
            if(set.contains(target - arr[i])) return true;

            set.add(arr[i]);
        }
        return false;
    }

    /**
     * Has Triplet Sum
     * - Each element either:
     *      - Contributes to a triplet that yields the target
     *      - Doesn't contribute to a triplet that yields the target
     *
     * [1, 4, 45, 6, 10, 8], target = 13
     * target,numberOfElements,elementsUsed
     *
     *                         13,6,0
     *           5,5,1                        13,5,0
     *           5,4,1
     *           5,3,1
     *           5,2,1
     *
     *  1,1,2       5,1,1
     *
     * 0,0,3        4,0,2
     *
     * Positive
     * - Target is 0 & Element used is 3
     * Negative
     * - Number of elements is 0
     *
     * Solution: Using Divide & Conquer (without memoization)
     *      Time (without DP): O(2^n)
     *      Space: O(n), which is the recursive call stack
     *
     * Solution Improved: Using Dynamic Programming
     *      Time: O(n), we process all children of the left-most subtree which is 2 * n = 2n
     *      Space: O(n), where n is recursive stack and 2n for all sub problems in the memo
     *
     * */
    public static boolean hasTripletSum(int[] arr, int target) {
        // Your code Here
        if(arr == null || arr.length < 3) return false;

        Map<String, Boolean> memo = new HashMap<>();

        int arrLength = arr.length;
        return hasTripletSum(arr, arrLength, 0, target, memo);
    }

    private static boolean hasTripletSum(int[] arr, int arrLength, int elementsUsed, int target, Map<String, Boolean> memo){
        if(target == 0 && elementsUsed == 3) return true;
        if(arrLength == 0 || elementsUsed > 3) return false;

        String memoKey = target + "-" + arrLength + "-" + elementsUsed;
        if(memo.containsKey(memoKey)) return memo.get(memoKey);

        boolean hasTripletSumWithoutLastElement = hasTripletSum(arr, arrLength-1, elementsUsed, target, memo);
        // Todo: We can short circuit here

        boolean hasTripletSumWithLastElement = false;
        if(arr[arrLength-1] <= target){
            hasTripletSumWithLastElement = hasTripletSum(arr, arrLength-1, elementsUsed+1, target-arr[arrLength-1], memo);
        }

        boolean hasTripletSum = hasTripletSumWithoutLastElement || hasTripletSumWithLastElement;
        memo.put(memoKey, hasTripletSum);
        return hasTripletSum;
    }
}
