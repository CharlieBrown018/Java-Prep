package com.interview.problems.junior;

import java.util.LinkedList;
import java.util.Vector;

public class Q14_LinkedListVector {
    public static void main(String[] args) {
        // Creating a LinkedList
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("Apple");
        linkedList.add("Banana");
        linkedList.add("Cherry");

        System.out.println("LinkedList: " + linkedList);

        // Creating a Vector with initial capacity of 5 and increments by 2
        Vector<Integer> vector = new Vector<>(5, 2);
        vector.add(10);
        vector.add(20);
        vector.add(30);
        vector.add(40);
        vector.add(50);
        vector.add(60); // Triggers capacity increment

        System.out.println("Vector: " + vector);
    }
}
