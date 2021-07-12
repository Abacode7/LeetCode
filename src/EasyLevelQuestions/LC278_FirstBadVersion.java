package EasyLevelQuestions;

public class LC278_FirstBadVersion {
    public static void main(String[] args){
        int input = 5;
        System.out.println(firstBadVersion(input));
    }

    // Works for input [1 2 3 4 5] where 4 is
    // the first bad version
    public static boolean isBadVersion(int n){
        if(n >= 4) return true;
        return false;
    }

    // Linear solution
    // Time limit exceeded
    public static int firstBadVersion(int n) {
        for(int i=n; i >= 1; i--){
            if(!isBadVersion(i)) return i+1;
        }
        return 1;
    }

    // Using binary search - accepted
    // Runtime O(log n), the search space is half at each turn
    // Space O(log n), which is the max height of the call stack
    public static int firstBadVersionImproved(int n) {
        return helper(1, n);
    }

    public static int helper(int low, int high){
        if(low == high) return low;

        int middle = low + (high-low)/2;

        if(!isBadVersion(middle)){
            return helper(middle+1, high);
        }else{
            return helper(low, middle);
        }
    }

    // BS solution using Iteration
    // Runtime O(log n), the search space is half at each turn
    // Space O(1)
    public static int firstBadVersionImp(int n) {
        int left = 1;
        int right = n;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (isBadVersion(mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }
}
