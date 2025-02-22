package com.interview.java;

public class Q01_Variables {
    /*
     * Question: What are the main primitive data types in Java?
     * Create variables of each type and demonstrate their usage.
     * This tests basic understanding of Java data types.
     */

    public static void main(String[] args) {
        // Numeric types
        byte myByte = 127;              // 8-bit, range: -128 to 127
        short myShort = 32000;          // 16-bit, range: -32,768 to 32,767
        int myInt = 2000000000;         // 32-bit, most common integer type
        long myLong = 9000000000000L;   // 64-bit

        // Floating-point types
        float myFloat = 10.4f;          // 32-bit
        double myDouble = 10.4;         // 64-bit, default for decimal numbers

        // Character type
        char myChar = 'A';              // 16-bit Unicode character

        // Boolean type
        boolean myboolean = true;       // true or false

        // Print all variables
        System.out.println("byte: " + myByte);
        System.out.println("short: " + myShort);
        System.out.println("int: " + myInt);
        System.out.println("long: " + myLong);
        System.out.println("float: " + myFloat);
        System.out.println("double: " + myDouble);
        System.out.println("char: " + myChar);
        System.out.println("boolean: " + myboolean);
    }
}
