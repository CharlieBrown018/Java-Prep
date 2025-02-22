package com.interview.combined;

import com.interview.util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Q03_BatchProcessing {
    /*
     * Question: How do you perform batch processing in JDBC?
     * Demonstrate batch inserts and updates.
     */

    public static void main(String[] args) {
        // Create sample products
        List<Product> products = new ArrayList<>();
        for (int i = 1; i <= 100; i++) {
            products.add(new Product(
                    "Product " + i,
                    Math.random() * 100,
                    (int) (Math.random() * 50)
            ));
        }

        try (Connection conn = DatabaseUtil.getConnection()) {
            // Disable auto-commit
            conn.setAutoCommit(false);

            // Batch insert
            String insertSQL = "INSERT INTO products (name, price, stock) VALUES (?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {

                long startTime = System.currentTimeMillis();

                for (Product product : products) {
                    pstmt.setString(1, product.name);
                    pstmt.setDouble(2, product.price);
                    pstmt.setInt(3, product.stock);
                    pstmt.addBatch();
                }

                int[] results = pstmt.executeBatch();
                conn.commit();

                long endTime = System.currentTimeMillis();

                System.out.printf("Inserted %d products in %d ms%n",
                        results.length, (endTime - startTime));
            }

            // Batch update
            String updateSQL = "UPDATE products SET price = price * 1.1 WHERE product_id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(updateSQL)) {

                long startTime = System.currentTimeMillis();

                // Update first 50 products
                for (int i = 1; i <= 50; i++) {
                    pstmt.setInt(1, i);
                    pstmt.addBatch();
                }

                int[] results = pstmt.executeBatch();
                conn.commit();

                long endTime = System.currentTimeMillis();

                System.out.printf("Updated %d products in %d ms%n",
                        results.length, (endTime - startTime));
            }

            // Show some results
            try (Statement stmt = conn.createStatement()) {
                ResultSet rs = stmt.executeQuery(
                        "SELECT COUNT(*) as count, AVG(price) as avg_price FROM products"
                );

                if (rs.next()) {
                    System.out.printf("Total products: %d, Average price: $%.2f%n",
                            rs.getInt("count"),
                            rs.getDouble("avg_price")
                    );
                }
            }

        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }

    static class Product {
        String name;
        double price;
        int stock;

        Product(String name, double price, int stock) {
            this.name = name;
            this.price = price;
            this.stock = stock;
        }
    }
}