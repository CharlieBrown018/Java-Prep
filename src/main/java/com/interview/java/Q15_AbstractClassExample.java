package com.interview.java;

public class Q15_AbstractClassExample {
    /*
     * Question: What is an abstract class?
     * Demonstrate abstract class usage and difference from interfaces.
     */

    public static void main(String[] args) {
        Car car = new Car("Toyota");

        car.start();    // implemented abstract method
        car.stop();     // implemented concrete method
        car.honk();     // implemented car-specific method

        // Vehicle vehicle = new Vehicle("Generic");    // Would not compile - abstract class
        Vehicle vehicle = new Car("Honda");
        vehicle.start();
        vehicle.stop();
        // vehicle.honk();  // WOn't work - not visible through Vehicle reference
    }

    // Abstract class
    abstract static class Vehicle {
        protected String brand;     // Can have fields

        // Can have constructor
        public Vehicle(String brand) {
            this.brand = brand;
        }

        // Abstract method - must be implemented by subclasses
        abstract void start();

        // Concrete method - can be used as is
        public void stop() {
            System.out.println("Vehicle stopping...");
        }
    }

    // Concrete class extending abstract class
    static class Car extends Vehicle {
        public Car(String brand) {
            super(brand);
        }

        @Override
        void start() {
            System.out.println(brand + " car starting...");
        }

        // Additional method specific to Car
        public void honk() {
            System.out.println("Beep beep!");
        }
    }
}
