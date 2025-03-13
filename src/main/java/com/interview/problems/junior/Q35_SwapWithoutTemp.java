package com.interview.problems.junior;

public class Q35_SwapWithoutTemp {
    public static void main(String[] args) {
        int a = 5, b = 10;

        a = a + b;
        b = a - b;
        a = a - b;

        System.out.println("After Swap: a = " + a + ", b = " + b);
    }
}
