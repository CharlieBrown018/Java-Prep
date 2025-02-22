package com.interview.sql;

import com.interview.util.DatabaseUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Q07_Subqueries {
    /*
     * Question: How do you use subqueries in SQL?
     * Demonstrate different types of subqueries.
     */

    public static void main(String[] args) {
        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement()) {

            // Scalar subquery
            String sql1 = """
                        SELECT name, price
                        FROM products
                        WHERE price > (SELECT AVG(price) FROM products)
                    """;

            System.out.println("Products above average price:");
            ResultSet rs1 = stmt.executeQuery(sql1);
            while (rs1.next()) {
                System.out.printf("%s - $%.2f%n",
                        rs1.getString("name"),
                        rs1.getDouble("price")
                );
            }

            // Correlated subquery
            String sql2 = """
                        SELECT c.name,
                               (SELECT COUNT(*) 
                                FROM orders o 
                                WHERE o.customer_id = c.customer_id) as order_count
                        FROM customers c
                    """;

            System.out.println("\nCustomer order counts (correlated):");
            ResultSet rs2 = stmt.executeQuery(sql2);
            while (rs2.next()) {
                System.out.printf("%s - %d orders%n",
                        rs2.getString("name"),
                        rs2.getInt("order_count")
                );
            }

            // IN clause with subquery
            String sql3 = """
                        SELECT name, email
                        FROM customers
                        WHERE customer_id IN (
                            SELECT customer_id
                            FROM orders
                            GROUP BY customer_id
                            HAVING COUNT(*) > 1
                        )
                    """;

            System.out.println("\nCustomers with multiple orders:");
            ResultSet rs3 = stmt.executeQuery(sql3);
            while (rs3.next()) {
                System.out.printf("%s - %s%n",
                        rs3.getString("name"),
                        rs3.getString("email")
                );
            }

        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }
}
