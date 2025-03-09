package com.interview.problems.junior;

public class Q24_BinarySearch {
    public static void main(String[] args) {
        int[] numbers = {1, 3, 5, 7, 9};
        int target = 5, left = 0, right = numbers.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (numbers[mid] == target) {
                System.out.println("Found at index: " + mid);
                return;
            }
            if (numbers[mid] < target) left = mid + 1;
            else right = mid - 1;
        }

        System.out.println("Not found");
    }
}
