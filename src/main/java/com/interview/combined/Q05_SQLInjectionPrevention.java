package com.interview.combined;

import com.interview.util.DatabaseUtil;

import java.sql.*;

public class Q05_SQLInjectionPrevention {
    /*
     * Question: How do you prevent SQL injection attacks?
     * Demonstrate unsafe vs safe SQL practices.
     */

    public static void main(String[] args) {
        // First, create users table
        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.execute("""
                        CREATE TABLE IF NOT EXISTS users (
                            user_id INTEGER PRIMARY KEY,
                            username TEXT UNIQUE NOT NULL,
                            password TEXT NOT NULL,
                            email TEXT UNIQUE NOT NULL
                        )
                    """);

        } catch (SQLException e) {
            System.err.println("Setup error: " + e.getMessage());
            return;
        }

        UserDAO userDAO = new UserDAO();

        // Insert test user
        userDAO.safeInsertUser("testuser", "password123", "test@example.com");

        // Test normal login
        System.out.println("Safe login (correct): " +
                userDAO.safeLogin("testuser", "password123"));

        // Test SQL injection attack
        String maliciousUsername = "' OR '1'='1";
        String maliciousPassword = "anything";

        System.out.println("\nTesting SQL injection:");
        System.out.println("Unsafe login (with injection): " +
                userDAO.unsafeLogin(maliciousUsername, maliciousPassword));
        System.out.println("Safe login (with injection): " +
                userDAO.safeLogin(maliciousUsername, maliciousPassword));
    }

    static class UserDAO {
        // UNSAFE method - vulnerable to SQL injection
        public boolean unsafeLogin(String username, String password) {
            String sql = "SELECT * FROM users WHERE username = '" + username +
                    "' AND password = '" + password + "'";

            try (Connection conn = DatabaseUtil.getConnection();
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {

                return rs.next(); // True if user found
            } catch (SQLException e) {
                System.err.println("Login error: " + e.getMessage());
                return false;
            }
        }

        // SAFE method - using prepared statement
        public boolean safeLogin(String username, String password) {
            String sql = "SELECT * FROM users WHERE username = ? AND password = ?";

            try (Connection conn = DatabaseUtil.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setString(1, username);
                pstmt.setString(2, password);

                try (ResultSet rs = pstmt.executeQuery()) {
                    return rs.next();
                }
            } catch (SQLException e) {
                System.err.println("Login error: " + e.getMessage());
                return false;
            }
        }

        // SAFE method for inserting user
        public void safeInsertUser(String username, String password, String email) {
            String sql = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";

            try (Connection conn = DatabaseUtil.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setString(1, username);
                pstmt.setString(2, password); // In real app, should be hashed
                pstmt.setString(3, email);

                pstmt.executeUpdate();

            } catch (SQLException e) {
                System.err.println("Insert error: " + e.getMessage());
            }
        }
    }
}