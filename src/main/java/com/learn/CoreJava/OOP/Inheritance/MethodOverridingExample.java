package com.learn.CoreJava.OOP.Inheritance;

// Parent class
class Animal2 {
    public void makeSound() {
        System.out.println("Some generic animal sound...");
    }
}

// Child class overrides the method
class Dog2 extends Animal2 {
    @Override
    public void makeSound() {
        System.out.println("Bark! Bark!");
    }
}

public class MethodOverridingExample {
    public static void main(String[] args) {
        Animal2 myAnimal = new Animal2();
        myAnimal.makeSound(); // Calls the parent method

        Dog2 myDog2 = new Dog2();
        myDog2.makeSound(); // Calls the overridden method in Dog class
    }
}
