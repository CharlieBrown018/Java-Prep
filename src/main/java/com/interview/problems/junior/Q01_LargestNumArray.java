package com.interview.problems.junior;

public class Q01_LargestNumArray {
    public static void main(String[] args) {
        // Define an array of numbers
        int[] numbers = {5, 3, 8, 1, 6, 9};

        // Initialize max with the first element of the array
        int max = numbers[0];

        // Iterate through the rest of the array to find the largest number
        for (int i = 1; i < numbers.length; i++) { // Start from index 1 since max is already numbers[0]
            if (numbers[i] > max) { // Compare current number with max
                max = numbers[i]; // Update max if a larger number is found
            }
        }

        // Print the largest number found in the array
        System.out.println("The largest number is: " + max);
    }
}
