package com.interview.java;

import java.util.ArrayList;
import java.util.List;

public class Q11_ArrayList {
    /*
     * Question: Demonstrate ArrayList usage in Java.
     * Show basic operations and common methods of List interface.
     */

    public static void main(String[] args) {
        // Creating ArrayList
        List<String> fruits = new ArrayList<>();

        // Adding elements
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Orange");

        // Displaying elements
        System.out.println("Original list: " + fruits);

        // Adding element at specific index
        fruits.add(1, "Mango");
        System.out.println("After adding Mango at index 1: " + fruits);

        // Removing element
        fruits.remove("Banana");
        System.out.println("After removing Banana: " + fruits);

        // Checking existence
        System.out.println("Contains Apple? " + fruits.contains("Apple"));

        // Getting element by index
        System.out.println("Element at index 1: " + fruits.get(1));

        // List size
        System.out.println("\nIterating through list:");
        for (String fruit : fruits) {
            System.out.println(fruit);
        }

        // Clearing the list
        fruits.clear();
        System.out.println("After clearing: " + fruits);
    }
}
