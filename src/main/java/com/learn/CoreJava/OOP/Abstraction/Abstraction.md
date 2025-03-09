---

# **Abstraction in Java**

## **What is Abstraction?**

Abstraction is the **hiding of implementation details** while exposing only the necessary features of an object.  
It allows us to **focus on what an object does rather than how it does it**.

### **Why Use Abstraction?**

✅ **Reduces Complexity** – Users interact with high-level functionality without worrying about implementation details.  
✅ **Enhances Maintainability** – Changes in implementation do not affect users of the class.  
✅ **Improves Security** – Sensitive details are hidden from the outside world.  
✅ **Encourages Code Reusability** – Allows defining general blueprints that different classes can implement.

---

## **How Abstraction Works in Java**

In Java, **abstraction** can be achieved using:

1. **Abstract Classes** (`abstract` keyword)
2. **Interfaces** (`interface` keyword)

---

## **Basic Example of Abstraction (Using Abstract Class)**

📌 **File: `AbstractionBasicExample.java`**

```java
package com.learn.Abstraction;

// Abstract class
abstract class Animal {
    String name;

    // Abstract method (no implementation)
    abstract void makeSound();

    // Concrete method (has implementation)
    public void eat() {
        System.out.println(name + " is eating.");
    }
}

// Concrete subclass
class Dog extends com.learn.CoreJava.Abstraction.OOP.Animal {
    public Dog(String name) {
        this.name = name;
    }

    // Implementing abstract method
    @Override
    void makeSound() {
        System.out.println(name + " barks: Woof! Woof!");
    }
}

public class AbstractionBasicExample {
    public static void main(String[] args) {
        com.learn.CoreJava.Abstraction.OOP.Dog myDog = new com.learn.CoreJava.Abstraction.OOP.Dog("Buddy");
        myDog.makeSound(); // Calls overridden method
        myDog.eat();       // Calls inherited concrete method
    }
}
```

### **Explanation:**

1. **`Animal`** is an **abstract class** that has both **abstract and concrete methods**.
2. **`Dog`** extends `Animal` and **implements the abstract method** `makeSound()`.
3. The `eat()` method is **already implemented** in `Animal`, so `Dog` inherits it.

### **Output:**

```
Buddy barks: Woof! Woof!
Buddy is eating.
```

---

## **Intermediate Example - Implementing an Interface**

📌 **File: `AbstractionIntermExample.java`**

```java
package com.learn.Abstraction;

// Interface
interface Payment {
    void processPayment(double amount);
}

// Concrete class implementing the interface
class CreditCardPayment implements com.learn.CoreJava.Abstraction.OOP.Payment {
    private String cardNumber;

    public CreditCardPayment(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public void processPayment(double amount) {
        System.out.println("Processing credit card payment of $" + amount + " using card: " + cardNumber);
    }
}

// Another concrete class implementing the interface
class PayPalPayment implements com.learn.CoreJava.Abstraction.OOP.Payment {
    private String email;

    public PayPalPayment(String email) {
        this.email = email;
    }

    @Override
    public void processPayment(double amount) {
        System.out.println("Processing PayPal payment of $" + amount + " using email: " + email);
    }
}

public class AbstractionIntermExample {
    public static void main(String[] args) {
        com.learn.CoreJava.Abstraction.OOP.Payment creditCard = new com.learn.CoreJava.Abstraction.OOP.CreditCardPayment("1234-5678-9876-5432");
        creditCard.processPayment(150.75);

        com.learn.CoreJava.Abstraction.OOP.Payment paypal = new com.learn.CoreJava.Abstraction.OOP.PayPalPayment("user@example.com");
        paypal.processPayment(89.99);
    }
}
```

### **Explanation:**

1. **`Payment`** is an **interface** that defines the blueprint for payment processing.
2. **`CreditCardPayment`** and **`PayPalPayment`** **implement** the interface by defining `processPayment()`.
3. We use **polymorphism** by referring to objects using the `Payment` interface.

### **Output:**

```
Processing credit card payment of $150.75 using card: 1234-5678-9876-5432
Processing PayPal payment of $89.99 using email: user@example.com
```

---

## **Key Concepts in Java Abstraction**

### 🔹 **Abstract Class vs. Interface**

| Feature                 | Abstract Class | Interface                     |
|-------------------------|----------------|-------------------------------|
| Methods with Code       | ✅ Yes          | ❌ No (until Java 8)           |
| Can Have Fields         | ✅ Yes          | ❌ No (only constants)         |
| Multiple Inheritance    | ❌ No           | ✅ Yes (since Java 8)          |
| Default Implementations | ✅ Yes          | ✅ Yes (via `default` methods) |
| Constructor Allowed     | ✅ Yes          | ❌ No                          |

---

## **Interview Questions on Abstraction**

1. **What is abstraction in Java?**
    - Abstraction is the process of hiding implementation details and showing only the necessary functionality.

2. **How can we achieve abstraction in Java?**
    - Using **abstract classes** (partial abstraction) and **interfaces** (full abstraction).

3. **Can we create an object of an abstract class?**
    - No, abstract classes **cannot be instantiated**, but we can create objects of their subclasses.

4. **What is the difference between an abstract class and an interface?**
    - Abstract classes **can have implemented methods**, whereas interfaces **only define method signatures** (before
      Java 8).

---

## **Conclusion**

Abstraction helps in **hiding unnecessary details** while providing a clear contract for object behavior.  
We use **abstract classes** when we need **shared behavior**, and **interfaces** when we need **multiple implementations
**.

---
