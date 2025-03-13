package com.interview.problems.junior;

public class Q36_SumOfDigits {
    public static void main(String[] args) {
        int[] numbers = {12, 34, 56};
        int totalSum = 0;

        for (int num : numbers) {
            while (num > 0) {
                totalSum += num % 10;
                num /= 10;
            }
        }

        System.out.println("Sum of all digits: " + totalSum);
    }
}
