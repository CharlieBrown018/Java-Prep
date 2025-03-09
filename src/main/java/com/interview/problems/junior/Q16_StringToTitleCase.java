package com.interview.problems.junior;

public class Q16_StringToTitleCase {
    public static void main(String[] args) {
        String text = "hello world, this is java."; // Sample text
        String[] words = text.split(" "); // Split string into words
        String titleCase = "";

        for (String word : words) {
            if (!word.isEmpty()) { // Ensure word is not empty (avoiding extra spaces)
                titleCase += word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase() + " ";
            }
        }

        System.out.println("Title Case: " + titleCase.trim()); // Trim extra space at the end
    }
}
