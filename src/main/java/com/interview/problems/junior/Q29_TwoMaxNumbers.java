package com.interview.problems.junior;

public class Q29_TwoMaxNumbers {
    public static void main(String[] args) {
        int[] numbers = {10, 5, 20, 8, 25};

        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE;

        for (int num : numbers) {
            if (num > max1) {
                max2 = max1; // Move previous max to second max
                max1 = num;  // Update first max
            } else if (num > max2) {
                max2 = num;  // Update second max
            }
        }

        System.out.println("First max: " + max1);
        System.out.println("Second max: " + max2);
    }
}
