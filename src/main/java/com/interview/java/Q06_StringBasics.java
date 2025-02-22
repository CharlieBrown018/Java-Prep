package com.interview.java;

public class Q06_StringBasics {
    /*
     * Question: Demonstrate basic String operations in Java.
     * Show String declaration, concatenation, and basic String methods.
     */

    public static void main(String[] args) {
        // String declaration
        String str1 = "Hello";
        String str2 = new String("World");

        // String concatenation
        System.out.println("Using + operator: " + str1 + " " + str2);
        System.out.println("Using concat(): " + str1.concat(" ").concat(str2));

        // Basic String methods
        String text = "Java Programming";

        System.out.println("\nString Methods:");
        System.out.println("Length: " + text.length());
        System.out.println("Uppercase: " + text.toUpperCase());
        System.out.println("Lowercase: " + text.toLowerCase());
        System.out.println("First char: " + text.charAt(0));
        System.out.println("Contains 'Java': " + text.contains("Java"));
        System.out.println("Substring(0,4): " + text.substring(0, 4));

        // String comparison
        String s1 = "Hello!";
        String s2 = "Hello!";
        String s3 = new String("Hello!");

        System.out.println("\nString Comparison:");
        System.out.println("s1 == s2: " + (s1 == s2));                // true (same reference)
        System.out.println("s1 == s3: " + (s1 == s3));                // false (different reference)
        System.out.println("s1.equals(s3): " + s1.equals(s3));        // true (same content)
        System.out.println("s1.equalsIgnoreCase(\"HELLO\"): " + s1.equalsIgnoreCase("HELLO"));               // true
    }

}

