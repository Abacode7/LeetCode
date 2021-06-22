package EasyLevelQuestions;

// LC Question - 38
public class CountAndSay {
    public static void main(String[] args){
        int input = 4; // 1211
        int input2 = 7; // 13112221
        System.out.println(countAndSay(7));
    }

    // Runtime - The countAndSay algorithm goes n times, while
    // the say runs in order of the length of the string, so we say
    // Runtime - O(n * max(stringLength))
    // Space - O(1)
    public static String countAndSay(int n) {
        if(n == 1) return "1";
        int i = 1;
        String temp = "1";
        while(i < n){
            temp = say(temp);
            i++;
        }
        return temp;
    }

    // Say takes the input string, pronounces it and
    // returns output string as pronounced
    public static String say(String numString){
        StringBuilder sb = new StringBuilder();
        int numLen = numString.length();
        int i = 0;
        while(i < numLen){
            int count = 1;
            while(i < numLen-1 && numString.charAt(i) == numString.charAt(i+1)){
                count++;
                i++;
            }
            sb.append(String.valueOf(count) + numString.charAt(i));

            i++;
        }
        return sb.toString();
    }
}
