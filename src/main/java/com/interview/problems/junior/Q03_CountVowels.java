package com.interview.problems.junior;

public class Q03_CountVowels {
    public static void main(String[] args) {
        // Example input string
        String input = "Hello World";

        // Convert input to lowercase for case insensitivity
        input = input.toLowerCase();

        int count = 0; // Variable to store vowel count

        // Loop through each character in the string
        for (int i = 0; i < input.length(); i++) {
            char ch = input.charAt(i); // Get the current character

            // Check if the character is a vowel (a, e, i, o, u)
            if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u') {
                count++; // Increment the vowel count
            }
        }

        // Print the total number of vowels found
        System.out.println("Number of vowels: " + count);
    }
}
