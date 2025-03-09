package com.learn.CoreJava.OOP.Abstraction;

// Abstract class
abstract class Animal {
    String name;

    // Abstract method (no implementation)
    abstract void makeSound();

    // Concrete method (has implementation)
    public void eat() {
        System.out.println(name + " is eating.");
    }
}

// Concrete subclass
class Dog extends Animal {
    public Dog(String name) {
        this.name = name;
    }

    // Implementing abstract method
    @Override
    void makeSound() {
        System.out.println(name + " barks: Woof! Woof!");
    }
}

public class AbstractionBasicExample {
    public static void main(String[] args) {
        Dog myDog = new Dog("Buddy");
        myDog.makeSound(); // Calls overridden method
        myDog.eat();       // Calls inherited concrete method
    }
}
