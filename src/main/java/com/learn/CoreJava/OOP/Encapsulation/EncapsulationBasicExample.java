package com.learn.CoreJava.OOP.Encapsulation;

class Person {
    private String name; // Private variable (Encapsulation)

    // Getter
    public String getName() {
        return name;
    }

    // Setter
    public void setName(String name) {
        this.name = name;
    }
}

public class EncapsulationBasicExample {
    public static void main(String[] args) {
        Person person = new Person();
        person.setName("John Doe"); // Using setter
        System.out.println("Person's Name: " + person.getName()); // Using getter
    }
}
