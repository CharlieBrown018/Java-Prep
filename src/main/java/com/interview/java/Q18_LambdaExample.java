package com.interview.java;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Q18_LambdaExample {
    /*
     * Question: What are Lambda expressions in Java?
     * Demonstrate lambda usage and functional interfaces.
     */

    public static void main(String[] args) {
        // Basic lambda expression
        Calculator add = (x, y) -> x + y;
        Calculator multiply = (x, y) -> x * y;

        System.out.println("Addition: " + add.calculate(5, 3));
        System.out.println("Multiplication: " + multiply.calculate(5, 3));

        // Using built-in functional interfaces
        // Predicate - takes an argument and returns boolean
        Predicate<Integer> isEven = num -> num % 2 == 0;
        System.out.println("\nIs 4 even? " + isEven.test(4));
        System.out.println("Is 5 even? " + isEven.test(5));

        // Consumer - takes an argument and returns nothing
        Consumer<String> printer = message -> System.out.println("Message: " + message);
        printer.accept("Hello Lambda!");

        // Function - takes an argument and returns a result
        Function<Integer, String> converter = num -> "Number: " + num;
        System.out.println(converter.apply(42));

        // Using lambdas with collections
        List<String> names = new ArrayList<>();
        names.add("Alice");
        names.add("Bob");
        names.add("Charlie");

        System.out.println("\nUsing forEach with lambda:");
        names.forEach(name -> System.out.println("Hello, " + name));

        // Method reference (shorthand lambda)
        System.out.println("\nUsing method reference:");
        names.forEach(System.out::println);
    }

    // Functional interface
    @FunctionalInterface
    interface Calculator {
        int calculate(int x, int y);
    }
}