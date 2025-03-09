## **1️⃣ Introduction to System Design**

System design is the process of defining the architecture, components, and interfaces of a system to meet functional and
non-functional requirements.

🔹 **Functional Requirements** → What the system should do (features, APIs).  
🔹 **Non-Functional Requirements (NFRs)** → Performance, scalability, reliability, security.

---

## **2️⃣ Monolithic vs Microservices Architecture**

| Feature         | Monolithic                 | Microservices                      |
|-----------------|----------------------------|------------------------------------|
| Deployment      | Single unit                | Multiple independent services      |
| Scaling         | Difficult                  | Easier (scale individual services) |
| Fault Isolation | Poor (entire system fails) | Good (failure is isolated)         |
| Tech Stack      | Single                     | Can use multiple                   |
| Dev Speed       | Fast initially             | Slower but better long-term        |

### **🛠 When to use?**

✅ Monolithic → Small applications, startups, fast development.  
✅ Microservices → Large, scalable, distributed applications.

---

## **3️⃣ Key Concepts in System Design**

### **📌 Load Balancing**

Distributes traffic across multiple servers to avoid overload.

- **Types**: Round Robin, Least Connections, IP Hashing.
- **Example**: AWS Elastic Load Balancer (ELB), Nginx.

### **📌 Caching**

Speeds up data retrieval by storing frequently used data.

- **Types**: In-Memory (Redis, Memcached), CDN (Cloudflare, Akamai).
- **Example**: Use Redis to cache database queries.

### **📌 Database Scaling**

- **Vertical Scaling (Scale-Up)** → Increase CPU/RAM.
- **Horizontal Scaling (Scale-Out)** → Add more servers.

#### **Sharding vs Replication**

| Feature  | Sharding                        | Replication                      |
|----------|---------------------------------|----------------------------------|
| Concept  | Splitting DB into smaller parts | Copying DB to multiple instances |
| Improves | Write performance               | Read performance                 |
| Example  | User data split by region       | Multiple read replicas           |

### **📌 API Rate Limiting**

- **Prevents abuse** (e.g., brute force attacks, DoS).
- **Methods**: Token Bucket, Leaky Bucket, Fixed Window, Sliding Window.
- **Example**: Limit login attempts to 5 per minute.

---

## **4️⃣ Common System Design Patterns**

### **✅ CQRS (Command Query Responsibility Segregation)**

- **Separates** read and write operations.
- **Use Case**: High-performance systems (e.g., banking, stock trading).

### **✅ Event-Driven Architecture**

- Uses an **Event Bus (Kafka, RabbitMQ)** to handle asynchronous events.
- **Use Case**: Microservices communication.

### **✅ Circuit Breaker**

- **Prevents cascading failures** in microservices.
- **Example**: Netflix Hystrix.

---

## **5️⃣ Designing a Scalable System (Step-by-Step)**

1️⃣ **Understand Requirements** → Functional + Non-Functional.  
2️⃣ **Choose Architecture** → Monolith, Microservices, Serverless.  
3️⃣ **Design API Endpoints** → REST, GraphQL, gRPC.  
4️⃣ **Select Database** → SQL (PostgreSQL, MySQL) or NoSQL (MongoDB, DynamoDB).  
5️⃣ **Plan Caching Strategy** → Redis, CDN.  
6️⃣ **Implement Load Balancing** → Nginx, AWS ELB.  
7️⃣ **Optimize Scalability** → Auto-scaling, DB replication.  
8️⃣ **Ensure Security** → OAuth, JWT, Rate limiting.

---

## **6️⃣ Real-World System Design Examples**

- **URL Shortener (bit.ly)** → Uses hashing, caching, and NoSQL DB.
- **Messaging System (WhatsApp)** → Uses WebSockets, Kafka, and partitioned DB.
- **E-commerce Platform (Amazon)** → Uses microservices, caching, CDN, and load balancing.

---

## **1️⃣ What are Design Patterns?**

Design patterns are reusable solutions to common software design problems.

---

## **2️⃣ Types of Design Patterns**

🔹 **Creational Patterns** → Object creation (Singleton, Factory, Builder).  
🔹 **Structural Patterns** → Organizing objects (Adapter, Decorator, Facade).  
🔹 **Behavioral Patterns** → Object interaction (Observer, Strategy, Command).

---

## **3️⃣ Creational Patterns**

### **✅ Singleton**

Ensures **only one instance** of a class exists.

```java
class Singleton {
    private static Singleton instance;

    private Singleton() {
    } // Private constructor

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}
```

✅ **Use Case**: Database connection, logging service.

### **✅ Factory**

Creates objects **without specifying the exact class**.

```java
interface Shape {
    void draw();
}

class Circle implements Shape {
    public void draw() {
        System.out.println("Drawing Circle");
    }
}

class ShapeFactory {
    public static Shape getShape(String type) {
        if ("circle".equalsIgnoreCase(type)) return new Circle();
        return null;
    }
}
```

✅ **Use Case**: UI components, database drivers.

### **✅ Builder**

Builds complex objects step by step.

```java
class Car {
    private String brand, engine;

    public static class Builder {
        private String brand, engine;

        public Builder setBrand(String brand) {
            this.brand = brand;
            return this;
        }

        public Builder setEngine(String engine) {
            this.engine = engine;
            return this;
        }

        public Car build() {
            return new Car(this);
        }
    }

    private Car(Builder builder) {
        this.brand = builder.brand;
        this.engine = builder.engine;
    }
}
```

✅ **Use Case**: Configuring objects with optional parameters.

---

## **4️⃣ Structural Patterns**

### **✅ Adapter**

Converts **one interface into another**.

```java
interface MediaPlayer {
    void play(String type, String file);
}

class VLCPlayer {
    void playVLC(String file) {
        System.out.println("Playing VLC: " + file);
    }
}

class MediaAdapter implements MediaPlayer {
    private VLCPlayer vlcPlayer = new VLCPlayer();

    public void play(String type, String file) {
        if ("vlc".equalsIgnoreCase(type)) vlcPlayer.playVLC(file);
    }
}
```

✅ **Use Case**: Legacy system integration.

### **✅ Facade**

Simplifies access to complex subsystems.

```java
class Database {
    void connect() {
        System.out.println("Connected to DB");
    }
}

class API {
    void request() {
        System.out.println("API Request Made");
    }
}

class Facade {
    private Database db = new Database();
    private API api = new API();

    void performOperation() {
        db.connect();
        api.request();
    }
}
```

✅ **Use Case**: Simplifying complex subsystems.

---

## **5️⃣ Behavioral Patterns**

### **✅ Observer**

Notifies multiple objects when **a state changes**.

```java
interface Observer {
    void update(String message);
}

class User implements Observer {
    private String name;

    User(String name) {
        this.name = name;
    }

    public void update(String message) {
        System.out.println(name + " received: " + message);
    }
}

class NewsPublisher {
    private List<Observer> observers = new ArrayList<>();

    void subscribe(Observer o) {
        observers.add(o);
    }

    void notifyAll(String msg) {
        for (Observer o : observers) o.update(msg);
    }
}
```

✅ **Use Case**: Notifications, event-driven systems.

### **✅ Strategy**

Encapsulates algorithms and allows **switching strategies dynamically**.

```java
interface PaymentStrategy {
    void pay(int amount);
}

class CreditCardPayment implements PaymentStrategy {
    public void pay(int amount) {
        System.out.println("Paid " + amount + " via Credit Card.");
    }
}

class PayPalPayment implements PaymentStrategy {
    public void pay(int amount) {
        System.out.println("Paid " + amount + " via PayPal.");
    }
}
```

✅ **Use Case**: Payment processing.

---

# **🎯 Conclusion**

✅ **System Design** is about **scalability, performance, and reliability**.  
✅ **Design Patterns** help **write clean, reusable, and maintainable code**.