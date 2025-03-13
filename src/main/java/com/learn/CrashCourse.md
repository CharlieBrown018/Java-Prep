# Complete Java Developer Crash Course for Amdocs Technical Interview

## Introduction

Hello Charles! I've prepared a comprehensive crash course covering all the essential topics for your Amdocs technical
interview. Based on your resume and the job description, I'll focus on Java fundamentals, OOP concepts, data structures,
SQL, and Unix - all the areas mentioned in the interviewer's tip.

The course is organized into clear sections with explanations, code examples, and practice questions similar to what you
might face. I'll make sure to explain concepts in an approachable way while covering everything thoroughly.

Let's begin!

## Core Java Fundamentals

### JDK, JRE, JVM

**JVM (Java Virtual Machine):** The runtime environment where Java bytecode executes. It provides platform independence.

**JRE (Java Runtime Environment):** Contains the JVM and standard libraries needed to run Java applications.

**JDK (Java Development Kit):** Contains the JRE plus development tools like the compiler (javac), debugger, and
documentation tools.

This three-tier architecture is why Java is "write once, run anywhere":

1. You write Java code
2. The Java compiler creates bytecode
3. The JVM executes this bytecode on any platform

### Memory Management and Garbage Collection

**Pointers in Java:** Java doesn't have direct pointer manipulation like C/C++. Instead, Java uses references to
objects. These references are automatically managed, making Java memory-safe.

**Garbage Collection:** Java automatically reclaims memory occupied by objects that are no longer referenced, preventing
memory leaks. The Garbage Collector (GC) runs in the background and identifies objects that are no longer accessible.

The GC uses algorithms like:

- **Mark and Sweep**: Marks all reachable objects and then sweeps away unmarked ones
- **Generational Garbage Collection**: Divides objects by age (young/old generations) since newer objects tend to die
  young

### Variable Types and Scope

**Primitive Types:** `int`, `double`, `boolean`, `char`, `byte`, `short`, `long`, `float`

**Reference Types:** Classes, Interfaces, Arrays, Enums

**Variable Scope:**

- **Instance Variables:** Declared in a class but outside methods, accessible to all methods
- **Class/Static Variables:** Shared among all instances of a class
- **Local Variables:** Declared within methods, only accessible within that method
- **Block Variables:** Declared within code blocks like loops or if statements

## Object-Oriented Programming (OOP)

### The Four Pillars of OOP

#### 1. Encapsulation

**Definition:** Bundling data (attributes) and methods that operate on the data into a single unit (class) and
restricting direct access to some components.

**Example:**

```
public class BankAccount {
    // Private variables - hidden from direct access
    private double balance;
    private String accountNumber;
    
    // Public methods - controlled access
    public double getBalance() {
        return balance;
    }
    
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited: " + amount);
        } else {
            System.out.println("Invalid deposit amount");
        }
    }
}
```

**Benefits:**

- Data hiding and protection
- Controlled access through methods
- Maintenance flexibility

#### 2. Inheritance

**Definition:** The ability of a class to inherit properties and methods from another class.

**Example:**

```
// Parent/super class
class Animal {
    protected String name;
    
    public Animal(String name) {
        this.name = name;
    }
    
    public void eat() {
        System.out.println(name + " is eating");
    }
}

// Child/sub class
class Dog extends Animal {
    private String breed;
    
    public Dog(String name, String breed) {
        super(name); // Call parent constructor
        this.breed = breed;
    }
    
    public void bark() {
        System.out.println(name + " is barking");
    }
}
```

**Usage:**

```
Dog dog = new Dog("Buddy", "Golden Retriever");
dog.eat();   // Inherited method
dog.bark();  // Dog's own method
```

**Types of Inheritance in Java:**

- Single
- Multilevel
- Hierarchical
- Multiple (through interfaces only)

### **Types of Inheritance in Java with Examples**

Java supports different types of inheritance that help in code reusability and the creation of hierarchical
relationships between classes.

---

### **1. Single Inheritance**

A child class inherits from a single parent class.

**Example:**

```
// Parent class
class Animal {
    protected String name;

    public Animal(String name) {
        this.name = name;
    }

    public void eat() {
        System.out.println(name + " is eating.");
    }
}

// Child class
class Dog extends Animal {
    private String breed;

    public Dog(String name, String breed) {
        super(name);
        this.breed = breed;
    }

    public void bark() {
        System.out.println(name + " is barking.");
    }
}

// Usage
public class SingleInheritanceDemo {
    public static void main(String[] args) {
        Dog dog = new Dog("Buddy", "Golden Retriever");
        dog.eat();  // Inherited from Animal
        dog.bark(); // Dog's own method
    }
}
```

✅ **Output:**

```
Buddy is eating.
Buddy is barking.
```

---

### **2. Multilevel Inheritance**

A class inherits from another class, which itself is inherited from a different class.

**Example:**

```
// Grandparent class
class Animal {
    protected String name;

    public Animal(String name) {
        this.name = name;
    }

    public void eat() {
        System.out.println(name + " is eating.");
    }
}

// Parent class
class Mammal extends Animal {
    public Mammal(String name) {
        super(name);
    }

    public void walk() {
        System.out.println(name + " is walking.");
    }
}

// Child class
class Dog extends Mammal {
    private String breed;

    public Dog(String name, String breed) {
        super(name);
        this.breed = breed;
    }

    public void bark() {
        System.out.println(name + " is barking.");
    }
}

// Usage
public class MultilevelInheritanceDemo {
    public static void main(String[] args) {
        Dog dog = new Dog("Charlie", "Labrador");
        dog.eat();  // Inherited from Animal
        dog.walk(); // Inherited from Mammal
        dog.bark(); // Dog's own method
    }
}
```

✅ **Output:**

```
Charlie is eating.
Charlie is walking.
Charlie is barking.
```

---

### **3. Hierarchical Inheritance**

Multiple child classes inherit from the same parent class.

**Example:**

```
// Parent class
class Animal {
    protected String name;

    public Animal(String name) {
        this.name = name;
    }

    public void eat() {
        System.out.println(name + " is eating.");
    }
}

// Child class 1
class Dog extends Animal {
    public Dog(String name) {
        super(name);
    }

    public void bark() {
        System.out.println(name + " is barking.");
    }
}

// Child class 2
class Cat extends Animal {
    public Cat(String name) {
        super(name);
    }

    public void meow() {
        System.out.println(name + " is meowing.");
    }
}

// Usage
public class HierarchicalInheritanceDemo {
    public static void main(String[] args) {
        Dog dog = new Dog("Rocky");
        Cat cat = new Cat("Whiskers");

        dog.eat();  // Inherited from Animal
        dog.bark(); // Dog's method

        cat.eat();  // Inherited from Animal
        cat.meow(); // Cat's method
    }
}
```

✅ **Output:**

```
Rocky is eating.
Rocky is barking.
Whiskers is eating.
Whiskers is meowing.
```

---

### **4. Multiple Inheritance (Using Interfaces)**

Java does **not** support multiple inheritance with classes to avoid ambiguity but allows it through **interfaces**.

**Example:**

```
// Interface 1
interface Swimmer {
    void swim();
}

// Interface 2
interface Walker {
    void walk();
}

// Class implementing multiple interfaces
class Frog implements Swimmer, Walker {
    private String name;

    public Frog(String name) {
        this.name = name;
    }

    public void swim() {
        System.out.println(name + " is swimming.");
    }

    public void walk() {
        System.out.println(name + " is walking.");
    }
}

// Usage
public class MultipleInheritanceDemo {
    public static void main(String[] args) {
        Frog frog = new Frog("Freddy");
        frog.swim();
        frog.walk();
    }
}
```

✅ **Output:**

```
Freddy is swimming.
Freddy is walking.
```

---

### **Summary Table**

| Type of Inheritance          | Description                                                         | Example                                    |
|------------------------------|---------------------------------------------------------------------|--------------------------------------------|
| **Single**                   | One class inherits from another.                                    | `Dog extends Animal`                       |
| **Multilevel**               | A class inherits from another, which in turn inherits from another. | `Dog → Mammal → Animal`                    |
| **Hierarchical**             | Multiple classes inherit from the same parent.                      | `Dog extends Animal`, `Cat extends Animal` |
| **Multiple (via Interface)** | A class implements multiple interfaces.                             | `Frog implements Swimmer, Walker`          |

Would you like additional variations or more complex examples? 🚀

#### 3. Polymorphism

**Definition:** The ability of objects to take on many forms. In Java, polymorphism occurs through method overloading
and method overriding.

**Method Overloading:** Same method name, different parameters

```
class Calculator {
    // Method overloading - different parameter counts
    int add(int a, int b) {
        return a + b;
    }
    
    int add(int a, int b, int c) {
        return a + b + c;
    }
    
    // Method overloading - different parameter types
    double add(double a, double b) {
        return a + b;
    }
}
```

**Method Overriding:** Implementing a method in a subclass that is already defined in the parent class

```
class Animal {
    void makeSound() {
        System.out.println("Animal makes a sound");
    }
}

class Dog extends Animal {
    @Override
    void makeSound() {
        System.out.println("Dog barks");
    }
}

class Cat extends Animal {
    @Override
    void makeSound() {
        System.out.println("Cat meows");
    }
}
```

**Usage:**

```
Animal myDog = new Dog(); // Animal reference, Dog object
Animal myCat = new Cat(); // Animal reference, Cat object

myDog.makeSound(); // Output: Dog barks
myCat.makeSound(); // Output: Cat meows
```

#### **4. Abstraction in Java**

#### **Definition:**

Abstraction is the process of **hiding implementation details** and exposing only the relevant parts of an object. It
helps in reducing complexity and increasing reusability.

Java provides **two** ways to achieve abstraction:

1. **Abstract Classes** (Partial Abstraction)
2. **Interfaces** (Full Abstraction)

---

### **1. Abstract Classes in Java**

An **abstract class** is a class that cannot be instantiated and may contain **abstract methods** (methods without
implementation).

**Key Features of Abstract Classes:**

- Can have both **abstract methods** (without implementation) and **concrete methods** (with implementation).
- Can declare **fields (instance variables)**.
- Can have **constructors**.
- Used when classes share some behavior but also need customization in subclasses.

### **Example: Abstract Class**

```
// Abstract class
abstract class Shape {
    abstract double calculateArea(); // Abstract method - No implementation

    void display() { // Concrete method - Has implementation
        System.out.println("This is a shape.");
    }
}

// Concrete subclass
class Circle extends Shape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    double calculateArea() {
        return Math.PI * radius * radius;
    }
}

// Usage
public class AbstractClassDemo {
    public static void main(String[] args) {
        Circle circle = new Circle(5.0);
        circle.display(); // Calls concrete method from Shape
        System.out.println("Area: " + circle.calculateArea());
    }
}
```

✅ **Output:**

```
This is a shape.
Area: 78.53981633974483
```

---

### **2. Interfaces in Java**

An **interface** is a blueprint for a class that defines a set of abstract methods (without implementation).

**Key Features of Interfaces:**

- **By default, all methods in an interface are public and abstract.**
- Can have **default methods** (Java 8+) with implementation.
- Cannot have instance variables (only `static final` constants).
- A class **can implement multiple interfaces** (Java does not support multiple inheritance with classes, but it allows
  it via interfaces).
- **100% abstraction** (before Java 8) since interfaces cannot have concrete methods (except default and static
  methods).

### **Example: Interface**

```
// Interface
interface Drawable {
    void draw(); // Abstract method (implicitly public and abstract)

    // Default method (Java 8+)
    default void display() {
        System.out.println("Displaying object.");
    }
}

// Class implementing the interface
class Rectangle implements Drawable {
    @Override
    public void draw() {
        System.out.println("Drawing rectangle.");
    }
}

// Usage
public class InterfaceDemo {
    public static void main(String[] args) {
        Rectangle rect = new Rectangle();
        rect.draw();   // Calls abstract method from interface
        rect.display(); // Calls default method from interface
    }
}
```

✅ **Output:**

```
Drawing rectangle.
Displaying object.
```

---

## **Comparison: Abstract Class vs. Interface**

| Feature                  | Abstract Class 🏛️                                           | Interface 🎭                                                                  |
|--------------------------|--------------------------------------------------------------|-------------------------------------------------------------------------------|
| **Abstraction Level**    | Partial (0-100%)                                             | Full (100%)                                                                   |
| **Method Type**          | Abstract + Concrete Methods                                  | Only Abstract (Until Java 8, now supports default/static)                     |
| **Fields**               | Can have instance variables                                  | Only `static final` constants                                                 |
| **Constructors**         | Yes                                                          | No                                                                            |
| **Access Modifiers**     | Can have any (private, protected, public)                    | Methods are `public` by default                                               |
| **Multiple Inheritance** | Not allowed (can only extend one class)                      | Allowed (a class can implement multiple interfaces)                           |
| **Best Used For**        | When you want to share code between multiple related classes | When you want to define a contract that multiple unrelated classes can follow |

---

### **When to Use What?**

✅ **Use Abstract Class** if:

- You need to define common fields and methods for subclasses.
- You need constructors or private/protected methods.
- You want to provide some default behavior but still allow method overriding.

✅ **Use Interface** if:

- You want to achieve full abstraction.
- You need multiple inheritance (a class can implement multiple interfaces).
- You want to define a contract that multiple unrelated classes should follow (e.g., `Runnable`, `Serializable`).

---

### **Example: Using Both Abstract Classes and Interfaces**

```
// Abstract class
abstract class Vehicle {
    String brand;

    public Vehicle(String brand) {
        this.brand = brand;
    }

    abstract void start();
}

// Interface
interface Electric {
    void chargeBattery();
}

// Class inheriting from an abstract class and implementing an interface
class Tesla extends Vehicle implements Electric {
    public Tesla(String brand) {
        super(brand);
    }

    @Override
    void start() {
        System.out.println(brand + " is starting silently.");
    }

    @Override
    public void chargeBattery() {
        System.out.println(brand + " is charging its battery.");
    }
}

// Usage
public class HybridExample {
    public static void main(String[] args) {
        Tesla myTesla = new Tesla("Tesla Model S");
        myTesla.start();
        myTesla.chargeBattery();
    }
}
```

✅ **Output:**

```
Tesla Model S is starting silently.
Tesla Model S is charging its battery.
```

---

### **Final Thoughts**

- **Abstract classes** are better when you need shared behavior **and** want to provide partial implementation.
- **Interfaces** are better when defining **only method signatures** that multiple classes can implement.
- **Java 8+ allows interfaces to have default and static methods, making them more powerful than before.**

---

### Constructors

**Definition:** Special methods used to initialize objects. They have the same name as the class.

**Types of Constructors:**

1. **Default Constructor:** No parameters, created by the compiler if no constructors are defined

```
class Student {
    // Compiler creates: Student() { }
}
```

2. **Parameterized Constructor:** Accepts parameters to initialize instance variables

```
class Student {
    private String name;
    private int age;
    
    // Parameterized constructor
    Student(String name, int age) {
        this.name = name;
        this.age = age;
    }
}
```

3. **Copy Constructor:** Creates an object by copying variables from another object

```
class Student {
    private String name;
    private int age;
    
    // Parameterized constructor
    Student(String name, int age) {
        this.name = name;
        this.age = age;
    }
    
    // Copy constructor
    Student(Student other) {
        this.name = other.name;
        this.age = other.age;
    }
}
```

### Final, Finally, Finalize

**`final` Keyword:**

- **final variable:** Cannot be changed (constant)
- **final method:** Cannot be overridden
- **final class:** Cannot be extended

```
final int MAX_SPEED = 120; // Constant
final class Math { } // Cannot be extended
public final void run() { } // Cannot be overridden
```

**`finally` Block:** Used in exception handling to execute code that must run regardless of whether an exception occurs

```
try {
    // Code that might throw an exception
    int result = 10 / 0;
} catch (ArithmeticException e) {
    // Handle the exception
    System.out.println("Cannot divide by zero");
} finally {
    // This code always executes
    System.out.println("Finally block executed");
}
```

**`finalize()` Method:** Called by the garbage collector before reclaiming an object's memory (deprecated in newer Java
versions)

```
protected void finalize() throws Throwable {
    // Clean up resources
    System.out.println("Object being garbage collected");
    super.finalize();
}
```

### Pass by Value vs. Pass by Reference

Java is strictly **pass by value**. However:

- For primitives, a copy of the value is passed
- For objects, a copy of the reference is passed (not the actual object)

```
void modifyValues(int num, StringBuilder str) {
    num = 20;  // Only modifies the local copy
    str.append(" World");  // Modifies the actual object through the reference
}

int x = 10;
StringBuilder sb = new StringBuilder("Hello");
modifyValues(x, sb);

System.out.println(x);     // Output: 10 (unchanged)
System.out.println(sb);    // Output: "Hello World" (changed)
```

## Data Structures

### Arrays

**Definition:** Fixed-size container for elements of the same type.

```
// Declaration and initialization
int[] numbers = new int[5]; // Creates array of size 5
int[] values = {1, 2, 3, 4, 5}; // Initializes array with values

// Accessing elements
numbers[0] = 10; // Assign value to first element
int firstValue = values[0]; // Access first element

// Iterating through array
for (int i = 0; i < values.length; i++) {
    System.out.println(values[i]);
}

// Enhanced for loop
for (int value : values) {
    System.out.println(value);
}
```

# **Linked List Implementations in Java**

## **LinkedList Overview**

### **Definition:**

A **linked list** is a data structure where elements (nodes) are stored in non-contiguous memory locations. Each node
contains **data** and a **reference (pointer)** to the next node.

### **Types of Linked List:**

1. **Singly Linked List** → Each node points to the **next** node.
2. **Doubly Linked List** → Each node points to **both previous and next** nodes.
3. **Circular Linked List** → The **last node** links back to the **first node**.

---

## **1. Singly Linked List Implementation**

Each node **stores data and a pointer** to the **next node**.

```
class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

class SinglyLinkedList {
    Node head;

    // Add node at the end
    void append(int data) {
        Node newNode = new Node(data);

        if (head == null) {
            head = newNode;
            return;
        }

        Node last = head;
        while (last.next != null) {
            last = last.next;
        }
        last.next = newNode;
    }

    // Print the list
    void printList() {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " → ");
            current = current.next;
        }
        System.out.println("null");
    }
}

// Usage
public class Main {
    public static void main(String[] args) {
        SinglyLinkedList list = new SinglyLinkedList();
        list.append(1);
        list.append(2);
        list.append(3);
        list.printList(); // Output: 1 → 2 → 3 → null
    }
}
```

---

## **2. Doubly Linked List Implementation**

Each node **stores data, a pointer to the next node, and a pointer to the previous node**.

```
class DoublyNode {
    int data;
    DoublyNode next;
    DoublyNode prev;

    DoublyNode(int data) {
        this.data = data;
        this.next = this.prev = null;
    }
}

class DoublyLinkedList {
    DoublyNode head;

    // Add node at the end
    void append(int data) {
        DoublyNode newNode = new DoublyNode(data);

        if (head == null) {
            head = newNode;
            return;
        }

        DoublyNode last = head;
        while (last.next != null) {
            last = last.next;
        }

        last.next = newNode;
        newNode.prev = last; // Link back to previous node
    }

    // Print list forward
    void printList() {
        DoublyNode current = head;
        while (current != null) {
            System.out.print(current.data + " ⇄ ");
            current = current.next;
        }
        System.out.println("null");
    }

    // Print list in reverse
    void printReverse() {
        DoublyNode current = head;
        if (current == null) return;

        // Move to the last node
        while (current.next != null) {
            current = current.next;
        }

        // Traverse backwards
        while (current != null) {
            System.out.print(current.data + " ⇄ ");
            current = current.prev;
        }
        System.out.println("null");
    }
}

// Usage
public class Main {
    public static void main(String[] args) {
        DoublyLinkedList list = new DoublyLinkedList();
        list.append(1);
        list.append(2);
        list.append(3);
        list.printList();    // Output: 1 ⇄ 2 ⇄ 3 ⇄ null
        list.printReverse(); // Output: 3 ⇄ 2 ⇄ 1 ⇄ null
    }
}
```

---

## **3. Circular Linked List Implementation**

A **circular linked list** loops back to the **first node** instead of ending in `null`.

### **Singly Circular Linked List**

- The **last node** links back to the **first node**.

```
class CircularNode {
    int data;
    CircularNode next;

    CircularNode(int data) {
        this.data = data;
        this.next = null;
    }
}

class CircularLinkedList {
    CircularNode head;

    // Add node at the end
    void append(int data) {
        CircularNode newNode = new CircularNode(data);

        if (head == null) {
            head = newNode;
            newNode.next = head; // Point to itself
            return;
        }

        CircularNode temp = head;
        while (temp.next != head) {
            temp = temp.next;
        }

        temp.next = newNode;
        newNode.next = head; // Link back to head
    }

    // Print list
    void printList() {
        if (head == null) return;

        CircularNode temp = head;
        do {
            System.out.print(temp.data + " → ");
            temp = temp.next;
        } while (temp != head);
        System.out.println("(back to head)");
    }
}

// Usage
public class Main {
    public static void main(String[] args) {
        CircularLinkedList list = new CircularLinkedList();
        list.append(1);
        list.append(2);
        list.append(3);
        list.printList(); // Output: 1 → 2 → 3 → (back to head)
    }
}
```

---

### **Doubly Circular Linked List**

- Each node **links forward and backward**, and the **last node** points to the **first node**.

```
class CircularDoublyNode {
    int data;
    CircularDoublyNode next;
    CircularDoublyNode prev;

    CircularDoublyNode(int data) {
        this.data = data;
        this.next = this.prev = null;
    }
}

class CircularDoublyLinkedList {
    CircularDoublyNode head;

    // Add node at the end
    void append(int data) {
        CircularDoublyNode newNode = new CircularDoublyNode(data);

        if (head == null) {
            head = newNode;
            head.next = head;
            head.prev = head;
            return;
        }

        CircularDoublyNode last = head.prev;
        last.next = newNode;
        newNode.prev = last;
        newNode.next = head;
        head.prev = newNode;
    }

    // Print list forward
    void printList() {
        if (head == null) return;

        CircularDoublyNode temp = head;
        do {
            System.out.print(temp.data + " ⇄ ");
            temp = temp.next;
        } while (temp != head);
        System.out.println("(back to head)");
    }

    // Print list in reverse
    void printReverse() {
        if (head == null) return;

        CircularDoublyNode temp = head.prev;
        do {
            System.out.print(temp.data + " ⇄ ");
            temp = temp.prev;
        } while (temp != head.prev);
        System.out.println("(back to tail)");
    }
}

// Usage
public class Main {
    public static void main(String[] args) {
        CircularDoublyLinkedList list = new CircularDoublyLinkedList();
        list.append(1);
        list.append(2);
        list.append(3);
        list.printList();    // Output: 1 ⇄ 2 ⇄ 3 ⇄ (back to head)
        list.printReverse(); // Output: 3 ⇄ 2 ⇄ 1 ⇄ (back to tail)
    }
}
```

---

## **Summary**

✅ **Singly Linked List:** Uses `next` pointers only.  
✅ **Doubly Linked List:** Uses `next` & `prev` pointers.  
✅ **Circular Linked List:** The **last node** connects to the **first node**.

### Stack and Queue

**Stack:** LIFO (Last In, First Out) data structure

```
// Using built-in Java Stack
import java.util.Stack;

Stack<Integer> stack = new Stack<>();
stack.push(1);
stack.push(2);
stack.push(3);

System.out.println(stack.pop());    // Output: 3
System.out.println(stack.peek());   // Output: 2
System.out.println(stack.isEmpty()); // Output: false
```

**Queue:** FIFO (First In, First Out) data structure

```
// Using Java LinkedList as a Queue
import java.util.LinkedList;
import java.util.Queue;

Queue<Integer> queue = new LinkedList<>();
queue.add(1);
queue.add(2);
queue.add(3);

System.out.println(queue.remove()); // Output: 1
System.out.println(queue.peek());   // Output: 2
System.out.println(queue.isEmpty()); // Output: false
```

**Implementing Queue with Two Stacks:**

```
import java.util.Stack;

class QueueUsingStacks {
    Stack<Integer> stack1 = new Stack<>();
    Stack<Integer> stack2 = new Stack<>();
    
    // Add element to the queue
    void enqueue(int x) {
        stack1.push(x);
    }
    
    // Remove element from the queue
    int dequeue() {
        // If both stacks are empty
        if (stack1.isEmpty() && stack2.isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }
        
        // If stack2 is empty, transfer all elements from stack1
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }
        
        // Pop from stack2
        return stack2.pop();
    }
}
```

### Maps

**HashMap:** Stores key-value pairs, allows null keys/values, not synchronized

```
import java.util.HashMap;
import java.util.Map;

Map<String, Integer> ages = new HashMap<>();
ages.put("John", 25);
ages.put("Alice", 30);
ages.put("Bob", 35);

System.out.println(ages.get("Alice")); // Output: 30
System.out.println(ages.containsKey("Dave")); // Output: false
System.out.println(ages.size()); // Output: 3

// Iterating through a HashMap
for (Map.Entry<String, Integer> entry : ages.entrySet()) {
    System.out.println(entry.getKey() + ": " + entry.getValue());
}
```

**ConcurrentHashMap:** Thread-safe version of HashMap that allows concurrent access

```
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

Map<String, Integer> safeMap = new ConcurrentHashMap<>();
safeMap.put("count", 0);

// Safe for multiple threads
safeMap.compute("count", (key, value) -> value + 1);
```

Key differences between HashMap and ConcurrentHashMap:

1. ConcurrentHashMap doesn't allow null keys or values
2. ConcurrentHashMap is thread-safe without locking the entire map
3. ConcurrentHashMap has better performance in multithreaded environments

## Exception Handling

### Types of Exceptions

1. **Checked Exceptions:** Must be caught or declared (e.g., IOException)
2. **Unchecked Exceptions (Runtime):** Not required to be caught (e.g., NullPointerException)
3. **Errors:** Typically unrecoverable (e.g., OutOfMemoryError)

### Try-Catch-Finally

```
import java.io.*;

public class ExceptionExample {
    public static void main(String[] args) {
        FileReader file = null;
        
        try {
            file = new FileReader("file.txt");
            int content;
            while ((content = file.read()) != -1) {
                System.out.print((char) content);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IO error: " + e.getMessage());
        } finally {
            try {
                if (file != null) {
                    file.close();
                }
            } catch (IOException e) {
                System.out.println("Error closing file: " + e.getMessage());
            }
        }
    }
}
```

### Try-with-Resources (Java 7+)

```
import java.io.*;

public class TryWithResourcesExample {
    public static void main(String[] args) {
        // Resources are automatically closed after the try block
        try (FileReader file = new FileReader("file.txt")) {
            int content;
            while ((content = file.read()) != -1) {
                System.out.print((char) content);
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
```

### Custom Exceptions

```
class InsufficientFundsException extends Exception {
    private double amount;
    
    public InsufficientFundsException(double amount) {
        super("Insufficient funds: trying to withdraw " + amount);
        this.amount = amount;
    }
    
    public double getAmount() {
        return amount;
    }
}

class BankAccount {
    private double balance;
    
    public void withdraw(double amount) throws InsufficientFundsException {
        if (amount > balance) {
            throw new InsufficientFundsException(amount);
        }
        balance -= amount;
    }
}
```

## Multithreading

### Creating Threads

**Method 1: Extending Thread class**

```
class MyThread extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getId() + ": " + i);
        }
    }
}

// Usage
MyThread thread = new MyThread();
thread.start(); // Important: call start(), not run()
```

**Method 2: Implementing Runnable interface (preferred)**

```
class MyRunnable implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getId() + ": " + i);
        }
    }
}

// Usage
Thread thread = new Thread(new MyRunnable());
thread.start();
```

### Thread Synchronization

```
class Counter {
    private int count = 0;
    
    // Synchronized method - only one thread can access at a time
    public synchronized void increment() {
        count++;
    }
    
    public int getCount() {
        return count;
    }
}

// Usage
Counter counter = new Counter();
Thread t1 = new Thread(() -> {
    for (int i = 0; i < 1000; i++) {
        counter.increment();
    }
});

Thread t2 = new Thread(() -> {
    for (int i = 0; i < 1000; i++) {
        counter.increment();
    }
});

t1.start();
t2.start();

// Wait for both threads to finish
t1.join();
t2.join();

System.out.println("Final count: " + counter.getCount()); // 2000
```

### Thread States

1. NEW: Thread is created but not started
2. RUNNABLE: Thread is executing or ready to execute
3. BLOCKED: Thread is waiting for a monitor lock
4. WAITING: Thread is waiting indefinitely for another thread
5. TIMED_WAITING: Thread is waiting for another thread for a specified time
6. TERMINATED: Thread has completed execution

Alright! Here’s a **fully rewritten and expanded** version of the **Java Collections Framework**, with **detailed
explanations, methods, and runnable examples** for each major collection type used in junior Java interviews.

---

# **📌 Java Collections Framework (JCF)**

The **Java Collections Framework** provides a set of classes and interfaces to **store, manipulate, and process** data
efficiently.

---

## **🚀 Collections Framework Hierarchy**

```
Collection (Interface)
├── List (Interface)
│   ├── ArrayList
│   ├── LinkedList
│   └── Vector
│       └── Stack
├── Set (Interface)
│   ├── HashSet
│   ├── LinkedHashSet
│   └── TreeSet
└── Queue (Interface)
    ├── PriorityQueue
    └── Deque (Interface)
        ├── ArrayDeque
        └── LinkedList
```

💡 **Note:** `Map<K, V>` is **not a Collection**, but it is part of the Collections Framework.

```
Map (Interface)
├── HashMap
├── LinkedHashMap
├── TreeMap
└── ConcurrentHashMap
```

---

# **1️⃣ List Interface (Ordered, Duplicates Allowed)**

A `List` is an **ordered collection** that allows duplicate elements.

## **✅ ArrayList (Fast Access, Slow Insertions/Deletions)**

- Uses a **dynamic array** internally.
- **Fast random access (O(1))**, but **slow insertions/deletions (O(n))**.
- Good when you need **frequent reads but rare modifications**.

### **ArrayList Example**

```java
import java.util.ArrayList;
import java.util.List;

public class ArrayListExample {
    public static void main(String[] args) {
        List<String> fruits = new ArrayList<>();

        // Add elements
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Cherry");

        // Access elements
        System.out.println("First fruit: " + fruits.get(0)); // Apple

        // Iterate
        for (String fruit : fruits) {
            System.out.println(fruit);
        }

        // Remove element
        fruits.remove("Banana");
        System.out.println("After removing Banana: " + fruits);
    }
}
```

---

## **✅ LinkedList (Fast Insertions/Deletions, Slow Access)**

- Uses a **doubly linked list** internally.
- **Fast insertions & deletions (O(1))**, but **slow random access (O(n))**.
- Good when you need **frequent modifications but rare lookups**.

### **LinkedList Example**

```java
import java.util.LinkedList;
import java.util.List;

public class LinkedListExample {
    public static void main(String[] args) {
        List<String> names = new LinkedList<>();

        // Add elements
        names.add("Alice");
        names.add("Bob");

        // Special LinkedList methods
        ((LinkedList<String>) names).addFirst("Zara");
        ((LinkedList<String>) names).addLast("Mike");

        // Access elements
        System.out.println("First name: " + ((LinkedList<String>) names).getFirst()); // Zara
        System.out.println("Last name: " + ((LinkedList<String>) names).getLast());   // Mike

        System.out.println("All names: " + names);
    }
}
```

---

## **✅ Vector (Thread-Safe ArrayList)**

- Similar to `ArrayList`, but **synchronized** (thread-safe).
- Slower than `ArrayList` due to synchronization.

### **Vector Example**

```java
import java.util.Vector;

public class VectorExample {
    public static void main(String[] args) {
        Vector<Integer> numbers = new Vector<>();
        numbers.add(10);
        numbers.add(20);
        numbers.add(30);

        System.out.println("Vector elements: " + numbers);
        numbers.remove(1);
        System.out.println("After removal: " + numbers);
    }
}
```

---

## **✅ Stack (LIFO - Last In, First Out)**

- A subclass of `Vector` that follows **LIFO (Last-In-First-Out)**.

### **Stack Example**

```java
import java.util.Stack;

public class StackExample {
    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();

        stack.push("A");
        stack.push("B");
        stack.push("C");

        System.out.println("Top of stack: " + stack.peek()); // C
        System.out.println("Popped element: " + stack.pop()); // C
        System.out.println("Remaining stack: " + stack);
    }
}
```

---

# **2️⃣ Set Interface (No Duplicates, Unordered)**

A `Set` is an **unordered collection** that **does not allow duplicate elements**.

## **✅ HashSet (Fastest, No Order)**

- **Uses hashing** (HashMap internally).
- **O(1) for add, remove, contains**.
- **Does not maintain order**.

### **HashSet Example**

```java
import java.util.HashSet;
import java.util.Set;

public class HashSetExample {
    public static void main(String[] args) {
        Set<String> colors = new HashSet<>();
        colors.add("Red");
        colors.add("Blue");
        colors.add("Green");
        colors.add("Red"); // Duplicate, won't be added

        System.out.println(colors);
    }
}
```

---

## **✅ LinkedHashSet (Maintains Insertion Order)**

- Same as `HashSet`, but **maintains insertion order**.

```java
import java.util.LinkedHashSet;
import java.util.Set;

public class LinkedHashSetExample {
    public static void main(String[] args) {
        Set<String> colors = new LinkedHashSet<>();
        colors.add("Red");
        colors.add("Blue");
        colors.add("Green");

        System.out.println(colors); // Output: [Red, Blue, Green]
    }
}
```

---

## **✅ TreeSet (Sorted, Slowest)**

- **Keeps elements sorted** (O(log n) operations).
- **No null values allowed**.

```java
import java.util.TreeSet;
import java.util.Set;

public class TreeSetExample {
    public static void main(String[] args) {
        Set<Integer> numbers = new TreeSet<>();
        numbers.add(5);
        numbers.add(2);
        numbers.add(8);

        System.out.println(numbers); // Output: [2, 5, 8]
    }
}
```

---

# **3️⃣ Map Interface (Key-Value Pairs)**

A `Map<K, V>` stores **key-value pairs** (unique keys, duplicate values allowed).

## **✅ HashMap (Fastest, No Order)**

```java
import java.util.HashMap;
import java.util.Map;

public class HashMapExample {
    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();
        map.put(1, "Apple");
        map.put(2, "Banana");
        map.put(3, "Cherry");

        System.out.println(map.get(2)); // Banana
        System.out.println(map.keySet()); // [1, 2, 3]
    }
}
```

---

## **✅ LinkedHashMap (Maintains Insertion Order)**

```java
import java.util.LinkedHashMap;
import java.util.Map;

public class LinkedHashMapExample {
    public static void main(String[] args) {
        Map<Integer, String> map = new LinkedHashMap<>();
        map.put(1, "Apple");
        map.put(2, "Banana");

        System.out.println(map); // {1=Apple, 2=Banana}
    }
}
```

---

## **✅ TreeMap (Sorted by Key)**

```java
import java.util.TreeMap;
import java.util.Map;

public class TreeMapExample {
    public static void main(String[] args) {
        Map<Integer, String> map = new TreeMap<>();
        map.put(3, "Cherry");
        map.put(1, "Apple");
        map.put(2, "Banana");

        System.out.println(map); // Sorted: {1=Apple, 2=Banana, 3=Cherry}
    }
}
```

---

## **🔥 Conclusion**

| Collection     | Best For                        |
|----------------|---------------------------------|
| **ArrayList**  | Fast access, dynamic array      |
| **LinkedList** | Fast insert/delete, slow access |
| **HashSet**    | Fast unique elements            |
| **HashMap**    | Fast key-value storage          |

## Algorithms

### Sorting Algorithms

**Bubble Sort:** Simple sorting algorithm that repeatedly steps through the list, compares adjacent elements, and swaps
them if they're in the wrong order.

```
void bubbleSort(int[] arr) {
    int n = arr.length;
    for (int i = 0; i < n - 1; i++) {
        for (int j = 0; j < n - i - 1; j++) {
            if (arr[j] > arr[j + 1]) {
                // Swap arr[j] and arr[j+1]
                int temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
            }
        }
    }
}
```

Time Complexity: O(n²) in worst and average cases

### Search Algorithms

**Linear Search:** Sequentially checks each element until it finds the target value.

```
int linearSearch(int[] arr, int target) {
    for (int i = 0; i < arr.length; i++) {
        if (arr[i] == target) {
            return i; // Return index of found element
        }
    }
    return -1; // Not found
}
```

Time Complexity: O(n)

**Binary Search:** Works on sorted arrays by repeatedly dividing the search interval in half.

```
int binarySearch(int[] arr, int target) {
    int left = 0;
    int right = arr.length - 1;
    
    while (left <= right) {
        int mid = left + (right - left) / 2;
        
        // Check if target is at mid
        if (arr[mid] == target) {
            return mid;
        }
        
        // If target is greater, ignore left half
        if (arr[mid] < target) {
            left = mid + 1;
        }
        // If target is smaller, ignore right half
        else {
            right = mid - 1;
        }
    }
    
    return -1; // Not found
}
```

Time Complexity: O(log n)

## Design Patterns

### Creational Patterns

**Singleton Pattern:** Ensures a class has only one instance and provides a global point of access to it.

```
public class Singleton {
    // Private static instance
    private static Singleton instance;
    
    // Private constructor to prevent instantiation
    private Singleton() { }
    
    // Public method to get the instance
    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
    
    // Other methods
    public void showMessage() {
        System.out.println("Hello from Singleton!");
    }
}
```

**Thread-safe Singleton with Double-Checked Locking:**

```
public class ThreadSafeSingleton {
    private static volatile ThreadSafeSingleton instance;
    
    private ThreadSafeSingleton() { }
    
    public static ThreadSafeSingleton getInstance() {
        if (instance == null) {
            synchronized (ThreadSafeSingleton.class) {
                if (instance == null) {
                    instance = new ThreadSafeSingleton();
                }
            }
        }
        return instance;
    }
}
```

**Factory Pattern:** Creates objects without specifying the exact class to create.

```
// Product interface
interface Shape {
    void draw();
}

// Concrete products
class Rectangle implements Shape {
    @Override
    public void draw() {
        System.out.println("Drawing Rectangle");
    }
}

class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("Drawing Circle");
    }
}

// Factory
class ShapeFactory {
    public Shape createShape(String shapeType) {
        if (shapeType == null) {
            return null;
        }
        if (shapeType.equalsIgnoreCase("RECTANGLE")) {
            return new Rectangle();
        } else if (shapeType.equalsIgnoreCase("CIRCLE")) {
            return new Circle();
        }
        return null;
    }
}
```

### Structural Patterns

**Adapter Pattern:** Allows incompatible interfaces to work together.

```
// Target interface
interface AdvancedMediaPlayer {
    void playFile(String filename);
}

// Adaptee
class VlcPlayer {
    public void playVlc(String filename) {
        System.out.println("Playing vlc file: " + filename);
    }
}

// Adapter
class MediaAdapter implements AdvancedMediaPlayer {
    private VlcPlayer vlcPlayer;
    
    public MediaAdapter() {
        this.vlcPlayer = new VlcPlayer();
    }
    
    @Override
    public void playFile(String filename) {
        if (filename.endsWith(".vlc")) {
            vlcPlayer.playVlc(filename);
        } else {
            System.out.println("Invalid media format");
        }
    }
}

// Client
class AudioPlayer {
    private MediaAdapter mediaAdapter;
    
    public AudioPlayer() {
        this.mediaAdapter = new MediaAdapter();
    }
    
    public void play(String audioType, String filename) {
        if (audioType.equalsIgnoreCase("mp3")) {
            System.out.println("Playing mp3 file: " + filename);
        } else if (audioType.equalsIgnoreCase("vlc")) {
            mediaAdapter.playFile(filename);
        } else {
            System.out.println("Invalid media type");
        }
    }
}
```

### Behavioral Patterns

**Observer Pattern:** Defines a one-to-many dependency where when one object changes state, all its dependents are
notified.

```
import java.util.ArrayList;
import java.util.List;

// Observer interface
interface Observer {
    void update(String message);
}

// Concrete Observer
class User implements Observer {
    private String name;
    
    public User(String name) {
        this.name = name;
    }
    
    @Override
    public void update(String message) {
        System.out.println(name + " received message: " + message);
    }
}

// Subject
class MessagePublisher {
    private List<Observer> observers = new ArrayList<>();
    
    public void attach(Observer observer) {
        observers.add(observer);
    }
    
    public void detach(Observer observer) {
        observers.remove(observer);
    }
    
    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }
    
    public void publishMessage(String message) {
        System.out.println("Publishing message: " + message);
        notifyObservers(message);
    }
}
```

## SQL

### Basic SQL Commands

**DDL (Data Definition Language):**

- CREATE: Creates a new table, database, or other objects
- ALTER: Modifies existing database objects
- DROP: Deletes tables, indexes, or databases
- TRUNCATE: Removes all data from a table

**DML (Data Manipulation Language):**

- INSERT: Adds new rows to a table
- UPDATE: Modifies existing data
- DELETE: Removes rows from a table

**DQL (Data Query Language):**

- SELECT: Retrieves data from tables

**TCL (Transaction Control Language):**

- COMMIT: Saves transactions
- ROLLBACK: Undoes transactions
- SAVEPOINT: Sets a savepoint for later rollback

**DCL (Data Control Language):**

- GRANT: Gives privileges to users
- REVOKE: Takes away privileges

### Example Database Operations

**Creating a Table:**

```sql
CREATE TABLE employees (
    id INT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    department VARCHAR(50),
    salary DECIMAL(10, 2)
);
```

**Inserting Data:**

```sql
INSERT INTO employees (id, name, department, salary)
VALUES (1, 'John Doe', 'IT', 60000);
```

**Updating Data:**

```sql
UPDATE employees
SET salary = 65000
WHERE id = 1;
```

**Deleting Data:**

```sql
DELETE FROM employees
WHERE id = 1;
```

### SQL Joins

**INNER JOIN:** Returns records with matching values in both tables

```sql
SELECT e.name, d.department_name
FROM employees e
INNER JOIN departments d ON e.department_id = d.id;
```

**LEFT JOIN:** Returns all records from the left table and matched records from the right table

```sql
SELECT e.name, d.department_name
FROM employees e
LEFT JOIN departments d ON e.department_id = d.id;
```

**RIGHT JOIN:** Returns all records from the right table and matched records from the left table

```sql
SELECT e.name, d.department_name
FROM employees e
RIGHT JOIN departments d ON e.department_id = d.id;
```

**FULL JOIN:** Returns all records when there's a match in either left or right table

```sql
SELECT e.name, d.department_name
FROM employees e
FULL JOIN departments d ON e.department_id = d.id;
```

### Common SQL Queries

**Query 1: Second Highest Salary**

```sql
SELECT MAX(salary)
FROM employees
WHERE salary < (SELECT MAX(salary) FROM employees);
```

Alternative approach:

```sql
SELECT salary
FROM employees
ORDER BY salary DESC
LIMIT 1 OFFSET 1;
```

**Query 2: Salary Greater Than a Given Limit**

```sql
SELECT name, salary
FROM employees
WHERE salary > 50000;
```

**Query 3: Joining Date Less Than Given Date**

```sql
SELECT name, hire_date
FROM employees
WHERE hire_date < '2020-01-01';
```

## Unix/Linux Commands

### Basic Commands

**File Navigation:**

- `pwd`: Print Working Directory - shows current directory
- `ls`: Lists files and directories
- `cd`: Change Directory - navigate to different directories
- `mkdir`: Make Directory - create a new directory
- `rmdir`: Remove Directory - delete an empty directory
- `rm`: Remove - delete files or directories

**File Operations:**

- `touch`: Create empty file
- `cp`: Copy - duplicate files or directories
- `mv`: Move - move or rename files or directories
- `cat`: Concatenate - display file content
- `grep`: Search for patterns in files

**Process Management:**

- `ps`: Process Status - list current processes
- `top`: Real-time display of system processes
- `kill`: Terminate a process
- `bg`: Run a job in the background
- `fg`: Bring a job to the foreground

**Shell Scripting Basics:**

```bash
#!/bin/bash
# This is a simple shell script

# Variables
name="John"
echo "Hello, $name!"

# Conditionals
if [ "$name" == "John" ]; then
    echo "Name is John"
else
    echo "Name is not John"
fi

# Loops
for i in 1 2 3 4 5
do
    echo "Number: $i"
done
```

## Spring Framework

### Spring Core Concepts

**Spring Beans:** Objects that are managed by the Spring IoC (Inversion of Control) container

**Bean Scopes:**

- **singleton:** Default scope; one instance per Spring container
- **prototype:** New instance each time it's requested
- **request:** New instance per HTTP request
- **session:** New instance per HTTP session
- **global-session:** New instance per global HTTP session

**Spring Configuration Methods:**

1. **XML-based configuration**
2. **Annotation-based configuration**
3. **Java-based configuration**

### Spring Annotations

**@Component:** Marks a class as a Spring component. Base stereotype annotation

**@Controller, @Service, @Repository:** Specialized versions of @Component for specific layers

**@Autowired:** Automatically injects dependencies

**@Qualifier:** Specifies which bean to inject when multiple beans of the same type exist

**@Configuration:** Indicates a class contains @Bean definitions

**@Bean:** Indicates a method produces a bean to be managed by Spring

```
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    
    @Bean
    public UserService userService() {
        return new UserServiceImpl();
    }
    
    @Bean
    public MessageService messageService() {
        return new EmailService();
    }
}
```

### Spring Boot

Spring Boot is a framework that makes it easier to create stand-alone, production-grade Spring applications with minimal
configuration.

**Key Features:**

- Embedded server (Tomcat, Jetty, Undertow)
- Auto-configuration
- Standalone applications
- Production-ready features (metrics, health checks)

**Basic Spring Boot Application:**

```
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class HelloApplication {
    
    @GetMapping("/hello")
    public String hello() {
        return "Hello, Spring Boot!";
    }
    
    public static void main(String[] args) {
        SpringApplication.run(HelloApplication.class, args);
    }
}
```

## Relational vs. Non-relational Databases

### Relational Databases (RDBMS)

**Characteristics:**

- Based on tables, rows, and columns
- Use SQL for querying
- Enforce schema
- Support ACID transactions
- Good for structured data

**Examples:** MySQL, PostgreSQL, Oracle, SQL Server

### Non-relational Databases (NoSQL)

**Characteristics:**

- Various data models (document, key-value, column-family, graph)
- No fixed schema, flexible
- May sacrifice ACID for performance/scalability
- Horizontally scalable
- Good for unstructured/semi-structured data

**Examples:** MongoDB (document), Redis (key-value), Cassandra (column-family), Neo4j (graph)

### ACID Properties

- **Atomicity:** Transactions are all-or-nothing
- **Consistency:** Transactions maintain database integrity
- **Isolation:** Concurrent transactions don't interfere with each other
- **Durability:** Completed transactions persist even after system failures

## Interview Preparation Tips

### Technical Interview Strategy

1. **Understand the problem completely** before starting to solve it
2. **Think out loud** to share your thought process
3. **Start with a simple approach**, then optimize if needed
4. **Test your solution** with multiple examples, including edge cases
5. **Be honest** if you don't know something, but explain how you would find the answer

### About Amdocs

- Amdocs is a software and services provider focused on the telecommunications industry
- Founded in 1982, headquartered in Missouri, USA
- Specializes in billing systems, customer relationship management, and digital services
- Works with major telecom providers worldwide
- Known for their digital transformation solutions and cloud services

### Projects to Highlight (Based on Your Resume)

#### YugTalk AAC Project

- Emphasize your leadership role in research and development
- Discuss your implementation of bilingual support and Flutter/Dart skills
- Mention the successful testing with pediatric patients and healthcare professionals

#### Notice Board Task Management System

- Highlight your full-stack development skills with Spring Boot and React
- Discuss the implementation of RESTful API and custom UI components
- Mention how you used modern web development techniques

#### Asset Acquisition and Monitoring System

- Discuss the desktop application development with Spring Boot and JavaFX
- Emphasize equipment tracking and real-time data validation features
- Mention the reporting capabilities you implemented

### Questions to Ask the Interviewer

1. "What technologies and frameworks does your team currently use?"
2. "What would a typical day look like for someone in this role?"
3. "What are the biggest challenges your team is currently facing?"
4. "How does Amdocs promote learning and professional growth?"
5. "What does success look like in this position in the first 3-6 months?"

