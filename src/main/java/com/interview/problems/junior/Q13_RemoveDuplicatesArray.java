package com.interview.problems.junior;

import java.util.Arrays;

public class Q13_RemoveDuplicatesArray {
    public static void main(String[] args) {
        int[] numbers = {1, 3, 3, 6, 6, 9, 9, 9, 10}; // Input array

        Arrays.sort(numbers); // Sort the array first

        int[] temp = new int[numbers.length]; // Temporary array to store unique elements
        int j = 0; // Index for the unique array

        for (int i = 0; i < numbers.length - 1; i++) {
            if (numbers[i] != numbers[i + 1]) {
                temp[j++] = numbers[i]; // Copy unique elements
            }
        }
        temp[j++] = numbers[numbers.length - 1]; // Copy the last element

        // Print unique elements
        System.out.print("Array after removing duplicates: ");
        for (int i = 0; i < j; i++) {
            System.out.print(temp[i] + " ");
        }
    }
}
