package com.interview.combined;

import com.interview.util.DatabaseUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.*;

public class Q06_ErrorHandling {
    /*
     * Question: How do you implement proper error handling and logging in a database application?
     * Demonstrate best practices for error handling and logging.
     */

    private static final Logger logger = Logger.getLogger(Q06_ErrorHandling.class.getName());

    public static void main(String[] args) {
        ProductService service = new ProductService();
        service.setupLogging();

        try {
            // Test valid product
            service.addProduct("Test Product", 29.99, 10);
            System.out.println("Product added successfully");

            // Test invalid product
            service.addProduct("", -10, -5);

        } catch (DatabaseException e) {
            System.err.println("Error: " + e.getMessage());
            if (e.getCause() != null) {
                System.err.println("Caused by: " + e.getCause().getMessage());
            }
        }

        try {
            // Test stock update
            service.updateStock(1, 5);
            System.out.println("Stock updated successfully");

            // Test invalid product update
            service.updateStock(999, 5);

        } catch (DatabaseException e) {
            System.err.println("Error: " + e.getMessage());
            if (e.getCause() != null) {
                System.err.println("Caused by: " + e.getCause().getMessage());
            }
        }
    }

    // Custom exception for database operations
    static class DatabaseException extends Exception {
        public DatabaseException(String message) {
            super(message);
        }

        public DatabaseException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    // Service class demonstrating error handling
    static class ProductService {
        public void setupLogging() {
            try {
                // Create file handler
                FileHandler fileHandler = new FileHandler("product-service.log", true);
                fileHandler.setFormatter(new SimpleFormatter());
                logger.addHandler(fileHandler);

                // Add console handler
                ConsoleHandler consoleHandler = new ConsoleHandler();
                logger.addHandler(consoleHandler);

                logger.setLevel(Level.ALL);
            } catch (IOException e) {
                System.err.println("Could not setup logging: " + e.getMessage());
            }
        }

        public void addProduct(String name, double price, int stock) throws DatabaseException {
            logger.entering(getClass().getName(), "addProduct");

            try (Connection conn = DatabaseUtil.getConnection()) {
                // Validate input
                validateProductInput(name, price, stock);

                String sql = "INSERT INTO products (name, price, stock) VALUES (?, ?, ?)";
                try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setString(1, name);
                    pstmt.setDouble(2, price);
                    pstmt.setInt(3, stock);
                    pstmt.executeUpdate();
                }

                logger.info(String.format("Product added successfully: %s", name));

            } catch (SQLException e) {
                logger.log(Level.SEVERE, "Database error while adding product", e);
                throw new DatabaseException("Error adding product", e);
            } catch (IllegalArgumentException e) {
                logger.log(Level.WARNING, "Invalid input for product", e);
                throw new DatabaseException("Invalid product data: " + e.getMessage());
            } finally {
                logger.exiting(getClass().getName(), "addProduct");
            }
        }

        private void validateProductInput(String name, double price, int stock) {
            if (name == null || name.trim().isEmpty()) {
                throw new IllegalArgumentException("Product name cannot be empty");
            }
            if (price <= 0) {
                throw new IllegalArgumentException("Price must be greater than zero");
            }
            if (stock < 0) {
                throw new IllegalArgumentException("Stock cannot be negative");
            }
        }

        public void updateStock(int productId, int quantity) throws DatabaseException {
            logger.entering(getClass().getName(), "updateStock");

            Connection conn = null;
            try {
                conn = DatabaseUtil.getConnection();
                conn.setAutoCommit(false);

                // Check if product exists
                if (!productExists(conn, productId)) {
                    throw new IllegalArgumentException("Product not found: " + productId);
                }

                // Update stock
                String sql = "UPDATE products SET stock = stock + ? WHERE product_id = ?";
                try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                    pstmt.setInt(1, quantity);
                    pstmt.setInt(2, productId);
                    int updated = pstmt.executeUpdate();

                    if (updated == 0) {
                        throw new DatabaseException("No product updated");
                    }
                }

                conn.commit();
                logger.info(String.format("Stock updated for product %d: %d", productId, quantity));

            } catch (SQLException e) {
                logger.log(Level.SEVERE, "Database error while updating stock", e);
                if (conn != null) {
                    try {
                        conn.rollback();
                        logger.info("Transaction rolled back");
                    } catch (SQLException ex) {
                        logger.log(Level.SEVERE, "Error during rollback", ex);
                    }
                }
                throw new DatabaseException("Error updating stock", e);
            } finally {
                if (conn != null) {
                    try {
                        conn.setAutoCommit(true);
                        conn.close();
                    } catch (SQLException e) {
                        logger.log(Level.WARNING, "Error closing connection", e);
                    }
                }
                logger.exiting(getClass().getName(), "updateStock");
            }
        }

        private boolean productExists(Connection conn, int productId) throws SQLException {
            String sql = "SELECT 1 FROM products WHERE product_id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setInt(1, productId);
                try (ResultSet rs = pstmt.executeQuery()) {
                    return rs.next();
                }
            }
        }
    }
}