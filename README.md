<h1 align="center"> Medical Records Microservice - Backend </h1>

<p align="center">
  This repository contains the backend implementation for the Medical Records Microservice, designed to efficiently manage and maintain patient medical records and related data. It provides essential CRUD operations for patient and report management, along with secure user authentication and audit logging for tracking changes.
</p>

---

### ✨ Key Features

- **Patient Record Management**:  
  Create, retrieve, update, and soft-delete patient records, ensuring data integrity.

- **Medical Report Management**:  
  Full CRUD operations for managing medical reports, with the ability to link reports to patient records.

- **Search & Filter**:  
  Advanced filtering options for medical records and reports based on attributes such as date, status, and doctor.

- **Soft Deletion**:  
  Ensure deleted records and reports are not permanently removed but marked inactive, preserving historical data.

---

### 📌 APIs

- **Patient Record API**:  
  Manage CRUD operations for patient records, including linking medical histories.

- **Report API**:  
  Endpoints for creating, updating, deleting, and retrieving medical reports, with report linkage to records.

- **Medical Record History API**:  
  Track changes to records and retrieve the history of updates.

- **Search & Filter API**:  
  Enable users to search and filter records and reports by criteria like date, status, or doctor.

---

### 🛠️ Tech Stack

- **Java 22**: The project is built using Java 22 as the primary language.
- **Spring Boot 3.3.3**: Provides a framework for building web applications and microservices.
- **Maven**: Dependency management and build automation tool.
- **Lombok**: Reduces boilerplate code with annotations.
- **Spring Boot Starter Web**: Handles web functionalities, including RESTful API development.
- **MySQL**: Placeholder for MySQL database connectivity, indicating potential future use of MySQL for persistent storage.

---
