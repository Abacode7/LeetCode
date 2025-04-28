package com.company;

import java.util.*;

@SuppressWarnings("DuplicatedCode")
public class YT_DynamicProgrammingLearnings {
    public static void main(String[] args){
        // Reference
        // https://www.youtube.com/watch?v=oBt53YbR9Kk

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
        System.out.println();

        // Can, Count Construct
        System.out.println(canConstruct("purple", new String[]{"purp", "p", "ur", "le", "purpl"}));
        System.out.println(canConstructMemoized("abcdef", new String[]{"ab", "abc", "cd", "def", "abcd"}, new HashMap<>()));
        System.out.println(countConstruct("purple", new String[]{"purp", "p", "ur", "le", "purpl"})); // 2
        System.out.println(countConstruct("abcdef", new String[]{"ab", "abc", "cd", "def", "abcd"})); // 1
        System.out.println(countConstructMemoized("eeeeeeeeeeeeeeeeeeeeeeeeeeeeef", new String[]{"e", "ee", "eee", "eeee", "eeeee"}, new HashMap<>())); //0
        System.out.println();


        // All Construct
        System.out.println(allConstruct("purple", new String[]{"purp", "p", "ur", "le", "purpl"}));
        System.out.println(allConstruct("abcdef", new String[]{"ab", "abc", "cd", "def", "abcd"}));
        System.out.println(allConstructMemoized("purple", new String[]{"purp", "p", "ur", "le", "purpl"}, new HashMap<>()));
        System.out.println(allConstructMemoized("abcdef", new String[]{"ab", "abc", "cd", "def", "abcd"}, new HashMap<>()));
        //System.out.println(allConstructMemoized("eeeeeeeeeeeeeeeeeeeeeeeeeeeeef", new String[]{"e", "ee", "eee", "eeee", "eeeee"}, new HashMap<>()));
        System.out.println();

        // Fib Tabular
        System.out.println(fibTabular(5));
        System.out.println(fibTabular(6));
        System.out.println(fibTabular(7));
        System.out.println(fibTabular(8));
        System.out.println();

        // Grid Traveller Tabular
        System.out.printf("Grid traveller matrix m: %s, n: %s, ways: %s\n", 1, 1, gridTravellerTabular(1, 1));
        System.out.printf("Grid Traveller Matrix m: %s, n: %s, ways: %s\n", 2, 3, gridTravellerTabular(2, 3));
        System.out.printf("Grid Traveller Matrix m: %s, n: %s, ways: %s\n", 3, 2, gridTravellerTabular(3, 2));
        System.out.printf("Grid Traveller Matrix m: %s, n: %s, ways: %s\n", 3, 3, gridTravellerTabular(3, 3));
        System.out.printf("Grid Traveller Matrix m: %s, n: %s, ways: %s\n", 18, 18, gridTravellerTabular(18, 18));
        System.out.println();

        // Can Sum Tabular
        System.out.printf("Can Sum %s %s: %s\n", 7, Arrays.toString(new int[]{2, 4}), canSumTabular(7, new int[]{2, 4}));
        System.out.printf("Can Sum %s %s: %s\n", 7, Arrays.toString(new int[]{5, 1, 4, 2, 7}), canSumTabular(7, new int[]{5, 1, 4, 2, 7}));
        System.out.printf("Can Sum %s %s: %s\n", 300, Arrays.toString(new int[]{7, 14}), canSumTabular(300, new int[]{7, 14}));
        System.out.println();


        // How, Best, All Sum Tabular
        System.out.printf("How Sum %s %s: %s\n", 7, Arrays.toString(new int[]{2, 4}), howSumTabular(7, new int[]{2, 4}));
        System.out.printf("How Sum %s %s: %s\n", 7, Arrays.toString(new int[]{5, 1, 4, 2, 7}), howSumTabular(7, new int[]{5, 1, 4, 2, 7}));
        System.out.printf("How Sum %s %s: %s\n", 8, Arrays.toString(new int[]{1, 4, 2, 3, 6}), howSumTabular(8, new int[]{1, 4, 2, 3, 6}));
        System.out.printf("Best Sum %s %s: %s\n", 8, Arrays.toString(new int[]{1, 4, 2, 3, 6}), bestSumTabular(8, new int[]{1, 4, 2, 3, 6}));
        System.out.printf("All Sum %s %s: %s\n", 7, Arrays.toString(new int[]{5, 1, 4, 2, 7}), allSumTabular(7, new int[]{5, 1, 4, 2, 7}));
        System.out.printf("All Sum %s %s: %s\n", 11, Arrays.toString(new int[]{1,5,6}), allSumTabular(11, new int[]{1,5,6}));
        //System.out.printf("How Sum %s %s: %s\n", 300, Arrays.toString(new int[]{7, 14}), howSumTabular(300, new int[]{7, 14}));
        System.out.println();


        // Can, Count, All Construct Tabular
        System.out.printf("Can Construct Tabular %s %s: %s\n", "purple", Arrays.toString(new String[]{"purp", "p", "ur", "le", "purpl"}),
                canConstructTabular("purple", new String[]{"purp", "p", "ur", "le", "purpl"}));
        System.out.printf("Can Construct Tabular %s %s: %s\n", "abcdef", Arrays.toString(new String[]{"ab", "abc", "cd", "def", "abcd"}),
                canConstructTabular("abcdef", new String[]{"ab", "abc", "cd", "def", "abcd"}));
        System.out.printf("Can Construct Tabular %s %s: %s\n", "eeeeeeeeeeeeeeeeeeeeeeeeeeeeef", Arrays.toString(new String[]{"e", "ee", "eee", "eeee", "eeeee"}),
                canConstructTabular("eeeeeeeeeeeeeeeeeeeeeeeeeeeeef", new String[]{"e", "ee", "eee", "eeee", "eeeee"}));
        System.out.println();


        // Count Construct Tabular
        System.out.printf("Count Construct Tabular %s %s: %s\n", "purple", Arrays.toString(new String[]{"purp", "p", "ur", "le", "purpl"}),
                countConstructTabular("purple", new String[]{"purp", "p", "ur", "le", "purpl"}));
        System.out.printf("Count Construct Tabular %s %s: %s\n", "abcdef", Arrays.toString(new String[]{"ab", "abc", "cd", "def", "abcd"}),
                countConstructTabular("abcdef", new String[]{"ab", "abc", "cd", "def", "abcd"}));
        System.out.printf("Count Construct Tabular %s %s: %s\n", "abcdef", Arrays.toString(new String[]{"ab", "abc", "cd", "def", "abcd", "ef"}),
                countConstructTabular("abcdef", new String[]{"ab", "abc", "cd", "def", "abcd", "ef"}));
        System.out.printf("Count Construct Tabular %s %s: %s\n", "eeeeeeeeeeeeeeeeeeeeeeeeeeeeef", Arrays.toString(new String[]{"e", "ee", "eee", "eeee", "eeeee"}),
                countConstructTabular("eeeeeeeeeeeeeeeeeeeeeeeeeeeeef", new String[]{"e", "ee", "eee", "eeee", "eeeee"}));
        System.out.println();


        // All Construct Tabular
        System.out.printf("All Construct Tabular %s %s: %s\n", "purple", Arrays.toString(new String[]{"purp", "p", "ur", "le", "purpl"}),
                allConstructTabular("purple", new String[]{"purp", "p", "ur", "le", "purpl"}));
        System.out.printf("All Construct Tabular %s %s: %s\n", "abcdef", Arrays.toString(new String[]{"ab", "abc", "cd", "def", "abcd"}),
                allConstructTabular("abcdef", new String[]{"ab", "abc", "cd", "def", "abcd"}));
        System.out.printf("All Construct Tabular %s %s: %s\n", "abcdef", Arrays.toString(new String[]{"ab", "abc", "cd", "def", "abcd", "ef"}),
                allConstructTabular("abcdef", new String[]{"ab", "abc", "cd", "def", "abcd", "ef"}));
        // System.out.printf("all Construct Tabular %s %s: %s\n", "eeeeeeeeeeeeeeeeeeeeeeeeeeeeef", Arrays.toString(new String[]{"e", "ee", "eee", "eeee", "eeeee"}),
        //        allConstructTabular("eeeeeeeeeeeeeeeeeeeeeeeeeeeeef", new String[]{"e", "ee", "eee", "eeee", "eeeee"}));
        System.out.println();


        // Combination Sum
        System.out.printf("All Sum: %s\n", allSumTabular(4, new int[]{1,2,3}));
        System.out.printf("All Sum Combination: %s\n", combinationAllSumTarget(4, new int[]{1,2,3}, 3));
        System.out.printf("All Sum Combination Infinite: %s\n", combinationInfiniteAllSumTarget(4, new int[]{1,2,3}, 3));
        System.out.printf("All Sum Combination Less/Equal Target: %s\n", combinationAllSumLessEqualsTarget(4, new int[]{1,2,3}, 3));
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




    /**
     * Solution: Recursive Divide & Conquer
     * Complexity, with m = target length & n = words length, then we have:
     * In the worst case we'll have a single letter with is always a prefix of target,
     * hence tree depth is m, with n branches.
     * We also account for substring with is linear with respect to m, hence we have:
     *       O(n^m * m) time
     *  For space, we have m recursive stack space. We also create substring of size relative to m in each m recursive
     *  stack call, hence we have:
     *       O(m^2) space
     */
    public static boolean canConstruct(String target, String[] words){
        if(target.length() == 0) return true;

        for(String word: words){
            if(target.startsWith(word)){
                String newTarget = target.substring(word.length());
                boolean canConstruct = canConstruct(newTarget, words);
                if(canConstruct) return true;
            }
        }
        return false;
    }

    /**
     * Solution: Recursive Divide & Conquer with Memoization
     * Complexity, with m = target length & n = words length, then we have:
     * In the worst case we'll have m sub-problem, hence tree depth is m, with n branches.
     * We also account for substring with is linear with respect to m, hence we have:
     *       O(n * m^2) time
     *  For space, we have m recursive stack space. We also create substring of size relative to m in each m recursive
     *  stack call, hence we have:
     *       O(m^2) space
     */
    public static boolean canConstructMemoized(String target, String[] words, Map<String, Boolean> memo){
        if(memo.containsKey(target)) return memo.get(target);
        if(target.length() == 0) return true;

        for(String word: words){
            if(target.startsWith(word)){
                String newTarget = target.substring(word.length());
                boolean canConstruct = canConstructMemoized(newTarget, words, memo);
                if(canConstruct) {
                    memo.put(target, true);
                    return true;
                }
            }
        }

        memo.put(target, false);
        return false;
    }



    /**
     * Solution: Recursive Divide & Conquer
     * Given m = target length and n = words length
     *      O(n^m * m) time
     *      O(m^2) space
     */
    public static int countConstruct(String target, String[] words){
        if(target.length() == 0) return 1;

        int count = 0;
        for(String word: words){
            if(target.startsWith(word)){
                String newTarget = target.substring(word.length());
                count += countConstruct(newTarget, words);
            }
        }

        return count;
    }

    /**
     * Solution: Divide & Conquer with Memoization
     * Given m = target length and n = words length
     *      O(m^2 * n) time
     *      O(m^2) space
     */
    public static int countConstructMemoized(String target, String[] words, Map<String, Integer> memo){
        if(memo.containsKey(target)) return memo.get(target);
        if(target.length() == 0) return 1;

        int count = 0;
        for(String word: words){
            if(target.startsWith(word)){
                String newTarget = target.substring(word.length());
                count += countConstructMemoized(newTarget, words, memo);
            }
        }

        memo.put(target, count);
        return count;
    }


    /**
     * "purple" | {"purp", "p", "ur", "le", "purpl"}
     * "" | {"purp", "p", "ur", "le", "purpl"}, return []
     * if we don't find any prefix for the sub-problem we return null
     *
     *      O(n^m * m * n), where n^m is the number of recursive calls, m is the length of substring
     *      and n is the max possible number of local constructs in each recursive call (which is n since we have n branches).
     *
     *      O(m * m * m*n) where m is the recursive call stack, extra m is the substring call, and m*n maximally stores the local construct
     *
     *      Typically, for problems like this we just take: O(n^m) time since its exponential already and O(m) space, excluding the
     *      return value size, since it takes a lot of space.
     *
     *      Similar optimisation applies to the allConstructMemoized.
     *
     *      NB: The worst case now is no longer: "eeeeeeeeeeeeeeeeeeeeeeeeeeeeef", new String[]{"e", "ee", "eee", "eeee", "eeeee"}
     *      but a case where we have "eeeeeeeeeeeeeeeeeeeeeeeeeeeeee", new String[]{"e", "ee", "eee", "eeee", "eeeee"} since we
     *      don't return early, and it requires us to generate arrays for these.
     */
    public static List<List<String>> allConstruct(String target, String[] words){
        if(target.length() == 0) {
            List<List<String>> baseConstruct = new ArrayList<>();
            baseConstruct.add(new ArrayList<>());
            return baseConstruct;
        }

        List<List<String>> allConstruct = new ArrayList<>();

        for(String word: words){
            if(target.startsWith(word)){
                String newTarget = target.substring(word.length());
                List<List<String>> localConstructs = allConstruct(newTarget, words);

                for(List<String> localConstruct: localConstructs){
                    localConstruct.add(word);
                    allConstruct.add(localConstruct);
                }
            }
        }

        return allConstruct;
    }

    public static List<List<String>> allConstructMemoized(String target, String[] words, Map<String, List<List<String>>> memo){
        if(memo.containsKey(target)) return memo.get(target);

        if(target.length() == 0) {
            List<List<String>> baseConstruct = new ArrayList<>();
            baseConstruct.add(new ArrayList<>());
            return baseConstruct;
        }

        List<List<String>> allConstruct = new ArrayList<>();

        for(String word: words){
            if(target.startsWith(word)){
                String newTarget = target.substring(word.length());
                List<List<String>> localConstructs = allConstruct(newTarget, words);

                for(List<String> localConstruct: localConstructs){
                    localConstruct.add(word);
                    allConstruct.add(localConstruct);
                }
            }
        }

        memo.put(target, allConstruct);
        return allConstruct;
    }



    /**
     * Given the Fibonacci Function:
     * n    0   1   2   3   4   5   6
     * f    0   1   1   2   3   5   8
     *
     * (Adding {0: 0} cos it maintains the function )
     *
     * The whole idea about Tabulation is such that we make the problem (& sub-problems) the indexes
     * then we build up the solution from the smallest sub-problem (index) up to the desired problem.
     *
     * In this case every n in fn affects the next two sub-problem e.g f(2) is added to f(3) and f(4)
     * So we can iterate over this list from the base case, adding its solution to the next two values.
     *
     * To get f(6), we declare and array of 7, why? cos indexes are 0 based, and we need the problem at 6 in the table.
     * The value at index n represents the solution to the problem.
     *
     * Complexity for fibonacci tabular
     *  O(n) time
     *  O(n) space
     */
    public static int fibTabular(int n){
        int[] table = new int[n+1];
        table[1] = 1; // base case

        int tableLength = table.length;

        for(int i=0; i<tableLength; i++){
            if(i + 1 < tableLength) table[i+1] += table[i];
            if(i + 2 < tableLength) table[i+2] += table[i];
        }
        return table[n];
    }



    /**
     * Grid Traveller
     * Given gridT(3, 3) => 6
     * Since we can go down and right only, it means:
     *      gridT(3,3) => gridT(3,2) + gridT(2,3)
     *      gridT(1,3) => gridT(0,3) + gridT(1,2)
     *      gridT(2,2) => gridT(2,1) + gridT(1,2)
     *
     * Observing closely, you'll see gridT(1,2) contributes to gridT(2,2) and gridT(1,3) only
     * Also knowing that at gridT(1,1) we have only 1 way to travel, hence this is the base case
     *
     * Complexity
     *      O(mn) time
     *      O(mn) space
     */
    public static long gridTravellerTabular(int m, int n){
        long[][] table  = new long[m+1][n+1];
        table[1][1] = 1;

        for(int i=0; i<m+1; i++){
            for(int j=0; j<n+1; j++){
                if(i+1 < m+1) table[i+1][j] += table[i][j];
                if(j+1 < n+1) table[i][j+1] += table[i][j];
            }
        }
        return table[m][n];
    }


    /**
     * The key here, like I earlier mentioned is to find the sub-problems and make them the indexes
     * target = 7, sub-problems = 6, 5, 4, 3, 2, 1
     *
     * Then choose a base case 0 => true, because you can always find the sum of 0 with no elements from the array
     * Iterate the base solution upwards via the tree branches (which is nums in this case) till you solve for problem
     *
     * Complexity
     *      O(mn) time, where m = target, n is length of nums
     *      O(m) space
     */
    public static boolean canSumTabular(int target, int[] nums){
        boolean[] table = new boolean[target + 1];
        table[0] = true;

        for(int i=0; i < nums.length; i++){
            if(!table[i]) continue;
            for(int num: nums){
                if(table[i]){
                    if(i + num < target+1) table[i + num] = table[i];
                }
            }
        }
        return table[target];
    }


    /**
     * Given: target = 7, nums = {5, 1, 4, 2, 7}
     *
     *  0   1   2        3       4       5         6         7
     * []  [1] [2]     [1 2]     [4]     [5]      [15]       [7]
     *        [1 1]              [1 4]
     *
     *                                   7
     *              2(-5)      6(-1)          3(-4)        0(-7)
     *  1(-1)   0(-2)
     *
     * We use the same intuition as in canSumTabular AND we add a little more optimization
     * Optimization: Since we're building bottom up, we can use focus on the path upwards from the base case 0
     * Meaning we don't need to solve sub-problems if they've not been effected by smaller sub-problems
     *
     * In implementation: You see this with the use of null, and the skipping of cases which remain null.
     *
     * Complexity: Given m = target and n = nums length
     *      O(m^2 * n) time, we perform m X n operations in loop, and in each iteration we do copy list values of length m
     *      O(m^2) space, where m is the initial list of sub-problems, with each sub-problem having a list maximally of m
     */
    public static List<Integer> howSumTabular(int target, int[] nums){
        List<List<Integer>> table = new ArrayList<>(target+1);

        for(int i=0; i<target+1; i++) table.add(null);

        table.set(0, new ArrayList<>()); // base case

        for(int i=0; i<target+1; i++){
            List<Integer> currentConstruct = table.get(i);
            if(currentConstruct == null) continue;

            for(int num: nums){
                if(i + num < target+1){
                    List<Integer> newConstruct = new ArrayList<>(currentConstruct);
                    newConstruct.add(num);

                    table.set(i+num, newConstruct);
                }
            }
        }

        return table.get(target);
    }



    /**
     * Best Sum Tabular is very similar to How Sum
     * Just that in this case before setting the solution for the sub-problems, we find the BEST solution for the sub-problems
     * by comparing the new solution with the solution present.
     *
     * Complexity:
     *      O(m^2 * n) time,
     *      O(m^2) space
     */
    public static List<Integer> bestSumTabular(int target, int[] nums){
        List<List<Integer>> table = new ArrayList<>(target+1);

        for(int i=0; i<target+1; i++) table.add(null);

        table.set(0, new ArrayList<>()); // base case

        for(int i=0; i<target+1; i++){
            List<Integer> currentConstruct = table.get(i);
            if(currentConstruct == null) continue;
            for(int num: nums){
                if(i + num < target+1){
                    List<Integer> newConstruct = new ArrayList<>(currentConstruct);
                    newConstruct.add(num);

                    if(table.get(i+num) == null) table.set(i+num, newConstruct);
                    else{
                        if(table.get(i+num).size() > newConstruct.size()){
                            table.set(i+num, newConstruct);
                        }
                    }
                }
            }

            if(table.get(target) != null) return table.get(target);
        }

        return new ArrayList<>();
    }



    /**
     * All Sum Tabular
     * This provides all possible patterns to achieve the target
     *
     * Hence, we have an exponential complexity for the tree
     *      *      ~ O(n^m) time, we're exploring all valid paths of the tree
     *      *      ~ O(n^m) space, each space in the table holds a list of a possible m items each of which is a base solution
     *      *      of the tree, hence n^m
     */
    public static List<List<Integer>> allSumTabular(int target, int[] nums){
        List<List<List<Integer>>> table = new ArrayList<>();

        for(int i=0; i<target+1; i++) table.add(new ArrayList<>());

        table.get(0).add(new ArrayList<>()); // base case

        for(int i=0; i<target+1; i++){
            List<List<Integer>> currentConstructs = table.get(i);
            for(int num: nums){
                if(i + num < target+1){
                    List<List<Integer>> futureConstruct = table.get(i+num);
                    for(List<Integer> currentConstruct: currentConstructs){
                        List<Integer> newConstruct = new ArrayList<>(currentConstruct);
                        newConstruct.add(num);

                        futureConstruct.add(newConstruct);
                    }
                }
            }
        }

        return table.get(target);
    }



    /***
     * Given: target = "abcdef" and strings {"ab", "abc", "cd", "def", "abcd"}
     *
     * Similarly, with other problems, we break down target = "abcdef" into sub-problems
     * to be solved. That is, we have: "a", "ab", "abc", "abcd", "abcde", "abcdef"
     *
     * We can store them in an array in this format:
     *      0       1       2        3           4           5          6
     *      [                                                             ]
     *      a       b       c        d           e           f
     *
     *      a       ab      abc     abcd        abcde       abcdef
     *
     *      Where index 0 means characters up to and excluding 0, so:
     *      at 0 we have "" (empty string)
     *      at 1 we have "a"
     *      at 2 we have "ab"
     *      at 5 we have "abcde"
     *      at 6 we have the full target
     *
     *      The contents of the array will have the solutions of the sub-problems: boolean in the case of canConstruct
     *      Initialize your base case at 0, which is: "" and has a solution of true
     *      Each index i, represents:
     *      i.  The end index (exclusive) of the previous sub-problem.
     *      ii. The start index of the next sub-problem branch you intend checking.
     *
     * Algorithm Complexity
     *      O(m^2 n) time,
     *      O(m) space
     */
    public static boolean canConstructTabular(String target, String[] words){
        int targetLength = target.length();
        boolean[] table = new boolean[targetLength+1];
        table[0] = true;

        for(int i=0; i<targetLength+1; i++){
            if(!table[i]) continue;

            for(String word: words){
                int endIndex = i + word.length();
                if(endIndex <= targetLength && word.equals(target.substring(i, endIndex))){
                    table[endIndex] = table[i];
                }
            }
        }
        return table[targetLength];
    }



    /**
     *    0   1   2   3   4   5   6
     *  [ 1                         ]
     *    a   b   c   d   e   f
     *''  a   ab  abc   abcde
     *            abcd     abcdef
     *
     * Algorithm Complexity
     *      O(m^2 n) time,
     *      O(m) space
     */
    public static int countConstructTabular(String target, String[] words){
        int targetLength = target.length();
        int[] table = new int[targetLength+1];
        table[0] = 1;

        for(int i=0; i < targetLength+1; i++){
            if(table[i] == 0) continue;

            for(String word: words){
                int endIndex = i + word.length();
                if(endIndex > targetLength) continue;

                if(word.equals(target.substring(i, endIndex))){
                    table[endIndex] += table[i];
                }
            }
        }

        return table[targetLength];
    }



    /**
     *
     * Given: target = "abcdef" and strings {"ab", "abc", "cd", "def", "abcd", "ef"},
     * ""       a       ab      abc     abcd        abcde       abcdef
     [[]]              [ab]             [abcd]                  [abc, def]
     *                          [abc]   [ab, cd]                [abcd, ef]
     *                                                          [ab, cd, ef]
     *
     *      0   1   2   3   4   5   6
     * ''   a   b   c   d   e   f
     *
     * Complexity
     * Since we're exploring all possible ways to construct the target,
     * we can't escape doing a Bruteforce (i.e exploring all of it)
     * Hence we have an exponential complexity for the tree
     *      ~ O(n^m) time, we're exploring all valid paths of the tree
     *      ~ O(n^m) space, each space in the table holds a list of a possible m items each of which is a base solution
     *      of the tree, hence n^m
     */
    public static List<List<String>> allConstructTabular(String target, String[] words){
        int targetLength = target.length();
        List<List<List<String>>> table = new ArrayList<>(targetLength+1);

        for(int i=0; i<targetLength+1; i++) table.add(new ArrayList<>());

        List<List<String>> baseCase = table.get(0);
        baseCase.add(new ArrayList<>());
        table.set(0, baseCase);

        for(int i=0; i<targetLength+1; i++){
            List<List<String>> baseConstructs = table.get(i);
            if(baseConstructs.isEmpty()) continue;

            for(String word: words){
                int endIndex = i + word.length();

                if(endIndex > targetLength) continue;

                if(word.equals(target.substring(i, endIndex))){
                    List<List<String>> futureConstruct = table.get(endIndex);
                    for(List<String> baseConstruct: baseConstructs){
                        List<String> newConstruct = new ArrayList<>(baseConstruct);
                        newConstruct.add(word);
                        futureConstruct.add(newConstruct); // This sets the valid within the table as well
                    }
                }
            }
        }

        return table.get(targetLength);
    }




    /**
     *      Given target: 11, denominations: {1, 5, 6}
     *
     *      Case 1: INFINITE AMOUNT OF EACH DENOMINATION
     *      (like PERMUTATION which allows multiple selection for its arrangement)
     *      To get this, we explore each denomination path i.e solve for each (target - denomination) using all denominations
     *
     *
     *      0   1   2   3   4   5   6   7   8   9   10  11
     *     []  [1]             [5] [6]
     *            [1,1]           [1,5][1,6]
     *               [1,1,1]        [1,1,5][1,1,6]
     *                   [1,1,1,1]    [1,1,1,5][1,1,1,6]
     *                        ...
     *                            [5,1]            [5,5][5,6]
     *                                [6,1]             [6,5]
     *                                    [7,1]
     *
     *
     *
     *
     *                                 11  | {1, 5, 6}
     *              10(-1)                   6(-5)           0(-11)
     *       9(-1)    5(-5) 4(-6)         5(-1)
     *    8(-1)     4(-1)                 4(-1)
     *  7(-1)...
     *
     *  Result: 11 [1, 5, 6]:
     *  [[5, 6], [1, 1, 1, 1, 1, 6], [6, 5], [1, 5, 5], [5, 1, 5], [1, 1, 1, 1, 1, 1, 5], [1, 1, 1, 1, 6, 1], [5, 5, 1],
     *  [1, 1, 1, 1, 1, 5, 1], [1, 1, 1, 6, 1, 1], [1, 1, 1, 1, 5, 1, 1], [1, 1, 6, 1, 1, 1], [1, 1, 1, 5, 1, 1, 1], [1, 6, 1, 1, 1, 1],
     *  [1, 1, 5, 1, 1, 1, 1], [6, 1, 1, 1, 1, 1], [1, 5, 1, 1, 1, 1, 1], [5, 1, 1, 1, 1, 1, 1], [1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1]]
     *
     *  For TABULAR:
     *  The dynamic programming relation is: Given target = m, where x1...xn are elements of arr n
     *      *      dp(n) = dp(m) OR dp(m-x2) or dp(m-x3) or ... or dp(m-xn)
     *
     *      Taking dp(m-x2) and changing to dp(m), we have:
     *            dp(m) = dp(m + x2)
     *            meaning each dp(m) affects forward dp(m+x1), dp(m+x2), ...., dp(m+xn)
     *
     *
     *
     *
     *
     *
     *
     *
     *
     *     Case 1: SINGLE AMOUNT OF EACH DENOMINATION
     *     (like combination which allows only a single selection for its purposes)
     *     To get this we explore two paths, path with denomination (target-denomination)
     *
     *                      11{1,5,6}
     *            11{1,5}               5{1,5}
     *     11{1}       6{1}         5{1}       0{1}
     *  11{}   10{}     6{}      5{}    5{}  4{}
     *
     *  Given: 11, {1,5,6}
     *                        11,3
     *                11,2                5,2
     *          11,1       6,1         5,1       0,1
     *       11,0  10,0   6,0 5,0    5,0  4,0
     *
     *
     * Tree View with Paths Shown:
     *
     *                       11{1,5,6}
     *             11{1,5}()                  5{1,5}(6)
     *     11{1}()         6{1}(5)          5{1}()       0{1}(5)
     *  11{}() 10{}(1)  6{}() 5{}(1)     5{}() 4{}(1)
     *
     *
     *  Result: 11 [1, 5, 6]:
     *  [5, 6] - It selects on the valid result that yields 11, without fiddling with their arrangement.
     *
     *  Observe paths: (), (1), (5), (1,5), (6), (1,6), (5,6)
     *  It gives us all possible selection which still fulfill the constraints of being <= 11
     *
     *
     *  For TABULAR:
     *  The dynamic programming relation is: Given target = m, arr[0...n-1] n
     *      *     if(arr[n-1] > m)
     *      *          dp(m,n) = dp(m,n-1)
     *      *    else
     *      *          dp(m,n) = dp(m,n-1) OR dp(m-arr[n-1],n-1)
     *      *
     *      *     This means dp(m,n-1) affects d(m,n):
     *      *      changing dp(m,n-1) to dp(m,n) => dp(m,n) becomes dp(m,n+1)
     *      *      changing dp(m-arr[n-1],n-1) to dp(m,n) => dp(m,n) becomes dp(m+arr[n-1],n+1)
     *
     *      Each dp(m,n) affects forward: dp(m,n+1) & dp(m+arr[n-1],n+1)'
     *
     *
     *
     *
     *
     *      Patterns
     *      Multi-selection, infinite
     *      dp(target) = dp(target-arr[0]) OR...OR dp(target-arr[n-1])
     *
     *      Selection, infinite
     *      dp(target, n) = dp(target, n-1) OR dp(target-arr[n-1], n)
     *
     *      Selection, not infinite
     *      dp(target, n) = dp(target, n-1) OR dp(target-arr[n-1], n-1)
     *
     *
     *      NB: The base case depends on whether you're focusing on returning values of the edges
     *      or a base value at the leaf node.
     *
     */


    static List<List<Integer>> combinationAllSumTarget(int target, int[] arr, int n){
        List<List<Integer>> weightCombinations = new ArrayList<>();
        if(target == 0) {
            weightCombinations.add(new ArrayList<>());
            return weightCombinations;
        }
        if(n == 0) return weightCombinations;

        List<List<Integer>> firstSubWeights = combinationAllSumTarget(target, arr, n-1);
        weightCombinations.addAll(firstSubWeights);

        if(arr[n-1] <= target){
            List<List<Integer>> secondSubWeights = combinationAllSumTarget(target-arr[n-1], arr, n-1);

            for(List<Integer> secondSubWeight: secondSubWeights){
                secondSubWeight.add(arr[n-1]);
                weightCombinations.add(secondSubWeight);
            }
        }

        return weightCombinations;
    }


    static List<List<Integer>> combinationAllSumLessEqualsTarget(int target, int[] arr, int n){
        List<List<Integer>> weightCombinations = new ArrayList<>();
        if(target == 0 || n == 0) {
            weightCombinations.add(new ArrayList<>());
            return weightCombinations;
        }

        List<List<Integer>> firstSubWeights = combinationAllSumLessEqualsTarget(target, arr, n-1);
        weightCombinations.addAll(firstSubWeights);

        if(arr[n-1] <= target){
            List<List<Integer>> secondSubWeights = combinationAllSumLessEqualsTarget(target-arr[n-1], arr, n-1);

            for(List<Integer> secondSubWeight: secondSubWeights){
                secondSubWeight.add(arr[n-1]);
                weightCombinations.add(secondSubWeight);
            }
        }

        return weightCombinations;
    }


    static List<List<Integer>> combinationInfiniteAllSumTarget(int target, int[] arr, int n){
        List<List<Integer>> weightCombinations = new ArrayList<>();
        if(target == 0) {
            weightCombinations.add(new ArrayList<>());
            return weightCombinations;
        }
        if(n == 0) return weightCombinations;

        List<List<Integer>> firstSubWeights = combinationInfiniteAllSumTarget(target, arr, n-1);
        weightCombinations.addAll(firstSubWeights);

        if(arr[n-1] <= target){
            List<List<Integer>> secondSubWeights = combinationInfiniteAllSumTarget(target-arr[n-1], arr, n);

            for(List<Integer> secondSubWeight: secondSubWeights){
                secondSubWeight.add(arr[n-1]);
                weightCombinations.add(secondSubWeight);
            }
        }

        return weightCombinations;
    }

}

