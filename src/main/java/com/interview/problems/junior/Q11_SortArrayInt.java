package com.interview.problems.junior;

import java.util.Arrays;

public class Q11_SortArrayInt {
    public static void main(String[] args) {
        int[] numbers = {5, 2, 9, 1, 5, 6}; // Original array

        // Bubble Sort
        for (int i = 0; i < numbers.length - 1; i++) {
            for (int j = 0; j < numbers.length - 1 - i; j++) {
                if (numbers[j] > numbers[j + 1]) {
                    int temp = numbers[j];
                    numbers[j] = numbers[j + 1];
                    numbers[j + 1] = temp;
                }
            }
        }

        System.out.println("Sorted using Bubble Sort: " + Arrays.toString(numbers));

        // Re-initialize array for Built-in Sort (since Bubble Sort modified it)
        numbers = new int[]{5, 2, 9, 1, 5, 6};

        // Built-in Sort
        Arrays.sort(numbers);

        System.out.println("Sorted using Built-in Sort: " + Arrays.toString(numbers));
    }
}
