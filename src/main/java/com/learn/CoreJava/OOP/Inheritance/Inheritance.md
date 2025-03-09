# **Inheritance in Java**

## **What is Inheritance?**

Inheritance is one of the **four pillars of Object-Oriented Programming (OOP)** that allows a **child class (subclass)**
to inherit the **fields and methods** of a **parent class (superclass)**.

### **Why Use Inheritance??**

✅ **Code Reusability** – Avoid duplicate code.  
✅ **Improved Maintainability** – Update the parent class to reflect changes in child classes.  
✅ **Establishes Relationships** – Represents real-world hierarchies (e.g., `Vehicle → Car`).  
✅ **Extensibility** – Allows adding or modifying behavior in subclasses.

---

## **How Inheritance Works in Java**

- Use the `extends` keyword to create a subclass.
- The child class inherits **all non-private members** from the parent class.
- The child class **can override methods** for different behavior.

---

## **Basic Example of Inheritance**

📌 **File: `InheritanceBasicExample.java`**

```java
package com.learn.Inheritance;

// Parent class
class Animal {
    String name;

    public void eat() {
        System.out.println(name + " is eating.");
    }
}

// Child class (inherits from Animal)
class Dog extends com.learn.CoreJava.Inheritance.OOP.Animal {
    public void bark() {
        System.out.println(name + " is barking.");
    }
}

public class InheritanceBasicExample {
    public static void main(String[] args) {
        com.learn.CoreJava.Inheritance.OOP.Dog myDog = new com.learn.CoreJava.Inheritance.OOP.Dog();
        myDog.name = "Buddy"; // Inherited field
        myDog.eat();  // Inherited method
        myDog.bark(); // Child class method
    }
}
```

### **Explanation:**

1. `Animal` (Parent Class) has a `name` field and an `eat()` method.
2. `Dog` (Child Class) **inherits** these properties and adds a `bark()` method.
3. The child class **can use** both inherited and its own methods.

### **Output:**

```
Buddy is eating.
Buddy is barking.
```

---

## **Intermediate Example - Extending a Bank Account**

📌 **File: `InheritanceIntermExample.java`**

```java
package com.learn.Inheritance;

// Parent class (Base Account)
class BankAccount {
    protected double balance; // Protected: accessible in child class

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: " + amount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

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

    public double getBalance() {
        return balance;
    }
}

// Child class (Savings Account)
class SavingsAccount extends com.learn.CoreJava.Inheritance.OOP.BankAccount {
    private double interestRate;

    public SavingsAccount(double interestRate) {
        this.interestRate = interestRate;
    }

    public void addInterest() {
        double interest = balance * interestRate / 100;
        balance += interest;
        System.out.println("Interest added: " + interest);
    }
}

public class InheritanceIntermExample {
    public static void main(String[] args) {
        com.learn.CoreJava.Inheritance.OOP.SavingsAccount mySavings = new com.learn.CoreJava.Inheritance.OOP.SavingsAccount(5.0);
        mySavings.deposit(1000.00);
        mySavings.addInterest();
        System.out.println("Final Balance: " + mySavings.getBalance());
    }
}
```

### **Explanation:**

1. `BankAccount` (Parent Class) has methods for `deposit()`, `withdraw()`, and `getBalance()`.
2. `SavingsAccount` (Child Class) **inherits** these methods and adds an `addInterest()` feature.
3. This demonstrates **reusability**, as `SavingsAccount` does not need to redefine `deposit()` and `withdraw()`.

### **Output:**

```
Deposited: 1000.0
Interest added: 50.0
Final Balance: 1050.0
```

---

## **Method Overloading vs. Method Overriding**

| Feature             | Method Overloading                                                                              | Method Overriding                                                |
|---------------------|-------------------------------------------------------------------------------------------------|------------------------------------------------------------------|
| **Definition**      | Multiple methods with the **same name** but **different parameters** within the **same class**. | A **subclass modifies** an inherited method from its superclass. |
| **Purpose**         | Improves **readability** and allows method **flexibility**.                                     | Provides **different behavior** in a subclass.                   |
| **Parameters**      | Must be **different** (different number, type, or order).                                       | Must be **exactly the same** (name + parameters).                |
| **Return Type**     | Can be **different**.                                                                           | Must be **the same or a subtype** (covariant return).            |
| **Access Modifier** | Can be **any**.                                                                                 | Cannot have a **weaker** modifier than the superclass method.    |
| **Static Methods**  | Can be overloaded.                                                                              | **Cannot be overridden** (but can be hidden).                    |
| **Final Methods**   | Can be overloaded.                                                                              | **Cannot be overridden**.                                        |

---

## **Example of Method Overloading**

📌 **File: `MethodOverloadingExample.java`**

```java
package com.learn.Inheritance;

// Method Overloading Example
class MathUtils {
    // Overloaded methods with different parameters
    public int add(int a, int b) {
        return a + b;
    }

    public double add(double a, double b) {
        return a + b;
    }

    public int add(int a, int b, int c) {
        return a + b + c;
    }
}

public class MethodOverloadingExample {
    public static void main(String[] args) {
        com.learn.CoreJava.Inheritance.OOP.MathUtils math = new com.learn.CoreJava.Inheritance.OOP.MathUtils();
        System.out.println("Sum (int, int): " + math.add(5, 10));
        System.out.println("Sum (double, double): " + math.add(5.5, 2.5));
        System.out.println("Sum (int, int, int): " + math.add(3, 4, 5));
    }
}
```

### **Output:**

```
Sum (int, int): 15
Sum (double, double): 8.0
Sum (int, int, int): 12
```

---

## **Example of Method Overriding**

📌 **File: `MethodOverridingExample.java`**

```java
package com.learn.Inheritance;

// Parent class
class Animal {
    public void makeSound() {
        System.out.println("Some generic animal sound...");
    }
}

// Child class overrides the method
class Dog extends com.learn.CoreJava.Inheritance.OOP.Animal {
    @Override
    public void makeSound() {
        System.out.println("Bark! Bark!");
    }
}

public class MethodOverridingExample {
    public static void main(String[] args) {
        com.learn.CoreJava.Inheritance.OOP.Animal myAnimal = new com.learn.CoreJava.Inheritance.OOP.Animal();
        myAnimal.makeSound(); // Calls the parent method

        com.learn.CoreJava.Inheritance.OOP.Dog myDog = new com.learn.CoreJava.Inheritance.OOP.Dog();
        myDog.makeSound(); // Calls the overridden method in Dog class
    }
}
```

### **Output:**

```
Some generic animal sound...
Bark! Bark!
```

---

## **Key Concepts in Java Inheritance**

### 🔹 **Access Modifiers & Inheritance**

| Modifier    | Accessible in Parent? | Accessible in Child? (Same Package) | Accessible in Child? (Different Package) |
|-------------|-----------------------|-------------------------------------|------------------------------------------|
| `private`   | ✅ Yes                 | ❌ No                                | ❌ No                                     |
| `default`   | ✅ Yes                 | ✅ Yes                               | ❌ No                                     |
| `protected` | ✅ Yes                 | ✅ Yes                               | ✅ Yes                                    |
| `public`    | ✅ Yes                 | ✅ Yes                               | ✅ Yes                                    |

---

### 🔹 **Method Overriding**

When a subclass **modifies** an inherited method, it's called **overriding**.

## **Inheritance Interview Q&A**

### **Basic Questions**

1️⃣ **What is inheritance in Java?**  
**Answer:** Inheritance is a mechanism in Java where one class (subclass) inherits the fields and methods of another
class (superclass). It promotes **code reusability** and allows for **hierarchical relationships** between classes.

2️⃣ **What is the `extends` keyword in Java?**  
**Answer:** The `extends` keyword is used to create a subclass that inherits from a superclass. Example:

   ```java
   class Animal {
    void eat() {
        System.out.println("This animal eats food.");
    }
}

class Dog extends Animal {
    void bark() {
        System.out.println("Dog barks!");
    }
}
   ```

3️⃣ **Can a subclass inherit private members of the superclass?**  
**Answer:** No, private members are **not inherited** by a subclass. However, they can be accessed via **public or
protected getter methods** in the superclass.

4️⃣ **What is the difference between single and multilevel inheritance?**  
**Answer:**

- **Single Inheritance:** A subclass inherits from a single superclass.
- **Multilevel Inheritance:** A subclass inherits from another subclass (forming a chain).

   ```java
   class Animal { }  // Superclass
   class Dog extends Animal { }  // Single Inheritance
   class Puppy extends Dog { }   // Multilevel Inheritance
   ```

---

### **Intermediate Questions**

5️⃣ **Can Java support multiple inheritance?**  
**Answer:** Java does **not** support multiple inheritance with classes to avoid **ambiguity** (Diamond Problem).
However, multiple inheritance is possible using **interfaces**.

   ```java
   interface A {
    void methodA();
}

interface B {
    void methodB();
}

class C implements A, B {
    public void methodA() {
        System.out.println("A method");
    }

    public void methodB() {
        System.out.println("B method");
    }
}
   ```

6️⃣ **What is the difference between `super` and `this` in Java?**  
**Answer:**

- `super` refers to the **superclass (parent class)** and is used to call parent methods or constructors.
- `this` refers to the **current class instance** and is used to differentiate instance variables from local variables.

   ```java
   class Parent {
       Parent() { System.out.println("Parent Constructor"); }
   }
   class Child extends Parent {
       Child() { 
           super(); // Calls Parent() constructor
           System.out.println("Child Constructor"); 
       }
   }
   ```

7️⃣ **What is method overriding?**  
**Answer:** Method overriding occurs when a subclass **redefines a method** from its superclass with the **same
signature**.

   ```java
   class Animal {
    void sound() {
        System.out.println("Some sound");
    }
}

class Dog extends Animal {
    @Override
    void sound() {
        System.out.println("Bark! Bark!");
    }
}
   ```

8️⃣ **Can a constructor be inherited in Java?**  
**Answer:** No, constructors are **not inherited** in Java. However, the subclass **implicitly calls** the constructor
of the superclass using `super()`.

---

### **Advanced Questions**

9️⃣ **Can you prevent a class from being inherited?**  
**Answer:** Yes, by declaring the class as `final`.

   ```java
   final class Animal {
}

class Dog extends Animal {
}  // ❌ Compilation error
   ```

🔟 **What happens if a subclass does not call `super()`?**  
**Answer:** Java automatically calls the **default constructor** of the superclass. However, if the superclass has a *
*parameterized constructor**, the subclass **must** explicitly call `super(arguments)`.

---