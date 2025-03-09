package com.interview.problems.junior;

public class Q22_StackDemo {
    public static void main(String[] args) {
        int[] stack = new int[5];
        int top = -1;

        // Push elements
        stack[++top] = 10;
        stack[++top] = 20;
        stack[++top] = 30;

        // Pop elements
        System.out.println("Popped: " + stack[top--]);
        System.out.println("Popped: " + stack[top--]);
    }
}
