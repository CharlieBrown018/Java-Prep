package com.interview.java;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class Q13_SetExample {
    /*
     * Question: What is a Set in Java?
     * Demonstrate HashSet and TreeSet usage and their differences.
     */

    public static void main(String[] args) {
        // HashSet example
        Set<String> hashSet = new HashSet<>();

        // Adding elements
        System.out.println("HashSet Example:");
        hashSet.add("Banana");
        hashSet.add("Apple");
        hashSet.add("Orange");
        hashSet.add("Apple");  // Duplicate - won't be added

        System.out.println("HashSet (unordered): " + hashSet);

        // TreeSet example (ordered)
        Set<String> treeSet = new TreeSet<>();

        System.out.println("\nTreeSet Example:");
        treeSet.add("Banana");
        treeSet.add("Apple");
        treeSet.add("Orange");
        treeSet.add("Apple");  // Duplicate - won't be added

        System.out.println("TreeSet (ordered): " + treeSet);

        // Common Set operations
        System.out.println("\nSet Operations:");
        System.out.println("Contains Apple? " + hashSet.contains("Apple"));
        System.out.println("Size: " + hashSet.size());

        // Removing element
        hashSet.remove("Apple");
        System.out.println("After removing Apple: " + hashSet);

        // Set operations with two sets
        Set<String> set1 = new HashSet<>();
        set1.add("A");
        set1.add("B");
        set1.add("C");

        Set<String> set2 = new HashSet<>();
        set2.add("B");
        set2.add("C");
        set2.add("D");

        // Union
        Set<String> union = new HashSet<>(set1);
        union.addAll(set2);
        System.out.println("\nUnion: " + union);

        // Intersection
        Set<String> intersection = new HashSet<>(set1);
        intersection.retainAll(set2);
        System.out.println("Intersection: " + intersection);

        // Difference
        Set<String> difference = new HashSet<>(set1);
        difference.removeAll(set2);
        System.out.println("Difference (set1 - set2): " + difference);
    }
}
