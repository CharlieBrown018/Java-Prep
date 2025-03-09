package com.interview.problems.junior;

import java.util.Arrays;

public class Q20_AnagramCheck {
    public static void main(String[] args) {
        String str1 = "listen", str2 = "silent";

        char[] arr1 = str1.toCharArray();
        char[] arr2 = str2.toCharArray();
        Arrays.sort(arr1);
        Arrays.sort(arr2);

        System.out.println(str1 + " and " + str2 + (Arrays.equals(arr1, arr2) ? " are " : " are NOT ") + "anagrams.");
    }
}
