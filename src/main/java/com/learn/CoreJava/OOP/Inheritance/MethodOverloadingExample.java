package com.learn.CoreJava.OOP.Inheritance;

// Method Overloading Example
class MathUtils {
    // Overloaded methods with different parameters
    public int add(int a, int b) {
        return a + b;
    }

    public double add(double a, double b) {
        return a + b;
    }

    public int add(int a, int b, int c) {
        return a + b + c;
    }
}

public class MethodOverloadingExample {
    public static void main(String[] args) {
        MathUtils math = new MathUtils();
        System.out.println("Sum (int, int): " + math.add(5, 10));
        System.out.println("Sum (double, double): " + math.add(5.5, 2.5));
        System.out.println("Sum (int, int, int): " + math.add(3, 4, 5));
    }
}