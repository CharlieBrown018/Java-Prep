package com.learn.CoreJava.OOP.Polymorphism;

class MathOperations {
    // Overloaded method with two integers
    public int add(int a, int b) {
        return a + b;
    }

    // Overloaded method with three integers
    public int add(int a, int b, int c) {
        return a + b + c;
    }

    // Overloaded method with double values
    public double add(double a, double b) {
        return a + b;
    }
}

public class PolymorphismBasicExample {
    public static void main(String[] args) {
        MathOperations math = new MathOperations();
        System.out.println(math.add(5, 10));       // Calls add(int, int)
        System.out.println(math.add(5, 10, 15));   // Calls add(int, int, int)
        System.out.println(math.add(5.5, 2.5));    // Calls add(double, double)
    }
}
