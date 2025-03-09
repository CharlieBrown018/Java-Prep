---

# **Encapsulation in Java**

## **What is Encapsulation?**

Encapsulation is one of the **four pillars of Object-Oriented Programming (OOP)**. It is the **practice of bundling
data (fields) and methods that operate on the data into a single unit (class)** while **restricting direct access** to
some of the object's details.

Instead of allowing external access to fields, **Encapsulation enforces controlled access using getters and setters**.

---

## **Why is Encapsulation Important?**

✅ **Data Protection:** Prevents unauthorized modification of data.  
✅ **Improves Maintainability:** Changes in the implementation do not affect external code.  
✅ **Enhances Code Readability:** Keeps related data and behavior within the class.  
✅ **Ensures Data Integrity:** Prevents setting invalid values.

---

## **How Encapsulation Works in Java**

- Use the `private` access modifier to **restrict access** to instance variables.
- Use **getter and setter methods** to allow controlled access.
- Define **business rules inside methods** to validate data before modifying it.

---

## **Basic Example of Encapsulation**

📌 **File: `EncapsulationBasicExample.java`**

```java
package com.learn.Encapsulation;

class Person {
    private String name; // Private variable (Encapsulation)

    // Getter
    public String getName() {
        return name;
    }

    // Setter
    public void setName(String name) {
        this.name = name;
    }
}

public class EncapsulationBasicExample {
    public static void main(String[] args) {
        com.learn.CoreJava.Encapsulation.OOP.Person person = new com.learn.CoreJava.Encapsulation.OOP.Person();
        person.setName("John Doe"); // Using setter
        System.out.println("Person's Name: " + person.getName()); // Using getter
    }
}
```

### **Explanation:**

1. The `name` variable is **private**, meaning it **cannot be accessed directly** outside the class.
2. The `setName()` method **modifies** the name safely.
3. The `getName()` method **retrieves** the name.
4. Encapsulation ensures **controlled access to data** while preventing direct modification.

### **Output:**

```
Person's Name: John Doe
```

---

## **Intermediate Example - Bank Account with Encapsulation**

📌 **File: `EncapsulationIntermExample.java`**

```java
package com.learn.Encapsulation;

class BankAccount {
    private double balance; // Private variable (Encapsulation)

    // Getter for balance
    public double getBalance() {
        return balance;
    }

    // Setter for balance with validation
    public void setBalance(double balance) {
        if (balance >= 0) {
            this.balance = balance;
        } else {
            System.out.println("Error: Negative balance is not allowed.");
        }
    }

    // Deposit method
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: " + amount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    // Withdraw method with validation
    public void withdraw(double amount) {
        if (amount > balance) {
            System.out.println("Insufficient funds!");
        } else if (amount > 0) {
            balance -= amount;
            System.out.println("Withdrawn: " + amount);
        } else {
            System.out.println("Invalid withdrawal amount.");
        }
    }
}

public class EncapsulationIntermExample {
    public static void main(String[] args) {
        com.learn.CoreJava.Encapsulation.OOP.BankAccount myAccount = new com.learn.CoreJava.Encapsulation.OOP.BankAccount();

        myAccount.setBalance(1000.00);
        System.out.println("Current Balance: " + myAccount.getBalance());

        myAccount.deposit(500.00);
        myAccount.withdraw(300.00);
        myAccount.withdraw(1500.00); // Should show insufficient funds
    }
}
```

### **Explanation:**

1. The `balance` variable is **private** to ensure **no direct modification**.
2. The `setBalance()` method **validates** that the balance cannot be set to a negative number.
3. The `deposit()` and `withdraw()` methods **contain logic** to ensure correct transactions.
4. Encapsulation helps **protect the integrity of the bank account** by enforcing validation rules.

### **Output:**

```
Current Balance: 1000.0
Deposited: 500.0
Withdrawn: 300.0
Insufficient funds!
```

---

## **Encapsulation Interview Questions**

1. **What is encapsulation in Java?**
    - Encapsulation is the concept of restricting direct access to an object’s fields and methods while providing
      controlled access through getter and setter methods.

2. **How does Java implement encapsulation?**
    - Java uses the `private` access modifier to hide data and provides `public` getter and setter methods to access and
      modify it safely.

3. **What are the advantages of encapsulation?**
    - Data protection, maintainability, code organization, and security.

4. **What is the difference between encapsulation and abstraction?**
    - Encapsulation **hides data** and allows controlled access, while abstraction **hides implementation details** and
      only shows necessary functionality.

---

## **Conclusion**

Encapsulation is a fundamental OOP concept that **protects data, improves code structure, and enhances security** by
restricting direct access to fields and enforcing controlled modification through methods.

---
