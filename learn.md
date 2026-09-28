# Task Management API — Java & Spring Boot Learning Project

Project ini merupakan project pembelajaran untuk mempelajari **Java 25, Spring Boot, Spring Data JPA, MySQL, dan JWT Authentication** melalui pembuatan REST API.

Pendekatan yang digunakan adalah:

> **Learn Java while building a real backend application.**

Tidak ada fase belajar Java secara terpisah dalam waktu lama. Konsep Java akan dipelajari ketika konsep tersebut dibutuhkan dalam implementasi project.

---

# 1. Project Overview

Kita akan membangun sebuah **Task Management API** dengan fitur:

* User registration
* User login
* JWT authentication
* Create task
* Get tasks
* Get task by ID
* Update task
* Delete task
* Update task status
* Filtering
* Pagination
* Sorting
* Validation
* Global exception handling
* User-task relationship
* Authorization
* Unit testing
* Integration testing

Architecture:

```text
                        ┌──────────────┐
                        │    Client    │
                        │   Postman    │
                        └──────┬───────┘
                               │
                               ▼
                     ┌──────────────────┐
                     │   Spring Security│
                     │   JWT Filter     │
                     └────────┬─────────┘
                              │
                              ▼
                     ┌──────────────────┐
                     │    Controller    │
                     └────────┬─────────┘
                              │
                              ▼
                     ┌──────────────────┐
                     │     Service      │
                     │  Business Logic  │
                     └────────┬─────────┘
                              │
                              ▼
                     ┌──────────────────┐
                     │    Repository    │
                     └────────┬─────────┘
                              │
                              ▼
                     ┌──────────────────┐
                     │   JPA / Hibernate│
                     └────────┬─────────┘
                              │
                              ▼
                     ┌──────────────────┐
                     │      MySQL       │
                     │     Docker       │
                     └──────────────────┘
```

---

# 2. Existing Environment

Environment project sudah disiapkan.

```text
Java        : 25
Build Tool  : Maven
Framework   : Spring Boot
ORM         : Spring Data JPA / Hibernate
Database    : MySQL
Database Env: Docker
API Testing : Postman
Version Ctrl: Git
```

Tidak perlu mengulang setup:

* Java
* Maven
* Spring Boot
* JPA
* MySQL
* Docker

Fokus project langsung pada **development dan pembelajaran**.

---

# 3. Main Learning Goals

Project ini memiliki dua tujuan utama:

### Backend

Mampu membuat REST API menggunakan:

```text
Spring Boot
Spring Data JPA
Hibernate
MySQL
Spring Security
JWT
```

### Java

Mempelajari Java secara praktis:

```text
Class
Object
Constructor
Encapsulation
Interface
Inheritance
Enum
Generics
Collections
Exception
Optional
Lambda
Stream API
Record
Annotation
Date & Time API
```

---

# 4. Learning Philosophy

Gunakan prinsip:

```text
Need a concept
      ↓
Learn the Java concept
      ↓
Apply it to the project
      ↓
Understand the Spring concept
      ↓
Implement
      ↓
Test
```

Contoh:

Kita membutuhkan:

```java
List<Task>
```

Maka kita belajar:

```text
List
Generic
ArrayList
```

Kemudian langsung digunakan dalam project.

---

# 5. Project Roadmap

```text
Phase 1
Project Structure & Java Fundamentals
        │
        ▼
Phase 2
Task & User Entity
        │
        ▼
Phase 3
JPA Repository
        │
        ▼
Phase 4
Service Layer
        │
        ▼
Phase 5
REST Controller
        │
        ▼
Phase 6
Task CRUD
        │
        ▼
Phase 7
DTO & Validation
        │
        ▼
Phase 8
Exception Handling
        │
        ▼
Phase 9
User Registration
        │
        ▼
Phase 10
JWT Authentication
        │
        ▼
Phase 11
Authorization
        │
        ▼
Phase 12
Filtering / Pagination / Sorting
        │
        ▼
Phase 13
Testing
        │
        ▼
Phase 14
Refactoring & Production Practices
```

---

# 6. Phase 1 — Understand the Project Structure

Target structure awal:

```text
src/
├── main/
│   ├── java/
│   │   └── com/example/taskmanagement/
│   │       └── TaskManagementApplication.java
│   │
│   └── resources/
│       └── application.properties
│
└── test/
```

Pelajari:

* Package
* Class
* Main method
* Annotation
* Spring Boot entry point

Contoh:

```java
@SpringBootApplication
public class TaskManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(
            TaskManagementApplication.class,
            args
        );
    }
}
```

## Java Topics

Pelajari:

* Class
* Method
* `public`
* `static`
* `void`
* Method argument
* Object reference

Tidak perlu menghafal.

Pahami apa yang dilakukan setiap bagian.

---

# 7. Phase 2 — Design the Domain

Kita akan memiliki dua entity utama:

```text
User
 │
 │ 1
 │
 │ N
 ▼
Task
```

## User

```text
User
├── id
├── username
├── email
├── password
└── role
```

## Task

```text
Task
├── id
├── title
├── description
├── status
├── createdAt
├── updatedAt
└── user
```

---

# 8. Task Status

Gunakan Java `enum`.

```java
public enum TaskStatus {

    TODO,
    IN_PROGRESS,
    DONE

}
```

## Java Concept

Pelajari:

* Enum
* Enum value
* Enum comparison
* Enum mapping ke database

Jangan menggunakan String untuk status jika tidak diperlukan.

---

# 9. User Role

Gunakan enum:

```java
public enum Role {

    USER,
    ADMIN

}
```

Role akan digunakan untuk authorization.

---

# 10. Phase 3 — Create User Entity

Buat:

```text
entity/
└── User.java
```

Target:

```java
@Entity
public class User {

    @Id
    @GeneratedValue
    private Long id;

    private String username;

    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

}
```

Pelajari:

```text
@Entity
@Id
@GeneratedValue
@Enumerated
```

## Java Concepts

Pada tahap ini pelajari:

* Class
* Field
* Access modifier
* Constructor
* Getter
* Setter
* Encapsulation

---

# 11. Phase 4 — Create Task Entity

Buat:

```text
entity/
└── Task.java
```

Target:

```java
@Entity
public class Task {

    @Id
    @GeneratedValue
    private Long id;

    private String title;

    private String description;

    @Enumerated(EnumType.STRING)
    private TaskStatus status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

}
```

Pelajari:

* `LocalDateTime`
* Entity
* Database column
* Enum mapping

## Java Concept

Pelajari:

```java
LocalDateTime.now()
```

dan Java Date & Time API.

---

# 12. Phase 5 — User & Task Relationship

Task dimiliki oleh User.

Gunakan:

```text
User 1 ─────── N Task
```

Task:

```java
@ManyToOne
@JoinColumn(name = "user_id")
private User user;
```

User:

```java
@OneToMany(mappedBy = "user")
private List<Task> tasks;
```

Pelajari:

* `@ManyToOne`
* `@OneToMany`
* `@JoinColumn`
* Foreign Key
* Java Collection
* Generic
* Entity relationship

---

# 13. Phase 6 — Repository Layer

Buat:

```text
repository/
├── UserRepository.java
└── TaskRepository.java
```

User:

```java
public interface UserRepository
        extends JpaRepository<User, Long> {
}
```

Task:

```java
public interface TaskRepository
        extends JpaRepository<Task, Long> {
}
```

---

# 14. Java Deep Dive — Interface & Generic

Sebelum melanjutkan, pahami:

```java
public interface TaskRepository
        extends JpaRepository<Task, Long>
```

Pecah menjadi:

```text
interface
   ↓
TaskRepository

extends
   ↓
inherit behavior

JpaRepository<Task, Long>
   ↓
Generic
   ↓
Task = Entity
Long = ID type
```

Target:

> Mampu menjelaskan kode tersebut tanpa menghafalnya.

---

# 15. Phase 7 — Service Layer

Buat:

```text
service/
├── UserService.java
└── TaskService.java
```

Contoh:

```java
@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

}
```

---

# 16. Java Deep Dive — Constructor Injection

Perhatikan:

```java
public TaskService(TaskRepository taskRepository) {
    this.taskRepository = taskRepository;
}
```

Pelajari:

* Constructor
* Parameter
* Field
* `this`
* Object reference
* `final`

Kemudian pahami bagaimana Spring melakukan Dependency Injection.

---

# 17. Phase 8 — Task CRUD

Implementasikan:

```text
GET    /api/tasks
GET    /api/tasks/{id}
POST   /api/tasks
PUT    /api/tasks/{id}
DELETE /api/tasks/{id}
```

---

## Get All Tasks

```http
GET /api/tasks
```

---

## Get Task

```http
GET /api/tasks/{id}
```

---

## Create Task

```http
POST /api/tasks
```

Request:

```json
{
    "title": "Learn Spring Boot",
    "description": "Learn Spring Data JPA"
}
```

---

## Update Task

```http
PUT /api/tasks/{id}
```

---

## Delete Task

```http
DELETE /api/tasks/{id}
```

---

# 18. Controller

Buat:

```text
controller/
└── TaskController.java
```

Contoh:

```java
@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

}
```

Pelajari:

* `@RestController`
* `@RequestMapping`
* `@GetMapping`
* `@PostMapping`
* `@PutMapping`
* `@DeleteMapping`

---

# 19. Java Deep Dive — Collections

Saat membuat API, kita akan banyak menggunakan:

```java
List<Task>
```

Pelajari:

```text
Collection
 └── List
      └── ArrayList
```

Kemudian:

```java
List<Task>
```

Pahami:

* Interface
* Implementation
* Generic
* Iteration
* `for`
* Enhanced `for`
* `forEach`

---

# 20. Phase 9 — DTO

Jangan menggunakan Entity secara langsung sebagai API request/response.

Buat:

```text
dto/
├── auth/
│   ├── RegisterRequest.java
│   └── LoginRequest.java
│
└── task/
    ├── CreateTaskRequest.java
    ├── UpdateTaskRequest.java
    └── TaskResponse.java
```

Contoh:

```java
public record CreateTaskRequest(
    String title,
    String description
) {
}
```

---

# 21. Java Deep Dive — Record

Pelajari:

```java
public record CreateTaskRequest(
    String title,
    String description
) {}
```

Bandingkan dengan class biasa.

Pahami:

* Immutable data
* Constructor
* Accessor
* `equals`
* `hashCode`
* `toString`

---

# 22. Phase 10 — Validation

Tambahkan validation:

```java
public record CreateTaskRequest(

    @NotBlank
    String title,

    @Size(max = 500)
    String description

) {}
```

Controller:

```java
public TaskResponse createTask(
        @Valid @RequestBody CreateTaskRequest request
) {
    ...
}
```

Pelajari:

* `@Valid`
* `@RequestBody`
* `@NotBlank`
* `@Size`
* Validation error

---

# 23. Phase 11 — Exception Handling

Buat:

```text
exception/
├── TaskNotFoundException.java
├── UserNotFoundException.java
└── GlobalExceptionHandler.java
```

Contoh:

```java
public class TaskNotFoundException
        extends RuntimeException {

    public TaskNotFoundException(Long id) {
        super("Task not found: " + id);
    }

}
```

Pelajari:

* Exception
* RuntimeException
* `throw`
* `throws`
* Constructor
* Inheritance

---

# 24. Global Exception Handler

Gunakan:

```java
@RestControllerAdvice
public class GlobalExceptionHandler {

}
```

Pelajari:

```java
@ExceptionHandler
```

Target response:

```json
{
    "status": 404,
    "message": "Task not found"
}
```

---

# 25. Phase 12 — User Registration

Endpoint:

```http
POST /api/auth/register
```

Request:

```json
{
    "username": "regan",
    "email": "regan@example.com",
    "password": "password123"
}
```

Flow:

```text
Register Request
       ↓
AuthController
       ↓
AuthService
       ↓
Check existing user
       ↓
Hash password
       ↓
Create User
       ↓
UserRepository
       ↓
MySQL
```

Password **tidak boleh disimpan dalam plaintext**.

---

# 26. Phase 13 — Password Hashing

Gunakan password encoder dari Spring Security.

Concept:

```text
Plain Password
      ↓
Password Encoder
      ↓
Hashed Password
      ↓
Database
```

Saat login:

```text
Password
   ↓
Password Encoder
   ↓
Compare
   ↓
Authentication
```

Pelajari:

* Hashing
* Password verification
* Authentication
* Security principles

---

# 27. Phase 14 — JWT Authentication

Setelah registration berhasil, implementasikan login.

Endpoint:

```http
POST /api/auth/login
```

Request:

```json
{
    "email": "regan@example.com",
    "password": "password123"
}
```

Response:

```json
{
    "token": "eyJhbGciOi..."
}
```

Flow:

```text
Login
  │
  ▼
Find User
  │
  ▼
Verify Password
  │
  ▼
Generate JWT
  │
  ▼
Return Token
```

---

# 28. JWT Request Flow

Setelah login:

```text
Client
   │
   │ Authorization: Bearer <JWT>
   ▼
Spring Security
   │
   ▼
JWT Filter
   │
   ▼
Validate Token
   │
   ▼
Extract User
   │
   ▼
Security Context
   │
   ▼
Controller
```

Pelajari:

* JWT
* Bearer Token
* HTTP Authorization Header
* Filter
* Authentication
* Security Context

---

# 29. Phase 15 — Spring Security

Buat:

```text
security/
├── SecurityConfig.java
├── JwtAuthenticationFilter.java
└── JwtService.java
```

Public endpoints:

```text
POST /api/auth/register
POST /api/auth/login
```

Protected endpoints:

```text
GET    /api/tasks
GET    /api/tasks/{id}
POST   /api/tasks
PUT    /api/tasks/{id}
DELETE /api/tasks/{id}
```

---

# 30. Authorization

User hanya boleh mengakses task miliknya.

Contoh:

```text
User A
 ├── Task 1
 └── Task 2

User B
 ├── Task 3
 └── Task 4
```

User A:

```text
GET /api/tasks/1
→ Allowed

GET /api/tasks/3
→ Forbidden / Not accessible
```

Pelajari perbedaan:

```text
Authentication
      vs
Authorization
```

---

# 31. Admin Role

Tambahkan role:

```text
USER
ADMIN
```

Contoh:

```text
USER
 ├── Manage own tasks
 └── View own tasks

ADMIN
 ├── View all users
 ├── View all tasks
 └── Manage tasks
```

Pelajari:

* Role
* Authority
* Authorization
* Method security

---

# 32. Phase 16 — Filtering

Tambahkan:

```http
GET /api/tasks?status=TODO
```

Contoh:

```http
GET /api/tasks?status=IN_PROGRESS
```

Gunakan:

```java
@RequestParam
```

Kemudian buat query repository yang sesuai.

Pelajari:

* Query parameter
* Spring Data query
* Derived query
* Optional parameter

---

# 33. Phase 17 — Pagination

Endpoint:

```http
GET /api/tasks?page=0&size=10
```

Pelajari:

```java
Page<Task>
Pageable
```

Response dapat berisi:

```json
{
    "content": [],
    "page": 0,
    "size": 10,
    "totalElements": 25,
    "totalPages": 3
}
```

---

# 34. Phase 18 — Sorting

Contoh:

```http
GET /api/tasks?sort=createdAt,desc
```

Pelajari:

```java
Sort
Pageable
```

---

# 35. Phase 19 — Java Stream API

Setelah CRUD dan filtering selesai, gunakan Stream API pada bagian yang memang sesuai.

Contoh:

```java
tasks.stream()
    .filter(...)
    .map(...)
    .toList();
```

Pelajari:

* Stream
* Lambda
* `filter`
* `map`
* `sorted`
* `collect`
* `toList`

Jangan menggunakan Stream hanya agar kode terlihat modern.

Pahami kapan Stream lebih tepat dibanding loop biasa.

---

# 36. Phase 20 — Optional

Gunakan:

```java
Optional<Task>
```

Contoh:

```java
taskRepository.findById(id)
```

Pelajari:

```text
Optional
 ├── isPresent
 ├── orElse
 ├── orElseThrow
 └── map
```

Fokus utama:

> Memahami mengapa `Optional` digunakan dan bagaimana menghindari `NullPointerException`.

---

# 37. Phase 21 — Testing

Tambahkan testing.

Target:

```text
src/test/
└── java/
    └── com/example/taskmanagement/
        ├── controller/
        ├── service/
        ├── repository/
        └── security/
```

Test minimal:

```text
TaskServiceTest
TaskControllerTest
TaskRepositoryTest
AuthServiceTest
JwtServiceTest
```

Pelajari:

* Unit testing
* Integration testing
* Mock
* Mockito
* Assertions
* TestNG / JUnit

---

# 38. Testing Scenarios

## Authentication

```text
Register success
Register duplicate email
Login success
Login wrong password
Invalid JWT
Expired JWT
Missing JWT
```

## Task

```text
Create task
Get task
Update task
Delete task
Task not found
Access another user's task
```

---

# 39. Phase 22 — Refactoring

Setelah semua fitur selesai, review codebase.

Periksa:

```text
Controller
Service
Repository
DTO
Entity
Exception
Security
```

Pastikan:

```text
Controller
→ HTTP handling

Service
→ Business logic

Repository
→ Database access

DTO
→ API data

Entity
→ Database model

Security
→ Authentication / Authorization
```

---

# 40. Final Project Structure

Target akhir:

```text
task-management-api/
│
├── src/
│   │
│   ├── main/
│   │   │
│   │   ├── java/
│   │   │   └── com/example/taskmanagement/
│   │   │       │
│   │   │       ├── config/
│   │   │       │
│   │   │       ├── controller/
│   │   │       │   ├── AuthController.java
│   │   │       │   └── TaskController.java
│   │   │       │
│   │   │       ├── dto/
│   │   │       │   ├── auth/
│   │   │       │   │   ├── LoginRequest.java
│   │   │       │   │   ├── RegisterRequest.java
│   │   │       │   │   └── AuthResponse.java
│   │   │       │   │
│   │   │       │   └── task/
│   │   │       │       ├── CreateTaskRequest.java
│   │   │       │       ├── UpdateTaskRequest.java
│   │   │       │       └── TaskResponse.java
│   │   │       │
│   │   │       ├── entity/
│   │   │       │   ├── User.java
│   │   │       │   ├── Task.java
│   │   │       │   ├── Role.java
│   │   │       │   └── TaskStatus.java
│   │   │       │
│   │   │       ├── exception/
│   │   │       │   ├── TaskNotFoundException.java
│   │   │       │   ├── UserNotFoundException.java
│   │   │       │   └── GlobalExceptionHandler.java
│   │   │       │
│   │   │       ├── repository/
│   │   │       │   ├── UserRepository.java
│   │   │       │   └── TaskRepository.java
│   │   │       │
│   │   │       ├── security/
│   │   │       │   ├── SecurityConfig.java
│   │   │       │   ├── JwtAuthenticationFilter.java
│   │   │       │   └── JwtService.java
│   │   │       │
│   │   │       ├── service/
│   │   │       │   ├── AuthService.java
│   │   │       │   └── TaskService.java
│   │   │       │
│   │   │       └── TaskManagementApplication.java
│   │   │
│   │   └── resources/
│   │       └── application.properties
│   │
│   └── test/
│
├── .gitignore
├── pom.xml
├── mvnw
├── mvnw.cmd
└── README.md
```

---

# 41. API Specification

## Authentication

| Method | Endpoint             | Description   |
| ------ | -------------------- | ------------- |
| POST   | `/api/auth/register` | Register user |
| POST   | `/api/auth/login`    | Login user    |

## Tasks

| Method | Endpoint          | Description      |
| ------ | ----------------- | ---------------- |
| GET    | `/api/tasks`      | Get user's tasks |
| GET    | `/api/tasks/{id}` | Get task         |
| POST   | `/api/tasks`      | Create task      |
| PUT    | `/api/tasks/{id}` | Update task      |
| DELETE | `/api/tasks/{id}` | Delete task      |

## Query

| Method | Endpoint                         | Description |
| ------ | -------------------------------- | ----------- |
| GET    | `/api/tasks?status=TODO`         | Filter      |
| GET    | `/api/tasks?page=0&size=10`      | Pagination  |
| GET    | `/api/tasks?sort=createdAt,desc` | Sorting     |

---

# 42. Git Commit Strategy

Commit setiap milestone.

```text
feat: create task and user entities
feat: add task repository
feat: add user repository
feat: implement task service
feat: implement task controller
feat: implement task crud
feat: add task dto
feat: add request validation
feat: add global exception handling
feat: implement user registration
feat: add password hashing
feat: implement jwt authentication
feat: add jwt security filter
feat: secure task endpoints
feat: add role authorization
feat: add task filtering
feat: add pagination and sorting
test: add task service tests
test: add authentication tests
refactor: improve task service structure
```

---

# 43. Learning Rules

## Rule 1 — Jangan Copy-Paste Blindly

Setiap kode baru harus bisa dijelaskan:

```text
Apa?
Mengapa?
Bagaimana?
Kapan digunakan?
```

---

## Rule 2 — Java Dipelajari Ketika Dibutuhkan

Contoh:

```java
public interface TaskRepository
        extends JpaRepository<Task, Long>
```

Jangan langsung menerima kode tersebut.

Pelajari:

```text
interface
        ↓
extends
        ↓
generic
        ↓
JpaRepository
```

Baru lanjut.

---

## Rule 3 — Jangan Overengineering

Project ini untuk belajar.

Jangan menambahkan:

```text
Microservices
Kafka
Redis
Kubernetes
CQRS
Event Sourcing
```

sebelum konsep dasar Spring Boot benar-benar dipahami.

Fokus:

```text
Java
Spring Boot
JPA
MySQL
REST API
JWT
Testing
```

---

# 44. Java Learning Checklist

Selama project berjalan, tandai:

```text
[ ] Class
[ ] Object
[ ] Constructor
[ ] Access Modifier
[ ] Encapsulation
[ ] this
[ ] static
[ ] final
[ ] Interface
[ ] Inheritance
[ ] Polymorphism
[ ] Enum
[ ] Exception
[ ] RuntimeException
[ ] Collections
[ ] List
[ ] Set
[ ] Map
[ ] Generic
[ ] Optional
[ ] Lambda
[ ] Stream
[ ] Record
[ ] LocalDateTime
[ ] Annotation
```

---

# 45. Spring Boot Learning Checklist

```text
[ ] @SpringBootApplication
[ ] @RestController
[ ] @RequestMapping
[ ] @GetMapping
[ ] @PostMapping
[ ] @PutMapping
[ ] @DeleteMapping
[ ] @RequestBody
[ ] @PathVariable
[ ] @RequestParam
[ ] @Service
[ ] @Repository
[ ] Dependency Injection
[ ] IoC
[ ] Bean
[ ] Component Scanning
```

---

# 46. JPA Learning Checklist

```text
[ ] @Entity
[ ] @Id
[ ] @GeneratedValue
[ ] @Enumerated
[ ] @ManyToOne
[ ] @OneToMany
[ ] @JoinColumn
[ ] JpaRepository
[ ] Derived Query
[ ] JPQL
[ ] Entity Relationship
[ ] Lazy Loading
[ ] Eager Loading
[ ] Cascade
[ ] Transaction
```

---

# 47. Security Learning Checklist

```text
[ ] Authentication
[ ] Authorization
[ ] Password Hashing
[ ] PasswordEncoder
[ ] JWT
[ ] Bearer Token
[ ] SecurityFilterChain
[ ] JWT Filter
[ ] SecurityContext
[ ] Role
[ ] Authority
```

---

# 48. Definition of Done

Project dianggap selesai apabila mampu menjelaskan:

### Java

* Bagaimana class dan object bekerja
* Bagaimana constructor bekerja
* Apa itu encapsulation
* Perbedaan class dan interface
* Bagaimana inheritance bekerja
* Apa itu generic
* Kapan menggunakan List, Set, dan Map
* Apa kegunaan Optional
* Bagaimana Lambda bekerja
* Bagaimana Stream API bekerja
* Apa itu Record
* Bagaimana Exception Handling bekerja

### Spring Boot

* Bagaimana Spring Boot menjalankan aplikasi
* Apa itu Bean
* Apa itu Dependency Injection
* Apa itu IoC
* Perbedaan Controller, Service, dan Repository
* Bagaimana request diproses Spring

### JPA

* Apa itu Entity
* Apa itu JPA
* Apa itu Hibernate
* Bagaimana Entity dipetakan ke database
* Bagaimana relationship bekerja
* Apa itu Lazy Loading
* Bagaimana Repository bekerja

### REST API

* GET
* POST
* PUT
* DELETE
* Request Body
* Path Variable
* Query Parameter
* HTTP Status Code
* DTO
* Validation

### Security

* Authentication
* Authorization
* Password Hashing
* JWT
* Bearer Token
* Security Filter
* Security Context
* Role

### Testing

* Unit Testing
* Integration Testing
* Mocking
* Assertion
* TestNG / JUnit

---

# 49. Final Architecture

```text
                              CLIENT
                                │
                                │ HTTP
                                ▼
                    ┌─────────────────────┐
                    │   Spring Security   │
                    │                     │
                    │   JWT Filter        │
                    └──────────┬──────────┘
                               │
                               ▼
                    ┌─────────────────────┐
                    │     Controller      │
                    │                     │
                    │ AuthController      │
                    │ TaskController      │
                    └──────────┬──────────┘
                               │
                               ▼
                    ┌─────────────────────┐
                    │       Service       │
                    │                     │
                    │ AuthService         │
                    │ TaskService         │
                    └──────────┬──────────┘
                               │
                               ▼
                    ┌─────────────────────┐
                    │     Repository      │
                    │                     │
                    │ UserRepository      │
                    │ TaskRepository      │
                    └──────────┬──────────┘
                               │
                               ▼
                    ┌─────────────────────┐
                    │   JPA / Hibernate   │
                    └──────────┬──────────┘
                               │
                               ▼
                    ┌─────────────────────┐
                    │       MySQL         │
                    │      Docker         │
                    └─────────────────────┘
```

---

# 50. First Milestone

Karena environment sudah tersedia, **jangan mulai dari instalasi**.

Mulai langsung dari:

```text
[ ] Pahami struktur project Maven
[ ] Pahami Task & User domain
[ ] Buat TaskStatus enum
[ ] Buat Role enum
[ ] Buat User Entity
[ ] Buat Task Entity
[ ] Buat relationship User → Task
[ ] Jalankan aplikasi
[ ] Pastikan Hibernate membuat table
[ ] Buat UserRepository
[ ] Buat TaskRepository
```

Kemudian:

```text
Entity
   ↓
Repository
   ↓
Service
   ↓
Controller
   ↓
CRUD
   ↓
DTO
   ↓
Validation
   ↓
Exception Handling
   ↓
Register
   ↓
JWT Login
   ↓
Authorization
   ↓
Testing
```

**Target utama project:**

> Membuat Task Management REST API yang memiliki CRUD, database MySQL, user management, JWT authentication, authorization, validation, exception handling, pagination, filtering, sorting, dan testing — sambil memahami Java yang digunakan di setiap tahap.
