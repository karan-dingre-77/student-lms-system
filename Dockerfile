# Use Java 17
FROM eclipse-temurin:17-jdk

# Set working directory
WORKDIR /app

# Copy jar (exact name)
COPY target/student-lms-system-0.0.1-SNAPSHOT.jar app.jar

# Expose port (Render uses 8080)
EXPOSE 8080

# Run Spring Boot app
ENTRYPOINT ["java", "-jar", "app.jar"]
