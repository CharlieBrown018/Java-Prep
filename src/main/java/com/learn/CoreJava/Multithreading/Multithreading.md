# **🚀 Java Multithreading (Basics & Intermediate)**

Multithreading allows Java programs to run **multiple tasks at the same time**, improving performance and
responsiveness.

---

## **1️⃣ What is Multithreading?**

- **Thread** = A lightweight process that runs independently.
- **Multithreading** = Running **multiple threads** at the same time.
- Java uses **Thread Class** or **Runnable Interface** to create threads.

---

## **2️⃣ Creating Threads (Thread Class & Runnable Interface)**

📌 **Two ways to create threads in Java:**

1. **Extending `Thread` class**
2. **Implementing `Runnable` interface** (Recommended ✅)

---

### **Method 1: Extending `Thread` Class**

📌 **File: `ThreadBasicExample.java`**

```java
package com.learn.Multithreading;

class MyThread extends Thread {
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println("Thread running: " + i);
            try {
                Thread.sleep(500); // Pauses for 500ms
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class ThreadBasicExample {
    public static void main(String[] args) {
        MyThread thread = new MyThread();
        thread.start(); // Starts a new thread
    }
}
```

### **Output:**

```
Thread running: 1
Thread running: 2
Thread running: 3
Thread running: 4
Thread running: 5
```

📝 **Key Takeaways:**

- Override `run()` to define thread behavior.
- Use `start()` to **run the thread** (DO NOT call `run()` directly!).

---

### **Method 2: Implementing `Runnable` Interface (Best Practice ✅)**

📌 **File: `RunnableBasicExample.java`**

```java
package com.learn.Multithreading;

class MyRunnable implements Runnable {
    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println("Runnable running: " + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

public class RunnableBasicExample {
    public static void main(String[] args) {
        Thread thread = new Thread(new MyRunnable());
        thread.start(); // Starts the thread
    }
}
```

📝 **Key Takeaways:**

- Implements `Runnable` ✅ (recommended for better flexibility).
- `Thread` takes a `Runnable` object.

---

## **3️⃣ Thread Lifecycle**

Threads go through **5 states**:

1. **New** → Created but not started.
2. **Runnable** → Ready to run.
3. **Running** → Running actively.
4. **Blocked/Waiting** → Paused, waiting for another thread.
5. **Terminated** → Finished execution.

---

## **4️⃣ Thread Synchronization (Preventing Race Conditions)**

- **Problem:** When multiple threads modify shared data **at the same time**, **race conditions** occur.
- **Solution:** Use **synchronized** blocks.

📌 **File: `SynchronizationIntermExample.java`**

```java
package com.learn.Multithreading;

class Counter {
    private int count = 0;

    public synchronized void increment() { // synchronized keyword prevents data corruption
        count++;
    }

    public int getCount() {
        return count;
    }
}

public class SynchronizationIntermExample {
    public static void main(String[] args) {
        Counter counter = new Counter();

        Thread t1 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) counter.increment();
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < 1000; i++) counter.increment();
        });

        t1.start();
        t2.start();

        try {
            t1.join(); // Wait for t1 to finish
            t2.join(); // Wait for t2 to finish
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Final Count: " + counter.getCount());
    }
}
```

### **Expected Output (Without Synchronization, count may be wrong!):**

```
Final Count: 2000
```

📝 **Key Takeaways:**

- `synchronized` prevents **data corruption** in multi-threaded environments.
- Use `.join()` to ensure threads finish before proceeding.

---

## **5️⃣ Thread Pool (Efficient Thread Management)**

- Creating multiple threads manually is inefficient.
- **ThreadPool** helps manage reusable threads.

📌 **File: `ThreadPoolIntermExample.java`**

```java
package com.learn.Multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class WorkerThread implements Runnable {
    private final int taskNumber;

    public WorkerThread(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    public void run() {
        System.out.println("Executing Task " + taskNumber + " on " + Thread.currentThread().getName());
    }
}

public class ThreadPoolIntermExample {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(3); // 3 worker threads

        for (int i = 1; i <= 5; i++) {
            executor.execute(new WorkerThread(i));
        }

        executor.shutdown(); // Stops accepting new tasks
    }
}
```

### **Output (Threads reused efficiently)**:

```
Executing Task 1 on pool-1-thread-1
Executing Task 2 on pool-1-thread-2
Executing Task 3 on pool-1-thread-3
Executing Task 4 on pool-1-thread-1
Executing Task 5 on pool-1-thread-2
```

📝 **Key Takeaways:**

- **ThreadPool** efficiently reuses worker threads.
- Use `Executors.newFixedThreadPool(n)` to create a **pool of `n` threads**.

---

## **🚀 Summary**

| Concept                    | Key Takeaways                                                      |
|----------------------------|--------------------------------------------------------------------|
| **Thread Creation**        | Use `Thread` class or `Runnable` interface (Runnable is preferred) |
| **Thread States**          | New → Runnable → Running → Blocked → Terminated                    |
| **Thread Synchronization** | Use `synchronized` to avoid race conditions                        |
| **Thread Pools**           | Use `Executors.newFixedThreadPool(n)` for efficient threading      |

---

✅ **You now understand Java Multithreading!** 🎉  
🔜 Next: **Exception Handling** – Let me know when you're ready! 🚀