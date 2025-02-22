package com.interview.java;

public class Q16_GenericsExample {
    /*
     * Question: What are Generics in Java?
     * Demonstrate generic classes and methods.
     */

    // Generic method
    static <T> void swapBoxes(Box<T> box1, Box<T> box2) {
        T temp = box1.getContent();
        box1.setContent(box2.getContent());
        box2.setContent(temp);
    }

    // Generic method with bounded type parameter
    static <T extends Number> double sum(T num1, T num2) {
        return num1.doubleValue() + num2.doubleValue();
    }

    public static void main(String[] args) {
        // Using generic class with different types
        Box<Integer> intBox = new Box<>(42);
        Box<String> stringBox = new Box<>("Hello Generics");

        System.out.println("Integer box: " + intBox.getContent());
        System.out.println("String box: " + stringBox.getContent());

        // Using generic method
        Box<Integer> box1 = new Box<>(1);
        Box<Integer> box2 = new Box<>(2);

        System.out.println("\nBefore swap:");
        System.out.println("Box1: " + box1.getContent());
        System.out.println("Box2: " + box2.getContent());

        swapBoxes(box1, box2);

        System.out.println("\nAfter swap:");
        System.out.println("Box1: " + box1.getContent());
        System.out.println("Box2: " + box2.getContent());

        // Using bounded type parameter
        double result = sum(5.5, 3.3);
        System.out.println("\nSum: " + result);

        // Multiple generic type parameters
        Pair<String, Integer> pair = new Pair<>("Age", 25);
        System.out.println("\nPair: " + pair.getKey() + " = " + pair.getValue());
    }

    // Generic class
    static class Box<T> {
        private T content;

        public Box(T content) {
            this.content = content;
        }

        public T getContent() {
            return content;
        }

        public void setContent(T content) {
            this.content = content;
        }
    }

    // Class with multiple generic type parameters
    static class Pair<K, V> {
        private K key;
        private V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }
    }
}