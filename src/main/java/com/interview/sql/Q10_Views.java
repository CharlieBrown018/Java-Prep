package com.interview.sql;

import com.interview.util.DatabaseUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Q10_Views {
    /*
     * Question: How do you work with views in SQLite?
     * Demonstrate creating and using views.
     */

    public static void main(String[] args) {
        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement()) {

            // Create a view for order summary
            stmt.execute("""
                        CREATE VIEW IF NOT EXISTS order_summary AS
                        SELECT 
                            c.name as customer_name,
                            p.name as product_name,
                            o.quantity,
                            p.price * o.quantity as total_amount
                        FROM orders o
                        JOIN customers c ON o.customer_id = c.customer_id
                        JOIN products p ON o.product_id = p.product_id
                    """);

            // Create a view for product statistics
            stmt.execute("""
                        CREATE VIEW IF NOT EXISTS product_stats AS
                        SELECT 
                            p.name,
                            p.price,
                            COALESCE(SUM(o.quantity), 0) as total_sold,
                            p.stock as current_stock
                        FROM products p
                        LEFT JOIN orders o ON p.product_id = o.product_id
                        GROUP BY p.product_id, p.name, p.price, p.stock
                    """);

            // Query views
            System.out.println("Order Summary:");
            ResultSet rs1 = stmt.executeQuery("SELECT * FROM order_summary");
            while (rs1.next()) {
                System.out.printf("%s bought %d x %s for $%.2f%n",
                        rs1.getString("customer_name"),
                        rs1.getInt("quantity"),
                        rs1.getString("product_name"),
                        rs1.getDouble("total_amount")
                );
            }

            System.out.println("\nProduct Statistics:");
            ResultSet rs2 = stmt.executeQuery("SELECT * FROM product_stats");
            while (rs2.next()) {
                System.out.printf("%s - Price: $%.2f, Sold: %d, In Stock: %d%n",
                        rs2.getString("name"),
                        rs2.getDouble("price"),
                        rs2.getInt("total_sold"),
                        rs2.getInt("current_stock")
                );
            }

            // Show all views in database
            ResultSet rs3 = stmt.executeQuery("""
                        SELECT name FROM sqlite_master 
                        WHERE type='view'
                    """);

            System.out.println("\nAll views in database:");
            while (rs3.next()) {
                System.out.println(rs3.getString("name"));
            }

        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }
}