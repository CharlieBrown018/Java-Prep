package com.learn.CoreJava.OOP.Polymorphism;

// Interface
interface Honkable {
    void honk(); // Abstract method (implicitly public & abstract)
}

// Abstract class
abstract class Vehicle {
    abstract void start(); // Abstract method
}

// Concrete class extending abstract class & implementing interface
class Car extends Vehicle implements Honkable {
    @Override
    void start() {
        System.out.println("Car is starting with a key.");
    }

    @Override
    public void honk() {
        System.out.println("Car honks: Beep! Beep!");
    }
}

// Another concrete class
class Bike extends Vehicle {
    @Override
    void start() {
        System.out.println("Bike is starting with a kick.");
    }
}

public class PolymorphismAdvancedExample {
    public static void main(String[] args) {
        Vehicle myCar = new Car();
        Vehicle myBike = new Bike();
        Honkable honkableCar = new Car();

        myCar.start();  // Calls Car's start()
        myBike.start(); // Calls Bike's start()
        honkableCar.honk(); // Calls Car's honk()
    }
}
