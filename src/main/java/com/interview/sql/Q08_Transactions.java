package com.interview.sql;

import com.interview.util.DatabaseUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Q08_Transactions {
    /*
     * Question: How do you handle transactions in SQL?
     * Demonstrate transaction management, commit, and rollback.
     */

    public static void main(String[] args) {
        Connection conn = null;
        try {
            conn = DatabaseUtil.getConnection();
            // Disable auto-commit
            conn.setAutoCommit(false);

            // Create statements
            Statement stmt = conn.createStatement();

            try {
                // First operation: Update product stock
                String updateStock = """
                            UPDATE products 
                            SET stock = stock - 1 
                            WHERE product_id = 1
                        """;
                stmt.executeUpdate(updateStock);

                // Second operation: Create new order
                String createOrder = """
                            INSERT INTO orders (order_id, customer_id, product_id, quantity)
                            VALUES (4, 1, 1, 1)
                        """;
                stmt.executeUpdate(createOrder);

                // If both operations succeed, commit the transaction
                conn.commit();
                System.out.println("Transaction committed successfully");

                // Show updated data
                showProductStock(stmt, 1);
                showOrders(stmt);

            } catch (SQLException e) {
                // If any operation fails, roll back the transaction
                if (conn != null) {
                    conn.rollback();
                }
                System.out.println("Transaction rolled back: " + e.getMessage());
            }

        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        } finally {
            if (conn != null) {
                try {
                    // Reset auto-commit to default
                    conn.setAutoCommit(true);
                    conn.close();
                } catch (SQLException e) {
                    System.err.println("Error closing connection: " + e.getMessage());
                }
            }
        }
    }

    private static void showProductStock(Statement stmt, int productId)
            throws SQLException {
        ResultSet rs = stmt.executeQuery(
                "SELECT name, stock FROM products WHERE product_id = " + productId
        );
        if (rs.next()) {
            System.out.printf("\nProduct: %s, Current stock: %d%n",
                    rs.getString("name"),
                    rs.getInt("stock")
            );
        }
    }

    private static void showOrders(Statement stmt) throws SQLException {
        ResultSet rs = stmt.executeQuery(
                "SELECT * FROM orders ORDER BY order_id DESC LIMIT 1"
        );
        if (rs.next()) {
            System.out.printf("Latest order: ID=%d, Customer=%d, Product=%d, Quantity=%d%n",
                    rs.getInt("order_id"),
                    rs.getInt("customer_id"),
                    rs.getInt("product_id"),
                    rs.getInt("quantity")
            );
        }
    }
}