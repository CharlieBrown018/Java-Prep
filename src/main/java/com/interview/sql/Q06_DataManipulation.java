package com.interview.sql;

import com.interview.util.DatabaseUtil;

import java.sql.*;

public class Q06_DataManipulation {
    /*
     * Question: Demonstrate INSERT, UPDATE, and DELETE operations.
     * Show basic data manipulation and returning affected rows.
     */

    public static void main(String[] args) {
        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement()) {

            // INSERT example
            String insertSQL = """
                        INSERT INTO customers (customer_id, name, email)
                        VALUES (4, 'Alice Johnson', 'alice@example.com')
                    """;

            int rowsInserted = stmt.executeUpdate(insertSQL);
            System.out.printf("Inserted %d row(s)%n", rowsInserted);

            // UPDATE example
            String updateSQL = """
                        UPDATE products 
                        SET price = price * 1.1
                        WHERE price < 50
                    """;

            int rowsUpdated = stmt.executeUpdate(updateSQL);
            System.out.printf("Updated %d row(s)%n", rowsUpdated);

            // DELETE example
            String deleteSQL = """
                        DELETE FROM orders 
                        WHERE order_date < date('now', '-1 year')
                    """;

            int rowsDeleted = stmt.executeUpdate(deleteSQL);
            System.out.printf("Deleted %d row(s)%n", rowsDeleted);

            // Show current data
            printTableData(conn, "customers", "Updated Customers:");
            printTableData(conn, "products", "\nUpdated Products:");

        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }

    private static void printTableData(Connection conn, String tableName, String message)
            throws SQLException {
        System.out.println(message);
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM " + tableName);
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();

        // Print column names
        for (int i = 1; i <= columnCount; i++) {
            System.out.print(metaData.getColumnName(i) + "\t");
        }
        System.out.println();

        // Print data
        while (rs.next()) {
            for (int i = 1; i <= columnCount; i++) {
                System.out.print(rs.getString(i) + "\t");
            }
            System.out.println();
        }
    }
}