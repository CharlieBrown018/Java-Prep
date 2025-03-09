package com.interview.problems.junior;

public class Q04_ReverseString {
    public static void main(String[] args) {
        String input = "Hello";

        if (input == null) {
            System.out.println("Input is null");
            return;
        }

        String reverse = ""; // Store the reversed string
        for (int i = 0; i < input.length(); i++) {
            reverse = input.charAt(i) + reverse; // Prepend each character
        }

        System.out.println("Reversed string: " + reverse);
    }
}
