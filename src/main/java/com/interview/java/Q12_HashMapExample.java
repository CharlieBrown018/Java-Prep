package com.interview.java;

import java.util.HashMap;
import java.util.Map;

public class Q12_HashMapExample {
    /*
     * Question: How does HashMap work in Java?
     * Demonstrate basic HashMap operations and common use cases.
     */

    public static void main(String[] args) {
        // Creating HashMap
        Map<String, Integer> studentScores = new HashMap<>();

        // Adding key-value pairs
        studentScores.put("John", 90);
        studentScores.put("Alice", 85);
        studentScores.put("Bob", 88);
        studentScores.put("Jake", 99);


        // Displaying all entries
        System.out.println("Student scores: " + studentScores);

        // Getting value for key
        System.out.println("John's score: " + studentScores.get("John"));

        // Checking if key exists
        System.out.println("Contains Alice? " + studentScores.containsKey("Alice"));

        // Checking if value exists
        System.out.println("Contains score 85? " + studentScores.containsValue(85));

        // Updating value
        studentScores.put("John", 92); // Overwrites old value
        System.out.println("John's new score: " + studentScores.get("John"));

        // Getting value with default
        System.out.println("Tom's score: " + studentScores.getOrDefault("Tom", 0));

        // Iterating through entries
        System.out.println("\nAll scores:");
        for (Map.Entry<String, Integer> entry : studentScores.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        // Iterating through keys
        System.out.println("\nAll students:");
        for (String student : studentScores.keySet()) {
            System.out.println(student);
        }

        // Removing entry
        studentScores.remove("Bob");
        System.out.println("\nAfter removing Bob: " + studentScores);

        // Size of map
        System.out.println("Number of students: " + studentScores.size());

        // Clearing the map
        studentScores.clear();
        System.out.println("After clearing: " + studentScores);
    }
}
