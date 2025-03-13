# Understanding the Notice Board Task Management System (JavaFX Version)

## Project Architecture: The Why Behind the Design

The Notice Board Task Management System uses a traditional JavaFX desktop application architecture with a clean
separation of concerns. Let's explore why this architecture was chosen and how each component works together.

### Why a Layered Architecture?

The project implements a layered architecture that provides several important benefits:

1. **Separation of Concerns**: Each layer has a specific responsibility, making the code more organized and
   maintainable.

2. **Modularity**: Components can be modified or replaced independently without affecting the entire system.

3. **Testability**: With clear boundaries between layers, each component can be tested in isolation.

The application follows this layered structure:

```
┌─────────────────────┐
│ Presentation Layer  │  ← JavaFX UI (FXML, Controllers)
└─────────┬───────────┘
          │
          ▼
┌─────────────────────┐
│   Business Layer    │  ← Service Layer (Task Service)
└─────────┬───────────┘
          │
          ▼
┌─────────────────────┐
│     Data Layer      │  ← Data Access Objects (DAO)
└─────────┬───────────┘
          │
          ▼
┌─────────────────────┐
│      Database       │  ← SQLite
└─────────────────────┘
```

### Why JDBC with SQLite?

The project uses JDBC (Java Database Connectivity) with SQLite as the database, rather than an ORM framework like Spring
Data JPA. This decision offers several advantages:

1. **Lightweight**: No need for a heavy framework, making the application start faster and use fewer resources.

2. **Direct Control**: JDBC provides direct control over SQL queries and database operations.

3. **Learning Value**: Working directly with JDBC helps understand the fundamentals of database interaction without
   abstraction layers.

4. **Portability**: SQLite is a file-based database that doesn't require a separate server, making the application easy
   to distribute.

Here's how the database connection is established:

```java
public class DatabaseUtil {
    // Define the database URL for SQLite
    private static final String DB_URL = "jdbc:sqlite:src/main/db/todo.db";

    // Method to establish a connection to the database
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    // Initialize the database and create the task table if it doesn't exist
    public static void initializeDatabase() {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            // Create tasks table
            stmt.execute("""
                    CREATE TABLE IF NOT EXISTS task (
                    id INTEGER PRIMARY KEY AUTOINCREMENT,
                    title TEXT NOT NULL,
                    description TEXT,
                    due_date TEXT,
                    status TEXT DEFAULT 'PENDING',
                    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
                    )
                    """);
        } catch (SQLException e) {
            System.err.println("Error initializing database: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }
}
```

This code establishes a connection to a SQLite database file and ensures the required table exists when the application
starts.

## OOP Principles: Practical Implementation

Let's examine how the project applies the four pillars of object-oriented programming in practical ways.

### Encapsulation: Protecting Data and Implementation

Encapsulation is about bundling data with the methods that operate on that data and restricting direct access. The
`Task` class demonstrates this principle:

```java
public class Task {
    private Integer id;
    private String title;
    private String description;
    private LocalDate dueDate;
    private Status status;
    private LocalDateTime createdAt;

    // Getters and setters with validation
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
        this.title = title.trim();
    }

    // Other getters and setters...
}
```

This encapsulation provides several benefits:

1. **Data Validation**: The `setTitle()` method ensures a task can never have an empty title.

2. **Implementation Hiding**: The internal representation of the task can change without affecting other parts of the
   application.

3. **Controlled Access**: Other classes must use the provided methods to modify task properties.

Without encapsulation, code throughout the application would need to perform validation checks whenever setting a task
title, leading to duplicated logic and potential inconsistencies.

### Inheritance: Building on Existing Functionality

While the project doesn't heavily use inheritance for domain classes, it employs interface inheritance with the DAO
pattern:

```java
// Interface defining contract
public interface TaskDAO {
    void insert(Task task);

    void update(Task task);

    void delete(int id);

    Task findById(int id);

    List<Task> findAll();

    List<Task> findByStatus(Status status);
}

// Implementation inheriting interface methods
public class TaskDAOImpl implements TaskDAO {
    // Implementation of all required methods
}
```

This approach offers several advantages:

1. **Abstraction**: The interface defines what operations are available without specifying how they work.

2. **Multiple Implementations**: You could create different implementations (like an in-memory version for testing) that
   all satisfy the same contract.

3. **Reduced Coupling**: Code that uses the DAO depends on the interface, not the concrete implementation.

If you needed to switch from SQLite to another database or even a completely different storage mechanism, you would only
need to create a new implementation of the `TaskDAO` interface without changing any code that uses it.

### Polymorphism: Same Interface, Different Behaviors

Polymorphism allows objects of different types to be treated through a common interface. This concept is demonstrated
with the `Status` enum:

```java
public enum Status {
    PENDING("Pending"),
    IN_PROGRESS("In Progress"),
    COMPLETED("Completed");

    private final String displayName;

    Status(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
```

The application uses this enum in various contexts:

```java
// In the DAO layer - storage as string
pstmt.setString(4,task.getStatus().

name());

// In the UI layer - display as human-readable text
        statusColumn.

setCellValueFactory(data ->
        new

SimpleStringProperty(data.getValue().

getStatus().

getDisplayName()));
```

This polymorphic approach allows:

1. **Consistent Representation**: Each status value knows how to represent itself both for storage and display.

2. **Type Safety**: The compiler ensures you can only use valid status values.

3. **Centralized Logic**: If you need to change how a status is displayed, you only need to modify the enum.

### Abstraction: Simplifying Complex Systems

Abstraction means hiding implementation details and exposing only what's necessary. The service layer demonstrates this
principle:

```java
public class TaskService {
    private final TaskDAO taskDAO;

    public TaskService() {
        this.taskDAO = new TaskDAOImpl();
    }

    // Business logic with validation
    public void addTask(String title, String description, String dueDate) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Task title cannot be empty.");
        }

        Task task = new Task();
        task.setTitle(title);
        task.setDescription(description);

        // Parse the date if it exists
        if (dueDate != null && !dueDate.isEmpty()) {
            task.setDueDate(LocalDate.parse(dueDate));
        }

        task.setStatus(Status.PENDING);
        task.setCreatedAt(LocalDateTime.now());

        taskDAO.insert(task);
    }

    // Other service methods...
}
```

This abstraction provides several benefits:

1. **Simplified Controller**: The controller only needs to call `addTask()` without worrying about how tasks are
   validated or stored.

2. **Business Logic Centralization**: All task-related business rules are in one place.

3. **Implementation Hiding**: The controller doesn't need to know about the DAO or database details.

The controller can focus on handling UI events and presenting data, delegating business logic to the service layer:

```java

@FXML
private void addTask() {
    String title = titleField.getText().trim();
    String description = descriptionField.getText().trim();
    LocalDate dueDate = dueDatePicker.getValue();

    if (title.isEmpty()) {
        showAlert("Validation Error", "Task title cannot be empty!", Alert.AlertType.ERROR);
        return;
    }

    try {
        // Simple delegation to service layer
        taskService.addTask(title, description, dueDate != null ? dueDate.toString() : null);
        refreshTaskList();
        clearInputFields();
    } catch (Exception e) {
        showAlert("Error", "Failed to add task: " + e.getMessage(), Alert.AlertType.ERROR);
    }
}
```

## The DAO Pattern: Bridging Object and Relational Worlds

The Data Access Object (DAO) pattern is central to this application's design, creating a bridge between the
object-oriented world of Java and the relational world of SQLite.

### Why Use the DAO Pattern?

The DAO pattern offers several significant benefits:

1. **Separation of Concerns**: Database access logic is isolated from business logic.

2. **Abstraction**: The rest of the application doesn't need to know how data is stored or retrieved.

3. **Code Reuse**: Database operations are centralized, preventing duplicate code.

4. **Testability**: You can mock the DAO for testing business logic without a real database.

Let's look at how the pattern is implemented:

```java
// TaskDAO interface defines the contract
public interface TaskDAO {
    void insert(Task task);

    void update(Task task);

    void delete(int id);

    Task findById(int id);

    List<Task> findAll();

    List<Task> findByStatus(Status status);
}

// TaskDAOImpl provides the actual implementation
public class TaskDAOImpl implements TaskDAO {
    @Override
    public void insert(Task task) {
        String sql = """
                INSERT INTO task (title, description, due_date, status, created_at)
                VALUES (?, ?, ?, ?, ?)
                """;

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Set parameters
            pstmt.setString(1, task.getTitle());
            pstmt.setString(2, task.getDescription());
            pstmt.setString(3, task.getDueDate() != null ? task.getDueDate().toString() : null);
            pstmt.setString(4, task.getStatus().name());
            pstmt.setString(5, task.getCreatedAt().toString());

            // Execute the insert
            pstmt.executeUpdate();

            // Get generated ID and set it on the task object
            // ...
        } catch (SQLException e) {
            throw new RuntimeException("Error inserting task", e);
        }
    }

    // Other methods implemented similarly...
}
```

### JDBC Best Practices

The implementation follows several JDBC best practices:

1. **Using Prepared Statements**: Prevents SQL injection attacks and improves performance.

2. **Try-with-Resources**: Ensures database resources are properly closed.

3. **Exception Handling**: SQLExceptions are caught and wrapped in runtime exceptions with meaningful messages.

4. **Parameter Binding**: Using `?` placeholders and setting values by position.

5. **Result Set Mapping**: Converting database results to domain objects.

The `extractTaskFromResultSet` method shows how database rows are converted to Java objects:

```java
private Task extractTaskFromResultSet(ResultSet rs) throws SQLException {
    Task task = new Task();
    task.setId(rs.getInt("id"));
    task.setTitle(rs.getString("title"));
    task.setDescription(rs.getString("description"));

    // Fix date parsing - handle database format
    String dueDateStr = rs.getString("due_date");
    if (dueDateStr != null && !dueDateStr.isEmpty()) {
        // Parse just the date part if it contains time
        if (dueDateStr.contains("T")) {
            dueDateStr = dueDateStr.substring(0, dueDateStr.indexOf("T"));
        }
        task.setDueDate(LocalDate.parse(dueDateStr));
    }

    task.setStatus(Status.valueOf(rs.getString("status")));

    // Handle created_at timestamp
    String createdAtStr = rs.getString("created_at");
    if (createdAtStr != null) {
        if (!createdAtStr.contains("T")) {
            createdAtStr = createdAtStr.replace(" ", "T");
        }
        task.setCreatedAt(LocalDateTime.parse(createdAtStr));
    }

    return task;
}
```

This method handles the complex task of converting database string representations to Java date/time objects, showing
how the DAO abstracts away these details from the rest of the application.

## JavaFX UI Implementation: Building a Responsive Interface

The user interface uses JavaFX with FXML and CSS to create a modern, responsive experience.

### FXML: Declarative UI Definition

FXML allows separating UI structure from behavior:

```xml

<VBox xmlns="http://javafx.com/javafx"
      xmlns:fx="http://javafx.com/fxml"
      fx:controller="com.todo.controller.MainController"
      spacing="20"
      style="-fx-padding: 24; -fx-background-color: #F3F4F6;"
      stylesheets="@../css/style.css">

    <!-- App Title -->
    <Label text="Todo App" styleClass="app-title"/>

    <!-- Input Card -->
    <VBox styleClass="card">
        <VBox spacing="16">
            <TextField fx:id="titleField" promptText="What needs to be done?" styleClass="modern-input"/>
            <TextArea fx:id="descriptionField"
                      promptText="Add details..."
                      styleClass="modern-input"
                      wrapText="true"
                      prefRowCount="3"
                      VBox.vgrow="SOMETIMES"/>

            <!-- More UI elements... -->
        </VBox>
    </VBox>

    <!-- Task List Card -->
    <VBox styleClass="card" VBox.vgrow="ALWAYS">
        <Label text="Tasks" styleClass="section-title"/>
        <TableView fx:id="taskTableView" onMouseClicked="#onTaskSelected" styleClass="modern-table" VBox.vgrow="ALWAYS">
            <columns>
                <TableColumn fx:id="titleColumn" text="Title" prefWidth="150"/>
                <TableColumn fx:id="descriptionColumn" text="Description" prefWidth="200"/>
                <TableColumn fx:id="dueDateColumn" text="Due Date" prefWidth="100"/>
                <TableColumn fx:id="statusColumn" text="Status" prefWidth="100"/>
            </columns>
        </TableView>
    </VBox>
</VBox>
```

This approach offers several advantages:

1. **Separation of Concerns**: UI structure is separate from behavior.

2. **Designer-Developer Workflow**: Designers can work on FXML while developers implement controllers.

3. **Readability**: The UI structure is easier to understand in XML than in procedural code.

### CSS Styling: Consistent Visual Design

CSS styling creates a modern, consistent look:

```css
/* Root and Base Styles */
.root {
    -fx-font-family: "Segoe UI", "SF Pro Text", Arial, sans-serif;
    -fx-background-color: #F3F4F6;
}

/* Cards */
.card {
    -fx-background-color: white;
    -fx-padding: 24;
    -fx-background-radius: 12;
    -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 8, 0, 0, 2);
}

/* Table Styles */
.modern-table {
    -fx-background-color: white;
    -fx-border-color: #E5E7EB;
    -fx-border-radius: 8;
}

/* Table Row States */
.modern-table .table-row-cell:hover {
    -fx-background-color: #F9FAFB;
}

.modern-table .table-row-cell:selected {
    -fx-background-color: #EFF6FF;
}
```

This CSS creates a cohesive design system that:

1. **Maintains Consistency**: Common elements share the same styling.

2. **Improves Maintainability**: Visual changes can be made in one place.

3. **Separates Concerns**: Visual styling is separate from structure and behavior.

### TableView with Property Binding

The controller sets up data binding for the TableView:

```java
// Modern table bindings
titleColumn.setCellValueFactory(data ->
        new

SimpleStringProperty(data.getValue().

getTitle()));
        descriptionColumn.

setCellValueFactory(data ->
        new

SimpleStringProperty(data.getValue().

getDescription()));
        statusColumn.

setCellValueFactory(data ->
        new

SimpleStringProperty(data.getValue().

getStatus().

getDisplayName()));
        dueDateColumn.

setCellValueFactory(data ->
        new

SimpleStringProperty(data.getValue().

getDueDate() !=null?
        data.

getValue().

getDueDate().

format(DATE_FORMAT) :"No date"));

// Make columns fill width
        titleColumn.

prefWidthProperty().

bind(taskTableView.widthProperty().

multiply(0.3));
        descriptionColumn.

prefWidthProperty().

bind(taskTableView.widthProperty().

multiply(0.4));
        dueDateColumn.

prefWidthProperty().

bind(taskTableView.widthProperty().

multiply(0.15));
        statusColumn.

prefWidthProperty().

bind(taskTableView.widthProperty().

multiply(0.15));
```

This approach allows:

1. **Dynamic Data Binding**: Table cells update automatically when task properties change.

2. **Custom Formatting**: Each column can format its data appropriately (like date formatting).

3. **Responsive Layout**: Columns resize proportionally when the window resizes.

### Event Handling

The controller handles user events through FXML-annotated methods:

```java

@FXML
private void onTaskSelected() {
    selectedTask = taskTableView.getSelectionModel().getSelectedItem();

    if (selectedTask != null) {
        titleField.setText(selectedTask.getTitle());
        descriptionField.setText(selectedTask.getDescription());
        dueDatePicker.setValue(
                selectedTask.getDueDate() != null ? selectedTask.getDueDate() : null
        );
        statusComboBox.setValue(selectedTask.getStatus());
    }
}

@FXML
private void addTask() {
    // Implementation...
}

@FXML
private void updateTask() {
    // Implementation...
}

@FXML
private void deleteTask() {
    // Implementation...
}
```

This event-driven approach:

1. **Responds to User Actions**: The application reacts to what the user does.

2. **Maintains UI State**: Selected task and form fields stay in sync.

3. **Separates Concerns**: Each method handles a specific user action.

## Exception Handling and Validation

The application implements robust error handling and validation to ensure data integrity and provide a good user
experience.

### Input Validation

Validation occurs at multiple levels:

1. **UI Level** - In the controller:

```java

@FXML
private void addTask() {
    String title = titleField.getText().trim();
    String description = descriptionField.getText().trim();
    LocalDate dueDate = dueDatePicker.getValue();

    if (title.isEmpty()) {
        showAlert("Validation Error", "Task title cannot be empty!", Alert.AlertType.ERROR);
        return;
    }

    // Proceed with adding the task...
}
```

2. **Service Level** - In the TaskService:

```java
public void addTask(String title, String description, String dueDate) {
    if (title == null || title.trim().isEmpty()) {
        throw new IllegalArgumentException("Task title cannot be empty.");
    }

    // Proceed with creating the task...
}
```

3. **Model Level** - In the Task class:

```java
public void setTitle(String title) {
    if (title == null || title.trim().isEmpty()) {
        throw new IllegalArgumentException("Title cannot be empty");
    }
    this.title = title.trim();
}
```

This multi-layered approach ensures:

1. **Data Integrity**: Invalid data can't enter the system.

2. **User Feedback**: Users are immediately informed about validation issues.

3. **Defense in Depth**: Even if one layer fails, others can catch the problem.

### Exception Handling

The application handles exceptions gracefully:

```java

@FXML
private void addTask() {
    // Validation...

    try {
        taskService.addTask(title, description, dueDate != null ? dueDate.toString() : null);
        refreshTaskList();
        clearInputFields();
    } catch (Exception e) {
        showAlert("Error", "Failed to add task: " + e.getMessage(), Alert.AlertType.ERROR);
    }
}

private void showAlert(String title, String message, Alert.AlertType type) {
    Alert alert = new Alert(type);
    alert.setTitle(title);
    alert.setContentText(message);
    alert.show();
}
```

In the DAO layer, SQL exceptions are caught and wrapped:

```java

@Override
public void insert(Task task) {
    // Implementation...

    try (Connection conn = DatabaseUtil.getConnection();
         PreparedStatement pstmt = conn.prepareStatement(sql)) {

        // Statement preparation...

    } catch (SQLException e) {
        throw new RuntimeException("Error inserting task", e);
    }
}
```

This approach provides:

1. **User-Friendly Error Messages**: Technical details are hidden from users.

2. **Centralized Error Handling**: Common error handling logic in one place.

3. **Robust Recovery**: The application can continue running after errors.

## Key Technical Concepts for Interview Discussion

When discussing this project in your interview, focus on these key technical aspects:

1. **Layered Architecture**: Explain how you separated the application into presentation, business, and data access
   layers, and the benefits this provides.

2. **DAO Pattern**: Discuss how you used the Data Access Object pattern to abstract database operations and provide a
   clean interface for the service layer.

3. **OOP Principles**: Demonstrate your understanding of encapsulation, inheritance, polymorphism, and abstraction with
   specific examples from the project.

4. **JDBC Best Practices**: Explain how you implemented secure and efficient database access with prepared statements,
   connection management, and exception handling.

5. **JavaFX UI Design**: Discuss how you structured the UI with FXML, CSS, and data binding for a responsive user
   experience.

6. **Exception Handling and Validation**: Explain your multi-layered approach to ensuring data integrity and providing
   user feedback.

## Interview-Ready Explanations

Here are some concise explanations you could use in your interview:

**On Architecture Choice:**
"I chose a layered architecture with clear separation between UI, business logic, and data access. Each layer has a
specific responsibility: the controller handles user interaction, the service layer implements business rules, and the
DAO manages database operations. This approach makes the code more maintainable and testable, as each component can be
modified or replaced independently."

**On Using JDBC with SQLite:**
"For this project, I used JDBC with SQLite because it provides a lightweight, portable database solution perfect for a
desktop application. I implemented the DAO pattern to abstract the database details from the rest of the application,
using prepared statements for security and efficiency. This approach gives me direct control over SQL queries while
maintaining clean separation between the database and business logic."

**On OOP Implementation:**
"I applied encapsulation by making all entity fields private and providing getters and setters with validation. I used
interface inheritance with the DAO pattern, defining a contract that different implementations can fulfill. Polymorphism
appears in how I handle task statuses, allowing them to be stored as strings in the database but displayed with
user-friendly names in the UI. The service layer demonstrates abstraction by hiding implementation details from the
controller."

**On JavaFX Implementation:**
"The UI uses FXML for structure, CSS for styling, and controller classes for behavior. This separation of concerns makes
the code more maintainable and allows for a cleaner architecture. I used property binding to connect the model to the
view, ensuring the UI automatically updates when data changes. The responsive design adapts to window size changes,
providing a better user experience."

By articulating these concepts clearly, you'll demonstrate that you understand not just how to write code, but how to
design and implement a complete application using sound software engineering principles.