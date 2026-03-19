🔐 Book Application – JWT Authentication (Spring Boot)
📌 Overview

This project demonstrates a secure REST API built using Spring Boot with JWT (JSON Web Token) authentication.

It implements:
- Stateless authentication
- Access & Refresh Token mechanism
-Secure API endpoints using Spring Security

🚀 Tech Stack
- Java 17+
- Spring Boot
- Spring Security
- JPA (Hibernate)
- JWT (io.jsonwebtoken)
- Maven
 -------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

🔐 Authentication Flow
🧠 High-Level Flow
Client (Postman / React)
        |
        | 1. Login (username, password)
        v
AuthController (/auth/login)
        |
        | 2. Validate credentials
        v
JwtUtil
        |
        | 3. Generate Access + Refresh Token
        v
Client receives tokens
        |
        | 4. Call APIs with Access Token
        v
JwtFilter (validates token)
        |
        | 5. If valid → allow request
        v
Protected Controller (BookController)
🔄 Refresh Token Flow
1. User logs in → gets Access + Refresh token
2. Access token expires (1 min)
3. Client calls /auth/refresh with refresh token
4. Server validates refresh token
5. New access token is issued
6. User continues without login
🧪 API Endpoints
🔑 Login API

POST /auth/login

Request Body:
{
  "username": "admin",
  "password": "password"
}
Response:
{
  "accessToken": "JWT_TOKEN",
  "refreshToken": "REFRESH_TOKEN"
}
🔄 Refresh Token API

POST /auth/refresh?refreshToken=YOUR_REFRESH_TOKEN

Response:
{
  "accessToken": "NEW_ACCESS_TOKEN"
}
📚 Get Book (Protected API)

GET /book/v1/getBook/{id}

Headers:
Authorization: Bearer <accessToken>
🔒 Security Implementation
✅ JWT Features

Stateless authentication

Token-based authorization

Short-lived access token (1 min)

Long-lived refresh token

Token validation using filter

🧱 Architecture
Controller Layer
 ├── AuthController
 ├── BookController

Service Layer
 ├── BookService

Repository Layer
 ├── BookRepository

Security Layer
 ├── SecurityConfig
 ├── JwtFilter
 ├── JwtUtil

DTO Layer
 ├── LoginRequest
⚙️ Configuration

application.properties
jwt.secret=mysecretkeymysecretkeymysecretkey123
jwt.access.expiration=60000
jwt.refresh.expiration=604800000

🧪 Testing (Postman)
1️⃣ Login

POST /auth/login

Get access + refresh token

2️⃣ Call API

Use access token in header

Authorization: Bearer <token>

3️⃣ Wait for Expiry

After 1 minute → token expires

4️⃣ Refresh Token

Call /auth/refresh

Get new access token

5️⃣ Call API Again

Use new token → ✅ success
