package com.interview.java;

public class Q05_Methods {
    /*
     * Question: What are methods in Java?
     * Demonstrate method declaration, parameters, return types, and method overloading.
     */

    // Method with no parameters and no return value
    public static void sayHello() {
        System.out.println("Hello!");
    }

    // Method with parameters and return value
    public static int add(int a, int b) {
        return a + b;
    }

    // Method overloading - same name, different parameters
    public static int add(int a, int b, int c) {
        return a + b + c;
    }

    // Method with array parameter
    public static double average(int[] numbers) {
        int sum = 0;
        for (int num : numbers) {
            sum += num;
        }
        return (double) sum / numbers.length;
    }

    public static void main(String[] args) {
        // Calling methods
        sayHello();

        int sum = add(5, 3);
        System.out.println("Sum of 5 and 3: " + sum);

        int sum3 = add(5, 3, 2);
        System.out.println("Sum of 5, 3, and 2: " + sum3);

        int[] numbers = {1, 2, 3, 4, 5};
        double avg = average(numbers);
        System.out.println("Average of array: " + avg);
    }
}
