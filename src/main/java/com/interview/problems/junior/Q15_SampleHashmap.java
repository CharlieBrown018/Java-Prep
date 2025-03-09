package com.interview.problems.junior;

import java.util.HashMap;

public class Q15_SampleHashmap {
    public static void main(String[] args) {
        // Integer-String HashMap
        HashMap<Integer, String> intStrMap = new HashMap<>();
        intStrMap.put(1, "Apple");
        intStrMap.put(2, "Banana");
        intStrMap.put(3, "Cherry");

        System.out.println("Integer-String HashMap: " + intStrMap);
        System.out.println("Size: " + intStrMap.size());

        // String-String HashMap
        HashMap<String, String> strStrMap = new HashMap<>();
        strStrMap.put("A", "Alpha");
        strStrMap.put("B", "Beta");
        strStrMap.put("C", "Gamma");

        System.out.println("\nString-String HashMap: " + strStrMap);
        System.out.println("Size: " + strStrMap.size());
    }
}
