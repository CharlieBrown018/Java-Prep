---

# **Java Collections Framework (JCF)**

## **What is Java Collections Framework?**

The **Java Collections Framework (JCF)** is a set of **classes and interfaces** that provides ready-made data structures
like **lists, sets, queues, and maps**. It makes data manipulation easy and efficient.

✅ **Why Use Collections?**  
✔ Eliminates the need for arrays' fixed size.  
✔ Provides built-in sorting, searching, and iteration.  
✔ Improves memory management and performance.

---

## **Hierarchy of Java Collections**

```
Collection (Interface)  
│  
├── List (Interface)  → Ordered, allows duplicates  
│   ├── ArrayList  
│   ├── LinkedList  
│   ├── Vector  
│   └── Stack  
│  
├── Set (Interface)  → Unordered, no duplicates  
│   ├── HashSet  
│   ├── LinkedHashSet  
│   └── TreeSet  
│  
└── Queue (Interface)  → FIFO, used in processing  
    ├── PriorityQueue  
    └── Deque (Interface)  
        ├── ArrayDeque  
        └── LinkedList  
```

### **Non-Collection Interface** (Map)

- `Map<K, V>` stores **key-value** pairs (not part of Collection but still in JCF).
- Implementations: `HashMap`, `TreeMap`, `LinkedHashMap`.

---

## **List Interface (Ordered, Allows Duplicates)**

📌 **Best for: Ordered data, random access, and iteration.**

### **1️⃣ ArrayList (Fast random access, slow insertion/removal)**

- Implements a **resizable array** (like Python lists).
- **Fast read (O(1))**, but **slow insert/delete (O(n))** for large lists.

📌 **File: `ArrayListBasicExample.java`**

```java
package com.learn.Collections;

import java.util.ArrayList;

public class ArrayListBasicExample {
    public static void main(String[] args) {
        ArrayList<String> fruits = new ArrayList<>();

        // Adding elements
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Cherry");

        // Accessing elements
        System.out.println("First Fruit: " + fruits.get(0));

        // Iterating using for-each loop
        for (String fruit : fruits) {
            System.out.println(fruit);
        }

        // Removing an element
        fruits.remove("Banana");
        System.out.println("After removal: " + fruits);
    }
}
```

### **Output:**

```
First Fruit: Apple
Apple
Banana
Cherry
After removal: [Apple, Cherry]
```

---

### **2️⃣ LinkedList (Fast insert/delete, slow random access)**

- Implements **doubly linked list**.
- **Fast insert/delete (O(1))**, but **slow read (O(n))**.

📌 **File: `LinkedListBasicExample.java`**

```java
package com.learn.Collections;

import java.util.LinkedList;

public class LinkedListBasicExample {
    public static void main(String[] args) {
        LinkedList<Integer> numbers = new LinkedList<>();

        // Adding elements
        numbers.add(10);
        numbers.add(20);
        numbers.add(30);

        // Removing first element
        numbers.removeFirst();

        // Iterating using lambda
        numbers.forEach(num -> System.out.println(num));
    }
}
```

### **Output:**

```
20
30
```

---

## **Set Interface (Unordered, No Duplicates)**

📌 **Best for: Unique elements, fast lookup.**

### **3️⃣ HashSet (Unordered, Unique Elements)**

- **No duplicates**, **unordered**.
- **O(1) average time complexity** for add, remove, contains.

📌 **File: `HashSetBasicExample.java`**

```java
package com.learn.Collections;

import java.util.HashSet;

public class HashSetBasicExample {
    public static void main(String[] args) {
        HashSet<String> colors = new HashSet<>();

        // Adding elements
        colors.add("Red");
        colors.add("Blue");
        colors.add("Red"); // Duplicate, won't be added

        // Printing the set
        System.out.println(colors);
    }
}
```

### **Output:**

```
[Red, Blue]  // Unordered and unique
```

---

## **Map Interface (Key-Value Pairs, Fast Lookups)**

📌 **Best for: Storing key-value pairs, fast lookup.**

### **4️⃣ HashMap (Unordered, Key-Value Storage)**

- **Fast lookups (O(1))**, allows **null keys**.
- Not synchronized (not thread-safe).

📌 **File: `HashMapBasicExample.java`**

```java
package com.learn.Collections;

import java.util.HashMap;

public class HashMapBasicExample {
    public static void main(String[] args) {
        HashMap<String, Integer> ages = new HashMap<>();

        // Adding key-value pairs
        ages.put("Alice", 25);
        ages.put("Bob", 30);
        ages.put("Charlie", 28);

        // Accessing a value
        System.out.println("Alice's age: " + ages.get("Alice"));

        // Iterating through keys
        for (String name : ages.keySet()) {
            System.out.println(name + " is " + ages.get(name) + " years old.");
        }
    }
}
```

### **Output:**

```
Alice's age: 25
Alice is 25 years old.
Bob is 30 years old.
Charlie is 28 years old.
```

---

## **When to Use What?**

| Data Structure | Order? | Duplicates?         | Best For                                    |
|----------------|--------|---------------------|---------------------------------------------|
| **ArrayList**  | ✅ Yes  | ✅ Yes               | Fast random access (O(1)), small lists      |
| **LinkedList** | ✅ Yes  | ✅ Yes               | Fast insertion/deletion (O(1)), large lists |
| **HashSet**    | ❌ No   | ❌ No                | Unique elements, fast lookup                |
| **HashMap**    | ❌ No   | ✅ Yes (keys unique) | Key-value storage, fast access              |

---

# **Advanced Java Collections Concepts**

Now that you understand basic collections (`ArrayList`, `LinkedList`, `HashSet`, `HashMap`), let's cover **sorting,
queues, and advanced maps**.

---

## **1️⃣ Sorting Collections (`Collections.sort()`)**

- Java provides the `Collections.sort()` method to **sort lists**.
- By default, it sorts in **ascending order**.
- We can provide a **custom comparator** for custom sorting.

📌 **File: `SortingListIntermExample.java`**

```java
package com.learn.Collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SortingListIntermExample {
    public static void main(String[] args) {
        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(50);
        numbers.add(10);
        numbers.add(40);
        numbers.add(20);

        // Sorting in ascending order (default)
        Collections.sort(numbers);
        System.out.println("Ascending: " + numbers);

        // Sorting in descending order using Comparator
        numbers.sort(Comparator.reverseOrder());
        System.out.println("Descending: " + numbers);
    }
}
```

### **Output:**

```
Ascending: [10, 20, 40, 50]
Descending: [50, 40, 20, 10]
```

📝 **Key Takeaway:**

- Use `Collections.sort()` for **ascending order**.
- Use `Comparator.reverseOrder()` for **descending order**.

---

## **2️⃣ PriorityQueue (Min Heap & Max Heap)**

📌 **Best for: Implementing a queue with priorities.**

- A **PriorityQueue** sorts elements based on **priority** (default: ascending order).
- **Min Heap** = smallest element first (default).
- **Max Heap** = largest element first (using a comparator).

📌 **File: `PriorityQueueIntermExample.java`**

```java
package com.learn.Collections;

import java.util.PriorityQueue;
import java.util.Collections;

public class PriorityQueueIntermExample {
    public static void main(String[] args) {
        // Min Heap (default)
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        minHeap.add(30);
        minHeap.add(10);
        minHeap.add(20);

        System.out.println("Min Heap (ascending order):");
        while (!minHeap.isEmpty()) {
            System.out.println(minHeap.poll()); // Removes and prints smallest element
        }

        // Max Heap (Descending order)
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        maxHeap.add(30);
        maxHeap.add(10);
        maxHeap.add(20);

        System.out.println("Max Heap (descending order):");
        while (!maxHeap.isEmpty()) {
            System.out.println(maxHeap.poll()); // Removes and prints largest element
        }
    }
}
```

### **Output:**

```
Min Heap (ascending order):
10
20
30

Max Heap (descending order):
30
20
10
```

📝 **Key Takeaway:**

- `PriorityQueue<>` defaults to **Min Heap** (smallest element first).
- `PriorityQueue<>(Collections.reverseOrder())` creates a **Max Heap**.

---

## **3️⃣ TreeMap (Sorted Key-Value Storage)**

📌 **Best for: Maintaining keys in **sorted order**.**

- Unlike `HashMap` (unordered), `TreeMap` **automatically sorts keys**.

📌 **File: `TreeMapIntermExample.java`**

```java
package com.learn.Collections;

import java.util.TreeMap;

public class TreeMapIntermExample {
    public static void main(String[] args) {
        TreeMap<Integer, String> students = new TreeMap<>();

        // Adding key-value pairs
        students.put(103, "Alice");
        students.put(101, "Bob");
        students.put(102, "Charlie");

        System.out.println("TreeMap (sorted by key): " + students);
    }
}
```

### **Output:**

```
TreeMap (sorted by key): {101=Bob, 102=Charlie, 103=Alice}
```

📝 **Key Takeaway:**

- `TreeMap` sorts by **keys automatically** in ascending order.
- Use `TreeMap` when **sorted ordering of keys** is needed.

---

## **4️⃣ LinkedHashMap (Maintains Insertion Order)**

📌 **Best for: Keeping order of insertion while using a `Map`**.

- Unlike `HashMap`, `LinkedHashMap` maintains **insertion order**.

📌 **File: `LinkedHashMapIntermExample.java`**

```java
package com.learn.Collections;

import java.util.LinkedHashMap;

public class LinkedHashMapIntermExample {
    public static void main(String[] args) {
        LinkedHashMap<Integer, String> students = new LinkedHashMap<>();

        // Adding key-value pairs
        students.put(103, "Alice");
        students.put(101, "Bob");
        students.put(102, "Charlie");

        System.out.println("LinkedHashMap (insertion order preserved): " + students);
    }
}
```

### **Output:**

```
LinkedHashMap (insertion order preserved): {103=Alice, 101=Bob, 102=Charlie}
```

📝 **Key Takeaway:**

- Use `LinkedHashMap` when **order of insertion matters**.

---

# **🔍 When to Use What?**

| Data Structure    | Sorted?             | Preserves Insertion Order? | Best For                        |
|-------------------|---------------------|----------------------------|---------------------------------|
| **ArrayList**     | ❌ No                | ✅ Yes                      | Fast random access              |
| **LinkedList**    | ❌ No                | ✅ Yes                      | Fast insert/delete              |
| **HashSet**       | ❌ No                | ❌ No                       | Unique values, fast lookup      |
| **TreeSet**       | ✅ Yes               | ❌ No                       | Unique sorted values            |
| **HashMap**       | ❌ No                | ❌ No                       | Key-value pairs, fast access    |
| **TreeMap**       | ✅ Yes               | ❌ No                       | Sorted key-value pairs          |
| **LinkedHashMap** | ❌ No                | ✅ Yes                      | Ordered key-value pairs         |
| **PriorityQueue** | ✅ Yes (by priority) | ❌ No                       | Processing elements by priority |

---

# **🚀 What's Next?**

✅ You’ve now mastered Java Collections! Now, let’s proceed to **Multithreading** next.
