package com.interview.problems.junior;

public class Q05_PrimeNum {
    public static void main(String[] args) {
        int[] numbers = {2, 3, 4, 5, 6, 7, 8, 9, 10, 11};

        for (int n : numbers) {
            int count = 0; // Count factors of the number

            for (int i = 1; i <= n; i++) { // Check divisibility from 1 to n
                if (n % i == 0) {
                    count++; // Count how many times it's divisible
                }
            }

            if (count == 2) { // Prime numbers have exactly 2 factors (1 and itself)
                System.out.println(n + " is a prime number.");
            } else {
                System.out.println(n + " is NOT a prime number.");
            }
        }
    }
}
