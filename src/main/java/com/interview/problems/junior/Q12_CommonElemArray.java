package com.interview.problems.junior;

public class Q12_CommonElemArray {
    public static void main(String[] args) {
        int[] array1 = {1, 3, 4, 6, 7, 9};
        int[] array2 = {2, 3, 6, 8, 9, 10};

        System.out.print("Common elements: ");
        for (int i = 0; i < array1.length; i++) {
            for (int j = 0; j < array2.length; j++) {
                if (array1[i] == array2[j]) {
                    System.out.print(array1[i] + " "); // Print common element
                    break; // Stop checking once a match is found
                }
            }
        }
    }
}
