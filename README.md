# Phasezero-Catalog-Service-Using-Rest-Api
A Spring Boot REST API for Product Catalog Management
This project is a backend service that manages product inventory.
It is built using Spring Boot 4, Spring Data JPA, and MySQL, and tested using Postman.

Features

Add new product

Fetch all products

Search by part name

Search by category

Sort products by price

Delete product

Calculate total inventory value

Tech Stack
Layer	Technology
Backend	Spring Boot 4
ORM	Spring Data JPA (Hibernate)
Database	MySQL
Build Tool	Maven
Validation	Spring Boot Validation
Java Version	17
Testing Tool	Postman

Project Structure
src/main/java/com/jsp/phasezero_catalog_service/
│
├── controller/
├── entity/
├── service/
├── repository/
└── exception/

🗄️ Database Design
products table
Column	Type	Description
id	INT	Auto-generated primary key
partNumber	VARCHAR	Unique identifier
partName	VARCHAR	Name of part
category	VARCHAR	Product category
price	DOUBLE	Product price
stock	INT	Quantity in stock
value	DOUBLE	price × stock
createdAt	TIMESTAMP	Auto-generated creation time
id->added to track our product very easy
createdAt->added to get the time when we created 

📌 API Endpoints (Tested in Postman)
Base URL
localhost:2025/products

1️) Add Product
POST /products
Body (JSON):
{
  "partNumber": "PN-101",
  "partName": "Oil Filter",
  "category": "Engine",
  "price": 450,
  "stock": 20
}

Possible Responses:

201 Created

400 Bad Request (invalid input)

409 Conflict (duplicate partNumber)

2️) Fetch All Products
GET /products
Responses:

200 OK

404 Not Found (empty database)

3️) Search by Part Name
GET /products/partName/{partName}

Example:

GET /products/partName/oil filter

4️) Search by Category
GET /products/{category}

Example:

GET /products/engine

5️) Sort by Price
GET /products/sortprice

Sorts products in ascending order by price.

6️) Delete Product
DELETE /products/delete/{id}

Example:

DELETE /products/delete/5


Response:

204 No Content

404 Not Found (if ID doesn't exist)

7️) Get Total Inventory Value
GET /products/inventory/value

Response Example:

{
  "message": "Total inventory value",
  "data": 35000.0
}

Sample Inputs (For Testing in Postman)
Product 1
{
  "partNumber": "PN-5001",
  "partName": "Air Filter",
  "category": "Engine",
  "price": 800,
  "stock": 10
}

Product 2
{
  "partNumber": "PN-6002",
  "partName": "Brake Pad",
  "category": "Brake",
  "price": 1200,
  "stock": 15
}

Product 3
{
  "partNumber": "PN-7003",
  "partName": "Clutch Plate",
  "category": "Transmission",
  "price": 1800,
  "stock": 5
}

Error Handling
Error Type	Meaning
400	Invalid inputs (price ≤ 0, missing fields, etc.)
404	No data found
409	Duplicate part number

Exceptions handled:

NoDataFoundException

ResourceConflictException

BadRequestException

Global error handler

⚙️ How to Run
1️) MySQL Setup

Create database:

CREATE DATABASE phasezero_catalog;

2️) Configure application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/phasezero_catalog
spring.datasource.username=root
spring.datasource.password=yourpassword
spring.jpa.hibernate.ddl-auto=update

3️)Run the Application
mvn spring-boot:run


Then open Postman and test all APIs.
