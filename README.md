# Integer to Roman API

This is a Spring Boot API that converts integers to Roman numerals. The API exposes a REST endpoint that accepts an integer and returns the corresponding Roman numeral.

## Technologies

- [Spring Boot 4.0.0](https://spring.io/projects/spring-boot)
- Java 25
- Maven (Apache Maven 3.x)
- Jar packaging
- Docker (for containerization)

## Prerequisites

Before running this project, ensure you have the following installed:

### 1. Java 25

Download and install Java 25 from the official website:

- [Oracle JDK Downloads](https://www.oracle.com/java/technologies/downloads/)

Verify installation:

```bash
java -version
```

### 2. Maven

Install Maven to build the project:

- [Apache Maven Installation](https://maven.apache.org/install.html)

On macOS, you can also install via Homebrew:

```bash
brew install maven
```

Verify installation:

```bash
mvn -version
```

### 3. Docker (optional for containerized runs)

Download and install Docker:

- [Docker Desktop](https://www.docker.com/products/docker-desktop/)

Verify installation:

```bash
docker --version
```

## Getting Started

### Build & Run Unit Tests

Run the following command to clean the project and execute unit tests:

```bash
mvn clean test
```

### Build Docker Image

This command builds a Docker image named `integer-to-roman-api-app`:

```bash
docker build -t integer-to-roman-api-app:latest .
```

### Run Docker Image

Run the API in a container and expose it on port 8080:

```bash
docker run -p 8080:8080 integer-to-roman-api-app
```

### Run without Docker

You can also run the API locally without Docker:

```bash
mvn spring-boot:run
```

Once running, the API is accessible at:
`http://localhost:8080/romannumeral?query=<integer>`

### Example Request

```bash
curl http://localhost:8080/romannumeral?query=1
```

Expected response:

```json
{
  "input": "1",
  "output": "I"
}
```

## Notes

- Ensure port 8080 is free when running the application.
- For containerized deployment, Docker must be installed and running.
- Use Java 25 and Maven 3.x to avoid compatibility issues.
- The project is packaged as a JAR and can be deployed anywhere a Java 25 runtime is available.
- Learn more about Spring Boot 4.0.0: [Spring Boot Documentation](https://docs.spring.io/spring-boot/docs/4.0.0/reference/htmlsingle/)
