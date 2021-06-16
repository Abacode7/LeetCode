package EasyLevelQuestions;

import java.util.*;

// LC Question 242
public class ValidAnagram {
    public static  void main(String[] args){
        String s = "anagram", t = "nagaram";
        String a = "rat", b = "car";
        System.out.println(isAnagram(s, t));
    }

    // Runtime O(n), Space O(n)
    // Both linear with respect to the input size
    public static boolean isAnagram(String s, String t) {
        if(s.length() != t.length()) return false;

        Map<Character, Integer> map = new HashMap<>();
        for(int i=0; i<s.length(); i++){
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
            map.put(t.charAt(i), map.getOrDefault(t.charAt(i), 0) - 1);
        }

        for(Map.Entry<Character,Integer> entry: map.entrySet()){
            if(entry.getValue() != 0) return false;
        }
        return true;
    }

    // Todo: Implement using arrays
}
