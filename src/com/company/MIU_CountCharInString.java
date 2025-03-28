package com.company;

public class MIU_CountCharInString {

    public static void main(String[] args){
        System.out.println(countCharsInString('A', "Abraham"));
        System.out.println(countCharsInString('d', "Dave"));
        System.out.println(countCharsInString('e', "Fola"));
    }

    public static int countCharsInString(char inputChar, String inputString){
        int count = 0;
        for(char loopChar: inputString.toCharArray()){
            if(Character.toLowerCase(inputChar) == Character.toLowerCase(loopChar)) count++;
        }
        return count;
    }

}
