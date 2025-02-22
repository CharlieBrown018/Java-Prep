package com.interview.java;

public class Q10_ExceptionHandling {
    /*
     * Question: What is exception handling in Java?
     * Demonstrate try-catch blocks, multiple catches, finally, and throwing exceptions.
     */

    // Method that may throw an exception
    public static int divide(int a, int b) throws ArithmeticException {
        return a / b;
    }

    // Method demonstrating array bounds checking
    public static int getArrayElement(int[] array, int index) {
        return array[index];
    }

    public static void main(String[] args) {
        // Basic try-catch
        try {
            int result = divide(10, 0);
            System.out.println("Result: " + result);
        } catch (ArithmeticException e) {
            System.out.println("Error: Division by zero");
        }

        // Multiple catch blocks
        int[] numbers = {1, 2, 3};
        try {
            System.out.println(getArrayElement(numbers, 5));
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error: Array index out of bounds");
        } catch (Exception e) {
            System.out.println("Error: Something else went wrong");
        }

        // try-catch-finally
        try {
            System.out.println("Attempting risky operation...");
            throw new RuntimeException("Something went wrong");
        } catch (RuntimeException e) {
            System.out.println("Caught error: " + e.getMessage());
        } finally {
            System.out.println("This always executes");
        }

        // try-with-resources (Java 7+)
        try (java.util.Scanner scanner = new java.util.Scanner(System.in)) {
            System.out.println("Scanner will be automatically closed");
        }
    }
}
