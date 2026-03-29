# 📌 Login Manager API
REST API for Manager User Authentication and Authorization using Spring Security and JWT.

## Features
- Register User
- Login Authentication with JWT
- Access control with Spring Security
- DB control using Migration
- CRUD

## Technology
![Java](https://img.shields.io/badge/Java-ED8B00?style=for-the-badge&logo=java&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![Spring Security](https://img.shields.io/badge/Spring%20Security-6DB33F?style=for-the-badge&logo=springsecurity&logoColor=white)
![Hibernate](https://img.shields.io/badge/Hibernate-000000?style=for-the-badge&logo=hibernate&logoColor=white)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-158EEB?style=for-the-badge&logo=postgresql&logoColor=white)
![JWT](https://img.shields.io/badge/JWT-000000?style=for-the-badge&logo=jsonwebtokens&logoColor=white)

## Endpoints
|Methods |Path      |Description  |
|--------|----------|-------------|
|POST    |/users/register |Create a new User|
|POST    |/users/login    |Log in with email and password|
|DELETE  |/users          |Delete User|
|GET     |/users          |List Users |
