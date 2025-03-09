package com.interview.problems.junior;

public class Q25_IntersectionTwoArrays {
    public static void main(String[] args) {
        int[] arr1 = {1, 2, 3, 4, 5};
        int[] arr2 = {3, 4, 5, 6, 7};

        System.out.print("Intersection: ");
        for (int num1 : arr1) {
            for (int num2 : arr2) {
                if (num1 == num2) {
                    System.out.print(num1 + " ");
                    break;
                }
            }
        }
    }
}
