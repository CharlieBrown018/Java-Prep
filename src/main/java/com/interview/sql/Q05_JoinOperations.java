package com.interview.sql;

import com.interview.util.DatabaseUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Q05_JoinOperations {
    /*
     * Question: Demonstrate different types of JOIN operations.
     * Show INNER JOIN, LEFT JOIN, and multiple table joins.
     */

    public static void main(String[] args) {
        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement()) {

            // INNER JOIN
            String sql1 = """
                        SELECT c.name as customer_name, p.name as product_name,
                               o.quantity, o.order_date
                        FROM orders o
                        INNER JOIN customers c ON o.customer_id = c.customer_id
                        INNER JOIN products p ON o.product_id = p.product_id
                    """;

            System.out.println("Order details (INNER JOIN):");
            ResultSet rs1 = stmt.executeQuery(sql1);
            while (rs1.next()) {
                System.out.printf("%s bought %d x %s on %s%n",
                        rs1.getString("customer_name"),
                        rs1.getInt("quantity"),
                        rs1.getString("product_name"),
                        rs1.getTimestamp("order_date")
                );
            }

            // LEFT JOIN to show all customers, even those without orders
            String sql2 = """
                        SELECT c.name, COUNT(o.order_id) as order_count
                        FROM customers c
                        LEFT JOIN orders o ON c.customer_id = o.customer_id
                        GROUP BY c.customer_id, c.name
                    """;

            System.out.println("\nAll customers and their order count (LEFT JOIN):");
            ResultSet rs2 = stmt.executeQuery(sql2);
            while (rs2.next()) {
                System.out.printf("%s - Orders: %d%n",
                        rs2.getString("name"),
                        rs2.getInt("order_count")
                );
            }

            // Multiple table join with conditions
            String sql3 = """
                        SELECT c.name as customer_name, 
                               p.name as product_name,
                               p.price * o.quantity as total_amount
                        FROM orders o
                        JOIN customers c ON o.customer_id = c.customer_id
                        JOIN products p ON o.product_id = p.product_id
                        WHERE p.price > 50
                        ORDER BY total_amount DESC
                    """;

            System.out.println("\nExpensive orders (price > $50):");
            ResultSet rs3 = stmt.executeQuery(sql3);
            while (rs3.next()) {
                System.out.printf("%s - %s - $%.2f%n",
                        rs3.getString("customer_name"),
                        rs3.getString("product_name"),
                        rs3.getDouble("total_amount")
                );
            }

        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }
}