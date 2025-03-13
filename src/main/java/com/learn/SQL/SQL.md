# SQL: Structured Query Language

## 1. Introduction to SQL

SQL (Structured Query Language) is a standardized programming language designed for managing and manipulating relational
databases. Since its development in the 1970s by IBM researchers, SQL has become the universal language for interacting
with relational database management systems (RDBMS).

The power of SQL lies in its ability to perform complex operations on data with relatively simple, declarative
statements. Instead of specifying how to retrieve data, you simply describe what data you want, and the database engine
determines the most efficient way to fulfill your request.

SQL is used for:

- Creating, modifying, and removing database structures (tables, views, indexes, etc.)
- Inserting, updating, and deleting data
- Retrieving data through queries
- Managing database access permissions
- Ensuring data integrity through constraints

Understanding SQL is essential for developers, data analysts, database administrators, and increasingly for
professionals in fields like marketing, finance, and operations where data-driven decision making is critical.

## 2. SQL Categories and Commands

SQL commands are grouped into several categories based on their function:

### 2.1 Data Definition Language (DDL)

DDL commands define and modify database structures:

**CREATE**: Creates new database objects (databases, tables, views, indexes, etc.)

```sql
CREATE TABLE Employees (
    EmployeeID INT PRIMARY KEY,
    FirstName VARCHAR(50) NOT NULL,
    LastName VARCHAR(50) NOT NULL,
    Department VARCHAR(50),
    Salary DECIMAL(10,2)
);
```

**ALTER**: Modifies existing database objects

```sql
ALTER TABLE Employees 
ADD Email VARCHAR(100);
```

**DROP**: Removes existing database objects

```sql
DROP TABLE Employees;
```

**TRUNCATE**: Removes all records from a table, but keeps the table structure

```sql
TRUNCATE TABLE Employees;
```

### 2.2 Data Manipulation Language (DML)

DML commands manage data within database objects:

**INSERT**: Adds new records to a table

```sql
INSERT INTO Employees (EmployeeID, FirstName, LastName, Department, Salary)
VALUES (1, 'John', 'Doe', 'IT', 75000.00);
```

**UPDATE**: Modifies existing records

```sql
UPDATE Employees
SET Salary = 80000.00
WHERE EmployeeID = 1;
```

**DELETE**: Removes records from a table

```sql
DELETE FROM Employees
WHERE EmployeeID = 1;
```

### 2.3 Data Query Language (DQL)

DQL commands retrieve data from the database:

**SELECT**: Retrieves data from one or more tables

```sql
SELECT FirstName, LastName, Salary
FROM Employees
WHERE Department = 'IT'
ORDER BY Salary DESC;
```

### 2.4 Data Control Language (DCL)

DCL commands manage database access permissions:

**GRANT**: Gives specific privileges to users

```sql
GRANT SELECT, INSERT ON Employees TO user1;
```

**REVOKE**: Removes previously granted privileges

```sql
REVOKE INSERT ON Employees FROM user1;
```

### 2.5 Transaction Control Language (TCL)

TCL commands manage database transactions:

**COMMIT**: Saves transaction changes permanently

```sql
BEGIN TRANSACTION;
UPDATE Accounts SET Balance = Balance - 100 WHERE AccountID = 1;
UPDATE Accounts SET Balance = Balance + 100 WHERE AccountID = 2;
COMMIT;
```

**ROLLBACK**: Restores database to the state since the last COMMIT

```sql
BEGIN TRANSACTION;
UPDATE Accounts SET Balance = Balance - 100 WHERE AccountID = 1;
-- Something went wrong
ROLLBACK;
```

**SAVEPOINT**: Creates points within a transaction to ROLLBACK to

```sql
SAVEPOINT SavePoint1;
```

## 3. Basic SQL Queries

SQL queries allow you to retrieve specific data from one or more tables. Let's explore the fundamental components of SQL
queries using the following example schema:

```sql
-- Create Customers Table
CREATE TABLE Customers (
    CustomerID INT PRIMARY KEY,
    FirstName VARCHAR(50),
    LastName VARCHAR(50),
    Email VARCHAR(100),
    Phone VARCHAR(15)
);

-- Create Orders Table
CREATE TABLE Orders (
    OrderID INT PRIMARY KEY,
    CustomerID INT,
    OrderDate DATE,
    TotalAmount DECIMAL(10,2),
    FOREIGN KEY (CustomerID) REFERENCES Customers(CustomerID)
);

-- Create Products Table
CREATE TABLE Products (
    ProductID INT PRIMARY KEY,
    ProductName VARCHAR(100),
    Price DECIMAL(10,2)
);

-- Create OrderDetails Table
CREATE TABLE OrderDetails (
    OrderDetailID INT PRIMARY KEY,
    OrderID INT,
    ProductID INT,
    Quantity INT,
    FOREIGN KEY (OrderID) REFERENCES Orders(OrderID),
    FOREIGN KEY (ProductID) REFERENCES Products(ProductID)
);
```

### 3.1 Basic SELECT Queries

The simplest SQL query retrieves all columns and rows from a table:

```sql
SELECT * FROM Customers;
```

To select specific columns:

```sql
SELECT FirstName, LastName, Email FROM Customers;
```

### 3.2 Filtering with WHERE

The WHERE clause filters results based on conditions:

```sql
SELECT * FROM Orders WHERE TotalAmount > 50;
```

Multiple conditions can be combined using logical operators (AND, OR, NOT):

```sql
SELECT * FROM Products 
WHERE Price > 20 AND Price < 100;

SELECT * FROM Customers 
WHERE LastName = 'Smith' OR LastName = 'Johnson';

SELECT * FROM Products 
WHERE NOT Price = 19.99;
```

### 3.3 Sorting with ORDER BY

The ORDER BY clause sorts results:

```sql
SELECT * FROM Products 
ORDER BY Price ASC;  -- ascending order (default)

SELECT * FROM Products 
ORDER BY Price DESC;  -- descending order

-- Multiple sort criteria
SELECT * FROM Customers 
ORDER BY LastName ASC, FirstName ASC;
```

### 3.4 Limiting Results

Many databases support limiting results:

```sql
-- MySQL and PostgreSQL
SELECT * FROM Products 
ORDER BY Price DESC 
LIMIT 5;

-- SQL Server
SELECT TOP 5 * FROM Products 
ORDER BY Price DESC;

-- Oracle
SELECT * FROM Products 
WHERE ROWNUM <= 5 
ORDER BY Price DESC;
```

### 3.5 Aggregation Functions

SQL provides functions to perform calculations across rows:

```sql
SELECT COUNT(*) FROM Customers;  -- Count total customers

SELECT AVG(TotalAmount) FROM Orders;  -- Average order amount

SELECT SUM(TotalAmount) FROM Orders;  -- Sum of all orders

SELECT MIN(Price) FROM Products;  -- Lowest price

SELECT MAX(Price) FROM Products;  -- Highest price
```

### 3.6 Grouping with GROUP BY

The GROUP BY clause groups rows with similar values:

```sql
-- Count customers by last name
SELECT LastName, COUNT(*) AS CustomerCount 
FROM Customers 
GROUP BY LastName;

-- Calculate total sales per customer
SELECT CustomerID, SUM(TotalAmount) AS TotalSpent 
FROM Orders 
GROUP BY CustomerID;
```

### 3.7 Filtering Groups with HAVING

The HAVING clause filters groups, similar to how WHERE filters individual rows:

```sql
-- Find customers who placed more than 3 orders
SELECT CustomerID, COUNT(OrderID) AS OrderCount 
FROM Orders 
GROUP BY CustomerID 
HAVING COUNT(OrderID) > 3;

-- Find products with average order quantity > 10
SELECT ProductID, AVG(Quantity) AS AvgQuantity 
FROM OrderDetails 
GROUP BY ProductID 
HAVING AVG(Quantity) > 10;
```

## 4. Joins in SQL

Joins combine rows from two or more tables based on related columns. They are essential for querying data from
relational databases.

### 4.1 Types of Joins

#### INNER JOIN

Returns only matching rows from both tables:

```sql
SELECT c.FirstName, c.LastName, o.OrderID, o.TotalAmount 
FROM Customers c
INNER JOIN Orders o ON c.CustomerID = o.CustomerID;
```

#### LEFT JOIN (or LEFT OUTER JOIN)

Returns all rows from the left table and matching rows from the right table:

```sql
SELECT c.FirstName, c.LastName, o.OrderID, o.TotalAmount 
FROM Customers c
LEFT JOIN Orders o ON c.CustomerID = o.CustomerID;
```

#### RIGHT JOIN (or RIGHT OUTER JOIN)

Returns all rows from the right table and matching rows from the left table:

```sql
SELECT c.FirstName, c.LastName, o.OrderID, o.TotalAmount 
FROM Customers c
RIGHT JOIN Orders o ON c.CustomerID = o.CustomerID;
```

#### FULL JOIN (or FULL OUTER JOIN)

Returns all rows when there's a match in either table:

```sql
SELECT c.FirstName, c.LastName, o.OrderID, o.TotalAmount 
FROM Customers c
FULL JOIN Orders o ON c.CustomerID = o.CustomerID;
```

#### CROSS JOIN

Returns the Cartesian product (all possible combinations) of the two tables:

```sql
SELECT c.FirstName, c.LastName, p.ProductName 
FROM Customers c
CROSS JOIN Products p;
```

#### SELF JOIN

Joins a table to itself:

```sql
SELECT e1.FirstName AS Employee, e2.FirstName AS Manager 
FROM Employees e1
JOIN Employees e2 ON e1.ManagerID = e2.EmployeeID;
```

### 4.2 Multi-Table Joins

You can join more than two tables:

```sql
SELECT c.FirstName, c.LastName, o.OrderID, p.ProductName, od.Quantity 
FROM Customers c
JOIN Orders o ON c.CustomerID = o.CustomerID
JOIN OrderDetails od ON o.OrderID = od.OrderID
JOIN Products p ON od.ProductID = p.ProductID;
```

## 5. Subqueries

Subqueries (or nested queries) are queries within a query. They provide ways to perform operations that would otherwise
require complex joins and unions.

### 5.1 Types of Subqueries

#### Single-Value Subquery

Returns a single value and can be used with comparison operators:

```sql
-- Find orders with total amount greater than the average
SELECT * FROM Orders 
WHERE TotalAmount > (SELECT AVG(TotalAmount) FROM Orders);
```

#### Row Subquery

Returns a single row with multiple columns:

```sql
-- Find customers whose first and last name match a specific pattern
SELECT * FROM Customers 
WHERE (FirstName, LastName) = (SELECT FirstName, LastName FROM Employees WHERE EmployeeID = 1);
```

#### Table Subquery

Returns a table result that can be used in the FROM clause:

```sql
-- Get customer order statistics
SELECT CustomerStats.CustomerID, FirstName, LastName, OrderCount, TotalSpent 
FROM (
    SELECT CustomerID, COUNT(*) AS OrderCount, SUM(TotalAmount) AS TotalSpent 
    FROM Orders 
    GROUP BY CustomerID
) AS CustomerStats
JOIN Customers ON CustomerStats.CustomerID = Customers.CustomerID;
```

#### Correlated Subquery

References columns from the outer query:

```sql
-- Find customers who placed orders above their average order amount
SELECT o1.OrderID, o1.CustomerID, o1.TotalAmount 
FROM Orders o1
WHERE o1.TotalAmount > (
    SELECT AVG(o2.TotalAmount) 
    FROM Orders o2 
    WHERE o2.CustomerID = o1.CustomerID
);
```

### 5.2 Subquery Operators

#### IN / NOT IN

Checks if a value matches any value in the subquery result:

```sql
-- Find customers who placed orders
SELECT * FROM Customers 
WHERE CustomerID IN (SELECT DISTINCT CustomerID FROM Orders);

-- Find customers who never placed orders
SELECT * FROM Customers 
WHERE CustomerID NOT IN (SELECT DISTINCT CustomerID FROM Orders);
```

#### EXISTS / NOT EXISTS

Checks if the subquery returns any rows:

```sql
-- Find customers who placed orders
SELECT * FROM Customers c
WHERE EXISTS (SELECT 1 FROM Orders o WHERE o.CustomerID = c.CustomerID);

-- Find customers who never placed orders
SELECT * FROM Customers c
WHERE NOT EXISTS (SELECT 1 FROM Orders o WHERE o.CustomerID = c.CustomerID);
```

#### ALL / ANY / SOME

Compares a value with all or any values returned by the subquery:

```sql
-- Find products priced higher than all products in the 'Electronics' category
SELECT * FROM Products 
WHERE Price > ALL (SELECT Price FROM Products WHERE Category = 'Electronics');

-- Find products priced higher than at least one product in the 'Electronics' category
SELECT * FROM Products 
WHERE Price > ANY (SELECT Price FROM Products WHERE Category = 'Electronics');
```

## 6. Common Table Expressions (CTEs)

Common Table Expressions provide a way to create temporary result sets that can be referenced within a SELECT, INSERT,
UPDATE, or DELETE statement.

```sql
-- Find customers with above-average spending
WITH CustomerSpending AS (
    SELECT c.CustomerID, c.FirstName, c.LastName, SUM(o.TotalAmount) AS TotalSpent 
    FROM Customers c
    JOIN Orders o ON c.CustomerID = o.CustomerID
    GROUP BY c.CustomerID, c.FirstName, c.LastName
)
SELECT * FROM CustomerSpending 
WHERE TotalSpent > (SELECT AVG(TotalSpent) FROM CustomerSpending);
```

CTEs can also be recursive, which is useful for hierarchical data:

```sql
-- List all employees and their management chain
WITH RECURSIVE EmployeeHierarchy AS (
    -- Base case: top-level employees (no manager)
    SELECT EmployeeID, FirstName, LastName, ManagerID, 0 AS Level 
    FROM Employees 
    WHERE ManagerID IS NULL
    
    UNION ALL
    
    -- Recursive case: employees with managers
    SELECT e.EmployeeID, e.FirstName, e.LastName, e.ManagerID, eh.Level + 1 
    FROM Employees e
    JOIN EmployeeHierarchy eh ON e.ManagerID = eh.EmployeeID
)
SELECT * FROM EmployeeHierarchy 
ORDER BY Level, LastName;
```

## 7. SQL Functions

SQL provides various built-in functions to manipulate data:

### 7.1 String Functions

```sql
SELECT CONCAT(FirstName, ' ', LastName) AS FullName FROM Customers;
SELECT UPPER(FirstName) FROM Customers;
SELECT LOWER(LastName) FROM Customers;
SELECT LENGTH(FirstName) FROM Customers;
SELECT SUBSTRING(FirstName, 1, 3) FROM Customers;
SELECT REPLACE(Email, '@gmail.com', '@company.com') FROM Customers;
SELECT TRIM(' John Doe ') FROM Customers;
```

### 7.2 Numeric Functions

```sql
SELECT ABS(-10);  -- Absolute value: 10
SELECT ROUND(3.14159, 2);  -- Round to 2 decimal places: 3.14
SELECT CEILING(3.14);  -- Round up to nearest integer: 4
SELECT FLOOR(3.99);  -- Round down to nearest integer: 3
SELECT POWER(2, 3);  -- 2 to the power of 3: 8
SELECT SQRT(16);  -- Square root: 4
```

### 7.3 Date Functions

```sql
SELECT CURRENT_DATE;  -- Current date
SELECT CURRENT_TIMESTAMP;  -- Current date and time
SELECT EXTRACT(YEAR FROM OrderDate) FROM Orders;  -- Extract year
SELECT EXTRACT(MONTH FROM OrderDate) FROM Orders;  -- Extract month
SELECT EXTRACT(DAY FROM OrderDate) FROM Orders;  -- Extract day
SELECT DATE_ADD(OrderDate, INTERVAL 30 DAY) FROM Orders;  -- Add days
SELECT DATEDIFF(CURRENT_DATE, OrderDate) FROM Orders;  -- Days between dates
```

### 7.4 Conditional Functions

```sql
-- CASE expression
SELECT 
    OrderID,
    TotalAmount,
    CASE 
        WHEN TotalAmount < 50 THEN 'Low'
        WHEN TotalAmount >= 50 AND TotalAmount < 100 THEN 'Medium'
        ELSE 'High'
    END AS AmountCategory
FROM Orders;

-- COALESCE: returns the first non-NULL value
SELECT COALESCE(Phone, Email, 'No Contact') AS Contact FROM Customers;

-- NULLIF: returns NULL if two values are equal
SELECT NULLIF(Quantity, 0) FROM OrderDetails;
```

## 8. Advanced SQL: Indexes and Performance

Indexes are crucial for database performance, as they allow the database engine to find rows quickly without scanning
the entire table.

### 8.1 Types of Indexes

#### Primary Key Index

Automatically created when defining a PRIMARY KEY constraint:

```sql
CREATE TABLE Customers (
    CustomerID INT PRIMARY KEY,  -- This creates an index automatically
    FirstName VARCHAR(50),
    LastName VARCHAR(50)
);
```

#### Unique Index

Ensures uniqueness of values in a column or columns:

```sql
CREATE UNIQUE INDEX idx_email 
ON Customers(Email);
```

#### Non-Unique Index

Improves query performance without enforcing uniqueness:

```sql
CREATE INDEX idx_lastname 
ON Customers(LastName);
```

#### Composite Index

Includes multiple columns:

```sql
CREATE INDEX idx_name 
ON Customers(LastName, FirstName);
```

#### Full-Text Index

Optimizes searches for text content:

```sql
CREATE FULLTEXT INDEX idx_product_description 
ON Products(Description);
```

### 8.2 When to Use Indexes

- Columns used in WHERE clauses
- Columns used in JOIN conditions
- Columns used in ORDER BY or GROUP BY clauses
- Columns with high selectivity (many unique values)

### 8.3 Index Considerations

While indexes improve read performance, they can degrade write performance because indexes must be updated when data
changes. Additionally, indexes consume disk space.

```sql
-- Find indexes on a table (SQL Server)
sp_helpindex 'Customers';

-- Find unused indexes (SQL Server)
SELECT 
    o.name AS TableName,
    i.name AS IndexName,
    s.user_seeks, 
    s.user_scans,
    s.user_lookups,
    s.user_updates
FROM sys.dm_db_index_usage_stats s
JOIN sys.indexes i ON s.object_id = i.object_id AND s.index_id = i.index_id
JOIN sys.objects o ON i.object_id = o.object_id
WHERE o.type = 'U' -- User tables only
ORDER BY (s.user_seeks + s.user_scans + s.user_lookups) ASC;
```

## 9. Transactions

Transactions ensure data integrity by grouping a set of SQL operations into a single unit of work that either completes
entirely or not at all.

### 9.1 ACID Properties

Transactions adhere to ACID properties:

- **Atomicity**: All operations complete successfully, or none do
- **Consistency**: The database remains in a consistent state
- **Isolation**: Transactions operate independently of each other
- **Durability**: Completed transactions persist even after system failures

### 9.2 Transaction Control

```sql
-- Basic transaction
BEGIN TRANSACTION;
    UPDATE Accounts SET Balance = Balance - 100 WHERE AccountID = 1;
    UPDATE Accounts SET Balance = Balance + 100 WHERE AccountID = 2;
COMMIT;

-- Transaction with error handling
BEGIN TRANSACTION;
    UPDATE Accounts SET Balance = Balance - 100 WHERE AccountID = 1;
    -- Check if the account has sufficient balance
    IF (SELECT Balance FROM Accounts WHERE AccountID = 1) < 0 THEN
        ROLLBACK;
    ELSE
        UPDATE Accounts SET Balance = Balance + 100 WHERE AccountID = 2;
        COMMIT;
    END IF;

-- Transaction with savepoint
BEGIN TRANSACTION;
    UPDATE Inventory SET Quantity = Quantity - 5 WHERE ProductID = 101;
    
    SAVEPOINT inventory_updated;
    
    UPDATE Orders SET Status = 'Shipped' WHERE OrderID = 1001;
    
    -- If something goes wrong with the order status update
    ROLLBACK TO inventory_updated;
    
    -- Try again with different status
    UPDATE Orders SET Status = 'Processing' WHERE OrderID = 1001;
    
    COMMIT;
```

## 10. Views

A view is a virtual table based on the result set of a SQL query. Views can simplify complex queries, restrict access to
specific data, and present data in a more meaningful way.

### 10.1 Creating Views

```sql
-- Create a view showing customer order information
CREATE VIEW CustomerOrders AS
SELECT 
    c.CustomerID,
    c.FirstName,
    c.LastName,
    COUNT(o.OrderID) AS OrderCount,
    SUM(o.TotalAmount) AS TotalSpent
FROM Customers c
LEFT JOIN Orders o ON c.CustomerID = o.CustomerID
GROUP BY c.CustomerID, c.FirstName, c.LastName;

-- Query the view
SELECT * FROM CustomerOrders WHERE OrderCount > 5;
```

### 10.2 Updatable Views

Under certain conditions, views can be updatable:

```sql
-- Create an updatable view
CREATE VIEW ActiveCustomers AS
SELECT CustomerID, FirstName, LastName, Email, Phone, IsActive
FROM Customers
WHERE IsActive = 1;

-- Update through the view
UPDATE ActiveCustomers
SET Phone = '555-123-4567'
WHERE CustomerID = 1;
```

For a view to be updatable, it must:

- Be based on a single table
- Not include GROUP BY, HAVING, or aggregate functions
- Not include DISTINCT
- Not include complex subqueries in the select list

### 10.3 Materialized Views

Some database systems support materialized views, which store the result set physically:

```sql
-- PostgreSQL materialized view
CREATE MATERIALIZED VIEW CustomerOrderSummary AS
SELECT 
    c.CustomerID,
    c.FirstName,
    c.LastName,
    COUNT(o.OrderID) AS OrderCount,
    SUM(o.TotalAmount) AS TotalSpent
FROM Customers c
LEFT JOIN Orders o ON c.CustomerID = o.CustomerID
GROUP BY c.CustomerID, c.FirstName, c.LastName;

-- Refresh the materialized view
REFRESH MATERIALIZED VIEW CustomerOrderSummary;
```

## 11. Stored Procedures and Functions

Stored procedures and functions encapsulate SQL logic that can be reused.

### 11.1 Stored Procedures

```sql
-- SQL Server stored procedure
CREATE PROCEDURE GetCustomerOrders
    @CustomerID INT
AS
BEGIN
    SELECT 
        o.OrderID,
        o.OrderDate,
        o.TotalAmount,
        p.ProductName,
        od.Quantity
    FROM Orders o
    JOIN OrderDetails od ON o.OrderID = od.OrderID
    JOIN Products p ON od.ProductID = p.ProductID
    WHERE o.CustomerID = @CustomerID
    ORDER BY o.OrderDate DESC;
END;

-- Execute the stored procedure
EXEC GetCustomerOrders @CustomerID = 1;
```

### 11.2 User-Defined Functions

```sql
-- SQL Server scalar function
CREATE FUNCTION CalculateOrderTotal
    (@OrderID INT)
RETURNS DECIMAL(10,2)
AS
BEGIN
    DECLARE @Total DECIMAL(10,2);
    
    SELECT @Total = SUM(od.Quantity * p.Price)
    FROM OrderDetails od
    JOIN Products p ON od.ProductID = p.ProductID
    WHERE od.OrderID = @OrderID;
    
    RETURN @Total;
END;

-- Use the function
SELECT OrderID, dbo.CalculateOrderTotal(OrderID) AS CalculatedTotal, TotalAmount
FROM Orders;
```

```sql
-- SQL Server table-valued function
CREATE FUNCTION GetProductsByPriceRange
    (@MinPrice DECIMAL(10,2), @MaxPrice DECIMAL(10,2))
RETURNS TABLE
AS
RETURN
    SELECT ProductID, ProductName, Price
    FROM Products
    WHERE Price BETWEEN @MinPrice AND @MaxPrice;

-- Use the function
SELECT * FROM GetProductsByPriceRange(10.00, 50.00);
```

## 12. Window Functions

Window functions perform calculations across a set of rows related to the current row.

```sql
-- Rank customers by total spending
SELECT 
    CustomerID,
    FirstName,
    LastName,
    TotalSpent,
    RANK() OVER (ORDER BY TotalSpent DESC) AS SpendingRank
FROM CustomerOrders;

-- Calculate running total of order amounts by customer
SELECT 
    o.OrderID,
    o.CustomerID,
    o.OrderDate,
    o.TotalAmount,
    SUM(o.TotalAmount) OVER (
        PARTITION BY o.CustomerID 
        ORDER BY o.OrderDate
        ROWS UNBOUNDED PRECEDING
    ) AS RunningTotal
FROM Orders o;

-- Calculate moving average of monthly sales
SELECT 
    EXTRACT(YEAR FROM OrderDate) AS Year,
    EXTRACT(MONTH FROM OrderDate) AS Month,
    SUM(TotalAmount) AS MonthlySales,
    AVG(SUM(TotalAmount)) OVER (
        ORDER BY EXTRACT(YEAR FROM OrderDate), EXTRACT(MONTH FROM OrderDate)
        ROWS BETWEEN 2 PRECEDING AND CURRENT ROW
    ) AS MovingAvg3Month
FROM Orders
GROUP BY EXTRACT(YEAR FROM OrderDate), EXTRACT(MONTH FROM OrderDate);
```

## 13. Common SQL Interview Questions and Solutions

### 13.1 Second Highest Salary

Finding the second highest salary is a classic SQL interview question.

```sql
-- Method 1: Using subquery
SELECT MAX(Salary) AS SecondHighestSalary
FROM Employees
WHERE Salary < (SELECT MAX(Salary) FROM Employees);

-- Method 2: Using LIMIT/OFFSET
SELECT Salary AS SecondHighestSalary
FROM Employees
ORDER BY Salary DESC
LIMIT 1 OFFSET 1;

-- Method 3: Using window functions
SELECT Salary AS SecondHighestSalary
FROM (
    SELECT Salary, DENSE_RANK() OVER (ORDER BY Salary DESC) AS SalaryRank
    FROM Employees
) RankedSalaries
WHERE SalaryRank = 2;
```

### 13.2 Employee Manager Relationships

Handling hierarchical relationships is another common challenge.

```sql
-- Find employees and their direct managers
SELECT 
    e.EmployeeID,
    e.FirstName AS EmployeeFirstName,
    e.LastName AS EmployeeLastName,
    m.FirstName AS ManagerFirstName,
    m.LastName AS ManagerLastName
FROM Employees e
LEFT JOIN Employees m ON e.ManagerID = m.EmployeeID;

-- Find employees with higher salary than their managers
SELECT 
    e.EmployeeID,
    e.FirstName,
    e.LastName,
    e.Salary AS EmployeeSalary,
    m.Salary AS ManagerSalary
FROM Employees e
JOIN Employees m ON e.ManagerID = m.EmployeeID
WHERE e.Salary > m.Salary;
```

### 13.3 Duplicate Detection

Finding and handling duplicates is a practical skill.

```sql
-- Find duplicate email addresses
SELECT Email, COUNT(*) AS Count
FROM Customers
GROUP BY Email
HAVING COUNT(*) > 1;

-- Delete duplicates (keeping the lowest ID)
DELETE FROM Customers
WHERE CustomerID NOT IN (
    SELECT MIN(CustomerID)
    FROM Customers
    GROUP BY Email
);
```

### 13.4 Date-Based Queries

Handling dates effectively is crucial in many applications.

```sql
-- Find orders placed in the last 30 days
SELECT *
FROM Orders
WHERE OrderDate >= CURRENT_DATE - INTERVAL '30 day';

-- Calculate orders by month and year
SELECT 
    EXTRACT(YEAR FROM OrderDate) AS Year,
    EXTRACT(MONTH FROM OrderDate) AS Month,
    COUNT(*) AS OrderCount,
    SUM(TotalAmount) AS MonthlyRevenue
FROM Orders
GROUP BY EXTRACT(YEAR FROM OrderDate), EXTRACT(MONTH FROM OrderDate)
ORDER BY Year, Month;

-- Find customers who placed orders on consecutive days
SELECT DISTINCT c.CustomerID, c.FirstName, c.LastName
FROM Customers c
JOIN Orders o1 ON c.CustomerID = o1.CustomerID
JOIN Orders o2 ON c.CustomerID = o2.CustomerID
WHERE o2.OrderDate = o1.OrderDate + INTERVAL '1 day';
```

### 13.5 Cumulative Sums

Calculating running totals is a common reporting need.

```sql
-- Calculate cumulative sum of order amounts by customer
SELECT 
    o.OrderID,
    o.CustomerID,
    o.OrderDate,
    o.TotalAmount,
    SUM(o.TotalAmount) OVER (
        PARTITION BY o.CustomerID 
        ORDER BY o.OrderDate
    ) AS CumulativeTotal
FROM Orders o;
```

## 14. SQL vs NoSQL

While SQL is the language for relational databases, it's worth understanding the key differences between SQL (
relational) and NoSQL (non-relational) databases:

### 14.1 SQL (Relational) Databases:

- **Structure**: Data organized in tables with predefined schemas
- **Relationships**: Strong relationships between entities using foreign keys
- **Query Language**: SQL with consistent syntax
- **Scaling**: Typically scales vertically (more powerful hardware)
- **ACID Compliance**: Prioritizes consistency and integrity
- **Best For**: Applications with complex queries, transactions, and fixed schema
- **Examples**: MySQL, PostgreSQL, Oracle, SQL Server, SQLite

### 14.2 NoSQL (Non-Relational) Databases:

- **Structure**: Various data models (document, key-value, column-family, graph)
- **Relationships**: Typically denormalized data with less emphasis on relationships
- **Query Language**: Each database may have its own
- **Scaling**: Typically scales horizontally (adding more servers)
- **CAP Theorem**: Often prioritizes availability and partition tolerance over consistency
- **Best For**: Large volumes of unstructured/semi-structured data, rapid development, horizontal scaling
- **Examples**: MongoDB (document), Redis (key-value), Cassandra (column-family), Neo4j (graph)

## 15. SQL Best Practices

To write efficient and maintainable SQL:

### 15.1 Performance Best Practices

- Use appropriate indexes for columns in WHERE, JOIN, and ORDER BY clauses
- Avoid using SELECT * and only request columns you need
- Use JOINs instead of subqueries when possible
- Use LIMIT or TOP to restrict result sets
- Be cautious with wildcard % searches at the beginning of LIKE patterns
- Avoid unnecessary sorting with ORDER BY
- Use EXPLAIN or execution plans to analyze query performance

### 15.2 Writing Maintainable SQL

- Use consistent aliasing for tables
- Format SQL with proper indentation and line breaks
- Use descriptive names for views, stored procedures, and variables
- Add comments for complex queries
- Break down complex queries using CTEs or views
- Use parameters or variables instead of hard-coded values
- Organize complex conditions for readability

### 15.3 Security Best Practices

- Use parameterized queries to prevent SQL injection
- Apply least privilege principles for database users
- Use views to restrict access to sensitive data
- Encrypt sensitive data
- Audit database access and changes
- Regularly back up databases

## 16. Conclusion

SQL is a powerful language for managing relational databases and extracting meaningful information from your data. From
simple queries to complex analysis, SQL provides the tools to interact with your data efficiently. While NoSQL databases
have gained popularity for specific use cases, SQL remains the dominant language for structured data and continues to
evolve with new features and optimizations.

By mastering SQL, you gain the ability to convert raw data into valuable insights, automate data operations, and build
robust, data-driven applications. Whether you're a developer, data analyst, or database administrator, SQL proficiency
is an essential skill in the modern technology landscape.

The examples and concepts covered in this guide provide a solid foundation for working with SQL across various database
systems. As you continue to develop your SQL skills, remember that practice with real-world data challenges is the key
to becoming proficient.