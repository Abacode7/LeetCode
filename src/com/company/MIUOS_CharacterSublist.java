package com.company;

import java.util.Arrays;
import java.util.Optional;

public class MIUOS_CharacterSublist {

    public static void main(String[] args){
        System.out.println(f(new char[]{'a', 'b', 'c'}, 0, 4));
        System.out.println(f(new char[]{'a', 'b', 'c'}, 0, 3));
        System.out.println(f(new char[]{'a', 'b', 'c'}, 0, 2));
        System.out.println(f(new char[]{'a', 'b', 'c'}, 0, 1));
        System.out.println(f(new char[]{'a', 'b', 'c'}, 1, 3));
        System.out.println(f(new char[]{'a', 'b', 'c'}, 1, 2));
        System.out.println(f(new char[]{'a', 'b', 'c'}, 1, 1));
        System.out.println(f(new char[]{'a', 'b', 'c'}, 2, 2));
        System.out.println(f(new char[]{'a', 'b', 'c'}, 2, 1));
        System.out.println(f(new char[]{'a', 'b', 'c'}, 3, 1));
        System.out.println(f(new char[]{'a', 'b', 'c'}, 1, 0));
        System.out.println(f(new char[]{'a', 'b', 'c'}, -1, 2));
        System.out.println(f(new char[]{'a', 'b', 'c'}, -1, -2));
        System.out.println(f(new char[]{}, 0, 1));
    }

    /**
     *  0     1    2    3    4    5
     * {'a', 'b', 'c', 'd', 'e', 'f'} | 0, 3
     *
     * 0, 3 = 2 => endIndex = 3 - 1 + 0 = startIndex + len - 1
     * 1, 3 = 3 => endIndex = 3
     */
    public static Optional<String> f(char[] a, int startIndex, int length){
        if(startIndex < 0 || length < 0) return Optional.of("null");

        int charLength = a.length;
        int endIndex = startIndex + length - 1;

        if(endIndex >= charLength) return Optional.of("null");

        char[] result = new char[length];
        int i = 0, index = startIndex;
        while(index <= endIndex){
            result[i] = a[index];
            i++;
            index++;
        }
        return Optional.of(Arrays.toString(result));
    }
}
