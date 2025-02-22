package com.interview.sql;

import com.interview.util.DatabaseUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Q04_GroupByAggregate {
    /*
     * Question: How do you use GROUP BY and aggregate functions?
     * Demonstrate common aggregate functions and grouping progressively.
     */

    public static void main(String[] args) {
        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement()) {

            // Example 1: Basic GROUP BY (Count orders per customer, no JOIN yet)
            String sql1 = """
                        SELECT customer_id, COUNT(order_id) as order_count
                        FROM orders
                        GROUP BY customer_id
                    """;
            System.out.println("Order count per customer (basic GROUP BY):");
            ResultSet rs1 = stmt.executeQuery(sql1);
            while (rs1.next()) {
                System.out.printf("Customer ID: %d - Orders: %d%n",
                        rs1.getInt("customer_id"),
                        rs1.getInt("order_count")
                );
            }

            // Example 2: GROUP BY with JOIN (Show customer names)
            String sql2 = """
                        SELECT c.name, COUNT(o.order_id) as order_count
                        FROM customers c
                        LEFT JOIN orders o ON c.customer_id = o.customer_id
                        GROUP BY c.customer_id, c.name
                    """;
            System.out.println("\nOrder count per customer (with JOIN):");
            ResultSet rs2 = stmt.executeQuery(sql2);
            while (rs2.next()) {
                System.out.printf("%s - Orders: %d%n",
                        rs2.getString("name"),
                        rs2.getInt("order_count")
                );
            }

            // Example 3: Aggregate functions on products
            String sql3 = """
                        SELECT MIN(price) as min_price,
                               MAX(price) as max_price,
                               AVG(price) as avg_price,
                               COUNT(*) as total_products
                        FROM products
                    """;
            System.out.println("\nProduct Statistics:");
            ResultSet rs3 = stmt.executeQuery(sql3);
            if (rs3.next()) {
                System.out.printf("Min Price: $%.2f%n", rs3.getDouble("min_price"));
                System.out.printf("Max Price: $%.2f%n", rs3.getDouble("max_price"));
                System.out.printf("Avg Price: $%.2f%n", rs3.getDouble("avg_price"));
                System.out.printf("Total Products: %d%n", rs3.getInt("total_products"));
            }

            // Example 4: Using HAVING to filter grouped results
            String sql4 = """
                        SELECT product_id, SUM(quantity) as total_sold
                        FROM orders
                        GROUP BY product_id
                        HAVING SUM(quantity) > 1
                    """;
            System.out.println("\nProducts sold more than once:");
            ResultSet rs4 = stmt.executeQuery(sql4);
            while (rs4.next()) {
                System.out.printf("Product ID: %d - Total Sold: %d%n",
                        rs4.getInt("product_id"),
                        rs4.getInt("total_sold")
                );
            }

        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }
}
