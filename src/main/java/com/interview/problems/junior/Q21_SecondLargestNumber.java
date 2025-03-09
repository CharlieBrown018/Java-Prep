package com.interview.problems.junior;

public class Q21_SecondLargestNumber {
    public static void main(String[] args) {
        int[] numbers = {10, 5, 20, 8, 15};
        int max = Integer.MIN_VALUE, secondMax = Integer.MIN_VALUE;

        for (int num : numbers) {
            if (num > max) {
                secondMax = max;
                max = num;
            } else if (num > secondMax && num != max) {
                secondMax = num;
            }
        }

        System.out.println("Second largest number: " + secondMax);
    }
}
