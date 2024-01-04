package com.company;

public class AE_CaesarCipherEncryptor {
    /**
     111111111222222
     01234567890123456789012345
     abcdefghijklmnopqrstuvwxyz

     'a': 97
     'A': 65

     'a' - 'a' = 0
     'x' - 'a' = 23

     use (char - 'a' = index) to get position
     shift index by key
     - If new index >= 26: index %= 26;
     compute character int value by adding 97 ('a')

     **/

    public static String caesarCypherEncryptor(String str, int key) {
        // Write your code here.
        StringBuilder sb = new StringBuilder();
        for(char value: str.toCharArray()){
            int newIndex = value - 'a' + key;
            if(newIndex >= 26) newIndex = newIndex % 26;
            newIndex += 97;
            char newChar = (char) newIndex;
            sb.append(newChar);
        }
        return sb.toString();
    }
}
