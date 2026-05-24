# 💳 Digital Wallet & Payment System

## Overview

A production-grade microservices-based digital wallet system built with Java 21
and Spring Boot 4.x. Users can register, manage digital wallets and perform
secure money transactions including deposits, withdrawals and peer-to-peer transfers.


---

## Tech Stack

| Technology              | Purpose                        |
| ----------------------- | ------------------------------ |
| Java 21                 | Core language                  |
| Spring Boot 4.x         | Application framework          |
| Spring Security 7       | Authentication & Authorization |
| JWT                     | Stateless authentication       |
| Spring Data JPA         | Database ORM                   |
| PostgreSQL              | Relational database            |
| Spring Cloud Eureka     | Service discovery              |
| OpenFeign               | Inter-service communication    |
| Docker & Docker Compose | Containerization               |

---

## Microservices

| Service        | Port | Description                          |
| -------------- | ---- | ------------------------------------ |
| eureka-server  | 8761 | Service registry & discovery         |
| user-service   | 8081 | User management & JWT authentication |
| wallet-service | 8082 | Wallet operations & transactions     |

---

## Features

- User registration & login
- JWT authentication with BCrypt password encryption
- Auto wallet creation on user registration
- Deposit money into wallet
- Withdraw money from wallet
- Peer-to-peer money transfer
- Transaction history
- Global exception handling with clean error responses
- Atomic transactions with @Transactional
- Inter-service communication via OpenFeign
- Service discovery with Eureka
- Containerized with Docker & Docker Compose
- Persistent data with Docker volumes

---

## Project Structure

digital-wallet-microservices/
├── eureka-server/
│ ├── src/
│ ├── Dockerfile
│ └── pom.xml
├── user-service/
│ ├── src/
│ │ └── main/
│ │ ├── java/
│ │ │ └── controller/
│ │ │ └── service/
│ │ │ └── repository/
│ │ │ └── model/
│ │ │ └── dto/
│ │ │ └── config/
│ │ │ └── exception/
│ │ └── resources/
│ │ ├── application.properties
│ │ └── application-docker.properties
│ ├── Dockerfile
│ └── pom.xml
├── wallet-service/
│ ├── src/
│ │ └── main/
│ │ ├── java/
│ │ │ └── controller/
│ │ │ └── service/
│ │ │ └── repository/
│ │ │ └── model/
│ │ │ └── dto/
│ │ │ └── config/
│ │ │ └── exception/
│ │ └── resources/
│ │ ├── application.properties
│ │ └── application-docker.properties
│ ├── Dockerfile
│ └── pom.xml
├── docker-compose.yml
└── README.md

---

## Getting Started

### Prerequisites

- Docker
- Docker Compose

### Running with Docker

**1. Clone the repository:**

```bash
git clone https://github.com/yourusername/digital-wallet-microservices.git
cd digital-wallet-microservices
```

**2. Run with Docker Compose:**

```bash
docker-compose up --build
```

**3. Services will be available at:**

| Service          | URL                   |
| ---------------- | --------------------- |
| Eureka Dashboard | http://localhost:8761 |
| User Service     | http://localhost:8081 |
| Wallet Service   | http://localhost:8082 |

**4. Stop the application:**

```bash
docker-compose down
```

**5. Stop and remove all data:**

```bash
docker-compose down -v
```

---

## API Endpoints

### User Service (http://localhost:8081)

| Method | Endpoint            | Auth Required | Description           |
| ------ | ------------------- | ------------- | --------------------- |
| POST   | /api/users/register | No            | Register new user     |
| POST   | /api/auth/login     | No            | Login & get JWT token |
| GET    | /api/users/{id}     | Yes           | Get user by id        |
| GET    | /api/users          | Yes           | Get all users         |

### Wallet Service (http://localhost:8082)

| Method | Endpoint             | Auth Required | Description        |
| ------ | -------------------- | ------------- | ------------------ |
| POST   | /api/wallet/deposit  | Yes           | Deposit money      |
| POST   | /api/wallet/withdraw | Yes           | Withdraw money     |
| POST   | /api/wallet/transfer | Yes           | Transfer money     |
| GET    | /api/wallet/{userId} | Yes           | Get wallet balance |

---

## Sample API Usage

### 1. Register a User

```bash
POST http://localhost:8081/api/users/register
Content-Type: application/json

{
    "fullName": "John Doe",
    "email": "johndoe@gmail.com",
    "password": "123456",
    "role": "USER"
}
```

### 2. Login

```bash
POST http://localhost:8081/api/auth/login
Content-Type: application/json

{
    "email": "johndoe@gmail.com",
    "password": "123456"
}
```

Response:

```json
{
  "token": "eyJhbGciOiJIUzI1NiJ9....."
}
```

### 3. Deposit Money

```bash
POST http://localhost:8082/api/wallet/deposit
Authorization: Bearer <your-token>
Content-Type: application/json

{
    "userId": 1,
    "amount": 1000,
    "description": "First deposit"
}
```

### 4. Withdraw Money

```bash
POST http://localhost:8082/api/wallet/withdraw
Authorization: Bearer <your-token>
Content-Type: application/json

{
    "userId": 1,
    "amount": 200,
    "description": "Withdrawal"
}
```

### 5. Transfer Money

```bash
POST http://localhost:8082/api/wallet/transfer
Authorization: Bearer <your-token>
Content-Type: application/json

{
    "userId": 1,
    "targetUserId": 2,
    "amount": 300,
    "description": "Sending money to Jane"
}
```

### 6. Get Wallet Balance

```bash
GET http://localhost:8082/api/wallet/1
Authorization: Bearer <your-token>
```

---

## Security

- All endpoints except `/api/users/register` and `/api/auth/login` require JWT token
- Passwords are encrypted using BCrypt
- JWT tokens expire after 24 hours
- Tokens must be sent in Authorization header as `Bearer <token>`

---

## Environment Variables

| Variable               | Description                    |
| ---------------------- | ------------------------------ |
| DB_USERNAME            | PostgreSQL username            |
| DB_PASSWORD            | PostgreSQL password            |
| JWT_SECRET             | JWT signing secret key         |
| SPRING_PROFILES_ACTIVE | Active Spring profile (docker) |

---

## Future Improvements

- [ ] API Gateway with Spring Cloud Gateway
- [ ] Kafka for event-driven wallet notifications
- [ ] Redis caching for performance
- [ ] Unit & Integration tests with JUnit & Mockito
- [ ] CI/CD pipeline with GitHub Actions
- [ ] Swagger/OpenAPI documentation
- [ ] Rate limiting
- [ ] Transaction pagination

---
