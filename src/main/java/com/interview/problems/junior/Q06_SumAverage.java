package com.interview.problems.junior;

public class Q06_SumAverage {
    public static void main(String[] args) {
        int[] numbers = {5, 3, 8, 1, 6, 9}; // Sample array

        int sum = 0; // Variable to store sum

        for (int num : numbers) { // Loop through array
            sum += num; // Add each number to sum
        }

        double average = (double) sum / numbers.length; // Calculate average

        // Print results
        System.out.println("Sum: " + sum);
        System.out.println("Average: " + average);
    }
}
