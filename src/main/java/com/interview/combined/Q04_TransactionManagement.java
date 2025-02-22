package com.interview.combined;

import com.interview.util.DatabaseUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Q04_TransactionManagement {
    /*
     * Question: How do you handle complex transactions with multiple operations?
     * Demonstrate transaction management with proper error handling.
     */

    private static final Logger logger = Logger.getLogger(Q04_TransactionManagement.class.getName());

    public static void main(String[] args) {
        // Configure logging
        ConsoleHandler handler = new ConsoleHandler();
        handler.setLevel(Level.ALL);
        logger.addHandler(handler);
        logger.setLevel(Level.ALL);

        OrderProcessor processor = new OrderProcessor();

        // Test successful order
        try {
            processor.processOrder(1, 1, 1);
            System.out.println("Order processed successfully");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }

        // Test failed order (insufficient stock)
        try {
            processor.processOrder(1, 1, 1000);
            System.out.println("Order processed successfully");
        } catch (Exception e) {
            System.err.println("Expected error: " + e.getMessage());
        }
    }

    static class OrderProcessor {
        public void processOrder(int customerId, int productId, int quantity) {
            Connection conn = null;
            try {
                conn = DatabaseUtil.getConnection();
                conn.setAutoCommit(false);

                // Check product availability
                if (!checkStock(conn, productId, quantity)) {
                    throw new SQLException("Insufficient stock");
                }

                // Update stock
                updateStock(conn, productId, quantity);

                // Create order
                createOrder(conn, customerId, productId, quantity);

                // Update customer stats
                updateCustomerStats(conn, customerId);

                // All operations successful, commit transaction
                conn.commit();
                logger.info("Order processed successfully");

            } catch (SQLException e) {
                logger.log(Level.SEVERE, "Error processing order", e);
                try {
                    if (conn != null) {
                        conn.rollback();
                        logger.info("Transaction rolled back");
                    }
                } catch (SQLException rollbackEx) {
                    logger.log(Level.SEVERE, "Error rolling back transaction", rollbackEx);
                }
                throw new RuntimeException("Order processing failed", e);
            } finally {
                try {
                    if (conn != null) {
                        conn.setAutoCommit(true);
                        conn.close();
                    }
                } catch (SQLException e) {
                    logger.log(Level.SEVERE, "Error closing connection", e);
                }
            }
        }

        private boolean checkStock(Connection conn, int productId, int quantity)
                throws SQLException {
            String sql = "SELECT stock FROM products WHERE product_id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, productId);
                ResultSet rs = pstmt.executeQuery();
                return rs.next() && rs.getInt("stock") >= quantity;
            }
        }

        private void updateStock(Connection conn, int productId, int quantity)
                throws SQLException {
            String sql = "UPDATE products SET stock = stock - ? WHERE product_id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, quantity);
                pstmt.setInt(2, productId);
                pstmt.executeUpdate();
            }
        }

        private void createOrder(Connection conn, int customerId, int productId, int quantity)
                throws SQLException {
            String sql = "INSERT INTO orders (customer_id, product_id, quantity) VALUES (?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, customerId);
                pstmt.setInt(2, productId);
                pstmt.setInt(3, quantity);
                pstmt.executeUpdate();
            }
        }

        private void updateCustomerStats(Connection conn, int customerId)
                throws SQLException {
            // This would update customer statistics like total orders, total spent, etc.
            String sql = """
                        UPDATE customer_stats 
                        SET total_orders = (
                            SELECT COUNT(*) 
                            FROM orders 
                            WHERE customer_id = ?
                        )
                        WHERE customer_id = ?
                    """;
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, customerId);
                pstmt.setInt(2, customerId);
                pstmt.executeUpdate();
            }
        }
    }
}