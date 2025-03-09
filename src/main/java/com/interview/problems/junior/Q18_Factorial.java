package com.interview.problems.junior;

public class Q18_Factorial {
    public static void main(String[] args) {
        int num = 5;

        // Using loop
        int fact = 1;
        for (int i = 1; i <= num; i++) {
            fact *= i;
        }
        System.out.println("Factorial (Loop): " + fact);

        // Using recursion
        System.out.println("Factorial (Recursion): " + factorial(num));
    }

    public static int factorial(int n) {
        return (n == 0) ? 1 : n * factorial(n - 1);
    }
}
