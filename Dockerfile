FROM eclipse-temurin:17-jdk

WORKDIR /app

COPY target/1_StudentManagement_System-1.0.war app.war

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.war"]
