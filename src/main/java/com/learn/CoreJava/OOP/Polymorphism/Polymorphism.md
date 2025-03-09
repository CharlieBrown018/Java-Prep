---

# **Polymorphism in Java**

## **What is Polymorphism?**

Polymorphism means "**many forms**." It allows methods to **behave differently** based on the object that calls them.

### **Why Use Polymorphism?**

✅ **Flexibility** – The same method works differently based on the object.  
✅ **Code Reusability** – Write general code that applies to multiple object types.  
✅ **Extensibility** – Add new behaviors without modifying existing code.

Polymorphism is achieved through:

1. **Method Overloading** (**Static Polymorphism**)
2. **Method Overriding** (**Dynamic Polymorphism**)
3. **Interfaces & Abstract Classes** (Used for achieving polymorphism)

---

## **1️⃣ Method Overloading (Static Polymorphism)**

Method Overloading occurs when multiple methods in **the same class** have the **same name** but **different parameters
**.

### **Rules for Method Overloading:**

- The **method name must be the same**.
- The **parameters must be different** in **number, type, or both**.
- Return type **does not** affect method overloading.

---

### **Basic Example of Method Overloading**

📌 **File: `PolymorphismBasicExample.java`**

```java
package com.learn.Polymorphism;

class MathOperations {
    // Overloaded method with two integers
    public int add(int a, int b) {
        return a + b;
    }

    // Overloaded method with three integers
    public int add(int a, int b, int c) {
        return a + b + c;
    }

    // Overloaded method with double values
    public double add(double a, double b) {
        return a + b;
    }
}

public class PolymorphismBasicExample {
    public static void main(String[] args) {
        com.learn.CoreJava.Polymorphism.OOP.MathOperations math = new com.learn.CoreJava.Polymorphism.OOP.MathOperations();
        System.out.println(math.add(5, 10));       // Calls add(int, int)
        System.out.println(math.add(5, 10, 15));   // Calls add(int, int, int)
        System.out.println(math.add(5.5, 2.5));    // Calls add(double, double)
    }
}
```

### **Output**

```
15
30
8.0
```

Here, the `add()` method is **overloaded** to handle different numbers and types of parameters.

---

## **2️⃣ Method Overriding (Dynamic Polymorphism)**

Method Overriding occurs when a **subclass provides a specific implementation** of a method that is already defined in
its **superclass**.

### **Rules for Method Overriding:**

- The method must have the **same name, parameters, and return type** as in the parent class.
- The **child class must provide its own version** of the method.
- The method cannot have a **lower visibility** than the parent class method (e.g., public → private ❌).

---

### **Intermediate Example of Method Overriding**

📌 **File: `PolymorphismIntermExample.java`**

```java
package com.learn.Polymorphism;

// Parent class
class Animal {
    public void makeSound() {
        System.out.println("Animal makes a sound.");
    }
}

// Child class (Overriding the method)
class Dog extends com.learn.CoreJava.Polymorphism.OOP.Animal {
    @Override
    public void makeSound() {
        System.out.println("Dog barks.");
    }
}

// Another child class (Overriding the method)
class Cat extends com.learn.CoreJava.Polymorphism.OOP.Animal {
    @Override
    public void makeSound() {
        System.out.println("Cat meows.");
    }
}

public class PolymorphismIntermExample {
    public static void main(String[] args) {
        com.learn.CoreJava.Polymorphism.OOP.Animal myAnimal = new com.learn.CoreJava.Polymorphism.OOP.Animal();
        com.learn.CoreJava.Polymorphism.OOP.Animal myDog = new com.learn.CoreJava.Polymorphism.OOP.Dog();
        com.learn.CoreJava.Polymorphism.OOP.Animal myCat = new com.learn.CoreJava.Polymorphism.OOP.Cat();

        myAnimal.makeSound(); // Calls Animal's method
        myDog.makeSound();    // Calls Dog's overridden method
        myCat.makeSound();    // Calls Cat's overridden method
    }
}
```

### **Output**

```
Animal makes a sound.
Dog barks.
Cat meows.
```

This demonstrates **method overriding** where subclasses provide their own specific behavior for `makeSound()`.

---

## **3️⃣ Interfaces & Abstract Classes in Polymorphism**

Both **interfaces** and **abstract classes** allow polymorphism by defining methods that must be implemented by
subclasses.

### **Abstract Class**

An **abstract class** **cannot be instantiated** and may have **both abstract and concrete methods**.

### **Interface**

An **interface** contains only **abstract methods** (before Java 8) and is implemented by classes.

### **Example: Abstract Class & Interface with Polymorphism**

📌 **File: `PolymorphismAdvancedExample.java`**

```java
package com.learn.Polymorphism;

// Abstract class
abstract class Vehicle {
    abstract void start(); // Abstract method
}

// Interface
interface Honkable {
    void honk(); // Abstract method (implicitly public & abstract)
}

// Concrete class extending abstract class & implementing interface
class Car extends com.learn.CoreJava.Polymorphism.OOP.Vehicle implements com.learn.CoreJava.Polymorphism.OOP.Honkable {
    @Override
    void start() {
        System.out.println("Car is starting with a key.");
    }

    @Override
    public void honk() {
        System.out.println("Car honks: Beep! Beep!");
    }
}

// Another concrete class
class Bike extends com.learn.CoreJava.Polymorphism.OOP.Vehicle {
    @Override
    void start() {
        System.out.println("Bike is starting with a kick.");
    }
}

public class PolymorphismAdvancedExample {
    public static void main(String[] args) {
        com.learn.CoreJava.Polymorphism.OOP.Vehicle myCar = new com.learn.CoreJava.Polymorphism.OOP.Car();
        com.learn.CoreJava.Polymorphism.OOP.Vehicle myBike = new com.learn.CoreJava.Polymorphism.OOP.Bike();
        com.learn.CoreJava.Polymorphism.OOP.Honkable honkableCar = new com.learn.CoreJava.Polymorphism.OOP.Car();

        myCar.start();  // Calls Car's start()
        myBike.start(); // Calls Bike's start()
        honkableCar.honk(); // Calls Car's honk()
    }
}
```

### **Output**

```
Car is starting with a key.
Bike is starting with a kick.
Car honks: Beep! Beep!
```

This example demonstrates **polymorphism using abstract classes and interfaces**.

---

## **Key Concepts in Java Polymorphism**

### 🔹 **Method Overloading vs. Method Overriding**

| Feature             | Method Overloading                     | Method Overriding                                                       |
|---------------------|----------------------------------------|-------------------------------------------------------------------------|
| **Definition**      | Same method name, different parameters | Same method name, same parameters, different implementation in subclass |
| **Where it occurs** | Same class                             | Parent-Child relationship                                               |
| **Return Type**     | Can be different                       | Must be the same                                                        |
| **Access Modifier** | No restriction                         | Cannot reduce visibility                                                |

---

### 🔹 **Abstract Classes vs. Interfaces**

| Feature         | Abstract Class                       | Interface                             |
|-----------------|--------------------------------------|---------------------------------------|
| **Methods**     | Can have abstract & concrete methods | Only abstract methods (before Java 8) |
| **Fields**      | Can have instance variables          | Only public static final variables    |
| **Inheritance** | Can extend only **one** class        | Can implement **multiple** interfaces |
| **Use Case**    | When classes have shared behavior    | When multiple behaviors are needed    |

---

## **Interview Questions on Polymorphism**

1. **What is the difference between method overloading and method overriding?**
2. **Can you override a private method in Java?**
    - No, because private methods are not inherited.
3. **Can you overload a method by just changing the return type?**
    - No, method overloading requires different parameters.
4. **What happens if the superclass method is static and you override it?**
    - You are not overriding; you're hiding the method.
5. **Why would you use an interface over an abstract class?**
    - Use an interface for **multiple inheritance** and when there is no common implementation.

---

## **Conclusion**

Polymorphism is a powerful OOP concept that allows Java programs to be **flexible, reusable, and extendable**.  
It is used extensively in frameworks, design patterns, and real-world applications.

---

Next, **we will cover Encapsulation**! Do you want to proceed? 🚀