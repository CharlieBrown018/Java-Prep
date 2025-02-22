package com.interview.java;

public class Q07_ClassesAndObjects {
    /*
     * Question: What are classes and objects in Java?
     * Demonstrate class creation, object instantiation, constructors,
     * and instance variables vs class variables.
     */

    public static void main(String[] args) {
        // Creating objects of Car class
        Car car1 = new Car("Toyota", "Camry");
        Car car2 = new Car("Honda", "Civic");
        Car car3 = new Car("Maclaren", "P1");

        // Using instance methods
        System.out.println("Car Information:");
        car1.displayInfo();
        car2.displayInfo();
        car3.displayInfo();

        // Accessing static method
        System.out.println("\nTotal cars created: " + Car.getTotalCars());
    }

    // Simple class to represent a Car
    public static class Car {
        // Class variable (shared among all objects)
        private static int totalCars = 0;
        // Instance variables (belong to each object)
        private String brand;
        private String model;

        // Constructor
        public Car(String brand, String model) {
            this.brand = brand;
            this.model = model;
            totalCars++;    // Increment counter when new card is created
        }

        // Static method to get total cars
        public static int getTotalCars() {
            return totalCars;
        }

        // Getter methods
        public String getBrand() {
            return brand;
        }

        public String getModel() {
            return model;
        }

        // Instance method to display car info
        public void displayInfo() {
            System.out.println("Car: " + brand + " " + model);
        }
    }
}
