package com.interview.java;

public class Q03_ControlFlow {
    /*
     * Question: Demonstrate the basic control flow statements in Java.
     * Show if-else, switch, while, do-while, and for loops.
     */

    public static void main(String[] args) {
        int num = 5;

        // if-else statement
        System.out.println("IF-Else Example:");
        if(num > 0) {
            System.out.println("Num is positive");
        } else if (num < 0) {
            System.out.println("Num is negative");
        } else {
            System.out.println("Num is zero");
        }

        // switch statement
        System.out.println("\nSwitch Example:");
        switch(num) {
            case 1:
                System.out.println("One");
                break;
            case 5:
                System.out.println("Five");
                break;
            default:
                System.out.println("Other number");
        }

        // for loop
        System.out.println("\nFor Loop Example:");
        for (int i = 1; i <= 3; i++) {
            System.out.println("Count: " + i);
        }

        // while loop
        System.out.println("\nWhile Loop Example:");
        int count = 1;
        while (count <= 3) {
            System.out.println("While count: " + count);
            count++;
        }

        // do-while loop
        System.out.println("\nDo-While Loop Example:");
        int doCount = 1;
        do {
            System.out.println("Do-While count: " + doCount);
            doCount++;
        } while (doCount <= 3);
    }
}
