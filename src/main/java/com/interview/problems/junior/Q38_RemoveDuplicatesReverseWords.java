package com.interview.problems.junior;

import java.util.LinkedHashSet;

public class Q38_RemoveDuplicatesReverseWords {
    public static void main(String[] args) {
        String str = "hello world";
        System.out.println("Without Duplicates: " + removeDuplicates(str));

        String sentence = "Java is fun";
        System.out.println("Reversed Words: " + reverseWords(sentence));
    }

    static String removeDuplicates(String str) {
        LinkedHashSet<Character> set = new LinkedHashSet<>();
        for (char c : str.toCharArray()) set.add(c);
        StringBuilder sb = new StringBuilder();
        for (char c : set) sb.append(c);
        return sb.toString();
    }

    static String reverseWords(String sentence) {
        String[] words = sentence.split(" ");
        StringBuilder sb = new StringBuilder();
        for (int i = words.length - 1; i >= 0; i--) sb.append(words[i]).append(" ");
        return sb.toString().trim();
    }
}
