package com.interview.java;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Q17_FileIOExample {
    /*
     * Question: Demonstrate basic file operations in Java.
     * Show reading, writing, and file manipulation using both IO and NIO.
     */

    public static void main(String[] args) {
        // File paths
        String filePath = "test.txt";
        String newFilePath = "test_copy.txt";

        try {
            // Writing to a file using BufferedWriter
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                writer.write("Hello, File IO!");
                writer.newLine();
                writer.write("This is a test file.");
            }

            // Reading from file using BufferedReader
            System.out.println("Reading file using BufferedReader:");
            try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            }

            // NIO.2 - Writing to file
            String content = "Hello, NIO.2!\nThis is another test.";
            Files.write(Paths.get(newFilePath), content.getBytes());

            // NIO.2 - Reading from file
            System.out.println("\nReading file using NIO.2:");
            Files.readAllLines(Paths.get(newFilePath))
                    .forEach(System.out::println);

            // File information
            File file = new File(filePath);
            System.out.println("\nFile Information:");
            System.out.println("Exists: " + file.exists());
            System.out.println("Size: " + file.length() + " bytes");
            System.out.println("Can read: " + file.canRead());
            System.out.println("Can write: " + file.canWrite());

            // Creating directory
            File dir = new File("testDir");
            if (dir.mkdir()) {
                System.out.println("\nDirectory created");
            }

            // List directory contents
            File[] files = dir.listFiles();
            if (files != null) {
                System.out.println("\nDirectory contents:");
                for (File f : files) {
                    System.out.println(f.getName());
                }
            }

            // Clean up
            new File(filePath).delete();
            new File(newFilePath).delete();
            dir.delete();

        } catch (IOException e) {
            System.err.println("IO Error: " + e.getMessage());
        }
    }
}