# 📦 Product Inventory with Pagination, Sorting, and File Import

## 🔍 Goal
Build a **Product Management System** with advanced data handling using Spring Boot, Spring Data JPA, and MySQL.

---

## ✅ Features

1. **Spring Boot + MySQL + Spring Data JPA**  
   - Set up a full-fledged backend application.  
   - Entity: `Product` with fields:  
     `id`, `name`, `price`, `quantity`, `category`

2. **CRUD APIs with Pagination and Sorting**  
   - Supports `GET`, `POST`, `PUT`, and `DELETE` operations.  
   - Integrated `Pageable` interface to handle large data sets efficiently.  
   - Allows sorting by fields such as `price`, `quantity`, etc.

3. **Filtering Support**  
   - Filter products by `category` or `price range`.  
   - Uses Spring JPA Specifications or Criteria API for dynamic querying.

4. **CSV Bulk Import**  
   - Upload `.csv` file to import products in bulk via REST endpoint.  
   - Uses `MultipartFile` + `Apache Commons CSV` (or `OpenCSV`).

5. **Scheduled Job (Inventory Alert)**  
   - Scheduled task (`@Scheduled`) runs daily.  
   - Generates a summary of **low-stock products**.  
   - Stores summary in DB or logs output.

6. **Auditing with JPA**  
   - Tracks `createdAt` and `updatedAt` using **JPA Auditing**.  
   - Auto-populated during insert/update.

---

## 🛠️ Tech Stack

- Java 17+  
- Spring Boot  
- Spring Data JPA  
- MySQL  
- Apache Commons CSV / OpenCSV  
- JPA Auditing  
- Spring Scheduler  
- Postman (for API testing)

---

## 🚀 How to Run

```bash
# Clone the repo
git clone https://github.com/KurraShiva/PaginationandScheduling.git

# Import as Maven project in your IDE

# Start MySQL server and create a DB (e.g., product_inventory)

# Update application.properties with DB credentials

# Run the Spring Boot Application
