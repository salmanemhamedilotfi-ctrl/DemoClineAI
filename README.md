# Spring Boot Demo Application

A Spring Boot application with Swagger documentation, palindrome checking functionality, and a simple login system.

## Features

- **REST API with Swagger Documentation**: Complete API documentation using SpringDoc OpenAPI
- **Palindrome Checker**: API endpoints to check if text is a palindrome (supports both GET and POST methods)
- **Login System**: Simple authentication system with Thymeleaf templates
- **Modern Spring Boot 4**: Built with Spring Boot 4.0.3

## API Endpoints

### Hello API
- `GET /api/hello` - Returns "Hello World!"
- `GET /api/palindrome?text={text}` - Check if text is a palindrome
- `POST /api/palindrome` - Check if text is a palindrome (JSON body)

### Login API
- `GET /login` - Display login form
- `POST /login` - Process login credentials
- `GET /dashboard` - Display dashboard (authenticated users)
- `GET /` - Redirect to login

## Getting Started

### Prerequisites
- Java 25
- Maven

### Running the Application
1. Clone the repository
2. Navigate to the project directory
3. Run: `mvn spring-boot:run`
4. Open your browser to: http://localhost:8081

### Swagger Documentation
Access the interactive API documentation at: http://localhost:8081/swagger-ui/index.html

## Login Credentials
- Username: `admin`
- Password: `password`

## Project Structure
```
src/
├── main/
│   ├── java/com/example/demo/
│   │   ├── config/
│   │   │   └── SwaggerConfig.java
│   │   ├── controller/
│   │   │   ├── HelloController.java
│   │   │   └── LoginController.java
│   │   └── DemoApplication.java
│   ├── resources/
│   │   ├── application.properties
│   │   ├── static/
│   │   │   └── css/
│   │   │       ├── login.css
│   │   │       └── dashboard.css
│   │   └── templates/
│   │       ├── login.html
│   │       └── dashboard.html
└── test/
    └── java/com/example/demo/controller/
        └── HelloControllerTest.java
```

## License
This project is open source and available under the [MIT License](LICENSE).