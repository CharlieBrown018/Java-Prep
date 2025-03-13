# Spring and Spring Boot: A Comprehensive Guide

## 1. Introduction to Spring Framework

Spring Framework is a powerful, lightweight, and widely-used Java framework designed to make Java EE development easier.
Created by Rod Johnson, Spring was introduced as an alternative to heavy Enterprise JavaBeans (EJB) development.

The core principle behind Spring is Inversion of Control (IoC), which shifts the responsibility of managing object
creation and dependencies from the application code to the framework. This makes your code more modular, testable, and
maintainable.

Spring has evolved into a comprehensive ecosystem of projects that address various aspects of modern application
development, including:

- Spring MVC for web applications
- Spring Data for database access
- Spring Security for authentication and authorization
- Spring Boot for simplified setup and configuration
- Spring Cloud for distributed systems and microservices

## 2. Core Concepts in Spring

### 2.1 Inversion of Control (IoC)

IoC is a design principle where the control flow of a program is inverted: instead of the programmer's code making calls
to a library, the framework calls the programmer's code. In Spring, this means the framework controls the instantiation
and lifecycle of objects.

Consider traditional object creation versus Spring's approach:

**Traditional Approach**:

```java
public class UserService {
    private UserRepository userRepository;

    public UserService() {
        this.userRepository = new UserRepositoryImpl(); // Direct instantiation
    }
}
```

**Spring IoC Approach**:

```java
public class UserService {
    private UserRepository userRepository;

    // Spring will inject the appropriate UserRepository implementation
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
```

In the Spring approach, UserService doesn't create UserRepository—it receives it from the framework. This makes the code
more flexible and easier to test.

### 2.2 Dependency Injection (DI)

Dependency Injection is the implementation mechanism of IoC. Instead of having your objects create or find their
dependencies, they are provided (injected) by an external entity (the IoC container).

Spring supports three types of dependency injection:

**1. Constructor Injection** (Recommended):

```java

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired // Optional in newer Spring versions
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
```

**2. Setter Injection**:

```java

@Service
public class UserService {
    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
```

**3. Field Injection** (Not recommended for production code):

```java

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
}
```

Constructor injection is generally preferred because:

- It ensures required dependencies are provided at object creation time
- It supports immutability (fields can be final)
- It makes dependencies explicit in the API
- It's easier to test

### 2.3 The Spring Container (ApplicationContext)

The Spring Container, often referred to as the ApplicationContext, is the core component that creates, manages, and
configures beans. It reads configuration metadata (from XML, Java annotations, or Java code) and uses that information
to create a fully configured system or application.

```java
// Creating a Spring container
ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
// or
ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

// Getting a bean from the container
UserService userService = context.getBean(UserService.class);
```

## 3. Spring Beans

A Spring Bean is an object that is instantiated, assembled, and managed by the Spring IoC container. Beans are the
backbone of your Spring application.

### 3.1 Bean Definition

Bean definitions tell Spring how to create a bean:

**Using XML configuration**:

```xml

<bean id="userService" class="com.example.service.UserServiceImpl">
    <constructor-arg ref="userRepository"/>
</bean>

<bean id="userRepository" class="com.example.repository.JpaUserRepository"/>
```

**Using Java Configuration**:

```java

@Configuration
public class AppConfig {
    @Bean
    public UserRepository userRepository() {
        return new JpaUserRepository();
    }

    @Bean
    public UserService userService() {
        return new UserServiceImpl(userRepository());
    }
}
```

**Using Component Scanning and Annotations**:

```java

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
}

@Repository
public class JpaUserRepository implements UserRepository {
    // Implementation
}
```

To enable component scanning, you need:

```java

@Configuration
@ComponentScan("com.example")
public class AppConfig {
    // Configuration
}
```

### 3.2 Bean Scope

Beans can have different scopes that define their lifecycle:

1. **singleton** (default): One instance per Spring container
   ```java
   @Bean
   @Scope("singleton")
   public UserService userService() {
       return new UserServiceImpl();
   }
   ```

2. **prototype**: New instance each time requested
   ```java
   @Bean
   @Scope("prototype")
   public UserService userService() {
       return new UserServiceImpl();
   }
   ```

3. **request**: One instance per HTTP request (web applications)
   ```java
   @Bean
   @Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
   public UserPreferences userPreferences() {
       return new UserPreferences();
   }
   ```

4. **session**: One instance per HTTP session (web applications)
   ```java
   @Bean
   @Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
   public ShoppingCart shoppingCart() {
       return new ShoppingCart();
   }
   ```

5. **application**: One instance per ServletContext (web applications)
   ```java
   @Bean
   @Scope("application")
   public AppPreferences appPreferences() {
       return new AppPreferences();
   }
   ```

6. **websocket**: One instance per WebSocket session
   ```java
   @Bean
   @Scope("websocket")
   public ChatSession chatSession() {
       return new ChatSession();
   }
   ```

### 3.3 Bean Lifecycle

Beans in Spring have a well-defined lifecycle:

1. **Instantiation**: Spring creates the bean instance
2. **Populating Properties**: Spring injects dependencies
3. **BeanNameAware's setBeanName**: If the bean implements BeanNameAware
4. **BeanFactoryAware's setBeanFactory**: If the bean implements BeanFactoryAware
5. **ApplicationContextAware's setApplicationContext**: If the bean implements ApplicationContextAware
6. **PreInitialization**: BeanPostProcessors process before initialization
7. **InitializingBean's afterPropertiesSet**: If the bean implements InitializingBean
8. **Custom init-method**: If defined in bean configuration
9. **PostInitialization**: BeanPostProcessors process after initialization
10. **Bean is ready for use**
11. **DisposableBean's destroy**: If the bean implements DisposableBean
12. **Custom destroy-method**: If defined in bean configuration

You can hook into this lifecycle in several ways:

**Using Annotations**:

```java

@Component
public class DatabaseService {
    @PostConstruct
    public void init() {
        System.out.println("DatabaseService is initializing");
    }

    @PreDestroy
    public void shutdown() {
        System.out.println("DatabaseService is shutting down");
    }
}
```

**Using Interfaces**:

```java

@Component
public class DatabaseService implements InitializingBean, DisposableBean {
    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("DatabaseService is initializing");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("DatabaseService is shutting down");
    }
}
```

**Using Configuration**:

```java

@Configuration
public class AppConfig {
    @Bean(initMethod = "init", destroyMethod = "shutdown")
    public DatabaseService databaseService() {
        return new DatabaseService();
    }
}
```

### 3.4 Bean Wiring and Autowiring

Bean wiring is the process of connecting beans together in the Spring container. Autowiring is when Spring automatically
resolves and injects dependencies.

Spring supports several autowiring modes:

1. **no**: No autowiring. References must be defined explicitly. (Default in XML)
2. **byName**: Autowiring by property name
3. **byType**: Autowiring by property type
4. **constructor**: Autowiring by constructor arguments

With annotation-based configuration, the most common approach is autowiring by type with `@Autowired`:

```java

@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final PriceCalculator priceCalculator;

    @Autowired
    public ProductService(ProductRepository productRepository,
                          PriceCalculator priceCalculator) {
        this.productRepository = productRepository;
        this.priceCalculator = priceCalculator;
    }
}
```

When multiple beans of the same type exist, you can use `@Qualifier` to specify which one to inject:

```java

@Service
public class PaymentService {
    private final PaymentProcessor paymentProcessor;

    @Autowired
    public PaymentService(@Qualifier("stripeProcessor") PaymentProcessor paymentProcessor) {
        this.paymentProcessor = paymentProcessor;
    }
}
```

Alternatively, you can use `@Primary` to establish a default bean when multiple candidates exist:

```java

@Component
@Primary
public class StripePaymentProcessor implements PaymentProcessor {
    // Implementation
}

@Component
public class PayPalPaymentProcessor implements PaymentProcessor {
    // Implementation
}
```

## 4. Spring Boot

Spring Boot is a project that simplifies Spring application development. It takes an opinionated view of the Spring
platform, so you can get started with minimal configuration.

### 4.1 Key Features of Spring Boot

1. **Auto-configuration**: Spring Boot automatically configures your application based on the dependencies you have
   added
2. **Starter dependencies**: Pre-configured dependencies that simplify the build configuration
3. **Embedded server**: No need to deploy WAR files; applications can be run as standalone JAR files
4. **Production-ready features**: Metrics, health checks, and externalized configuration

### 4.2 Creating a Spring Boot Application

The simplest way to create a Spring Boot application is using Spring Initializr (start.spring.io) or using your IDE's
Spring Boot project generators.

A basic Spring Boot application looks like this:

```java

@SpringBootApplication
public class MyApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);
    }
}
```

The `@SpringBootApplication` annotation combines:

- `@Configuration`: Tags the class as a source of bean definitions
- `@EnableAutoConfiguration`: Tells Spring Boot to start adding beans based on classpath settings
- `@ComponentScan`: Tells Spring to look for components in the package of the application class

### 4.3 Spring Boot Starters

Spring Boot starters are dependency descriptors that dramatically simplify your build configuration. Instead of manually
specifying all the libraries needed for a feature, you include a single starter dependency.

Some common starters include:

- `spring-boot-starter-web`: For building web applications, including RESTful applications using Spring MVC
- `spring-boot-starter-data-jpa`: For using Spring Data JPA with Hibernate
- `spring-boot-starter-security`: For using Spring Security
- `spring-boot-starter-test`: For testing Spring Boot applications

Example using Maven:

```xml

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>

<dependency>
<groupId>org.springframework.boot</groupId>
<artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
```

### 4.4 Spring Boot Auto-configuration

Spring Boot automatically configures your Spring application based on the dependencies on the classpath. For example, if
you have H2 database on your classpath, Spring Boot automatically configures an in-memory database if you haven't
configured any other datasource.

You can see what auto-configuration is being applied by enabling debug logging:

```properties
# application.properties
debug=true
```

You can exclude specific auto-configurations if needed:

```java

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class MyApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyApplication.class, args);
    }
}
```

### 4.5 Configuration in Spring Boot

Spring Boot allows you to configure your application in several ways:

**1. application.properties/application.yml**:

```properties
# application.properties
server.port=8080
spring.datasource.url=jdbc:mysql://localhost:3306/mydb
spring.datasource.username=user
spring.datasource.password=password
```

or

```yaml
# application.yml
server:
  port: 8080
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/mydb
    username: user
    password: password
```

**2. Type-safe Configuration Properties**:

```java

@Configuration
@ConfigurationProperties(prefix = "mail")
public class MailProperties {
    private String host;
    private int port;
    private String username;
    private String password;

    // Getters and setters
}
```

```properties
# application.properties
mail.host=smtp.example.com
mail.port=25
mail.username=user
mail.password=secret
```

**3. Profiles for Environment-Specific Configuration**:

```properties
# application-dev.properties
server.port=8080
logging.level.root=DEBUG
# application-prod.properties
server.port=80
logging.level.root=INFO
```

You can activate a profile:

```
java -jar myapp.jar --spring.profiles.active=dev
```

Or in application.properties:

```properties
spring.profiles.active=dev
```

### 4.6 Spring Boot Actuator

Spring Boot Actuator provides production-ready features for monitoring and managing your application.

To add Actuator to your project:

```xml

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
```

Actuator provides several built-in endpoints:

- **/actuator/health**: Shows application health information
- **/actuator/info**: Displays arbitrary application information
- **/actuator/metrics**: Shows metrics information
- **/actuator/env**: Exposes environment properties

You can configure which endpoints are exposed:

```properties
# Expose all endpoints
management.endpoints.web.exposure.include=*
# Or expose specific endpoints
management.endpoints.web.exposure.include=health,info,metrics
```

## 5. RESTful Services with Spring Boot

Spring Boot makes it easy to create RESTful web services.

### 5.1 Creating a REST Controller

```java

@RestController
@RequestMapping("/api/products")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable Long id) {
        return productService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody @Valid Product product) {
        Product savedProduct = productService.save(product);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedProduct.getId())
                .toUri();
        return ResponseEntity.created(location).body(savedProduct);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id,
                                                 @RequestBody @Valid Product product) {
        return productService.updateProduct(id, product)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        if (productService.deleteProduct(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
```

### 5.2 Exception Handling in REST APIs

You can create a global exception handler to handle exceptions consistently across your API:

```java

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFound(ResourceNotFoundException ex) {
        ErrorResponse error = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        ErrorResponse error = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Validation failed",
                System.currentTimeMillis(),
                errors
        );
        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGenericException(Exception ex) {
        ErrorResponse error = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "An unexpected error occurred",
                System.currentTimeMillis()
        );
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
```

### 5.3 Spring Data JPA for Database Access

Spring Data JPA simplifies data access by reducing the boilerplate code required to implement data access layers.

First, define your entity:

```java

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String name;

    @Positive
    private double price;

    @Min(0)
    private int stock;

    // Getters and setters
}
```

Then create a repository interface:

```java

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByNameContaining(String name);

    List<Product> findByPriceLessThan(double price);

    List<Product> findByStockGreaterThan(int stock);
}
```

Spring Data JPA will automatically implement these methods for you.

Create a service to use the repository:

```java

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public Optional<Product> updateProduct(Long id, Product productDetails) {
        return productRepository.findById(id)
                .map(product -> {
                    product.setName(productDetails.getName());
                    product.setPrice(productDetails.getPrice());
                    product.setStock(productDetails.getStock());
                    return productRepository.save(product);
                });
    }

    public boolean deleteProduct(Long id) {
        return productRepository.findById(id)
                .map(product -> {
                    productRepository.delete(product);
                    return true;
                })
                .orElse(false);
    }
}
```

## 6. Spring Security

Spring Security is a powerful and highly customizable authentication and access-control framework.

### 6.1 Basic Configuration

To add Spring Security to your project:

```xml

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
```

A basic security configuration:

```java

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/", "/home").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .permitAll()
                )
                .logout(logout -> logout
                        .permitAll()
                );

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .roles("USER")
                .build();

        UserDetails admin = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("admin")
                .roles("USER", "ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user, admin);
    }
}
```

### 6.2 JWT Authentication

For RESTful APIs, JWT (JSON Web Token) authentication is common:

```java

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtTokenProvider jwtTokenProvider;

    public SecurityConfig(JwtTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/auth/**").permitAll()
                        .anyRequest().authenticated()
                )
                .apply(new JwtTokenFilterConfigurer(jwtTokenProvider));

        return http.build();
    }
}
```

## 7. Spring Boot Testing

Spring Boot provides good testing support with `spring-boot-starter-test`, which includes:

- JUnit 5: The standard testing framework for Java
- Spring Test & Spring Boot Test: Utilities and integration test support for Spring applications
- AssertJ: Fluent assertion library
- Hamcrest: Matchers objects
- Mockito: A Java mocking framework
- JSONassert: An assertion library for JSON

### 7.1 Unit Testing with Mockito

```java

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    void findById_shouldReturnProduct_whenProductExists() {
        // Arrange
        Long id = 1L;
        Product product = new Product();
        product.setId(id);
        product.setName("Test Product");

        when(productRepository.findById(id)).thenReturn(Optional.of(product));

        // Act
        Optional<Product> result = productService.findById(id);

        // Assert
        assertTrue(result.isPresent());
        assertEquals("Test Product", result.get().getName());
        verify(productRepository).findById(id);
    }
}
```

### 7.2 Integration Testing with @SpringBootTest

```java

@SpringBootTest
class ProductControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void getProductById_shouldReturnProduct_whenProductExists() {
        // Arrange
        Long id = 1L;

        // Act
        ResponseEntity<Product> response = restTemplate.getForEntity(
                "/api/products/{id}", Product.class, id);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(id, response.getBody().getId());
    }
}
```

### 7.3 Web Layer Testing with MockMvc

```java

@WebMvcTest(ProductController.class)
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Test
    void getProductById_shouldReturnProduct_whenProductExists() throws Exception {
        // Arrange
        Long id = 1L;
        Product product = new Product();
        product.setId(id);
        product.setName("Test Product");

        when(productService.findById(id)).thenReturn(Optional.of(product));

        // Act & Assert
        mockMvc.perform(get("/api/products/{id}", id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.name").value("Test Product"));
    }
}
```

## 8. Spring Boot in Production

### 8.1 Application Packaging and Deployment

Spring Boot applications can be packaged as executable JAR files:

```bash
./mvnw package
java -jar target/myapp-0.0.1-SNAPSHOT.jar
```

You can also build Docker images:

```dockerfile
FROM eclipse-temurin:17-jre
VOLUME /tmp
COPY target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
```

### 8.2 Externalized Configuration

For production, you typically want to externalize configuration:

```bash
java -jar myapp.jar --spring.config.location=file:/path/to/application.properties
```

Or using environment variables:

```bash
export SPRING_DATASOURCE_URL=jdbc:mysql://production-db:3306/mydb
export SPRING_DATASOURCE_USERNAME=produser
export SPRING_DATASOURCE_PASSWORD=s3cr3t
java -jar myapp.jar
```

### 8.3 Monitoring with Actuator and Prometheus

For production monitoring, you can integrate Spring Boot Actuator with Prometheus:

```xml

<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-actuator</artifactId>
</dependency>
<dependency>
<groupId>io.micrometer</groupId>
<artifactId>micrometer-registry-prometheus</artifactId>
</dependency>
```

```properties
management.endpoints.web.exposure.include=health,info,prometheus
management.metrics.export.prometheus.enabled=true
```

## 9. Advanced Spring Features

### 9.1 Spring AOP (Aspect-Oriented Programming)

AOP complements OOP by providing another way of thinking about program structure. It allows you to add behavior to
existing code without modifying the code itself.

```java

@Aspect
@Component
public class LoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Before("execution(* com.example.service.*.*(..))")
    public void logBefore(JoinPoint joinPoint) {
        logger.info("Executing: " + joinPoint.getSignature().getName());
    }

    @AfterReturning(pointcut = "execution(* com.example.service.*.*(..))", returning = "result")
    public void logAfterReturning(JoinPoint joinPoint, Object result) {
        logger.info("Method returned: " + result);
    }

    @AfterThrowing(pointcut = "execution(* com.example.service.*.*(..))", throwing = "error")
    public void logAfterThrowing(JoinPoint joinPoint, Throwable error) {
        logger.error("Exception in {}.{}(): {}",
                joinPoint.getSignature().getDeclaringTypeName(),
                joinPoint.getSignature().getName(),
                error.getMessage());
    }
}
```

### 9.2 Spring WebFlux for Reactive Programming

Spring WebFlux provides reactive programming support:

```java

@RestController
public class ProductController {
    private final ProductRepository productRepository;

    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/products")
    public Flux<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/products/{id}")
    public Mono<ResponseEntity<Product>> getProductById(@PathVariable String id) {
        return productRepository.findById(id)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping("/products")
    public Mono<ResponseEntity<Product>> createProduct(@RequestBody Product product) {
        return productRepository.save(product)
                .map(savedProduct -> ResponseEntity
                        .created(URI.create("/products/" + savedProduct.getId()))
                        .body(savedProduct));
    }
}
```

### 9.3 Spring Integration

Spring Integration extends the Spring programming model to support Enterprise Integration Patterns:

```java

@Configuration
@EnableIntegration
public class FileIntegrationConfig {

    @Bean
    public IntegrationFlow fileReadingFlow() {
        return IntegrationFlows
                .from(Files.inboundAdapter(new File("/path/to/input"))
                                .patternFilter("*.txt"),
                        e -> e.poller(Pollers.fixedDelay(5000)))
                .handle(Files.outboundAdapter(new File("/path/to/output")))
                .get();
    }
}
```

## 10. Spring vs Spring Boot: Key Differences

While Spring Boot is built on top of the Spring Framework, there are some important differences:

1. **Configuration**: Spring requires explicit configuration, while Spring Boot provides auto-configuration
2. **Dependencies**: Spring requires you to define all dependencies, while Spring Boot offers starter dependencies
3. **Embedded Server**: Spring requires a separate server, while Spring Boot includes an embedded server
4. **Deployment**: Spring applications are typically deployed as WAR files, while Spring Boot applications can be
   deployed as standalone JAR files
5. **Production Readiness**: Spring Boot includes production-ready features like health checks and metrics

## 11. Conclusion

Spring and Spring Boot significantly simplify Java application development by providing powerful abstractions,
conventions over configuration, and a rich ecosystem of related projects. By understanding the core concepts—such as
IoC, DI, Beans, and auto-configuration—you can leverage these frameworks to build robust, scalable, and maintainable
applications.

Whether you're building a simple REST API, a complex enterprise application, or a microservices architecture, Spring and
Spring Boot offer the tools and libraries to help you succeed. The extensive community support, comprehensive
documentation, and continuous evolution make Spring one of the most popular Java frameworks today.