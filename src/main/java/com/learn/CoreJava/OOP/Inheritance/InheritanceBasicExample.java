package com.learn.CoreJava.OOP.Inheritance;

// Parent class
class Animal {
    String name;

    public void eat() {
        System.out.println(name + " is eating.");
    }
}

// Child class (inherits from Animal)
class Dog extends Animal {
    public void bark() {
        System.out.println(name + " is barking.");
    }
}

public class InheritanceBasicExample {
    public static void main(String[] args) {
        Dog myDog = new Dog();
        myDog.name = "Buddy"; // Inheriting the 'name' field
        myDog.eat();  // Inherited method
        myDog.bark(); // Dog's own method
    }
}
