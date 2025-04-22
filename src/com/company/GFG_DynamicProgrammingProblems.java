package com.company;

import java.util.*;

@SuppressWarnings("DuplicatedCode")
public class GFG_DynamicProgrammingProblems {
    public static void main(String[] args){
        // Grid Traveller
        System.out.printf("Grid traveller matrix m: %s, n: %s, ways: %s\n", 1, 1, gridTraveller(1, 1));
        System.out.printf("Grid Traveller Matrix m: %s, n: %s, ways: %s\n", 2, 3, gridTraveller(2, 3));
        System.out.printf("Grid Traveller Matrix m: %s, n: %s, ways: %s\n", 3, 2, gridTraveller(3, 2));
        System.out.printf("Grid Traveller Matrix m: %s, n: %s, ways: %s\n", 3, 3, gridTraveller(3, 3));
        System.out.printf("Grid Traveller Matrix m: %s, n: %s, ways: %s\n", 18, 18, gridTravellerMemoized(18, 18, new HashMap<>()));
        System.out.println();

        // Can Sum
        System.out.printf("Can Sum %s: %s\n", Arrays.toString(new int[]{2, 4}), canSum(7, new int[]{2, 4}));
        System.out.printf("Can Sum %s: %s\n", Arrays.toString(new int[]{5, 1, 4, 2, 7}), canSum(7, new int[]{5, 1, 4, 2, 7}));
        System.out.printf("Can Sum %s: %s\n", Arrays.toString(new int[]{7, 14}), canSumMemoized(300, new int[]{7, 14}, new HashMap<>()));
        System.out.println();

        // How Sum
        System.out.println(howSum(7, new int[]{2, 4}));
        System.out.println(howSum(7, new int[]{5, 1, 4, 2, 7}));
        System.out.println(howSumRefactored(7, new int[]{5, 1, 4, 2, 7}));
        System.out.println(howSumRefactoredAndMemoized(100, new int[]{5, 2, 25, 1}, new HashMap<>()));
        System.out.println();

        // Best Sum
        System.out.println(bestSum(7, new int[]{2, 4}));
        System.out.println(bestSum(7, new int[]{5, 1, 4, 2, 7}));
        System.out.println(bestSum(7, new int[]{5, 1, 4, 2, 7}));
        System.out.println(bestSum(8, new int[]{1, 4, 5}));
        System.out.println(bestSumMemoized(100, new int[]{1, 2, 5, 25}, new HashMap<>()));
    }

    /**
     * Given: Analyse the time and space complexity of calls for dib function
     *                  5
     *           4            4
     *        3     3      3      3
     *   2    2    2   2   2   2    2    2
     *  1 1  1 1 1 1 1  1 1 1 1 1  1 1  1  1
     *
     * Intuition
     * n = 3
     * (2^0 + 2^1 + 2^2) - 1
     * (1 + 2 + 2^2) - 1
     * 2 + 2^2
     * 2(1 + 2)
     * 2 * 3
     *
     * 2 + 2^2 + 2^3 + 2^4
     * 2(1 + 2 + 4 + 8)
     * -------------
     * Breaking it down
     * 2^0 + 2^1 + 2^2 + ... + 2^(n-1) = 2^n - 1
     * It's a Geometric Progression:
     * => a * (r^n - 1) / (r - 1)
     * where a is first value = 1
     * r is common factor = 2
     * => 1 * (2^n - 1) / (2 - 1) = 2^n - 1
     * => 2^0 + 2^1 + 2^2 + ... + 2^(n-1) = 2^n - 1
     *
     * OR
     *
     * Noticing at the leaf level we always have 2^(n-1) leaves, also adding the fact that
     * the total number of elements before the leaves is always equals total number of (leaves - 1).
     * So we have:
     * 2^(n-1) + 2^(n-1) - 1 => 2 * 2^(n-1) - 1 => 2^(n-1+1) - 1 => 2^n - 1
     *
     * Dib
     * Time: O(2^n) as we've shown above
     * Space: O(n), where n is the depth of the call stack.
     * It's easy to assume we use up 2^n space but we always backtrack, so the stack space never exceeds n.
     *
     * LIb
     * Here the tree is half it's height, meaning n/2
     * The complexity is still O(2^n) time & O(n) space, stripping off all constants.
     *
     * Fib
     * Now consider the fibonacci function, it's in between lib and dib
     * lib <= fib <= dib
     * O(2^n) <= X <= O(2^n)
     * hence fib is O(2^n)
     *
     */
    public int dib(int n){
        if(n <= 1) return 1;
        return dib(n-1) + dib(n - 1);
    }

    public int lib(int n){
        if(n <= 1) return 1;
        return lib(n-2) + lib(n - 2);
    }

    public int fib(int n){
        if(n <= 1) return 1;
        return fib(n-1) + fib(n - 2);
    }

    /**
     * For Fib with Memoization, we'll have:
     *                  5
     *           4            4
     *        3     3
     *   2    2
     *  1 1
     *
     *  Notice for n = 3, 4, & 5, the number of operation/calls, simply increases linearly.
     *  It's 2n, with every n having just 1 pair.
     *
     *  Hence, we have:
     *  Time: O(n) time
     *  Space: O(n) space too, n space of stack, also 2n for key pair in memoir store
     */
    public int fib(int n, Map<Integer, Integer> map){
        if(map.containsKey(n)) return map.get(n);
        if(n <= 1) return 1;

        int result = fib(n-1) + fib(n - 2);
        map.put(n, result);
        return result;
    }



    /**
     * Grid Traveller Problem
     *
     *                  10, 3
     *            9,3           10, 2
     *          8,3  9,2        9,2    10,1
     *     7,3 8,2   8,2 9,1
     *   6,3 7,2  7,2 7,1
     *
     *              5,2
     *           5,1   4,2
     *                4,1  3,2
     *                    3,1  2,2
     *                        2,1 1,2
     *
     *  Intuition:
     *  The num of ways to travel from a point S, going down/right is sum of num of ways if you go down
     *  and num of ways if you go right.
     *  Also:
     *  If grid m,n is 1,1 the num of ways is 1, meaning start and end point are exactly the same.
     *  If the grid m, n has any m or n equalling 1, the num of ways becomes exactly 1.
     *  We can use these facts as the base case.
     *
     *  Solution: Brute Force
     *   Time: O(2^max(m, n)) time
     *   Space: O(max(m, n)) space too, n space of stack, also 2n for key pair in memoir store
     *   where max(m, n) is the height of the tree

     *   Solution: With Memoization
     *   Time: O(max(m,n)) time, where max(m,n) is the height of the tree.
     *   Space: O(max(m,n)) space, we go down at most max(m,n) in times in the stack.
     *
     *   NB: Height of tree would have been: h = m + n
     *   If we had stopping condition if(m == 1 && n == 1).
     *   Making the Brute force solution be:
     *      O(2^m+n) time
     *       O(m+n) space
     *   And the Memoized solution be:
     *      O(m*n) time
     *      O(m+n) space.
     *   m*n time because we have m possible values and n possible values, whose combination would be
     *   executed a maximum of m*n times
     */
    public static long gridTraveller(int m, int n){
        if(m == 1 || n == 1) return 1;
        return gridTraveller(m-1, n) + gridTraveller(m, n - 1);
    }

    public static long gridTravellerMemoized(int m, int n, Map<String, Long> memo){
        String key = m + "," + n;
        if(memo.containsKey(key)) return memo.get(key);
        if(m == 1 || n == 1) return 1;

        long numOfWays = gridTravellerMemoized(m-1, n, memo) + gridTravellerMemoized(m, n - 1, memo);

        memo.put(key, numOfWays);
        return numOfWays;
    }



    /**
     * target = 7
     * nums = [5, 1, 4, 2, 7]
     * returns = true
     * Numbers can be reused multiple times:
     *  Meaning for target = 7, nums = [1, 5], return is true because, 1 can be used to get 7, seven times.
     *
     * Intuition
     *                   7
     *  2(-5)  6(-1)  3(-4) 4(-3) 5(-2) 0(-7)
     * .....
     *
     * Complexity for Brute Force Solution
     * Given target is m and length of nums is n, we have:
     *  O(n^m) time, where m (target) is the height of the tree, in worst case we have 1 in the nums list, meaning
     *  we deduct 1 at each level, totalling to m levels.
     *  n is the number of branches we have from the parent node. So like we've had 2^n (numOfBranches ^ treeHeight),
     *  we have n^m.
     *  O(m), the recursive stack has a max of tree height depth, which is m in this case.
     */
    public static boolean canSum(int target, int[] nums){
        if(target == 0) return true;

        for(int num: nums){
            if (target - num >= 0){
                if(canSum(target-num, nums)) return true;
            }
        }
        return false;
    }

    /**
     * Intuition
     * target = 4, [1, 3, 2]
     *                      4
     *       3(-1)                   1(-3)  2(-2)
     *  2(-1)      0(-3) 1(-2)               0(-2)
     * 1(-) 0(-2)           0(-1)
     *
     * Intuition
     * If for all sub-problems of m = 4, 3, 2, 1, we process them n times using [1, 3, 2] and save the solution,
     * we'll have m*n time work to do maximally.
     *
     * Complexity for Memoized Solution
     * Given target is m and length of nums is n, we have:
     *  Since we have m = 4, and sub-problems ranging 3, 2, 1 possible
     *  For each of this processing of 4, we go maximally n times i.e, each of m = 4,3,2,1 processes n children maximally
     *  whose results gets 'memoir-ed' and reused so we have:
     *      m = 4, 3, 2 1 each processed n branches/times in the worst case
     *      with results stored, we have a time complexity of:
     *          O(m*n) times
     *          O(m) space still retained
     */
    public static boolean canSumMemoized(int target, int[] nums, Map<Integer, Boolean> memo){
        if(memo.containsKey(target)) return memo.get(target);
        if(target == 0) return true;

        for(int num: nums){
            if (target - num >= 0){
                if(canSumMemoized(target-num, nums, memo)) {
                    memo.put(target, true);
                    return true;
                }
            }
        }

        memo.put(target, false);
        return false;
    }



    /** Top Bottoms: This maintains varying lists from to bottom, then returns null for invalid list,
     * leaving only the valid list.
     *
     * Solution: Bruteforce
     * n^m * m => n^m * m time, since we have an extra m operation of copying the list in line, done at each node operation.
     * It is m because, we have m values (not n) in the array (m number of 1s in the array).
     *      O(n^m * m) time, following the above explanation.
     *
     * Since each path downwards has its own list, which is of size m maximally.
     * Even if we have n^(m-1) paths maximally (the num of leave nodes), we DON'T exceed m list size at any instance in time.
     * So we have: m stack space + m list size:
     *      O(m) space
     * **/
    public static List<Integer> howSum(int target, int[] nums){
        return howSum(target, nums, new ArrayList<>());
    }
    private static List<Integer> howSum(int target, int[] nums, List<Integer> list){
        if(target == 0) return list;

        for(int num: nums){
            if(target-num >= 0){
                List<Integer> inputList = new ArrayList<>(list);
                inputList.add(num);
                List<Integer> resultList = howSum(target-num, nums, inputList);
                if(resultList != null) return resultList;
            }
        }
        return null;
    }

    /**
     * Bottoms Up: This maintains only a list of valid nums to the target sum.
     * Time complexity follows from analysing the problem tree:
     *      O(n^m) time, since we have a constant time operation when adding element to the list.
     *
     * Since we return the first array found AND length of the first array we find is m.
     * Also, the stack space is m, making it: m + m => 2m, we have:
     *      O(m) space
     * **/
    public static List<Integer> howSumRefactored(int target, int[] nums){
        if(target == 0) return new ArrayList<>();

        for(int num: nums){
            int remainder = target - num;
            if(remainder >= 0){
                List<Integer> list = howSumRefactored(remainder, nums);
                if(list != null){
                    list.add(num);
                    return list;
                }
            }
        }
        return null;
    }

    /**
     * The worst case is the case where we process n branches under m ranging from m...0 sub-problems.
     * Giving us: m * n processing time, hence we have:
     *      O(m * n) time
     *
     * Asides the stack space of m, we have the memoir which takes m keys (all sub-problems of m), which have a list of
     * integers possibly of size m, hence we have: m + m*m
     *      O(m^2) space
     * **/
    public static List<Integer> howSumRefactoredAndMemoized(int target, int[] nums, Map<Integer, List<Integer>> memo){
        if(memo.containsKey(target)) return memo.get(target);
        if(target == 0) return new ArrayList<>();

        for(int num: nums){
            int remainder = target - num;
            if(remainder >= 0){
                List<Integer> list = howSumRefactored(remainder, nums);
                if(list != null){
                    list.add(num);
                    memo.put(target, list);
                    return list;
                }
            }
        }
        memo.put(target, null);
        return null;
    }



    /**
     * Solution 1: DP Brute force, following the analysis above
     *      O(n^m) time
     *      O(m^2) space, reasoning is we store bestList arraylist on each recursive call which is maximally m in size,
     *      and we have a recursive stack depth of m, hence we have m * m => m^2
     */
    public static List<Integer> bestSum(int target, int[] nums){
        if(target == 0) return new ArrayList<>();

        List<Integer> bestList = null;

        for(int num: nums){
            int remainder = target - num;
            if(remainder >= 0){
                List<Integer> list = bestSum(remainder, nums);
                if(list != null) {
                    list.add(num);

                    if(bestList == null || list.size() < bestList.size()) {
                        bestList = list;
                    }
                }
            }
        }
        return bestList;
    }

    /**
     * Solution 1: DP Memo, following the analysis above:
     * Here alongside the n * m operations to be performed, we copy the list in each of its operation, which is size m maximally.
     * So we have n * m * m => m^2 * n
     *      O(m^2 * n) time, as follows from above (WITH copying the new list)
     *      O(m^2) space, reasoning is we store bestList arraylist on each recursive call which is maximally m in size,
     *      and we have a recursive stack depth of m. hence we have m * m => m^2
     *      we also have the memo, storing m keys possible (sub-problem of m) with m size lists as value, giving  us m^2
     *          Hence we have m^2 + m^2 => 2m^2 => (m^2)
     *
     *
     * NB: It's advised to copy to a new list especially while using memoization
     */
    public static List<Integer> bestSumMemoized(int target, int[] nums, Map<Integer,List<Integer>> memo){
        if(memo.containsKey(target)) return memo.get(target);
        if(target == 0) return new ArrayList<>();

        List<Integer> bestList = null;

        for(int num: nums){
            int remainder = target - num;
            if(remainder >= 0){
                List<Integer> resultList = bestSumMemoized(remainder, nums, memo);
                if(resultList != null) {
                    List<Integer> list = new ArrayList<>(resultList);
                    list.add(num);

                    if(bestList == null || list.size() < bestList.size()) {
                        bestList = list;
                    }
                }
            }
        }
        memo.put(target, bestList);
        return bestList;
    }

}
