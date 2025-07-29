# 💳 bankApp — NUBAN Account Management API

An emulation of a Nigerian bank account system that generates, validates, and manages NUBAN (Nigeria Uniform Bank Account Number) using the official CBN (Central Bank of Nigeria) specification.

Built with **Spring Boot 3**, **Java 17**, and **Spring Data JPA**.

---

## 🚀 Features

- ✅ Generate valid 10-digit NUBAN account numbers using checksum
- ✅ Validate existing NUBAN account numbers
- ✅ Store and retrieve account details (account number, name, bank code)
- ✅ Input validation using Jakarta Bean Validation
- ✅ H2/PostgreSQL database support
- ✅ Global exception handling
- ✅ RESTful design with meaningful HTTP status codes
- ✅ Swagger/OpenAPI documentation (if enabled)

---

## 🛠️ Technologies

- Java 17
- Spring Boot 3.x
- Spring Web
- Spring Data JPA
- Bean Validation
- PostgreSQL / H2 (runtime)
- Lombok
- Swagger (optional)

---

## 📦 Getting Started

### 1. Clone the repository

```bash
git clone https://github.com/your-username/bankApp.git
cd bankApp
```

2. Run the application

Make sure you have Java 17+ and Maven installed.

./mvnw spring-boot:run

The app will start on:
http://localhost:8080


3. Create a new bank account

POST /api/accounts

Request body:

{
  "bankCode": "999",
  "accountName": "John Doe"
}

Response:

{
  "bankCode": "999",
  "accountNumber": "1234567891",
  "accountName": "John Doe"
}

Validate a NUBAN account number

GET /api/accounts/validate?accountNumber=1234567891&bankCode=999

Response:

{
  "valid": true
}


Get account details by account number

GET /api/accounts/{accountNumber}
