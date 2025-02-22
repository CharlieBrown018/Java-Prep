package com.interview.util;

import java.sql.*;

public class CustomerStatsUtil {
    private static final String DB_URL = "jdbc:sqlite:src/main/db/interview.db";

    public static void main(String[] args) {
        System.out.println("Starting customer stats update...");

        try (Connection conn = getConnection()) {
            System.out.println("Database connection successful!");
            createCustomerStatsTable();
            updateCustomerStats();
            verifyCustomerStats();
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    private static void createCustomerStatsTable() {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {

            // Create customer_stats table if it doesn't exist
            stmt.execute("""
                        CREATE TABLE IF NOT EXISTS customer_stats (
                            customer_id INTEGER PRIMARY KEY,
                            total_orders INTEGER DEFAULT 0,
                            FOREIGN KEY (customer_id) REFERENCES customers(customer_id) ON DELETE CASCADE
                        )
                    """);

            System.out.println("customer_stats table ensured to exist.");

        } catch (SQLException e) {
            System.err.println("Error creating customer_stats table: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void updateCustomerStats() {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {

            // Update total_orders based on orders count
            stmt.execute("""
                        INSERT INTO customer_stats (customer_id, total_orders)
                        SELECT c.customer_id, COUNT(o.order_id)
                        FROM customers c
                        LEFT JOIN orders o ON c.customer_id = o.customer_id
                        ON CONFLICT(customer_id) DO UPDATE 
                        SET total_orders = excluded.total_orders
                    """);

            System.out.println("Customer stats updated successfully.");

        } catch (SQLException e) {
            System.err.println("Error updating customer stats: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void verifyCustomerStats() {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {

            System.out.println("\nVerifying customer_stats:");

            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) as count FROM customer_stats");
            if (rs.next()) {
                System.out.println("Customer stats count: " + rs.getInt("count"));
            }

            System.out.println("\nCustomer stats verification complete!");

        } catch (SQLException e) {
            System.err.println("Error verifying customer_stats: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
