package com.interview.sql;

import com.interview.util.DatabaseUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Q03_OrderByClause {
    /*
     * Question: How do you sort results in SQL?
     * Demonstrate ORDER BY clause with different options, progressively.
     */
    public static void main(String[] args) {
        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement()) {

            // Example 1: Basic ORDER BY (Sort by price ascending)
            String sql1 = "SELECT name, price FROM products ORDER BY price ASC";
            System.out.println("Products sorted by price (ascending):");
            printProductResults(stmt.executeQuery(sql1));

            // Example 2: ORDER BY Descending (Sort by price descending)
            String sql2 = "SELECT name, price FROM products ORDER BY price DESC";
            System.out.println("\nProducts sorted by price (descending):");
            printProductResults(stmt.executeQuery(sql2));

            // Example 3: ORDER BY with text column (Sort products by name)
            String sql3 = "SELECT name, price FROM products ORDER BY name ASC";
            System.out.println("\nProducts sorted alphabetically:");
            printProductResults(stmt.executeQuery(sql3));

            // Example 4: Sorting with Multiple Columns
            String sql4 = """
                    SELECT customer_id, COUNT(*) as order_count, MAX(order_date) as latest_order
                    FROM orders
                    GROUP BY customer_id
                    ORDER BY order_count DESC, latest_order DESC
                    """;
            System.out.println("\nCustomers by order count and latest order:");
            ResultSet rs4 = stmt.executeQuery(sql4);
            while (rs4.next()) {
                System.out.printf("Customer ID: %d, Orders: %d, Latest: %s%n",
                        rs4.getInt("customer_id"),
                        rs4.getInt("order_count"),
                        rs4.getTimestamp("latest_order"));
            }

        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }

    private static void printProductResults(ResultSet rs) throws SQLException {
        while (rs.next()) {
            System.out.printf("Product: %s - Price: $%.2f%n",
                    rs.getString("name"),
                    rs.getDouble("price"));
        }
    }
}
