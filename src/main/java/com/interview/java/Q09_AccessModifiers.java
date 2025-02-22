package com.interview.java;

public class Q09_AccessModifiers {
    /*
     * Question: What are access modifiers in Java?
     * Demonstrate public, private, protected, and default access levels.
     */
    public static void main(String[] args) {
        Student student = new Student("John", 20, "A", "Section-1");

        // Accessing different members based on their access modifiers
        System.out.println("Accessing with different access modifiers:");
        student.displayPublicInfo(); // Public
        student.displayPrivateInfo(); // Private - won't work in other packages
        student.displayProtectedInfo(); // Protected
        student.displayDefaultInfo(); // Same package
    }

    public static class Student {
        public String name;         // Accessible from anywhere
        protected String grade;      // Accessible in some package and subclasses
        String section;             // Default/package access - same package only
        private int age;            // Only accessible within this class

        public Student(String name, int age, String grade, String section) {
            this.name = name;
            this.age = age;
            this.grade = grade;
            this.section = section;
        }

        // Public method
        public void displayPublicInfo() {
            System.out.println("Name: " + name);
        }

        // Private method
        private void displayPrivateInfo() {
            System.out.println("Age: " + age);
        }

        // Protected method
        private void displayProtectedInfo() {
            System.out.println("Grade: " + grade);
        }

        // Default method
        void displayDefaultInfo() {
            System.out.println("Section: " + section);
        }

        // Public method
        public void displayAllInfo() {
            displayPublicInfo();
            displayPrivateInfo();
            displayProtectedInfo();
            displayDefaultInfo();
        }

    }
}
