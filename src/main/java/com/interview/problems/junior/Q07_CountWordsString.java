package com.interview.problems.junior;

public class Q07_CountWordsString {
    public static void main(String[] args) {
        String text = "Hello world, this is a test"; // Sample string

        int count = 1; // Start with 1 since the last word doesn't have a space

        for (char c : text.toCharArray()) { // Loop through characters
            if (c == ' ') count++; // Count spaces as word separators
        }

        System.out.println("Word count: " + count);
    }
}
