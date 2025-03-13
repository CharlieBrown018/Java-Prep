# SQL Interview Guide for Java Junior Developer at Amdocs

SQL (Structured Query Language) is essential for working with relational databases, and understanding SQL fundamentals
will be critical for your Amdocs interview. This guide will help you relearn SQL concepts while preparing you for
interview questions.

## Introduction to SQL

SQL is the standard language for communicating with relational databases. As a Java developer at Amdocs, you'll likely
use SQL to:

- Query customer data
- Generate reports
- Perform data analysis
- Create data-driven applications

Let's start by understanding the database schema we'll be working with throughout this guide.

## 1️⃣ The Database Schema

The schema below represents a simple e-commerce system with customers, orders, products, and order details:

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

Notice the relationships:

- A customer can place multiple orders (one-to-many)
- An order can contain multiple products (many-to-many through OrderDetails)
- OrderDetails serves as a junction table between Orders and Products

Let's populate these tables with sample data to practice our SQL queries:

```sql
INSERT INTO Customers VALUES
(1, 'Alice', 'Smith', 'alice@email.com', '123-456-7890'),
(2, 'Bob', 'Johnson', 'bob@email.com', '987-654-3210'),
(3, 'Charlie', 'Brown', 'charlie@email.com', '555-555-5555'),
(4, 'David', 'Miller', 'david@email.com', '222-333-4444'),
(5, 'Emma', 'Wilson', 'emma@email.com', '999-888-7777');

INSERT INTO Orders VALUES
(101, 1, '2024-03-01', 59.99),
(102, 2, '2024-03-02', 34.50),
(103, 3, '2024-03-03', 120.00),
(104, 4, '2024-03-04', 75.25),
(105, 5, '2024-03-05', 99.99);

INSERT INTO Products VALUES
(201, 'Laptop', 799.99),
(202, 'Mouse', 19.99),
(203, 'Keyboard', 49.99),
(204, 'Monitor', 199.99),
(205, 'Headphones', 99.99);

INSERT INTO OrderDetails VALUES
(301, 101, 202, 2),
(302, 102, 203, 1),
(303, 103, 201, 1),
(304, 104, 205, 3),
(305, 105, 204, 1);
```

## 2️⃣ Basic SQL Queries

### Retrieving Data with SELECT

The most fundamental SQL operation is retrieving data using the SELECT statement.

#### Q01: Retrieve All Records from Customers

```sql
SELECT * FROM Customers;
```

This query returns all columns (`*`) and all rows from the Customers table.

**Output:**

| CustomerID | FirstName | LastName | Email             | Phone        |
|------------|-----------|----------|-------------------|--------------|
| 1          | Alice     | Smith    | alice@email.com   | 123-456-7890 |
| 2          | Bob       | Johnson  | bob@email.com     | 987-654-3210 |
| 3          | Charlie   | Brown    | charlie@email.com | 555-555-5555 |
| 4          | David     | Miller   | david@email.com   | 222-333-4444 |
| 5          | Emma      | Wilson   | emma@email.com    | 999-888-7777 |

If you want to retrieve specific columns instead of all columns, you can list them:

```sql
SELECT FirstName, LastName, Email FROM Customers;
```

This gives you more control over the data you receive, which is especially important when working with large tables.

## 3️⃣ Common SQL Interview Questions for Junior Developers

Let's explore some common SQL questions you might face in your Amdocs interview:

### Q02: Finding the Second Highest Value

Finding the second highest value is a classic SQL interview question. Here's how to find the second highest total amount
from Orders:

```sql
SELECT MAX(TotalAmount) AS SecondHighestSalary 
FROM Orders 
WHERE TotalAmount < (SELECT MAX(TotalAmount) FROM Orders);
```

**How this works:**

1. The inner query `SELECT MAX(TotalAmount) FROM Orders` finds the maximum total amount
2. The outer query finds the maximum amount that is less than the overall maximum

**Output:**

| SecondHighestSalary |
|---------------------|
| 99.99               |

This technique uses a subquery, which is a query nested inside another query. Subqueries are powerful for solving
complex problems.

### Q03: Filtering Records with WHERE Clause

The WHERE clause allows you to filter records based on conditions:

```sql
SELECT * FROM Orders WHERE TotalAmount > 50;
```

**Output:**

| OrderID | CustomerID | OrderDate  | TotalAmount |
|---------|------------|------------|-------------|
| 101     | 1          | 2024-03-01 | 59.99       |
| 103     | 3          | 2024-03-03 | 120.00      |
| 104     | 4          | 2024-03-04 | 75.25       |
| 105     | 5          | 2024-03-05 | 99.99       |

You can use various comparison operators in your WHERE clause:

- Equal to: `=`
- Not equal to: `<>` or `!=`
- Greater than: `>`
- Less than: `<`
- Greater than or equal to: `>=`
- Less than or equal to: `<=`

### Q04: Filtering by Date

Working with dates is a common requirement. Let's find orders placed before a specific date:

```sql
SELECT * FROM Orders WHERE OrderDate < '2024-03-04';
```

**Output:**

| OrderID | CustomerID | OrderDate  | TotalAmount |
|---------|------------|------------|-------------|
| 101     | 1          | 2024-03-01 | 59.99       |
| 102     | 2          | 2024-03-02 | 34.50       |
| 103     | 3          | 2024-03-03 | 120.00      |

Date comparisons work similarly to number comparisons. You can use the same comparison operators, but make sure your
date format matches your database's expected format.

### Q05: Aggregate Functions - Calculating Totals

Aggregate functions perform calculations on a set of values and return a single value. They're essential for data
analysis:

```sql
SELECT SUM(TotalAmount) AS TotalRevenue FROM Orders;
```

**Output:**

| TotalRevenue |
|--------------|
| 389.73       |

Common aggregate functions include:

- SUM(): Calculates the sum of values
- AVG(): Calculates the average of values
- COUNT(): Counts the number of rows
- MIN(): Finds the minimum value
- MAX(): Finds the maximum value

### Q06: GROUP BY - Grouping Data for Analysis

The GROUP BY clause groups rows that have the same values into summary rows:

```sql
SELECT CustomerID, COUNT(OrderID) AS OrderCount 
FROM Orders 
GROUP BY CustomerID;
```

**Output:**

| CustomerID | OrderCount |
|------------|------------|
| 1          | 1          |
| 2          | 1          |
| 3          | 1          |
| 4          | 1          |
| 5          | 1          |

This query counts how many orders each customer has placed. In our sample data, each customer has exactly one order, but
in a real database, you'd likely see varying counts.

### Q07: ORDER BY with LIMIT - Finding Top Records

To find the highest-priced product:

```sql
SELECT * FROM Products ORDER BY Price DESC LIMIT 1;
```

**Output:**

| ProductID | ProductName | Price  |
|-----------|-------------|--------|
| 201       | Laptop      | 799.99 |

This query:

1. Orders all products by price in descending order (highest first)
2. Limits the results to just the first row (the highest-priced product)

The ORDER BY clause can sort by multiple columns (e.g., `ORDER BY LastName, FirstName`), and you can specify ascending (
ASC) or descending (DESC) order.

### Q08: NOT IN - Finding Records that Don't Match

To find customers who haven't placed any orders:

```sql
SELECT * FROM Customers 
WHERE CustomerID NOT IN (SELECT DISTINCT CustomerID FROM Orders);
```

This query:

1. Uses a subquery to get the IDs of customers who have placed orders
2. Finds customers whose IDs are NOT IN that list

In our sample data, all customers have placed orders, so this query wouldn't return any results. But it's an important
pattern to understand for finding missing relationships.

### Q09: Finding Duplicates with GROUP BY and HAVING

The HAVING clause filters groups created by the GROUP BY clause:

```sql
SELECT Email, COUNT(*) AS Count 
FROM Customers 
GROUP BY Email 
HAVING COUNT(*) > 1;
```

This query finds duplicate email addresses by:

1. Grouping customers by email
2. Counting how many customers share each email
3. Filtering for emails with a count greater than 1

Since our sample data doesn't have duplicate emails, this query wouldn't return any results. But it's a common pattern
for finding duplicates in real-world databases.

### Q10: Summarizing Data with GROUP BY and Aggregate Functions

Let's count how many of each product has been sold:

```sql
SELECT ProductID, SUM(Quantity) AS TotalSold 
FROM OrderDetails 
GROUP BY ProductID;
```

**Output:**

| ProductID | TotalSold |
|-----------|-----------|
| 201       | 1         |
| 202       | 2         |
| 203       | 1         |
| 204       | 1         |
| 205       | 3         |

This query:

1. Groups order details by product ID
2. Sums the quantity for each product across all orders

## Advanced SQL Concepts to Understand

While the above queries cover the basics, your Amdocs interview might include more advanced SQL concepts:

### 1. JOINs - Combining Data from Multiple Tables

JOINs combine rows from two or more tables based on related columns. For example, to get customer information along with
their orders:

```sql
SELECT c.CustomerID, c.FirstName, c.LastName, o.OrderID, o.TotalAmount
FROM Customers c
JOIN Orders o ON c.CustomerID = o.CustomerID;
```

Types of JOINs to understand:

- INNER JOIN: Returns records that have matching values in both tables
- LEFT JOIN: Returns all records from the left table and matching records from the right table
- RIGHT JOIN: Returns all records from the right table and matching records from the left table
- FULL JOIN: Returns all records when there's a match in either table

### 2. Subqueries vs JOINs

Both subqueries and JOINs can solve many of the same problems, but they work differently:

```sql
-- Using a subquery to find customers who placed orders over $50
SELECT * FROM Customers 
WHERE CustomerID IN (SELECT CustomerID FROM Orders WHERE TotalAmount > 50);

-- Using a JOIN to achieve the same result
SELECT DISTINCT c.* 
FROM Customers c
JOIN Orders o ON c.CustomerID = o.CustomerID
WHERE o.TotalAmount > 50;
```

Understanding when to use each approach is important for optimizing query performance.

### 3. Indexing

Indexes improve query performance but add overhead to data modification. At Amdocs, you might be asked about when and
how to use indexes:

```sql
-- Creating an index on the CustomerID column in the Orders table
CREATE INDEX idx_customer_id ON Orders(CustomerID);
```

### 4. Transactions

Transactions ensure that a series of SQL operations are performed as a single unit, maintaining database integrity:

```sql
BEGIN TRANSACTION;
    UPDATE Products SET Price = Price * 1.1 WHERE ProductID = 201;
    UPDATE OrderDetails SET Quantity = Quantity + 1 WHERE OrderDetailID = 303;
COMMIT;
```

## Additional Amdocs Interview SQL Questions

### SQL Query to Find Employees in a Specific Department with Salary Range

```sql
-- Assuming an Employees table with Department and Salary columns
SELECT * FROM Employees 
WHERE Department = 'IT' 
AND Salary BETWEEN 50000 AND 70000;
```

**Explanation:**
This query combines multiple conditions in the WHERE clause to find employees who:

1. Work in the IT department
2. Have a salary between 50,000 and 70,000 (inclusive)

The BETWEEN operator is a convenient way to check if a value falls within a range, equivalent to
`Salary >= 50000 AND Salary <= 70000`.

### SQL Query to Calculate Average Salary by Department

```sql
-- Assuming an Employees table with Department and Salary columns
SELECT Department, AVG(Salary) AS AverageSalary
FROM Employees
GROUP BY Department
ORDER BY AverageSalary DESC;
```

**Explanation:**
This query:

1. Groups employees by department
2. Calculates the average salary for each department
3. Orders the results by average salary in descending order (highest first)

This type of aggregation question frequently appears in interviews, testing your understanding of GROUP BY and aggregate
functions.

### SQL Query to Find Employees Who Have Not Been Assigned to Any Project

```sql
-- Assuming Employees and ProjectAssignments tables
SELECT e.*
FROM Employees e
LEFT JOIN ProjectAssignments pa ON e.EmployeeID = pa.EmployeeID
WHERE pa.EmployeeID IS NULL;
```

**Explanation:**
This query uses a LEFT JOIN to include all employees, even those without project assignments. The WHERE clause then
filters for employees where no matching project assignment was found (indicated by a NULL value).

This tests your understanding of OUTER JOINs and how to find records that don't have related records in another table.

### SQL Query to Find the Most Recent Hire in Each Department

```sql
-- Assuming an Employees table with Department and HireDate columns
SELECT Department, MAX(HireDate) AS MostRecentHireDate
FROM Employees
GROUP BY Department;
```

**Explanation:**
This query:

1. Groups employees by department
2. Finds the maximum (most recent) hire date within each group

This is another common type of "find the maximum value within each group" question that appears in interviews.

### SQL Query to Find Duplicate Employee Records

```sql
-- Assuming an Employees table where Email should be unique
SELECT Email, COUNT(*) AS Count
FROM Employees
GROUP BY Email
HAVING COUNT(*) > 1;
```

**Explanation:**
This query identifies potential duplicate employee records by:

1. Grouping by email address (which should be unique)
2. Counting how many records share each email
3. Filtering for emails that appear more than once

This is similar to the duplicate email query in the original content but specifically focused on employee data.

These additional examples cover more common SQL interview scenarios you might encounter at Amdocs. Remember that the key
to success is understanding the underlying concepts rather than memorizing specific queries. In an interview, you'll
likely need to adapt these patterns to slightly different scenarios or table structures.

If any operation fails, you can ROLLBACK the transaction to prevent partial updates.

## Preparing for Your Amdocs Interview

As you prepare for your SQL interview at Amdocs:

1. **Practice writing queries**: Use a tool like SQLite, MySQL Workbench, or an online SQL sandbox to practice writing
   and running queries.

2. **Understand the fundamentals**: Make sure you understand SELECT, WHERE, GROUP BY, HAVING, and JOINs thoroughly.

3. **Learn to optimize**: Understand how to write efficient queries and recognize common performance issues.

4. **Study the relationship between SQL and Java**: Know how to use JDBC to connect to databases and execute SQL queries
   from Java code.

5. **Be prepared to explain your approach**: During the interview, explain your thought process as you solve SQL
   problems.

With a solid understanding of these SQL concepts and practice with the examples provided, you'll be well-prepared for
the SQL portion of your Amdocs Java developer interview. Good luck!