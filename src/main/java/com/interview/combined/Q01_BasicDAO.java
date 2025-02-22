package com.interview.combined;

import com.interview.util.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Q01_BasicDAO {
    /*
     * Question: How do you implement a basic DAO (Data Access Object) pattern?
     * Demonstrate CRUD operations using DAO pattern.
     */

    public static void main(String[] args) {
        try {
            ProductDAO dao = new ProductDAOImpl();

            // Test findAll
            System.out.println("All products:");
            dao.findAll().forEach(System.out::println);

            // Test findById
            Product product = dao.findById(1);
            System.out.println("\nProduct with ID 1: " + product);

            // Test save
            Product newProduct = new Product("New Product", 99.99, 10);
            dao.save(newProduct);
            System.out.println("\nAfter adding new product:");
            dao.findAll().forEach(System.out::println);

            // Test update
            product.setPrice(product.getPrice() + 10);
            dao.update(product);
            System.out.println("\nAfter updating product 1:");
            System.out.println(dao.findById(1));

        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
        }
    }

    // DAO interface
    interface ProductDAO {
        Product findById(int id) throws SQLException;

        List<Product> findAll() throws SQLException;

        void save(Product product) throws SQLException;

        void update(Product product) throws SQLException;

        void delete(int id) throws SQLException;
    }

    // Product model class
    public static class Product {
        private int id;
        private String name;
        private double price;
        private int stock;

        // Constructors
        public Product() {
        }

        public Product(String name, double price, int stock) {
            this.name = name;
            this.price = price;
            this.stock = stock;
        }

        // Getters and setters
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public int getStock() {
            return stock;
        }

        public void setStock(int stock) {
            this.stock = stock;
        }

        @Override
        public String toString() {
            return String.format("Product[id=%d, name='%s', price=%.2f, stock=%d]",
                    id, name, price, stock);
        }
    }

    // DAO implementation
    static class ProductDAOImpl implements ProductDAO {
        @Override
        public Product findById(int id) throws SQLException {
            String sql = "SELECT * FROM products WHERE product_id = ?";
            try (Connection conn = DatabaseUtil.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setInt(1, id);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    Product product = new Product();
                    product.setId(rs.getInt("product_id"));
                    product.setName(rs.getString("name"));
                    product.setPrice(rs.getDouble("price"));
                    product.setStock(rs.getInt("stock"));
                    return product;
                }
                return null;
            }
        }

        @Override
        public List<Product> findAll() throws SQLException {
            List<Product> products = new ArrayList<>();
            String sql = "SELECT * FROM products";

            try (Connection conn = DatabaseUtil.getConnection();
                 Statement stmt = conn.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {

                while (rs.next()) {
                    Product product = new Product();
                    product.setId(rs.getInt("product_id"));
                    product.setName(rs.getString("name"));
                    product.setPrice(rs.getDouble("price"));
                    product.setStock(rs.getInt("stock"));
                    products.add(product);
                }
            }
            return products;
        }

        @Override
        public void save(Product product) throws SQLException {
            String sql = "INSERT INTO products (name, price, stock) VALUES (?, ?, ?)";
            try (Connection conn = DatabaseUtil.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setString(1, product.getName());
                stmt.setDouble(2, product.getPrice());
                stmt.setInt(3, product.getStock());
                stmt.executeUpdate();
            }
        }

        @Override
        public void update(Product product) throws SQLException {
            String sql = "UPDATE products SET name=?, price=?, stock=? WHERE product_id=?";
            try (Connection conn = DatabaseUtil.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setString(1, product.getName());
                stmt.setDouble(2, product.getPrice());
                stmt.setInt(3, product.getStock());
                stmt.setInt(4, product.getId());
                stmt.executeUpdate();
            }
        }

        @Override
        public void delete(int id) throws SQLException {
            String sql = "DELETE FROM products WHERE product_id=?";
            try (Connection conn = DatabaseUtil.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(sql)) {

                stmt.setInt(1, id);
                stmt.executeUpdate();
            }
        }
    }
}