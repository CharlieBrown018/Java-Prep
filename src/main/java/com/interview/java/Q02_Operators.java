package com.interview.java;

public class Q02_Operators {
    /*
     * Question: Demonstrate the basic operators in Java.
     * Show arithmetic, assignment, comparison, and logical operators.
     */

    public static void main(String[] args) {
        // Arithmetic operators
        int a = 10;
        int b = 3;

        System.out.println("a:" + a + " b:" + b);

        System.out.println("Arithmetic Operators:");
        System.out.println("Addition: " + (a + b));        // 13
        System.out.println("Subtraction: " + (a - b));     // 7
        System.out.println("Multiplication: " + (a * b));  // 30
        System.out.println("Division: " + (a / b));        // 3
        System.out.println("Modulus: " + (a % b));         // 1

        // Assignment operators
        System.out.println("\nAssignment Operators:");
        int c = 5;
        System.out.println("Normal Assignment: c = " + c);
        c += 3; // Same as: c = c + 3
        System.out.println("Add and Assign: c += 3 gives " + c);

        // Comparison operators
        System.out.println("\nComparison Operators:");
        System.out.println("Equal to: " + (a == b));       // false
        System.out.println("Not equal to: " + (a != b));   // true
        System.out.println("Greater than: " + (a > b));    // true
        System.out.println("Less than: " + (a < b));       // false

        // Logical operators
        boolean x = true;
        boolean y = false;
        System.out.println("\nLogical Operators:");
        System.out.println("AND: " + (x && y));         // false
        System.out.println("OR: " + (x || y));          // true
        System.out.println("NOT: " + (!x));             // false

    }
}
