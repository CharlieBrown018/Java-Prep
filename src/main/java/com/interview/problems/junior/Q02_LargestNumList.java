package com.interview.problems.junior;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.List;

public class Q02_LargestNumList {
    public static void main(String[] args) {
        // Define a list of numbers
        List<Integer> numbers = Arrays.asList(3, 2, 3, 6, 7, 2);

        // Use IntSummaryStatistics to find min & max in one go
        IntSummaryStatistics stats = numbers.stream().mapToInt(x -> x).summaryStatistics();

        // Print highest number in the list
        System.out.println("Highest number in list: " + stats.getMax());
        System.out.println("Lowest number in list: " + stats.getMin());
    }
}
