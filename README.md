# Integer to Roman API

This is a Spring Boot API that converts integers to Roman numerals.

## Technologies

- Spring Boot 4.0.0
- Java 25
- Maven
- Jar packaging
- Docker

## Getting Started

### Build Docker Image

```bash
docker build -t integer-to-roman-api-app:latest .
```

### Run Docker Image

```bash
docker run -p 8080:8080 integer-to-roman-api-app
```

### Run without Docker

```bash
mvn spring-boot:run
```

## Build & Test

```bash
mvn clean test
```

The API runs on `http://localhost:8080` by default.
