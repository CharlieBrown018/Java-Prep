package com.interview.java;

class Car {
    private String brand;
    private String engine;
    private int seats;

    // Private constructor
    private Car(CarBuilder builder) {
        this.brand = builder.brand;
        this.engine = builder.engine;
        this.seats = builder.seats;
    }

    @Override
    public String toString() {
        return "Car [Brand=" + brand + ", Engine=" + engine + ", Seats=" + seats + "]";
    }

    public static class CarBuilder {
        private String brand;
        private String engine;
        private int seats;

        public CarBuilder setBrand(String brand) {
            this.brand = brand;
            return this;
        }

        public CarBuilder setEngine(String engine) {
            this.engine = engine;
            return this;
        }

        public CarBuilder setSeats(int seats) {
            this.seats = seats;
            return this;
        }

        public Car build() {
            return new Car(this);
        }
    }
}

public class Q24_BuilderPattern {
    public static void main(String[] args) {
        Car car = new Car.CarBuilder().setBrand("Tesla").setEngine("Electric").setSeats(5).build();
        System.out.println(car);
    }
}
