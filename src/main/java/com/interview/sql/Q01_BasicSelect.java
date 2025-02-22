package com.interview.sql;

import com.interview.util.DatabaseUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Q01_BasicSelect {
    /*
     * Question: Write a basic SELECT query to retrieve all customers.
     * Demonstrate simple SELECT statement usage.
     */

    public static void main(String[] args) {
        String sql = "SELECT * FROM customers";

        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            System.out.println("All Customers: ");
            System.out.println("ID  | Name        | Email           | Created At");
            System.out.println("------------------------------------------------------------");

            while (rs.next()) {
                System.out.printf("%d  | %s    | %s    | %s%n",
                        rs.getInt("customer_id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getTimestamp("created_at"));
            }

        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }
}
