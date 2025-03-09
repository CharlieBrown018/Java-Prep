package com.interview.problems.junior;

public class Q08_CheckPalindrome {
    public static void main(String[] args) {
        String text = "madam"; // Sample string
        int length = text.length();

        boolean isPalindrome = true; // Assume it's a palindrome

        for (int i = 0; i < length / 2; i++) { // Compare characters from both ends
            if (text.charAt(i) != text.charAt(length - 1 - i)) {
                isPalindrome = false; // If mismatch, it's not a palindrome
                break;
            }
        }

        System.out.println(isPalindrome ? "Palindrome" : "Not a palindrome");
    }
}
