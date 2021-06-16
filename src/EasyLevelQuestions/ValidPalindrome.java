package EasyLevelQuestions;

public class ValidPalindrome {
    public static void main(String[] args){
        String s = "A man, a plan, a canal: Panama";
        // expected output: true
        System.out.println(isPalindrome(s));
    }

    public static boolean isPalindrome(String s) {
        // Filter out non alphanumeric characters
        StringBuilder sb = new StringBuilder();
        for(char value: s.toCharArray()){
            if(Character.isDigit(value)) sb.append(value);
            if(Character.isLetter(value)) sb.append(Character.toLowerCase(value));
        }

        // Check if palindrome
        int i = 0;
        int j = sb.length() - 1;
        while(i < j){
            if(sb.charAt(i) != sb.charAt(j)) return false;
            i++;
            j--;
        }
        return true;
    }
}
