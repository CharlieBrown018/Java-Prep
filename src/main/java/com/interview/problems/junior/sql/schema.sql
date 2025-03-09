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