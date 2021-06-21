package EasyLevelQuestions;

// LC Question 28
public class Implement_strStr {
    public static void  main(String[] args){
        String haystack = "hello";
        String needle = "ll";
        System.out.println(strStr(haystack, needle));
    }


    // First solution
    // Runtime has upper bound of m * n (more specifically (m*n)*n)
    // where m is length of haystack and n is length of needle, hence O(m*n)
    // Space O(1)
    public static int strStr(String haystack, String needle) {
        int haystackLen = haystack.length();
        int needleLen = needle.length();

        if(needleLen == 0) return 0;

        // We say haystackLen - needleLen, cos beyond this
        // value, we exhaust the haystack before the needle
        for(int i=0; i <= haystackLen - needleLen; i++){
            if(haystack.charAt(i) == needle.charAt(0)){
                int k = i+1;
                int j = 1;

                while(k < haystackLen && j < needleLen &&
                        haystack.charAt(k) == needle.charAt(j)){
                    k++;
                    j++;
                }

                // If we go the entire needle length
                // without exiting the loop
                if(j == needleLen) return i;
            }
        }
        return -1;
    }

    // Using startsWith method
    public static int strStr2(String haystack, String needle) {
        if (haystack.equals(needle) || needle.isEmpty()) return 0;
        for (int i = 0; i < haystack.length(); i++) {
            if (haystack.charAt(i) == needle.charAt(0)) {
                if (haystack.startsWith(needle, i)) {
                    return i;
                }
            }
        }
        return -1;
    }
}
