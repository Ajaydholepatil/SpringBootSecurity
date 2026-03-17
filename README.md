🔐 JWT Authentication Flow

- User sends credentials to /auth/login
- Server validates and generates JWT token
- Client stores token
- Client sends token in Authorization header
- JwtFilter intercepts every request
- Token is validated using JwtUtil
- If valid → request proceeds
- If invalid → request is rejected

  🔐 JWT Authentication Flow (Diagram)
✅ 1. High-Level Flow
[ Client (Postman / React) ]
            |
            | 1. Login (username + password)
            v
[ AuthController (/auth/login) ]
            |
            | 2. Validate Credentials
            v
[ JwtUtil ]
            |
            | 3. Generate JWT Token
            v
[ Client receives Token ]
            |
            | 4. Send Request with Token
            |    Authorization: Bearer <JWT>
            v
[ JwtFilter ]
            |
            | 5. Validate Token
            v
[ Spring Security Context ]
            |
            | 6. Allow Request
            v
[ BookController (/book/v1/getBook/{id}) ]
            |
            v
[ Response Returned ]
------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

🔄 2. Detailed Request Flow
STEP 1: LOGIN

Client  --->  POST /auth/login
               {
                 "username": "admin",
                 "password": "password"
               }

Server ---> Validate user
        ---> Generate JWT
        ---> Return token


STEP 2: ACCESS PROTECTED API

Client  --->  GET /book/v1/getBook/1
               Authorization: Bearer <JWT>

JwtFilter:
   - Extract token
   - Validate token
   - Extract username
   - Set Authentication in SecurityContext

If valid:
   ---> Controller executes

If invalid:
   ---> 401 / 403 response
   --------------------------------------------------------------------------------------------------------------------------------------------------------------------
   🧱 4. Component Architecture
Controller Layer
   |
   |-- AuthController  ---> Login API
   |-- BookController  ---> Protected APIs
   |
Service Layer
   |
   |-- BookService
   |
Repository Layer
   |
   |-- BookRepository (JPA)
   |
Security Layer
   |
   |-- SecurityConfig
   |-- JwtFilter
   |-- JwtUtil
   |
DTO Layer
   |
   |-- LoginRequest
