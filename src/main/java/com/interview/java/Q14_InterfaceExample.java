package com.interview.java;

public class Q14_InterfaceExample {
    /*
     * Question: What is an interface in Java?
     * Demonstrate interface implementation and multiple interface inheritance.
     */

    public static void main(String[] args) {
        Circle circle = new Circle(5);

        System.out.println("Circle Area: " + circle.calculateArea());
        System.out.println("Circle Perimeter: " + circle.calculatePerimeter());

        circle.display();  // Using default method

        Circle circle2 = new Circle(3);
        System.out.println("Circle comparison: " + circle.compareTo(circle2));
    }

    // Define a simple interface for a shape
    interface Shape {
        double calculateArea();  // Abstract method

        double calculatePerimeter();

        // Default method (Java 8+)
        default void display() {
            System.out.println("This is a shape");
        }
    }

    // Another interface for comparable shapes
    interface Comparable {
        int compareTo(Shape other);
    }

    // Class implementing multiple interfaces
    static class Circle implements Shape, Comparable {
        private double radius;

        public Circle(double radius) {
            this.radius = radius;
        }

        @Override
        public double calculateArea() {
            return Math.PI * radius * radius;
        }

        @Override
        public double calculatePerimeter() {
            return 2 * Math.PI * radius;
        }

        @Override
        public int compareTo(Shape other) {
            if (this.calculateArea() > other.calculateArea()) return 1;
            if (this.calculateArea() < other.calculateArea()) return -1;
            return 0;
        }
    }
}
