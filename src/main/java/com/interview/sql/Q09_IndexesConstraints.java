package com.interview.sql;

import com.interview.util.DatabaseUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Q09_IndexesConstraints {
    /*
     * Question: How do you work with indexes and constraints in SQLite?
     * Demonstrate creating indexes and various constraints.
     */

    public static void main(String[] args) {
        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement()) {

            // Create table with constraints
            stmt.execute("""
                        CREATE TABLE IF NOT EXISTS employees (
                            employee_id INTEGER PRIMARY KEY,
                            email TEXT UNIQUE NOT NULL,
                            first_name TEXT NOT NULL,
                            last_name TEXT NOT NULL,
                            salary DECIMAL(10,2) CHECK (salary > 0),
                            department_id INTEGER,
                            FOREIGN KEY (department_id) REFERENCES departments(department_id)
                        )
                    """);

            // Create index on last_name
            stmt.execute("""
                        CREATE INDEX IF NOT EXISTS idx_employee_lastname 
                        ON employees(last_name)
                    """);

            // Create composite index
            stmt.execute("""
                        CREATE INDEX IF NOT EXISTS idx_emp_dept 
                        ON employees(department_id, last_name)
                    """);

            // Try inserting valid data
            try {
                stmt.execute("""
                            INSERT INTO employees 
                            (employee_id, email, first_name, last_name, salary)
                            VALUES (1, 'john@company.com', 'John', 'Doe', 50000)
                        """);
                System.out.println("Valid employee inserted");
            } catch (SQLException e) {
                System.out.println("Error inserting valid employee: " + e.getMessage());
            }

            // Try inserting invalid data (negative salary)
            try {
                stmt.execute("""
                            INSERT INTO employees 
                            (employee_id, email, first_name, last_name, salary)
                            VALUES (2, 'jane@company.com', 'Jane', 'Smith', -1000)
                        """);
            } catch (SQLException e) {
                System.out.println("Expected error with negative salary: " + e.getMessage());
            }

            // Try inserting duplicate email
            try {
                stmt.execute("""
                            INSERT INTO employees 
                            (employee_id, email, first_name, last_name, salary)
                            VALUES (3, 'john@company.com', 'John', 'Smith', 60000)
                        """);
            } catch (SQLException e) {
                System.out.println("Expected error with duplicate email: " + e.getMessage());
            }

            // Show table indexes
            ResultSet rs = stmt.executeQuery("""
                        SELECT name FROM sqlite_master 
                        WHERE type='index' AND tbl_name='employees'
                    """);

            System.out.println("\nIndexes on employees table:");
            while (rs.next()) {
                System.out.println(rs.getString("name"));
            }

        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }
}