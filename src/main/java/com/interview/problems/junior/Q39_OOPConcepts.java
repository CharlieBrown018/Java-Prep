package com.interview.problems.junior;

// 1️⃣ Encapsulation - Hiding data and providing controlled access
class BankAccount {
    private double balance; // Private variable (data hiding)

    public BankAccount(double balance) { // Constructor
        this.balance = balance;
    }

    public double getBalance() { // Getter method
        return balance;
    }

    public void deposit(double amount) { // Setter method with validation
        if (amount > 0) {
            balance += amount;
        } else {
            System.out.println("Invalid deposit amount!");
        }
    }
}

// 2️⃣ Abstraction - Hiding implementation details, only exposing essential features
abstract class Vehicle {
    abstract void start(); // Abstract method (must be implemented by subclasses)

    public void stop() { // Concrete method (common behavior)
        System.out.println("Vehicle stopped.");
    }
}

class Car extends Vehicle {
    public void start() {
        System.out.println("Car engine started.");
    }
}

// 3️⃣ Inheritance - Reusing properties and behaviors from parent class
class Animal {
    String name;

    public Animal(String name) {
        this.name = name;
    }

    public void eat() {
        System.out.println(name + " is eating.");
    }
}

class Dog extends Animal { // Dog inherits from Animal
    public Dog(String name) {
        super(name);
    }

    public void bark() {
        System.out.println(name + " is barking.");
    }
}

// 4️⃣ Polymorphism - Using the same method in different ways
class Shape {
    public void draw() {
        System.out.println("Drawing a shape.");
    }
}

class Circle extends Shape { // Method overriding (same method, different implementation)
    public void draw() {
        System.out.println("Drawing a circle.");
    }
}

class Square extends Shape {
    public void draw() {
        System.out.println("Drawing a square.");
    }
}

public class Q39_OOPConcepts {
    public static void main(String[] args) {
        // Encapsulation Example
        BankAccount account = new BankAccount(1000);
        account.deposit(500);
        System.out.println("Account Balance: $" + account.getBalance());

        // Abstraction Example
        Vehicle car = new Car();
        car.start();
        car.stop();

        // Inheritance Example
        Dog dog = new Dog("Buddy");
        dog.eat();
        dog.bark();

        // Polymorphism Example
        Shape shape1 = new Circle();
        Shape shape2 = new Square();
        shape1.draw();
        shape2.draw();
    }
}
