package com.interview.java;

import java.util.*;
import java.util.stream.Collectors;

public class Q19_StreamsExample {
    /*
     * Question: What are Streams in Java?
     * Demonstrate common Stream operations and their usage.
     */

    public static void main(String[] args) {
        List<Person> people = Arrays.asList(
                new Person("Alice", 25),
                new Person("Bob", 30),
                new Person("Charlie", 35),
                new Person("David", 28),
                new Person("Eve", 22)
        );

        // Filtering
        System.out.println("People over 25:");
        people.stream()
                .filter(p -> p.getAge() > 25)
                .forEach(System.out::println);

        // Mapping
        System.out.println("\nJust names:");
        people.stream()
                .map(Person::getName)
                .forEach(System.out::println);

        // Collecting to List
        List<String> names = people.stream()
                .map(Person::getName)
                .collect(Collectors.toList());
        System.out.println("\nCollected names: " + names);

        // Sorting
        System.out.println("\nPeople sorted by age:");
        people.stream()
                .sorted(Comparator.comparing(Person::getAge))
                .forEach(System.out::println);

        // Statistics
        double averageAge = people.stream()
                .mapToInt(Person::getAge)
                .average()
                .orElse(0.0);
        System.out.println("\nAverage age: " + averageAge);

        // Reduce
        int totalAge = people.stream()
                .map(Person::getAge)
                .reduce(0, Integer::sum);
        System.out.println("Total age: " + totalAge);

        // Grouping
        Map<Integer, List<Person>> byAge = people.stream()
                .collect(Collectors.groupingBy(Person::getAge));
        System.out.println("\nGrouped by age: " + byAge);

        // Any/All match
        boolean allAdults = people.stream()
                .allMatch(p -> p.getAge() >= 18);
        System.out.println("\nAll adults? " + allAdults);

        // Finding
        Optional<Person> youngest = people.stream()
                .min(Comparator.comparing(Person::getAge));
        youngest.ifPresent(p -> System.out.println("Youngest: " + p));

        // Distinct values
        List<Integer> numbers = Arrays.asList(1, 2, 2, 3, 3, 4, 5, 5);
        System.out.println("\nDistinct numbers: " +
                numbers.stream()
                        .distinct()
                        .collect(Collectors.toList()));
    }

    static class Person {
        private String name;
        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        @Override
        public String toString() {
            return name + " (" + age + ")";
        }
    }
}