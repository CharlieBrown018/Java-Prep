package com.interview.sql;

import com.interview.util.DatabaseUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Q02_WhereClause {
    /*
     * Question: Demonstrate using WHERE clause to filter results.
     * Show different comparison operators and conditions.
     */

    public static void main(String[] args) {
        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement()) {

            // Example 1: Simple WHERE clause
            String sql1 = "SELECT * FROM products WHERE price > 50";
            System.out.println("Products over $50:");
            printProductResults(stmt.executeQuery(sql1));

            // Example 2: Multiple conditions (Products under $100 with stock > 20
            String sql2 = "SELECT * FROM products WHERE price < 100 AND stock > 20";
            System.out.println("\nProducts under $100 with stock > 20:");
            printProductResults((stmt.executeQuery(sql2)));

            // Example 3: LIKE operator (Ccustomers with exammle.com email)
            String sql3 = "SELECT * FROM customers WHERE email LIKE '%@example.com'";
            System.out.println("\nCustomers with example.com email:");
            printCustomerResults((stmt.executeQuery(sql3)));

        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }

    private static void printProductResults(ResultSet rs) throws SQLException {
        while (rs.next()) {
            System.out.printf("ID: %d, Name: %s, Price: $%.2f, Stock: %d%n",
                    rs.getInt("product_id"),
                    rs.getString("name"),
                    rs.getDouble("price"),
                    rs.getInt("stock"));
        }
    }

    private static void printCustomerResults(ResultSet rs) throws SQLException {
        while (rs.next()) {
            System.out.printf("%s - %s%n",
                    rs.getString("name"),
                    rs.getString("email"));
        }
    }
}
