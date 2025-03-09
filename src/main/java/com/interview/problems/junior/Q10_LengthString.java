package com.interview.problems.junior;

public class Q10_LengthString {
    public static void main(String[] args) {
        String text1 = "Hello"; // Use .length() for this
        String text2 = "World!"; // Use recursion for this

        // Using .length() for text1
        int length1 = text1.length();
        System.out.println("Length of '" + text1 + "': " + length1);

        // Using recursion for text2
        int length2 = 0;
        while (!text2.equals("")) {
            text2 = text2.substring(1); // Remove first character
            length2++; // Increase count
        }
        System.out.println("Length of 'World!': " + length2);
    }
}
