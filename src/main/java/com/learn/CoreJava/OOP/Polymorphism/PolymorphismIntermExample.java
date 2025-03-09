package com.learn.CoreJava.OOP.Polymorphism;

// Parent class
class Animal {
    public void makeSound() {
        System.out.println("Animal makes a sound.");
    }
}

// Child class (Overriding the method)
class Dog extends Animal {
    @Override
    public void makeSound() {
        System.out.println("Dog barks.");
    }
}

// Another child class (Overriding the method)
class Cat extends Animal {
    @Override
    public void makeSound() {
        System.out.println("Cat meows.");
    }
}

public class PolymorphismIntermExample {
    public static void main(String[] args) {
        Animal myAnimal = new Animal();
        Animal myDog = new Dog();
        Animal myCat = new Cat();

        myAnimal.makeSound(); // Calls Animal's method
        myDog.makeSound();    // Calls Dog's overridden method
        myCat.makeSound();    // Calls Cat's overridden method
    }
}
