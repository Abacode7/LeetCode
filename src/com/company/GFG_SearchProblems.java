package com.company;

import java.util.ArrayList;
import java.util.Arrays;

public class GFG_SearchProblems {
    public static void main(String[] args){
        System.out.println(missingNumber3(new int[]{12, -7, 12, 10, -6, 4, -18, 12, 0, 10, 17, 0, 1, 2, 2, 9, -7, 16}));
    }

    /**
     * [5, 6, 7, 8, [9, 10, 1, 2, 3] | key = 3
     *
     * 2 4 5 6 8 10 12 | 5
     *
     * To Search in sorted rotated array
     * - The idea is to find the minimum item index
     * - if array[min_index] == key, return
     * - if min_index == 0, binaray search the entire array
     * - if array[0] <= key, binary search array[0...min_index-1]
     * - else binary_search[min_index + 1...end]
     *
     * To find the minimum item in the index
     * - Initialize low = 0, high = n-1
     * - Initialize midIndex = (low + high) / 2
     * - if array[midIndex] > array[high]:
     *      - then array[low...midIndex] is sorted
     *      - low = midIndex + 1, because the smallest element is between [midIndex +1...high]
     * - if array[midIndex] <= array[high]:
     *      - then array[midIndex...high] is sorted
     *      - high = midIndex, we still include midIndex because it could be the smallest element
     *      and the smallest elements are between [low...midIndex]
     *
     * */

    /**
     * Minimum in a Sorted and Rotated Array
     */

    /**
     * Search in a Sorted and Rotated Array
     */



    /**
     * int x = sqrt(n)
     * Since 1 = sqrt(1)
     * Search 1....n to find sqrt n i.e 1...sqrt(n)...n
     * Rather than search linearly we can search logarithmic fashion
     *
     * Solution: O(log n) time, O(1) space - OPTIMAL
     * */
    int floorSqrt(int n) {
        // Your code here - OPTIMAL
        int low = 1, high = n;
        int result = 1;

        while(low <= high){
            int mid = (low + high)/2;

            if(mid * mid <= n){
                result = mid;
                low = mid + 1;
            }else{
                high = mid - 1;
            }
        }
        return result;
    }

    /**
     * Mathematically to find Floor Sqrt
     * x^2 = n
     * log(x^2) = log(n)
     * 2log(x) = log(n)
     * log(x) = 1/2 * log(n)
     * x = e ^ (0.5 * log(n))
     * where e is exponential and log is natural logarithm
     */
    static int floorSqrtMathematically(int n) {
        // Your code here - OPTIMAL
        int result = (int) Math.exp((0.5 * Math.log(n)));

        if((result + 1) * (result + 1) <= n) return result + 1;
        return result;
    }



    /**
     * Live Breakdown
     * Given: [555], key = 5, result {0, 2}
     * - The mid is 5, but we still need to search further,
     * so if lowIndex != highIndex, we need the highIndex to SHIFT LOWER to the midIndex
     * (midIndex inclusive since it still holds the value of 5)
     * - Down to [low -> 5,  5 <- high], midIndex = (low + high)/2, gives midIndex the lowIndex value
     * meaning it naturally shifts lower.
     * - We stop when arr[midIndex] = key, and low == high
     *
     * We reverse this logic for binary search highest
     * if arr[midIndex] == key and lowIndex != highIndex, we set lowIndex = midIndex - SHIFT HIGH
     * Down to 2 items, we do int midIndex = (int) Math.ceil((low+high)/2.0) to always pick the HIGHER index
     *
     * Solution: O(log n), O(1) time - OPTIMAL
     * Modified Binary Search, O(log n) time, O(1) space
     * */
    ArrayList<Integer> findLowestAndHighestIndexOfElement(int[] arr, int x) {
        // code here - OPTIMAL
        int n = arr.length;
        int lowerIndex = binarySearchLowest(arr, 0, n-1, x);
        int higherIndex = binarySearchHighest(arr, 0, n-1, x);

        ArrayList<Integer> result = new ArrayList<>();
        result.add(lowerIndex);
        result.add(higherIndex);
        return result;
    }

    private int binarySearchLowest(int[]arr, int low, int high, int key){
        while(low <= high){
            int midIndex = (low + high) / 2;

            if(arr[midIndex] == key){
                if(low == high) {
                    return low;
                }else{
                    high = midIndex;
                }
            }else if(arr[midIndex] > key){
                high = midIndex-1;
            }else{
                low = midIndex+1;
            }
        }
        return -1;
    }

    private int binarySearchHighest(int[]arr, int low, int high, int key){
        while(low <= high){
            int midIndex = (int) Math.ceil((low + high) / 2.0);

            if(arr[midIndex] == key){
                if(low == high) {
                    return high;
                }else{
                    low = midIndex;
                }
            }else if(arr[midIndex] > key){
                high = midIndex-1;
            }else{
                low = midIndex+1;
            }
        }
        return -1;
    }



    /**
     * [2, -3, 4, 1, 1, 7]
     *
     * 2, 4, 1, 1, 7, -3
     *
     * -1, -1, -2, -3, 5, 7, -3
     *
     * positive = 3
     * -3 1 1 2 4 7
     * if num == postive :
     *  positive++
     * if num > postive
     *  return positive
     *
     *  Solution: Using sorting, O(nlog n) time, O(1) space
     *
     *  Solution 2: Using extra space, O(n) time, O(n) space - BETTER
     *
     *  Solution 3: O(n) time, O(1) space - OPTIMAL
     *  We can do it in place by shifting negative values to the right of the array
     *  Then perform the same algorithm as the second solution (this time using negatives to denote visited)
     * */
    // Function to find the smallest positive number missing from the array.
    public int missingNumber(int[] arr) {
        // Your code here
        Arrays.sort(arr);
        int positive = 1;
        for(int number: arr){
            if(number == positive){
                positive++;
            }else if(number > positive){
                return positive;
            }
        }
        return positive;
    }

    public int missingNumber2(int[] arr) {
        // Your code here - BETTER
        int n = arr.length;
        boolean[] visited = new boolean[n];

        for(int value: arr){
            if(value >= 1 && value <= n) visited[value-1] = true;
        }

        int i = 0;
        while(i < n){
            if(!visited[i]) return i+1;
            i++;
        }
        return i+1;
    }

    public static int missingNumber3(int[] arr) {
        // Your code here - OPTIMAL
        int n = arr.length;
        int k = movePositivesLeft(arr);

        for(int i=0; i < k; i++){
            int value = Math.abs(arr[i]);

            if(value >= 1 && value <= k){
                arr[value - 1] = -1 * Math.abs(arr[value - 1]);
            }
        }

        int i = 0;
        while(i < k){
            if(arr[i] > 0) return i + 1;
            i++;
        }
        return k+1;
    }

    private static int movePositivesLeft(int[] arr){
        int pivot = 0; // which holds the index of the next position
        int j = 0; // which explores all indexes

        while(j < arr.length){
            if(arr[j] > 0){ // j finds a positive value
                int temp = arr[pivot];
                arr[pivot] = arr[j];
                arr[j] = temp;
                pivot++;
            }
            j++;
        }
        return pivot;
    }



    /**
     * Live Breakdown
     * Input: arr =  [1, 2, 4, 5, 7, 8, 3]
     * Output: true
     * Explanation: arr[5] = 8 is a peak element because arr[4] < arr[5] > arr[6].
     * Edges index -1 and n, have negative infinity values
     *
     * Solution: Linear, O(n) time, O(1) space
     *
     * Solution 2: Binary Search, O(log n) time, O(1) space - OPTIMAL
     * Intuition
     * If arr[i] < arr[i+1], it means there is a peak to the right,
     * or the very last value is a peak
     * If arr[i] > arr[i+], the converse is true
     * so we keep adjusting low, high indexes till we get to a peak
     * */
    public int peakElement(int[] arr) {
        // code here
        int n = arr.length;
        if(n == 1) return 0;

        if(arr[n-1] > arr[n-2]) return n-1;
        if(arr[0] > arr[1]) return 0;

        for(int i=1; i<n-1; i++){
            if((arr[i] > arr[i-1]) && (arr[i] > arr[i+1])) return i;
        }
        return 0;
    }

    public int peakElement2(int[] arr) {
        // code here - OPTIMAL
        int n = arr.length;
        if(n == 1) return 0;

        if(arr[n-1] > arr[n-2]) return n-1;
        if(arr[0] > arr[1]) return 0;

        int low = 1, high = n-2;
        while(low <= high){
            int mid = (low + high) / 2;

            if(arr[mid] > arr[mid + 1] && arr[mid] > arr[mid-1]){
                return mid;
            }else if(arr[mid] < arr[mid+1]){
                low = mid + 1;
            }else{
                high = mid - 1;
            }
        }
        return 0;
    }
}
