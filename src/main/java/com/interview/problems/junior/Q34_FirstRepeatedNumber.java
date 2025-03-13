package com.interview.problems.junior;

public class Q34_FirstRepeatedNumber {
    public static void main(String[] args) {
        int[] numbers = {2, 1, 4, 2, 5, 6};

        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < i; j++) { // Check previous elements
                if (numbers[i] == numbers[j]) {
                    System.out.println("First repeated number: " + numbers[i]);
                    return;
                }
            }
        }

        System.out.println("No duplicates found");
    }
}
