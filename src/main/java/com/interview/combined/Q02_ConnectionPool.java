package com.interview.combined;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Q02_ConnectionPool {
    /*
     * Question: How do you implement a basic connection pool?
     * Demonstrate a simple connection pool implementation.
     */

    public static void main(String[] args) {
        SimpleConnectionPool pool = new SimpleConnectionPool("jdbc:sqlite:src/main/db/interview.db", 5);

        try {
            // Simulate multiple threads using connections
            Runnable task = () -> {
                try {
                    Connection conn = pool.getConnection();
                    try {
                        // Simulate some database work
                        Statement stmt = conn.createStatement();
                        ResultSet rs = stmt.executeQuery("SELECT * FROM products");
                        while (rs.next()) {
                            System.out.printf("Thread %s read product: %s%n",
                                    Thread.currentThread().getName(),
                                    rs.getString("name"));
                        }
                        Thread.sleep(100); // Simulate work
                    } finally {
                        pool.releaseConnection(conn);
                    }
                } catch (Exception e) {
                    System.err.println("Error in thread: " + e.getMessage());
                }
            };

            // Create and start multiple threads
            Thread[] threads = new Thread[10];
            for (int i = 0; i < threads.length; i++) {
                threads[i] = new Thread(task, "Thread-" + i);
                threads[i].start();
            }

            // Wait for all threads to complete
            for (Thread thread : threads) {
                thread.join();
            }

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            pool.closeAll();
        }
    }

    static class SimpleConnectionPool {
        private BlockingQueue<Connection> pool;
        private List<Connection> allConnections;
        private String url;

        public SimpleConnectionPool(String url, int poolSize) {
            this.url = url;
            pool = new ArrayBlockingQueue<>(poolSize);
            allConnections = new ArrayList<>(poolSize);

            initializePool(poolSize);
        }

        private void initializePool(int poolSize) {
            try {
                for (int i = 0; i < poolSize; i++) {
                    Connection conn = createConnection();
                    pool.offer(conn);
                    allConnections.add(conn);
                }
            } catch (SQLException e) {
                throw new RuntimeException("Error initializing pool", e);
            }
        }

        private Connection createConnection() throws SQLException {
            return DriverManager.getConnection(url);
        }

        public Connection getConnection() throws InterruptedException {
            return pool.take();
        }

        public void releaseConnection(Connection connection) {
            pool.offer(connection);
        }

        public void closeAll() {
            for (Connection conn : allConnections) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    System.err.println("Error closing connection: " + e.getMessage());
                }
            }
        }
    }
}