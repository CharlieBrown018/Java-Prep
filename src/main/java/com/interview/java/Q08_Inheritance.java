package com.interview.java;

public class Q08_Inheritance {
    /*
     * Question: What is inheritance in Java?
     * Demonstrate basic inheritance, method overriding, and super keyword usage.
     */

    public static void main(String[] args) {
        // Creating objects
        Animal animal = new Animal("Generic Animal");
        Dog dog = new Dog("Buddy", "Golden Retriever");

        // Demonstrating inheritance
        System.out.println("Animal sounds:");
        animal.makeSound();  // Prints "Some sound"
        dog.makeSound();     // Prints "Woof!"

        // Using Dog-specific method
        dog.displayInfo();

        // Demonstrating polymorphism
        Animal dogAsAnimal = new Dog("Max", "German Shepherd");
        dogAsAnimal.makeSound();  // Still prints "Woof!"
    }

    // Parent class (superclass)
    public static class Animal {
        protected String name;

        public Animal(String name) {
            this.name = name;
        }

        public void makeSound() {
            System.out.println("Some sound");
        }
    }

    // Child class (subclass)
    public static class Dog extends Animal {
        private String breed;

        public Dog(String name, String breed) {
            super(name);  // Call parent constructor
            this.breed = breed;
        }

        // Method overriding
        @Override
        public void makeSound() {
            System.out.println("Woof!");
        }

        // Additional method specific to Dog
        public void displayInfo() {
            System.out.println("Dog: " + name + ", Breed: " + breed);
        }
    }
}