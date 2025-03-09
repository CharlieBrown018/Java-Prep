Great! Now let’s move on to **Spring Boot**.

We’ll follow the same structure:  
✅ **Concepts** → ✅ **Code Examples** → ✅ **Results** → ✅ **Q&A**

---

# **Spring Boot Overview**

**Spring Boot** is a framework that simplifies Java-based web applications. It is built on **Spring Framework** but
eliminates the need for excessive configuration.

## **1. Why Spring Boot?**

✅ **Main Features:**

- **Auto Configuration** → No need for manual `XML` setup.
- **Embedded Servers** → Runs without needing an external Tomcat server.
- **Microservices Ready** → Perfect for scalable applications.
- **Spring Boot Starter Packs** → Ready-to-use dependencies.

---

## **2. Setting Up a Spring Boot Project**

### **🔹 Using Spring Initializr (Easiest Way)**

Go to [Spring Initializr](https://start.spring.io/) and select:

- **Project** → Maven
- **Language** → Java
- **Spring Boot Version** → Latest stable
- **Dependencies** → **Spring Web**
- **Packaging** → JAR
- **Java Version** → 17+
- Click **Generate**, then extract the ZIP file.

### **🔹 Alternative: Manual Setup (Maven)**

If you want to create a Spring Boot project manually, create a `pom.xml` and add:

```xml

<dependencies>
    <!-- Spring Boot Starter for Web Applications -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    <!-- Spring Boot Starter for Testing -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>
```

---

## **3. Spring Boot Core Components**

Spring Boot apps have three main layers:
| **Layer** | **Description** |
|-----------|---------------|
| **Controller** | Handles HTTP requests (REST API). |
| **Service** | Contains business logic. |
| **Repository** | Handles database operations. |

---

## **4. Creating a Simple Spring Boot App**

### **Step 1: Main Application**

```java
package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
```

✅ **This starts the Spring Boot application.**

### **Step 2: Creating a REST Controller**

```java
package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class HelloController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello, Spring Boot!";
    }
}
```

✅ **When we visit `http://localhost:8080/api/hello`, it returns:**

```
Hello, Spring Boot!
```

---

## **5. Dependency Injection in Spring Boot**

### **🔹 What is Dependency Injection?**

✅ **Instead of manually creating objects, Spring injects them automatically.**  
Example **Without DI**:

```java
public class Car {
    private Engine engine = new Engine(); // Manual object creation
}
```

Example **With DI (Spring Boot)**:

```java

@Component
public class Engine {
}

@Component
public class Car {
    private final Engine engine;

    @Autowired
    public Car(Engine engine) {
        this.engine = engine;  // Automatically injected
    }
}
```

---

## **6. Connecting Spring Boot to a Database (Spring Data JPA)**

### **🔹 Adding Dependencies**

In `pom.xml`, add:

```xml

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
<dependency>
<groupId>org.h2database</groupId>
<artifactId>h2</artifactId>
<scope>runtime</scope>
</dependency>
```

✅ **This enables Spring Boot to connect to a database.**

### **🔹 Configuring `application.properties`**

```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
```

✅ **This sets up an in-memory database using H2.**

### **🔹 Creating an Entity (Database Table)**

```java
package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String department;

    // Getters and Setters
}
```

✅ **This automatically maps the `Employee` class to a database table.**

### **🔹 Creating a Repository (Database Operations)**

```java
package com.example.demo.repository;

import com.example.demo.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
}
```

✅ **This allows us to perform CRUD operations on `Employee` data.**

### **🔹 Creating a Service Layer**

```java
package com.example.demo.service;

import com.example.demo.model.Employee;
import com.example.demo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository repository;

    public List<Employee> getAllEmployees() {
        return repository.findAll();
    }
}
```

### **🔹 Exposing the API**

```java
package com.example.demo.controller;

import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }
}
```

✅ **Now, visiting `http://localhost:8080/employees` shows all employees.**

---

# **Spring Boot Interview Questions & Answers**

### ✅ **Basic Questions**

#### 1️⃣ What is Spring Boot?

✅ Spring Boot is a **framework for building Java web applications** with minimal configuration.

#### 2️⃣ How is Spring Boot different from Spring Framework?

| **Feature**   | **Spring Framework**               | Spring Boot               |
|---------------|------------------------------------|---------------------------|
| Configuration | Requires XML or Java-based setup   | Auto-configured           |
| Web Server    | Needs manual setup (Tomcat, Jetty) | Embedded Tomcat/Jetty     |
| Dependencies  | Manually managed                   | Uses Spring Boot Starters |

#### 3️⃣ What is the role of `@SpringBootApplication`?

✅  
It combines three annotations:

- `@Configuration` → Marks the class as a config file.
- `@EnableAutoConfiguration` → Enables auto-config.
- `@ComponentScan` → Scans for components automatically.

#### 4️⃣ What is `@RestController`?

✅ It is a combination of:

- `@Controller` → Marks a class as a web controller.
- `@ResponseBody` → Returns JSON responses.

#### 5️⃣ How do you handle exceptions in Spring Boot?

✅ Using `@ControllerAdvice`:

```java

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
```

---

# ✅ **Next Steps**

📌 Read through this and **run the examples yourself**. Once you're comfortable, we’ll move to **Unix/Linux! 🚀**