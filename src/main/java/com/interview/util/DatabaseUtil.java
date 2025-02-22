package com.interview.util;

import java.io.File;
import java.sql.*;

public class DatabaseUtil {
    private static final String DB_URL = "jdbc:sqlite:src/main/db/interview.db";

    static {
        createDatabaseFile();
    }

    private static void createDatabaseFile() {
        File dbFile = new File("src/main/db/interview.db");
        if (!dbFile.getParentFile().exists()) {
            dbFile.getParentFile().mkdirs();
        }
        if (!dbFile.exists()) {
            System.out.println("Creating new database file at: " + dbFile.getAbsolutePath());
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    public static void main(String[] args) {
        System.out.println("Starting database initialization...");

        try (Connection conn = getConnection()) {
            System.out.println("Database connection successful!");
            initializeDatabase();
            verifyDatabaseSetup();
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        }
    }

    private static void initializeDatabase() {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {

            System.out.println("Dropping existing tables...");
            stmt.execute("DROP TABLE IF EXISTS orders");
            stmt.execute("DROP TABLE IF EXISTS customers");
            stmt.execute("DROP TABLE IF EXISTS products");

            System.out.println("Creating tables...");
            // Create customers table
            stmt.execute("""
                        CREATE TABLE customers (
                            customer_id INTEGER PRIMARY KEY,
                            name TEXT NOT NULL,
                            email TEXT UNIQUE,
                            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
                        )
                    """);

            // Create products table
            stmt.execute("""
                        CREATE TABLE products (
                            product_id INTEGER PRIMARY KEY,
                            name TEXT NOT NULL,
                            price DECIMAL(10,2) NOT NULL,
                            stock INTEGER DEFAULT 0
                        )
                    """);

            // Create orders table
            stmt.execute("""
                        CREATE TABLE orders (
                            order_id INTEGER PRIMARY KEY,
                            customer_id INTEGER,
                            product_id INTEGER,
                            quantity INTEGER NOT NULL,
                            order_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                            FOREIGN KEY (customer_id) REFERENCES customers(customer_id),
                            FOREIGN KEY (product_id) REFERENCES products(product_id)
                        )
                    """);

            System.out.println("Inserting sample data...");
            // Insert sample data
            stmt.execute("""
                        INSERT INTO customers (customer_id, name, email) VALUES 
                        (1, 'John Doe', 'john@example.com'),
                        (2, 'Jane Smith', 'jane@example.com'),
                        (3, 'Bob Wilson', 'bob@example.com')
                    """);

            stmt.execute("""
                        INSERT INTO products (product_id, name, price, stock) VALUES 
                        (1, 'Laptop', 999.99, 10),
                        (2, 'Mouse', 24.99, 50),
                        (3, 'Keyboard', 59.99, 30)
                    """);

            stmt.execute("""
                        INSERT INTO orders (order_id, customer_id, product_id, quantity) VALUES 
                        (1, 1, 1, 1),
                        (2, 1, 2, 2),
                        (3, 2, 3, 1)
                    """);

            System.out.println("Database initialization completed!");

        } catch (SQLException e) {
            System.err.println("Error initializing database: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void verifyDatabaseSetup() {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {

            System.out.println("\nVerifying database setup:");

            // Check customers
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) as count FROM customers");
            if (rs.next()) {
                System.out.println("Customers count: " + rs.getInt("count"));
            }

            // Check products
            rs = stmt.executeQuery("SELECT COUNT(*) as count FROM products");
            if (rs.next()) {
                System.out.println("Products count: " + rs.getInt("count"));
            }

            // Check orders
            rs = stmt.executeQuery("SELECT COUNT(*) as count FROM orders");
            if (rs.next()) {
                System.out.println("Orders count: " + rs.getInt("count"));
            }

            System.out.println("\nDatabase verification complete!");

        } catch (SQLException e) {
            System.err.println("Error verifying database: " + e.getMessage());
            e.printStackTrace();
        }
    }
}