package com.interview.java;

public class Q22_FactoryExample {
    /*
     * Question: What is the Factory pattern?
     * Demonstrate factory method pattern implementation.
     */

    public static void main(String[] args) {
        AnimalFactory factory = new AnimalFactory();

        // Creating different animals using factory
        try {
            Animal dog = factory.createAnimal("dog");
            Animal cat = factory.createAnimal("cat");
            Animal bird = factory.createAnimal("bird");

            System.out.println("Dog sound:");
            dog.makeSound();

            System.out.println("\nCat sound:");
            cat.makeSound();

            System.out.println("\nBird sound:");
            bird.makeSound();

            // Try creating invalid animal
            System.out.println("\nTrying to create invalid animal:");
            Animal invalid = factory.createAnimal("fish");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    // Product interface
    interface Animal {
        void makeSound();
    }

    // Concrete products
    static class Dog implements Animal {
        @Override
        public void makeSound() {
            System.out.println("Woof!");
        }
    }

    static class Cat implements Animal {
        @Override
        public void makeSound() {
            System.out.println("Meow!");
        }
    }

    static class Bird implements Animal {
        @Override
        public void makeSound() {
            System.out.println("Chirp!");
        }
    }

    // Factory class
    static class AnimalFactory {
        public Animal createAnimal(String type) {
            if (type == null || type.isEmpty()) {
                return null;
            }

            switch (type.toLowerCase()) {
                case "dog":
                    return new Dog();
                case "cat":
                    return new Cat();
                case "bird":
                    return new Bird();
                default:
                    throw new IllegalArgumentException("Unknown animal type: " + type);
            }
        }
    }
}