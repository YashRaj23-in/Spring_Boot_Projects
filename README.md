# BorrowBuddy API

> A RESTful backend engine for a peer-to-peer campus item sharing platform. 

BorrowBuddy provides a secure, relational infrastructure for students to list inventory, search for available items, and manage borrowing requests through an automated transactional state machine.

## 🚀 Tech Stack

*   **Framework:** Spring Boot 3.x
*   **Language:** Java 17+
*   **Database:** MySQL / Spring Data JPA (Hibernate)
*   **Architecture:** REST API (Controllers, Repositories, Entity Models)

## 🏗 Core Architecture & State Machine

BorrowBuddy implements an automated business logic layer to prevent double-booking of physical inventory. 

When a `BorrowRequest` transitions from `PENDING` to `APPROVED`, the engine intercepts the transaction and automatically toggles the associated Item's `isAvailable` flag to `false`. Custom derived query methods (`findByTitleContainingIgnoreCaseAndIsAvailableTrue`) guarantee that checked-out items are mathematically blinded from the search engine until they are returned.

## 🗄️ Relational Entities

The system strictly enforces data integrity across three core tables:

1.  **User:** Represents a system user. Enforces unique constraints on `name` and `email` with a baseline `reputationscore` of 5.0.
2.  **Item:** Represents physical inventory. Tied to a User (Owner) via a `@ManyToOne` relationship. Tracks real-time availability.
3.  **BorrowRequest:** The transactional bridge linking a Borrower (User) to an Item via two `@ManyToOne` foreign keys. Enforces a strict `PENDING` default state on creation.

## 🔌 API Reference

### User Management
| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `POST` | `/users` | Registers a new user. |
| `GET` | `/users/{userId}` | Fetches a user profile by ID. |

### Inventory Management
| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `POST` | `/items` | Creates a new item attached to an owner. |
| `GET` | `/items/search?Keyword={term}` | Searches available inventory by title (ignores checked-out items). |
| `GET` | `/items/owner/{ownerId}` | Retrieves all inventory owned by a specific user. |

### Request & Transaction Engine
| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `POST` | `/requests` | Creates a new borrow request (Defaults to PENDING). |
| `GET` | `/requests/borrower/{borrowId}` | Retrieves all outgoing requests made by a borrower. |
| `GET` | `/requests/owner/{ownerId}` | Cross-table JOIN retrieving all incoming requests for an owner's items. |
| `PATCH` | `/requests/{requestId}/status?status={state}` | Updates request status and automatically triggers item visibility logic if APPROVED. |

## 🛠️ Local Setup & Installation

1. **Clone the repository:**
   ```bash
   git clone [https://github.com/YashRaj23-in/Spring_Boot_Projects.git](https://github.com/YashRaj23-in/Spring_Boot_Projects.git)

Ensure MySQL is running on your local machine. Update the application.properties file with your local database credentials:
spring.datasource.url=jdbc:mysql://localhost:3306/borrowbuddy
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update

Execute the Spring Boot application via your IDE or command line using Maven:
mvn spring-boot:run
