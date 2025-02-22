package com.interview.java;

public class Q04_Arrays {
    /*
     * Question: Demonstrate basic array operations in Java.
     * Show array declaration, initialization, and basic operations.
     */

    public static void main(String[] args) {
        // Array declaration and initialization
        int[] numbers = new int[5];
        numbers[0] = 1;
        numbers[1] = 2;
        numbers[2] = 3;
        numbers[3] = 4;
        numbers[4] = 5;

        // Alternative initialization
        int[] quickNumbers = {1, 2, 3, 4, 5}; // Shorthand

        // Printing array elements
        System.out.println("Array Elements:");
        for (int i = 0; i < numbers.length; i++) {
            System.out.println("Element at index " + i + ": " + numbers[i]);
        }

        // Using enhanced for loop (for-each)
        System.out.println("\nUsing enhanced for loop:");
        for (int number : quickNumbers) {
            System.out.println("Element: " + number);
        }

        // 2D Array example
        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        System.out.println("\n2D Array Elements:");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println(); // New line after each row
        }
    }
}
