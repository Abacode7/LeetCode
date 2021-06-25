package EasyLevelQuestions;

// LC Question 14
public class LongestCommonPrefix {
    public static void main(String[] args){
        String[] strs = {"flower","flow","flight"};
        String[] strs2 = {"dog","racecar","car"};
        System.out.println(longestCommonPrefix(strs));
    }

    // Runtime O(n * min(stringLength))
    // Space O(1)
    public static String longestCommonPrefix(String[] strs) {
        int i = 0;
        StringBuilder sb = new StringBuilder();
        while(i < strs[0].length()){
            // Using the first array value as bench mark
            // we take every character in the string and
            // compare across board
            char value = strs[0].charAt(i);
            for(int j=1; j<strs.length; j++){
                if(i >= strs[j].length() || value != strs[j].charAt(i)) return sb.toString();
            }
            sb.append(value);
            i++;
        }
        return sb.toString();
    }
}
