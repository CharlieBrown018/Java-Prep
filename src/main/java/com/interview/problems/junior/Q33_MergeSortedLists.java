package com.interview.problems.junior;

import java.util.Arrays;

public class Q33_MergeSortedLists {
    public static void main(String[] args) {
        int[] list1 = {1, 3, 5};
        int[] list2 = {2, 4, 6};

        int[] merged = new int[list1.length + list2.length];

        int i = 0, j = 0, k = 0;
        while (i < list1.length && j < list2.length) {
            merged[k++] = (list1[i] < list2[j]) ? list1[i++] : list2[j++];
        }

        while (i < list1.length) merged[k++] = list1[i++];
        while (j < list2.length) merged[k++] = list2[j++];

        System.out.println("Merged List: " + Arrays.toString(merged));
    }
}
