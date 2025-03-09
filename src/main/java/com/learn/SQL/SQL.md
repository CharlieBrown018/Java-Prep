Alright! I’ll structure **SQL.md** to include:

1. **Concepts & Examples**
2. **Actual Table Creation** (so you can run them)
3. **Executed SQL Queries with Results**

---

# **Structured Query Language (SQL)**

SQL (Structured Query Language) is used to **manage relational databases**. It allows us to **create, modify, delete,
and retrieve** data efficiently.

---

## **1. SQL Categories**

SQL is divided into five categories:

| **Category**                           | **Commands**                          | **Description**               |
|----------------------------------------|---------------------------------------|-------------------------------|
| **DDL (Data Definition Language)**     | `CREATE`, `ALTER`, `DROP`, `TRUNCATE` | Defines database structure.   |
| **DML (Data Manipulation Language)**   | `INSERT`, `UPDATE`, `DELETE`          | Modifies data inside tables.  |
| **DQL (Data Query Language)**          | `SELECT`                              | Retrieves data from tables.   |
| **TCL (Transaction Control Language)** | `COMMIT`, `ROLLBACK`, `SAVEPOINT`     | Manages transactions.         |
| **DCL (Data Control Language)**        | `GRANT`, `REVOKE`                     | Manages database permissions. |

---

## **2. Creating Tables (DDL)**

### **🔹 Creating a Sample Database**

```sql
CREATE DATABASE CompanyDB;
USE CompanyDB;
```

✅ **Now we are inside `CompanyDB` database.**

### **🔹 Creating Tables**

```sql
CREATE TABLE Employees (
    id INT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    department VARCHAR(50),
    salary DECIMAL(10,2) CHECK (salary > 0)
);
```

✅ **This creates an `Employees` table.**

### **🔹 Verifying the Table Structure**

```sql
DESCRIBE Employees;
```

**🔍 Output:**  
| Field | Type | Null | Key | Default | Extra |
|--------|------------|------|------|---------|-------|
| id | INT | NO | PRI | NULL | |
| name | VARCHAR(50) | NO | | NULL | |
| department | VARCHAR(50) | YES | | NULL | |
| salary | DECIMAL(10,2) | YES | | NULL | |

---

## **3. Data Manipulation (DML)**

### **🔹 Inserting Data**

```sql
INSERT INTO Employees (id, name, department, salary)
VALUES 
(1, 'John Doe', 'IT', 50000.00),
(2, 'Alice Smith', 'HR', 60000.00),
(3, 'Bob Johnson', 'Finance', 70000.00);
```

✅ **Data is now inserted into the `Employees` table.**

### **🔹 Viewing the Data**

```sql
SELECT * FROM Employees;
```

**🔍 Output:**  
| id | name | department | salary |
|----|--------|------------|---------|
| 1 | John Doe | IT | 50000.00 |
| 2 | Alice Smith | HR | 60000.00 |
| 3 | Bob Johnson | Finance | 70000.00 |

### **🔹 Updating Data**

```sql
UPDATE Employees SET salary = 75000 WHERE id = 3;
```

✅ **Bob Johnson’s salary is now updated.**

### **🔹 Deleting Data**

```sql
DELETE FROM Employees WHERE id = 1;
```

✅ **John Doe’s record has been deleted.**

---

## **4. Querying Data (DQL)**

### **🔹 Selecting Specific Columns**

```sql
SELECT name, salary FROM Employees;
```

**🔍 Output:**  
| name | salary |
|-------|---------|
| Alice Smith | 60000.00 |
| Bob Johnson | 75000.00 |

### **🔹 Filtering with `WHERE`**

```sql
SELECT * FROM Employees WHERE department = 'HR';
```

**🔍 Output:**  
| id | name | department | salary |
|----|------------|------------|---------|
| 2 | Alice Smith | HR | 60000.00 |

### **🔹 Ordering Results**

```sql
SELECT * FROM Employees ORDER BY salary DESC;
```

**🔍 Output:**  
| id | name | department | salary |
|----|------------|------------|---------|
| 3 | Bob Johnson | Finance | 75000.00 |
| 2 | Alice Smith | HR | 60000.00 |

---

## **5. SQL Joins**

### **🔹 Creating a Departments Table**

```sql
CREATE TABLE Departments (
    department_id INT PRIMARY KEY,
    department_name VARCHAR(50) UNIQUE
);
```

✅ **Departments table is created.**

### **🔹 Inserting Data**

```sql
INSERT INTO Departments (department_id, department_name)
VALUES 
(1, 'IT'),
(2, 'HR'),
(3, 'Finance');
```

✅ **Departments added.**

### **🔹 INNER JOIN**

```sql
SELECT Employees.name, Departments.department_name
FROM Employees
INNER JOIN Departments ON Employees.department = Departments.department_name;
```

**🔍 Output:**  
| name | department_name |
|--------|----------------|
| Alice Smith | HR |
| Bob Johnson | Finance |

---

## **6. SQL Constraints**

| **Constraint**  | **Description**            |
|-----------------|----------------------------|
| **PRIMARY KEY** | Ensures uniqueness.        |
| **FOREIGN KEY** | Links tables together.     |
| **NOT NULL**    | Prevents NULL values.      |
| **UNIQUE**      | Ensures values are unique. |
| **CHECK**       | Sets a condition.          |

Example:

```sql
CREATE TABLE Employees (
    id INT PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    salary DECIMAL(10,2) CHECK (salary > 0),
    department VARCHAR(50) DEFAULT 'General'
);
```

---

## **7. SQL Aggregations**

| **Function** | **Description**       |
|--------------|-----------------------|
| **COUNT()**  | Counts rows.          |
| **SUM()**    | Adds values.          |
| **AVG()**    | Calculates average.   |
| **MIN()**    | Finds smallest value. |
| **MAX()**    | Finds largest value.  |

Example:

```sql
SELECT COUNT(*) FROM Employees;
SELECT AVG(salary) FROM Employees;
```

**🔍 Output:**  
| COUNT(*) |
|---------|
| 2 |

---

## **8. Transactions (TCL)**

```sql
START TRANSACTION;
UPDATE Employees SET salary = salary * 1.1 WHERE department = 'HR';
COMMIT; -- Saves changes

START TRANSACTION;
DELETE FROM Employees WHERE department = 'Finance';
ROLLBACK; -- Cancels deletion
```

---

# **SQL Interview Questions & Answers**

### ✅ **Basic Questions**

#### 1️⃣ What is SQL?

✅ SQL (Structured Query Language) is used to **manage and query relational databases**.

#### 2️⃣ What is the difference between `DELETE`, `TRUNCATE`, and `DROP`?

✅

- **DELETE** → Removes specific records (can be rolled back).
- **TRUNCATE** → Removes all records (cannot be rolled back).
- **DROP** → Deletes the entire table.

#### 3️⃣ Explain `INNER JOIN`, `LEFT JOIN`, `RIGHT JOIN`, and `FULL JOIN`.

✅

- **INNER JOIN** → Returns only matching rows.
- **LEFT JOIN** → Returns all rows from the left table, even if no match in the right table.
- **RIGHT JOIN** → Returns all rows from the right table.
- **FULL JOIN** → Returns all rows from both tables.

#### 4️⃣ What is the difference between `HAVING` and `WHERE`?

✅

- **WHERE** → Filters before aggregation (`GROUP BY`).
- **HAVING** → Filters after aggregation.

---

### ✅ **Next Steps**

📌 Read through this and **run the queries yourself** to verify the results. Let me know when you're ready for **Spring
Boot! 🚀**