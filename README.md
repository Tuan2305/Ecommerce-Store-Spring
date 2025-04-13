# Ecommerce Store

Ecommerce Store is a full-stack e-commerce web application built with Java Spring Boot. The project provides features such as cart management, order placement, payment processing, user management, and seller management.

## Project Description

This project includes the following key features:

- **Users**:
  - Register, login, and OTP verification.
  - Manage shopping cart and place orders.
  - View purchase history.

- **Sellers**:
  - Register, login, and manage account information.
  - Manage products and orders.
  - View revenue and transaction reports.

- **Payments**:
  - Integrated payment simulation via VNPay.

- **Security**:
  - JWT-based authentication and authorization.

## Project Structure
Ecommerce-Store/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── tuanvn/
│   │   │           └── Ecommerce/
│   │   │               ├── EcommerceStoreApplication.java # Main application entry point
│   │   │               ├── config/        # Configuration files (e.g., security, application settings)
│   │   │               ├── controller/    # API controllers
│   │   │               ├── domain/        # Core domain models
│   │   │               ├── exceptions/    # Custom exception handling
│   │   │               ├── modal/         # Database entities
│   │   │               ├── repository/    # Database repositories
│   │   │               ├── request/       # Request DTOs
│   │   │               ├── response/      # Response DTOs
│   │   │               ├── service/       # Business logic services
│   │   │               └── utils/         # Utility classes
│   └── resources/
│       ├── application.properties # Application configuration
│       ├── static/                # Static resources (e.g., CSS, JS, images)
│       └── templates/             # HTML templates (if any)
├── test/                          # Test cases
├── pom.xml                        # Maven configuration file
└── README.md                      # Project documentation

## System Requirements

- **Java**: Version 17 or higher.
- **Maven**: Version 3.6 or higher.
- **MySQL**: For database storage.

## How to Run the Project

### 1. Set Up the Environment

1. Install Java 17 and Maven.
2. Install MySQL and create a new database (e.g., `ecommerce_store`).

### 2. Configure the Application

1. Open the `src/main/resources/application.properties` file.
2. Update the MySQL connection details:

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/ecommerce_store
   spring.datasource.username=<your-username>
   spring.datasource.password=<your-password>

3. Run the Application
    3.1. Open a terminal and navigate to the project directory.

    3.2. Run the following command to build and start the application: run EcommerceStoreApplication.java

    3.3. The application will be available at http://localhost:8080.

4. Test the APIs
Use Postman or any API testing tool to test the endpoints.
Key endpoints:
/auth: For registration and login.
/api/orders: For order management.
/api/transactions: For transaction management.