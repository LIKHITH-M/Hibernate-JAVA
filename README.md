# Hibernate 7 & Java 21 Demo Project

A foundational demonstration of **Object-Relational Mapping (ORM)** using Hibernate 7 and Java 21, connecting to a PostgreSQL database. This project provides a fully documented, working example of JPA annotations and standard CRUD operations.

---

## 🛠 Key Technologies
* **Java 21**: Core programming language.
* **Hibernate ORM 7.0.0.Beta3**: Seamless Java-to-Database mapping.
* **PostgreSQL (v42.7.4)**: Relational database for data storage.
* **Maven**: Dependency management and build automation.

---

## 📂 Project Structure & Core Components

* **`pom.xml`**: Manages project metadata, Java 21 compiler settings, and external dependencies.
* **`hibernate.cfg.xml`**: Central configuration for JDBC connection, database credentials, and automatic schema updates (`hbm2ddl.auto`).
* **`src/main/java/com/likhith/Alien.java`**: The Entity Model mapped to the `alien_data` table using JPA annotations (`@Entity`, `@Table`, `@Id`).
* **`src/main/java/com/likhith/Main.java`**: The application entry point demonstrating the complete Hibernate lifecycle:
    * **Bootstrapping**: Reading configuration and building the `SessionFactory`.
    * **Transaction Management**: Ensuring ACID properties during database tasks.
    * **CRUD Operations**: Implementation of `persist()` (Create), `find()` (Read), `merge()` (Update), and `remove()` (Delete).

---

## 🚀 How to Execute

### 1. Prerequisites
* Ensure **PostgreSQL** is running locally on port `5432`.
* Database: `likhith` | User: `postgres` | Password: `l@123`.

### 2. Build & Run
Open your terminal in the project root (`c:\Desktop\Projects\Hibernate`) and run:

```bash
# Compile the project
mvn clean compile

# Execute the application
mvn exec:java "-Dexec.mainClass=com.likhith.Main"
