# **🚀 Java Exception Handling (Basics & Intermediate)**

Exception handling is **how Java handles runtime errors gracefully** without crashing the program.

---

## **1️⃣ What is Exception Handling?**

📌 **Exception** = An **unexpected event** that disrupts program execution.  
📌 **Exception Handling** = Using `try-catch-finally` to handle errors safely.

---

## **2️⃣ Types of Exceptions**

| **Exception Type**      | **Example**                                              | **Checked/Unchecked?** |
|-------------------------|----------------------------------------------------------|------------------------|
| **Checked Exception**   | `IOException`, `SQLException`                            | Checked ✅              |
| **Unchecked Exception** | `NullPointerException`, `ArrayIndexOutOfBoundsException` | Unchecked ❌            |

📌 **Checked Exceptions** must be **handled with `try-catch` or `throws`**, while **Unchecked Exceptions** are
programming errors.

---

## **3️⃣ Handling Exceptions with `try-catch`**

📌 **File: `TryCatchBasicExample.java`**

```java
package com.learn.ExceptionHandling;

public class TryCatchBasicExample {
    public static void main(String[] args) {
        try {
            int result = 10 / 0; // ❌ This will throw ArithmeticException
            System.out.println(result);
        } catch (ArithmeticException e) {
            System.out.println("Error: Cannot divide by zero!");
        }
        System.out.println("Program continues...");
    }
}
```

### **Output**

```
Error: Cannot divide by zero!
Program continues...
```

📝 **Key Takeaways:**

- **`try` block** contains risky code.
- **`catch` block** handles exceptions.
- The program **does not crash** and continues.

---

## **4️⃣ Using `finally` Block**

📌 **File: `FinallyBasicExample.java`**

```java
package com.learn.ExceptionHandling;

public class FinallyBasicExample {
    public static void main(String[] args) {
        try {
            System.out.println("Trying...");
            int result = 10 / 0;
        } catch (ArithmeticException e) {
            System.out.println("Caught Exception: " + e);
        } finally {
            System.out.println("Finally block always executes!");
        }
    }
}
```

### **Output**

```
Trying...
Caught Exception: java.lang.ArithmeticException: / by zero
Finally block always executes!
```

📝 **Key Takeaways:**

- `finally` **always** runs, even if an exception occurs.
- Useful for **closing resources** (like files, databases).

---

## **5️⃣ Throwing Exceptions with `throw`**

📌 **File: `ThrowIntermExample.java`**

```java
package com.learn.ExceptionHandling;

public class ThrowIntermExample {
    public static void validateAge(int age) {
        if (age < 18) {
            throw new IllegalArgumentException("Age must be 18+");
        }
        System.out.println("Access granted!");
    }

    public static void main(String[] args) {
        validateAge(16);
    }
}
```

### **Output**

```
Exception in thread "main" java.lang.IllegalArgumentException: Age must be 18+
```

📝 **Key Takeaways:**

- `throw` manually generates exceptions.
- Stops execution if not handled.

---

## **6️⃣ Propagating Exceptions with `throws`**

📌 **File: `ThrowsIntermExample.java`**

```java
package com.learn.ExceptionHandling;

import java.io.*;

public class ThrowsIntermExample {
    public static void readFile() throws IOException {
        FileReader file = new FileReader("nonexistent.txt"); // File does not exist
        file.read();
    }

    public static void main(String[] args) {
        try {
            readFile();
        } catch (IOException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }
}
```

### **Output**

```
File not found: nonexistent.txt (No such file or directory)
```

You're absolutely right! The three **`final`**, **`finally`**, and **`finalize`** keywords are commonly confused but are
important in Java. While `finally` belongs in Exception Handling, `final` and `finalize` relate to memory management and
object lifecycle. Let’s include them now:

---

## **7️⃣ `final` vs. `finally` vs. `finalize`**

| Keyword          | Used For                                                  | Example                                          | Explanation                                                                                 |
|------------------|-----------------------------------------------------------|--------------------------------------------------|---------------------------------------------------------------------------------------------|
| **`final`**      | **Prevention** (modifying variables, methods, or classes) | `final int x = 10;`                              | Prevents changes to variables, methods (override prevention), and classes (no subclassing). |
| **`finally`**    | **Exception Handling** (cleanup code)                     | `finally { System.out.println("Always runs"); }` | Ensures a block of code always runs, even if an exception occurs.                           |
| **`finalize()`** | **Garbage Collection** (object cleanup)                   | `protected void finalize() {}`                   | Called by the garbage collector before an object is removed. (⚠️ Deprecated in modern Java) |

---

## **8️⃣ `final` Keyword (Prevention Mechanism)**

### **Final Variable (Value Cannot Change)**

📌 **File: `FinalVariableExample.java`**

```java
package com.learn.Keywords;

public class FinalVariableExample {
    public static void main(String[] args) {
        final int MAX_VALUE = 100;
        MAX_VALUE = 200; // ❌ Error: cannot assign a new value
    }
}
```

📝 **Takeaway:** `final` variables act as **constants** and cannot be reassigned.

---

### **Final Method (Cannot Be Overridden)**

📌 **File: `FinalMethodExample.java`**

```java
package com.learn.Keywords;

class Parent {
    final void show() {
        System.out.println("Final method in Parent.");
    }
}

class Child extends Parent {
    void show() { // ❌ Error: cannot override final method
        System.out.println("Child method.");
    }
}
```

📝 **Takeaway:** A `final` method **cannot be overridden** by subclasses.

---

### **Final Class (Cannot Be Inherited)**

📌 **File: `FinalClassExample.java`**

```java
package com.learn.Keywords;

final class Vehicle {
    void drive() {
        System.out.println("Driving...");
    }
}

// ❌ Error: Cannot inherit from final class
class Car extends Vehicle {
} 
```

📝 **Takeaway:** A `final` class **cannot be extended**.

---

## **9️⃣ `finally` Block (Always Executes)**

📌 **File: `FinallyExample.java`**

```java
package com.learn.ExceptionHandling;

public class FinallyExample {
    public static void main(String[] args) {
        try {
            System.out.println("Inside try block");
            int num = 5 / 0; // ❌ Throws ArithmeticException
        } catch (ArithmeticException e) {
            System.out.println("Caught an exception: " + e);
        } finally {
            System.out.println("Finally block always executes!");
        }
    }
}
```

### **Output:**

```
Inside try block
Caught an exception: java.lang.ArithmeticException: / by zero
Finally block always executes!
```

📝 **Takeaway:** `finally` runs **even if an exception occurs or not**.

---

## **🔟 `finalize()` Method (Deprecated in Modern Java)**

📌 **File: `FinalizeExample.java`**

```java
package com.learn.Keywords;

public class FinalizeExample {
    protected void finalize() {
        System.out.println("Finalize method called before garbage collection.");
    }

    public static void main(String[] args) {
        FinalizeExample obj = new FinalizeExample();
        obj = null; // Mark object for garbage collection
        System.gc(); // Suggests garbage collection (not guaranteed)
    }
}
```

### **Output (May Vary):**

```
Finalize method called before garbage collection.
```

📝 **Takeaway:**

- `finalize()` is **called by the garbage collector** before an object is removed.
- It’s **deprecated in Java 9+** (Use `try-with-resources` instead).

---

## **✅ Summary Table: `final` vs `finally` vs `finalize()`**

| Keyword          | Purpose              | Behavior                                                              |
|------------------|----------------------|-----------------------------------------------------------------------|
| **`final`**      | Prevent modification | Variables → Constant, Methods → No Override, Classes → No Inheritance |
| **`finally`**    | Guarantee execution  | Runs **always**, used in exception handling                           |
| **`finalize()`** | Object cleanup       | Called before garbage collection (⚠️ Deprecated)                      |

## **🚀 Summary**

| Concept         | Key Takeaways                         |
|-----------------|---------------------------------------|
| **`try-catch`** | Handles exceptions gracefully ✅       |
| **`finally`**   | Always executes, useful for cleanup ✅ |
| **`throw`**     | Manually throws exceptions ✅          |
| **`throws`**    | Declares possible exceptions ✅        |

---

📝 **Key Takeaways:**

- `throws` **declares** exceptions a method might throw.
- **Caller must handle it** with `try-catch`.

---
🎯 **Now we’ve covered all important Exception Handling and `final`-related concepts!**  
🔜 **Next Up:** Java Keywords. Let me know when you're ready! 🚀
---