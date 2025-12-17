FROM eclipse-temurin:25-jdk
WORKDIR /app
COPY ./target/integer-to-roman-api-0.0.1-SNAPSHOT.jar /app/app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]
