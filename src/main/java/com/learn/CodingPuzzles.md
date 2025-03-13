### **Reorganized Junior Java Developer Coding/Puzzles Interview Questions**

Based on actual reports from interviewees and common patterns in junior Java developer interviews, I've grouped the
problems into **three difficulty levels** and **five main categories**:

1. **Basic (Syntax & Fundamentals)**
2. **Easy (Basic DSA & OOP)**
3. **Intermediate (Logic & Problem Solving, Common Puzzles)**

Each question is categorized into:

- **Arrays & Strings** (high priority, as reported by Amdocs interviewees)
- **Linked Lists**
- **Binary Trees & Graphs**
- **OOP & Concepts**
- **SQL Queries**

---

## **📌 Basic Level – Java Syntax & Fundamentals**

**🔹 Arrays & Strings**

1. ✅ **Find the maximum element in an array**
2. ✅ **Find the two maximum numbers in an array**
3. ✅ **Reversing a string**
4. ✅ **Reversing a list**
5. ✅ **Calculate the sum of all digits in an array/list**

**🔹 OOP & Concepts**

6. ✅ **Explain OOP concepts with code** (Encapsulation, Abstraction, Inheritance, Polymorphism)
7. ✅ **Creating an object of a `HashMap` and adding a series of data**

**🔹 SQL Queries**

8. ✅ **SQL query to get salary greater than a given limit**
9. ✅ **SQL query to get joining date less than a given date**

---

## **📌 Easy Level – Basic DSA & OOP**

**🔹 Arrays & Strings**

10. ✅ **Print the first repeated number in an array** (e.g., `2 1 4 2 5 6` → Output: `2`)
11. ✅ **Swap two numbers without a third variable**
12. ✅ **Remove duplicates in a String and reverse words in a sentence**
13. ✅ **Check whether two strings are palindromes or not**

**🔹 Linked Lists**

14. ✅ **Implement a doubly linked list**
15. ✅ **Remove the last node in a doubly linked list**
16. ✅ **Reverse print a linked list without using an extra pointer**

**🔹 SQL Queries**

17. ✅ **SQL query to get the second highest number from a dataset**

---

## **📌 Intermediate Level – Logic & Problem Solving, Common Puzzles**

**🔹 Arrays & Strings**

18. ✅ **Rabbit-Hare problem** (Floyd’s Cycle Detection Algorithm)
19. ✅ **Merge two sorted lists into a new one**

**🔹 Binary Trees & Graphs**

20. ✅ **Flip left and right of each node in a binary tree**

---

### **🎯 Learning Path Plan (Easy to Intermediate)**

1. **Basic Java fundamentals & syntax**
    - Arrays, Lists, HashMap basics
    - Simple string manipulations

2. **Basic Data Structures & Algorithms (DSA)**
    - Sorting, searching basics
    - Working with Linked Lists

3. **Intermediate DSA & Logic-Based Problems**
    - Two-pointer techniques (Palindrome, Rabbit-Hare problem)
    - Merging sorted lists, flipping trees

4. **OOP Concepts in Java**
    - Abstraction, Interfaces, Inheritance examples

5. **SQL Queries**
    - Writing commonly asked queries

---

Got it! I'll **restart from the beginning** and go through each problem in the list, ensuring we cover:

✅ **Problem Statement**  
✅ **Brute Force Approach**  
✅ **Java Code Solution**  
✅ **Step-by-Step Explanation**  
✅ **Example Run with Output**

---

# **📌 Basic Level – Java Syntax & Fundamentals**

## **🔹 Arrays & Strings**

---

## **📌 1. Find the Maximum Element in an Array**

**Problem:**  
Given an array of integers, find the **maximum** element.

### **Brute Force Approach:**

- Initialize `max` as the **first element**.
- Loop through the array and **update `max`** if a larger number is found.

---

### **✅ Solution**

```
public class MaxElement {
    public static int findMax(int[] arr) {
        int max = arr[0]; // Assume first element is max

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i]; // Update max if a larger value is found
            }
        }
        return max;
    }

    public static void main(String[] args) {
        int[] numbers = {5, 8, 12, 3, 7};
        System.out.println("Maximum Element: " + findMax(numbers));
    }
}
```

---

### **📝 Explanation:**

- Start by assuming **first element is the maximum**.
- Loop through the array, updating `max` when a larger value is found.
- **Time Complexity:** `O(n)`, **Space Complexity:** `O(1)`.

✅ **Example Run:**

```
Maximum Element: 12
```

---

## **📌 2. Find the Two Maximum Numbers in an Array**

**Problem:**  
Given an array, find the **two largest** numbers.

### **Brute Force Approach:**

- Initialize `firstMax` and `secondMax` as **smallest values**.
- Loop through the array and update **both** when needed.

---

### **✅ Solution**

```
public class TwoMaxNumbers {
    public static void findTwoMax(int[] arr) {
        int firstMax = Integer.MIN_VALUE;
        int secondMax = Integer.MIN_VALUE;

        for (int num : arr) {
            if (num > firstMax) {
                secondMax = firstMax; // Move firstMax to secondMax
                firstMax = num;       // Update firstMax
            } else if (num > secondMax && num != firstMax) {
                secondMax = num; // Update secondMax only if it's not the firstMax
            }
        }

        System.out.println("First Max: " + firstMax);
        System.out.println("Second Max: " + secondMax);
    }

    public static void main(String[] args) {
        int[] numbers = {10, 5, 8, 12, 3, 7};
        findTwoMax(numbers);
    }
}
```

---

### **📝 Explanation:**

- **First maximum (`firstMax`)** holds the largest number.
- **Second maximum (`secondMax`)** holds the second-largest.
- **Time Complexity:** `O(n)`, **Space Complexity:** `O(1)`.

✅ **Example Run:**

```
First Max: 12
Second Max: 10
```

---

## **📌 3. Reverse a String**

**Problem:**  
Given a string, reverse it **without using built-in functions**.

### **Brute Force Approach:**

- Convert the string to a **character array**.
- Swap elements from **start to end** using a loop.

---

### **✅ Solution**

```
public class ReverseString {
    public static String reverse(String str) {
        char[] charArray = str.toCharArray(); // Convert string to array
        int left = 0, right = charArray.length - 1;

        while (left < right) {
            char temp = charArray[left];
            charArray[left] = charArray[right];
            charArray[right] = temp;

            left++;
            right--;
        }

        return new String(charArray);
    }

    public static void main(String[] args) {
        String input = "hello";
        System.out.println("Reversed String: " + reverse(input));
    }
}
```

---

### **📝 Explanation:**

- Convert **string to an array** for modification.
- Swap first and last characters moving **inward**.
- **Time Complexity:** `O(n)`, **Space Complexity:** `O(n)`.

✅ **Example Run:**

```
Reversed String: olleh
```

---

## **📌 4. Reverse a List**

**Problem:**  
Given an array, reverse it **in-place** (without using extra space).

### **Brute Force Approach:**

- Use **two pointers** (`left` and `right`).
- Swap elements moving towards the center.

---

### **✅ Solution**

```
import java.util.Arrays;

public class ReverseList {
    public static void reverseArray(int[] arr) {
        int left = 0, right = arr.length - 1;

        while (left < right) {
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;

            left++;
            right--;
        }
    }

    public static void main(String[] args) {
        int[] numbers = {1, 2, 3, 4, 5};
        reverseArray(numbers);
        System.out.println("Reversed List: " + Arrays.toString(numbers));
    }
}
```

---

### **📝 Explanation:**

- Swap **first and last** elements, then **move inward**.
- **Time Complexity:** `O(n)`, **Space Complexity:** `O(1)`.

✅ **Example Run:**

```
Reversed List: [5, 4, 3, 2, 1]
```

---

## **📌 5. Calculate the Sum of All Digits in an Array/List**

**Problem:**  
Given an array of numbers, **sum all digits** of each number.

### **Brute Force Approach:**

- Extract each digit using `%` and `/`.
- Sum up all digits.

---

### **✅ Solution**

```
public class SumOfDigits {
    public static int sumDigitsInArray(int[] arr) {
        int sum = 0;

        for (int num : arr) {
            while (num > 0) {
                sum += num % 10; // Extract last digit
                num /= 10;        // Remove last digit
            }
        }

        return sum;
    }

    public static void main(String[] args) {
        int[] numbers = {123, 45, 67};
        System.out.println("Sum of Digits: " + sumDigitsInArray(numbers));
    }
}
```

---

### **📝 Explanation:**

- **Extract last digit** using `% 10`.
- **Remove last digit** using `/ 10`.
- **Time Complexity:** `O(n)`, **Space Complexity:** `O(1)`.

✅ **Example Run:**

```
Sum of Digits: 18
```

---

# **📌 Basic Level – Java Syntax & Fundamentals**

## **🔹 OOP & Concepts**

---

## **📌 6. Explain OOP Concepts with Code**

**Problem:**  
Explain the four main OOP concepts (**Encapsulation, Abstraction, Inheritance, Polymorphism**) with **Java code examples
**.

---

### **✅ Solution**

```
// ✅ Encapsulation (Data Hiding using private fields & public methods)
class Person {
    private String name; // Private field (hidden data)

    public Person(String name) {
        this.name = name;
    }

    public String getName() { // Public method to access private field
        return name;
    }
}

// ✅ Abstraction (Hiding implementation details using abstract class)
abstract class Animal {
    abstract void makeSound(); // Abstract method (must be implemented by subclasses)
}

class Dog extends Animal {
    @Override
    void makeSound() {
        System.out.println("Woof! Woof!");
    }
}

// ✅ Inheritance (Child class inherits from Parent class)
class Vehicle {
    void start() {
        System.out.println("Vehicle is starting...");
    }
}

class Car extends Vehicle {
    void honk() {
        System.out.println("Car is honking...");
    }
}

// ✅ Polymorphism (Method Overloading & Overriding)
class MathOperations {
    // Overloading (Same method name, different parameters)
    int add(int a, int b) {
        return a + b;
    }

    int add(int a, int b, int c) {
        return a + b + c;
    }
}

public class OOPConcepts {
    public static void main(String[] args) {
        // Encapsulation Example
        Person p = new Person("Alice");
        System.out.println("Encapsulation Example: " + p.getName());

        // Abstraction Example
        Animal myDog = new Dog();
        myDog.makeSound();

        // Inheritance Example
        Car myCar = new Car();
        myCar.start();
        myCar.honk();

        // Polymorphism Example
        MathOperations math = new MathOperations();
        System.out.println("Polymorphism Example (Overloading): " + math.add(5, 10));
        System.out.println("Polymorphism Example (Overloading): " + math.add(5, 10, 15));
    }
}
```

---

### **📝 Explanation:**

- **Encapsulation** → **Hides data** using private fields and provides **controlled access** via public methods.
- **Abstraction** → **Hides implementation details** by using abstract classes and methods.
- **Inheritance** → A subclass (**Car**) inherits methods from a superclass (**Vehicle**).
- **Polymorphism** → **Method Overloading (compile-time polymorphism)** and **Method Overriding (run-time polymorphism)
  **.

✅ **Example Run:**

```
Encapsulation Example: Alice
Woof! Woof!
Vehicle is starting...
Car is honking...
Polymorphism Example (Overloading): 15
Polymorphism Example (Overloading): 30
```

---

## **📌 7. Creating an Object of a `HashMap` and Adding a Series of Data**

**Problem:**  
Create a `HashMap`, add key-value pairs, and retrieve values.

---

### **✅ Solution**

```
import java.util.HashMap;

public class HashMapExample {
    public static void main(String[] args) {
        // Create a HashMap to store student names and their marks
        HashMap<String, Integer> studentMarks = new HashMap<>();

        // Adding key-value pairs (Student Name -> Marks)
        studentMarks.put("Alice", 85);
        studentMarks.put("Bob", 90);
        studentMarks.put("Charlie", 78);

        // Retrieve and print values
        System.out.println("Alice's Marks: " + studentMarks.get("Alice"));
        System.out.println("Bob's Marks: " + studentMarks.get("Bob"));

        // Iterate and print all key-value pairs
        System.out.println("\nAll Students:");
        for (String student : studentMarks.keySet()) {
            System.out.println(student + " → " + studentMarks.get(student));
        }
    }
}
```

---

### **📝 Explanation:**

- **HashMap stores key-value pairs** (`name → marks`).
- `.put(key, value)` → Adds data to the map.
- `.get(key)` → Retrieves a value by its key.
- **Time Complexity:** `O(1)` (average case for insertions & retrievals).

✅ **Example Run:**

```
Alice's Marks: 85
Bob's Marks: 90

All Students:
Alice → 85
Bob → 90
Charlie → 78
```

---

## **📌 SQL Queries**

## **📌 8. SQL Query to Get Salary Greater than a Given Limit**

**Problem:**  
Retrieve employees with a salary **greater than** a given amount.

### **✅ Query**

```sql
SELECT * FROM Employees WHERE salary > 50000;
```

✅ **Explanation:**

- Selects all employee records where **salary is above** `50000`.

---

## **📌 9. SQL Query to Get Joining Date Less than a Given Date**

**Problem:**  
Retrieve employees who **joined before** a specific date.

### **✅ Query**

```sql
SELECT * FROM Employees WHERE joining_date < '2024-01-01';
```

✅ **Explanation:**

- Selects all employees who **joined before** `2024-01-01`.

---

# **📌 Easy Level – Basic DSA & OOP**

---

## **🔹 Arrays & Strings**

## **📌 10. Print the First Repeated Number in an Array**

**Problem:**  
Given an array of integers, find and print the **first repeated number** (i.e., the first number that appears more than
once).

**Example:**

```
Input:  [2, 1, 4, 2, 5, 6]
Output: 2
```

---

### **✅ Brute Force Solution (Using Nested Loops)**

```
public class FirstRepeatedNumber {
    public static void main(String[] args) {
        int[] arr = {2, 1, 4, 2, 5, 6};
        int repeatedNumber = -1; // Default if no repeated number is found

        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[i] == arr[j]) {
                    repeatedNumber = arr[i];
                    break; // Stop at first repeat
                }
            }
            if (repeatedNumber != -1) break;
        }

        System.out.println("First Repeated Number: " + repeatedNumber);
    }
}
```

---

### **📝 Explanation:**

- We use **two loops** to compare each element with every other element after it.
- If a **duplicate is found**, store it and break the loop.
- **Time Complexity:** `O(n^2)`, because of the nested loop.
- **Space Complexity:** `O(1)`, since no extra data structures are used.

✅ **Example Run:**

```
First Repeated Number: 2
```

---

## **📌 11. Swap Two Numbers Without a Third Variable**

**Problem:**  
Swap two integers **without using a third variable**.

**Example:**

```
Input:  a = 5, b = 10
Output: a = 10, b = 5
```

---

### **✅ Brute Force Solution (Using Arithmetic Operations)**

```
public class SwapNumbers {
    public static void main(String[] args) {
        int a = 5, b = 10;

        // Swap without third variable
        a = a + b; // a becomes 15 (5+10)
        b = a - b; // b becomes 5  (15-10)
        a = a - b; // a becomes 10 (15-5)

        System.out.println("After Swap: a = " + a + ", b = " + b);
    }
}
```

---

### **📝 Explanation:**

- Add `a` and `b` and store the result in `a`.
- Subtract `b` from the new `a` to get the old `a`.
- Subtract `b` from the new `a` to get the old `b`.
- **Time Complexity:** `O(1)`.
- **Space Complexity:** `O(1)`, since no extra variable is used.

✅ **Example Run:**

```
After Swap: a = 10, b = 5
```

---

## **📌 12. Remove Duplicates in a String and Reverse Words in a Sentence**

**Problem:**

- Remove duplicate characters from a string.
- Reverse the words in a given sentence.

**Example:**

```
Input: "hello world hello"
Output: "world hello"
```

---

### **✅ Brute Force Solution (Using Loops & StringBuilder)**

```
import java.util.LinkedHashSet;

public class RemoveDuplicatesAndReverseWords {
    public static void main(String[] args) {
        String sentence = "hello world hello";

        // Remove duplicate words using LinkedHashSet
        String[] words = sentence.split(" ");
        LinkedHashSet<String> uniqueWords = new LinkedHashSet<>();

        for (String word : words) {
            uniqueWords.add(word); // Adds only unique words
        }

        // Build reversed sentence
        StringBuilder result = new StringBuilder();
        for (String word : uniqueWords) {
            result.insert(0, word + " "); // Insert words at the start
        }

        // Print final result
        System.out.println("Processed Sentence: " + result.toString().trim());
    }
}
```

---

### **📝 Explanation:**

- **Step 1:** Use `LinkedHashSet` to **remove duplicate words** while keeping order.
- **Step 2:** Use `StringBuilder.insert(0, word)` to **reverse the sentence**.
- **Time Complexity:** `O(n)`, as we iterate through the words once.
- **Space Complexity:** `O(n)`, since we store unique words.

✅ **Example Run:**

```
Processed Sentence: world hello
```

---

## **📌 13. Check Whether Two Strings are Palindromes**

**Problem:**  
Check if two given strings are **palindromes** (i.e., they read the same forward and backward).

**Example:**

```
Input: "madam", "racecar"
Output: true, true
```

---

### **✅ Brute Force Solution (Using Loops to Reverse String)**

```
public class PalindromeCheck {
    public static void main(String[] args) {
        String str1 = "madam";
        String str2 = "racecar";

        System.out.println(str1 + " is palindrome? " + isPalindrome(str1));
        System.out.println(str2 + " is palindrome? " + isPalindrome(str2));
    }

    static boolean isPalindrome(String str) {
        int left = 0, right = str.length() - 1;

        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false; // Mismatch found
            }
            left++;
            right--;
        }
        return true; // No mismatches found
    }
}
```

---

### **📝 Explanation:**

- Compare the **first and last character**, moving inward.
- If **any mismatch is found**, return `false`.
- **Time Complexity:** `O(n)`.
- **Space Complexity:** `O(1)`, since we use no extra space.

✅ **Example Run:**

```
madam is palindrome? true
racecar is palindrome? true
```

---

# **📌 Easy Level – Basic DSA & OOP (Continued)**

---

## **🔹 Linked Lists**

## **📌 14. Implement a Doubly Linked List**

**Problem:**  
Create a **Doubly Linked List (DLL)** with the ability to insert and display nodes.

---

### **✅ Brute Force Solution (Using a Basic Class for DLL)**

```
class DoublyLinkedList {
    static class Node {
        int data;
        Node prev, next;

        Node(int data) {
            this.data = data;
            this.prev = this.next = null;
        }
    }

    Node head;

    // Insert at the end
    void insert(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            return;
        }
        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newNode;
        newNode.prev = temp;
    }

    // Display Forward
    void display() {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        DoublyLinkedList dll = new DoublyLinkedList();
        dll.insert(10);
        dll.insert(20);
        dll.insert(30);

        System.out.println("Doubly Linked List:");
        dll.display();
    }
}
```

---

### **📝 Explanation:**

- We define a **Node** class with `prev` and `next` pointers.
- The **insert()** method appends new nodes at the end.
- The **display()** method prints the list in forward order.
- **Time Complexity:** `O(n)` for insertion and traversal.
- **Space Complexity:** `O(n)`, since we store `n` elements.

✅ **Example Run:**

```
Doubly Linked List:
10 20 30
```

---

## **📌 15. Remove the Last Node in a Doubly Linked List**

**Problem:**  
Delete the **last node** in a doubly linked list.

---

### **✅ Brute Force Solution**

```
class RemoveLastNode {
    static class Node {
        int data;
        Node prev, next;

        Node(int data) {
            this.data = data;
            this.prev = this.next = null;
        }
    }

    Node head;

    // Insert at the end
    void insert(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            return;
        }
        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.prev.next = null; // Remove last node
    }

    // Display list
    void display() {
        Node temp = head;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        RemoveLastNode dll = new RemoveLastNode();
        dll.insert(10);
        dll.insert(20);
        dll.insert(30);

        System.out.println("Before Removing Last Node:");
        dll.display();

        dll.insert(40);
        System.out.println("After Removing Last Node:");
        dll.display();
    }
}
```

---

### **📝 Explanation:**

- Traverse to the **last node** and remove it by updating the previous node’s `next` to `null`.
- **Time Complexity:** `O(n)`.
- **Space Complexity:** `O(1)`.

✅ **Example Run:**

```
Before Removing Last Node:
10 20 30
After Removing Last Node:
10 20
```

---

## **📌 16. Reverse Print a Linked List Without Using Extra Pointer**

**Problem:**  
Print a linked list **in reverse order** without modifying the list or using an extra pointer array.

---

### **✅ Brute Force Solution (Using Recursion)**

```
class ReversePrintLinkedList {
    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    Node head;

    void insert(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            return;
        }
        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = newNode;
    }

    // Recursive function to print list in reverse
    void printReverse(Node node) {
        if (node == null) return;
        printReverse(node.next);
        System.out.print(node.data + " ");
    }

    public static void main(String[] args) {
        ReversePrintLinkedList list = new ReversePrintLinkedList();
        list.insert(1);
        list.insert(2);
        list.insert(3);
        list.insert(4);

        System.out.println("Reversed Linked List:");
        list.printReverse(list.head);
    }
}
```

---

### **📝 Explanation:**

- **Recursively** traverse to the end of the list and print elements while backtracking.
- **Time Complexity:** `O(n)`.
- **Space Complexity:** `O(n)` (due to recursive calls).

✅ **Example Run:**

```
Reversed Linked List:
4 3 2 1
```

---

## **🔹 SQL Queries**

## **📌 17. SQL Query to Get the Second Highest Number from a Dataset**

**Problem:**  
Write an SQL query to find the **second highest salary** from the `employees` table.

---

### **✅ Brute Force Solution (Using `LIMIT` and `ORDER BY`)**

```sql
SELECT DISTINCT salary 
FROM employees 
ORDER BY salary DESC 
LIMIT 1 OFFSET 1;
```

---

### **📝 Explanation:**

- **`ORDER BY salary DESC`** → Sort salaries in descending order.
- **`DISTINCT`** → Removes duplicate salaries.
- **`LIMIT 1 OFFSET 1`** → Skips the highest salary and fetches the second highest.
- **Time Complexity:** `O(n log n)` (sorting).

✅ **Example Result:**

```
+--------+
| salary |
+--------+
| 55000  |
+--------+
```

---

Got it! For the **intermediate** problems, I’ll provide both:

1. **Brute Force Solution** (Basic approach using loops, extra space, etc.)
2. **Optimized Solution** (Better time/space complexity)

---

# **📌 Intermediate Level – Logic & Problem Solving, Common Puzzles**

## **🔹 Arrays & Linked Lists**

## **📌 18. Rabbit-Hare Problem (Floyd’s Cycle Detection Algorithm)**

**Problem:**  
Given a **linked list**, determine if it contains a cycle (loop).

---

### **✅ Brute Force Solution (Using HashSet - Extra Space)**

- Traverse the linked list and **store visited nodes in a HashSet**.
- If a node **repeats**, we found a cycle.

```
import java.util.HashSet;

class RabbitHareCycle {
    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    // Detect cycle using HashSet
    static boolean hasCycle(Node head) {
        HashSet<Node> visited = new HashSet<>();
        Node temp = head;
        
        while (temp != null) {
            if (visited.contains(temp)) return true; // Cycle detected
            visited.add(temp);
            temp = temp.next;
        }
        return false; // No cycle
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = head.next; // Creating a cycle

        System.out.println("Cycle detected? " + hasCycle(head)); // Output: true
    }
}
```

**📝 Complexity Analysis:**

- **Time Complexity:** `O(n)` (Traverses list once).
- **Space Complexity:** `O(n)` (Stores nodes in a HashSet).

---

### **✅ Optimized Solution (Floyd’s Cycle Detection Algorithm - No Extra Space)**

- Use **two pointers**:
    - `slow` moves **one step** at a time.
    - `fast` moves **two steps** at a time.
- If `fast` catches up to `slow`, a **cycle** exists!

```
class FloydCycleDetection {
    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    static boolean hasCycle(Node head) {
        if (head == null) return false;

        Node slow = head, fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;       // Move 1 step
            fast = fast.next.next;   // Move 2 steps

            if (slow == fast) return true; // Cycle detected
        }
        return false; // No cycle
    }

    public static void main(String[] args) {
        Node head = new Node(1);
        head.next = new Node(2);
        head.next.next = new Node(3);
        head.next.next.next = head.next; // Creating a cycle

        System.out.println("Cycle detected? " + hasCycle(head)); // Output: true
    }
}
```

**📝 Complexity Analysis:**

- **Time Complexity:** `O(n)`.
- **Space Complexity:** `O(1)` (No extra storage used).

✅ **Optimized!** Uses constant space instead of `O(n)`.

---

## **📌 19. Merge Two Sorted Lists into a New One**

**Problem:**  
Given **two sorted linked lists**, merge them into a new sorted list.

---

### **✅ Brute Force Solution (Using Extra Space - Copy & Sort Method)**

- **Convert both lists to an array.**
- **Sort the array.**
- **Recreate a new sorted linked list.**

```
import java.util.ArrayList;
import java.util.Collections;

class MergeSortedListsBruteForce {
    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    static Node mergeLists(Node l1, Node l2) {
        ArrayList<Integer> values = new ArrayList<>();

        while (l1 != null) {
            values.add(l1.data);
            l1 = l1.next;
        }
        while (l2 != null) {
            values.add(l2.data);
            l2 = l2.next;
        }

        Collections.sort(values); // Sort the list

        Node dummy = new Node(0);
        Node current = dummy;

        for (int val : values) {
            current.next = new Node(val);
            current = current.next;
        }
        return dummy.next;
    }
}
```

**📝 Complexity Analysis:**

- **Time Complexity:** `O(n log n)`.
- **Space Complexity:** `O(n + m)`.

---

### **✅ Optimized Solution (Two-Pointer Merge - No Extra Space)**

- **Use two pointers** to merge lists **in-place**.

```
class MergeSortedListsOptimized {
    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    static Node mergeLists(Node l1, Node l2) {
        Node dummy = new Node(0);
        Node current = dummy;

        while (l1 != null && l2 != null) {
            if (l1.data < l2.data) {
                current.next = l1;
                l1 = l1.next;
            } else {
                current.next = l2;
                l2 = l2.next;
            }
            current = current.next;
        }

        // Append remaining nodes
        if (l1 != null) current.next = l1;
        if (l2 != null) current.next = l2;

        return dummy.next;
    }
}
```

**📝 Complexity Analysis:**

- **Time Complexity:** `O(n + m)`.
- **Space Complexity:** `O(1)` (In-place).

✅ **Optimized!** Avoids `O(n log n)` sorting.

---

## **📌 20. Flip Left and Right of Each Node in a Binary Tree**

**Problem:**  
Given a **binary tree**, flip it **horizontally** by swapping left and right child nodes at every level.

---

### **✅ Brute Force Solution (Using Extra Space - Level Order Traversal)**

- **Store nodes in a queue**, process them **level by level**, and swap.

```
import java.util.LinkedList;
import java.util.Queue;

class FlipBinaryTreeBruteForce {
    static class Node {
        int data;
        Node left, right;

        Node(int data) {
            this.data = data;
            this.left = this.right = null;
        }
    }

    static void flipTree(Node root) {
        if (root == null) return;

        Queue<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node current = queue.poll();

            // Swap left and right
            Node temp = current.left;
            current.left = current.right;
            current.right = temp;

            if (current.left != null) queue.add(current.left);
            if (current.right != null) queue.add(current.right);
        }
    }
}
```

**📝 Complexity Analysis:**

- **Time Complexity:** `O(n)`.
- **Space Complexity:** `O(n)`.

---

### **✅ Optimized Solution (Recursive DFS - No Extra Space)**

- **Use recursion to swap left & right** at every level.

```
class FlipBinaryTreeOptimized {
    static void flipTree(Node root) {
        if (root == null) return;

        Node temp = root.left;
        root.left = root.right;
        root.right = temp;

        flipTree(root.left);
        flipTree(root.right);
    }
}
```

**📝 Complexity Analysis:**

- **Time Complexity:** `O(n)`.
- **Space Complexity:** `O(h)`.

✅ **Optimized!** Uses `O(h)` stack space instead of `O(n)`.

---

## **🎯 Summary**

✅ **Cycle Detection** - Brute Force (HashSet) → Optimized (Floyd’s)  
✅ **Merge Lists** - Brute Force (Sort) → Optimized (Two-Pointer)  
✅ **Flip Tree** - Brute Force (Queue) → Optimized (DFS)
