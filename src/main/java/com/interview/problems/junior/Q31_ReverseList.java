package com.interview.problems.junior;

import java.util.Arrays;

public class Q31_ReverseList {
    public static void main(String[] args) {
        int[] numbers = {1, 2, 3, 4, 5};

        int left = 0, right = numbers.length - 1;
        while (left < right) {
            int temp = numbers[left];
            numbers[left] = numbers[right];
            numbers[right] = temp;
            left++;
            right--;
        }

        System.out.println("Reversed list: " + Arrays.toString(numbers));
    }
}
