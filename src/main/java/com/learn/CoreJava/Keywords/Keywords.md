# **Java Keywords** 🏷️

## **What Are Java Keywords?**

Java **keywords** are **reserved words** that have a special meaning in the language and **cannot be used as identifiers
** (e.g., variable names, class names).

💡 **Java has 50+ keywords**, but we’ll focus on the most important ones for junior interviews.

---

## **1️⃣ Categories of Java Keywords**

| **Category**           | **Keywords**                                                                                                     |
|------------------------|------------------------------------------------------------------------------------------------------------------|
| **Data Type Keywords** | `int`, `double`, `char`, `boolean`, `float`, `byte`, `short`, `long`, `void`                                     |
| **Control Flow**       | `if`, `else`, `switch`, `case`, `default`, `for`, `while`, `do`, `break`, `continue`, `return`                   |
| **Class & Object**     | `class`, `interface`, `extends`, `implements`, `new`, `this`, `super`, `instanceof`                              |
| **Exception Handling** | `try`, `catch`, `finally`, `throw`, `throws`                                                                     |
| **Access Modifiers**   | `public`, `private`, `protected`                                                                                 |
| **Object Lifecycle**   | `static`, `final`, `abstract`, `synchronized`, `volatile`, `transient`                                           |
| **Memory Management**  | `new`, `delete` (not in Java!), `null`                                                                           |
| **Multi-threading**    | `synchronized`, `volatile`                                                                                       |
| **Other Keywords**     | `import`, `package`, `strictfp`, `native`, `assert`, `goto` (reserved but unused), `const` (reserved but unused) |

---

## **2️⃣ `this` Keyword (Refers to the Current Object)**

📌 **File: `ThisKeywordExample.java`**

```java
package com.learn.Keywords;

class Car {
    String model;

    Car(String model) {
        this.model = model; // Refers to instance variable
    }

    void display() {
        System.out.println("Car model: " + this.model);
    }
}

public class ThisKeywordExample {
    public static void main(String[] args) {
        Car myCar = new Car("Toyota");
        myCar.display();
    }
}
```

### **Output:**

```
Car model: Toyota
```

📝 **Takeaway:**

- `this` is used to **differentiate instance variables** from method parameters.
- `this()` can also **call a constructor inside another constructor**.

---

## **3️⃣ `super` Keyword (Refers to Parent Class)**

📌 **File: `SuperKeywordExample.java`**

```java
package com.learn.Keywords;

class Animal {
    String name = "Animal";

    void makeSound() {
        System.out.println("Some generic sound...");
    }
}

class Dog extends Animal {
    String name = "Dog";

    void display() {
        System.out.println("Child class name: " + name);
        System.out.println("Parent class name: " + super.name); // Refers to parent class variable
        super.makeSound(); // Calls parent class method
    }
}

public class SuperKeywordExample {
    public static void main(String[] args) {
        Dog myDog = new Dog();
        myDog.display();
    }
}
```

### **Output:**

```
Child class name: Dog
Parent class name: Animal
Some generic sound...
```

📝 **Takeaway:**

- `super.variable` refers to **parent class variables**.
- `super.method()` calls **parent class methods**.
- `super()` calls **parent class constructor**.

---

## **4️⃣ `static` Keyword (Shared Across All Objects)**

📌 **File: `StaticKeywordExample.java`**

```java
package com.learn.Keywords;

class Counter {
    static int count = 0; // Shared variable

    Counter() {
        count++; // Increments shared counter
    }

    void displayCount() {
        System.out.println("Counter: " + count);
    }
}

public class StaticKeywordExample {
    public static void main(String[] args) {
        Counter c1 = new Counter();
        Counter c2 = new Counter();
        Counter c3 = new Counter();

        c3.displayCount(); // Shows the shared count
    }
}
```

### **Output:**

```
Counter: 3
```

📝 **Takeaway:**

- **Static variables** are **shared across all objects** of a class.
- **Static methods** **don’t need an object** to be called (`ClassName.method()`).

---

## **5️⃣ `abstract` Keyword (For Abstract Classes & Methods)**

📌 **File: `AbstractKeywordExample.java`**

```java
package com.learn.Keywords;

abstract class Shape {
    abstract void draw(); // Abstract method (no body)
}

class Circle extends Shape {
    void draw() {
        System.out.println("Drawing a circle...");
    }
}

public class AbstractKeywordExample {
    public static void main(String[] args) {
        Shape myShape = new Circle(); // Upcasting
        myShape.draw();
    }
}
```

### **Output:**

```
Drawing a circle...
```

📝 **Takeaway:**

- **Abstract classes cannot be instantiated**.
- **Abstract methods must be overridden** in subclasses.

---

## **6️⃣ `instanceof` Keyword (Type Checking)**

📌 **File: `InstanceofExample.java`**

```java
package com.learn.Keywords;

class Animal {
}

class Dog extends Animal {
}

public class InstanceofExample {
    public static void main(String[] args) {
        Animal myAnimal = new Dog();

        System.out.println(myAnimal instanceof Dog);   // ✅ true
        System.out.println(myAnimal instanceof Animal); // ✅ true
        System.out.println(myAnimal instanceof String); // ❌ Error
    }
}
```

### **Output:**

```
true
true
```

📝 **Takeaway:**

- `instanceof` checks **if an object belongs to a specific class or subclass**.

---

## **7️⃣ `synchronized` Keyword (Thread Safety)**

📌 **File: `SynchronizedExample.java`**

```java
package com.learn.Keywords;

class Counter {
    private int count = 0;

    public synchronized void increment() {
        count++; // Ensures only one thread can modify `count` at a time
    }

    public int getCount() {
        return count;
    }
}

public class SynchronizedExample {
    public static void main(String[] args) {
        Counter counter = new Counter();
        counter.increment();
        System.out.println("Count: " + counter.getCount());
    }
}
```

### **Output:**

```
Count: 1
```

📝 **Takeaway:**

- `synchronized` ensures **only one thread accesses the method at a time**.

---

# **📝 Java Keywords Q&A**

### **1️⃣ What is the difference between `this` and `super`?**

| Feature          | `this`                                              | `super`                                            |
|------------------|-----------------------------------------------------|----------------------------------------------------|
| Refers To        | Current class                                       | Parent class                                       |
| Used For         | Instance variables, method calls, constructor calls | Parent class variables, methods, constructor calls |
| Constructor Call | `this()`                                            | `super()`                                          |

---

### **2️⃣ What is the difference between `static` and `final`?**

| Feature     | `static`                   | `final`                     |
|-------------|----------------------------|-----------------------------|
| Shared?     | Yes (class level)          | No                          |
| Can Change? | Yes                        | No (constant)               |
| Used With   | Variables, methods, blocks | Variables, methods, classes |

---

### **3️⃣ Can we declare a constructor as `final`?**

❌ **No**, because constructors **cannot be inherited or overridden**.

---

### **4️⃣ What happens if we use `final` with a method?**

- **The method cannot be overridden** in a subclass.
- It can still be inherited and called.

---

### **5️⃣ What is the difference between `abstract` and `interface`?**

| Feature     | `abstract class`                   | `interface`                    |
|-------------|------------------------------------|--------------------------------|
| Methods     | Can have normal & abstract methods | Only abstract methods (Java 7) |
| Inheritance | Single                             | Multiple                       |

---

## **✅ Next Up: Collections!**

Let me know when you're ready! 🚀