# Use JRE only (lighter than JDK)
FROM eclipse-temurin:17-jre

WORKDIR /app

# Copy the already built JAR
COPY target/analytics-service-1.0.0.jar app.jar

EXPOSE 8081

ENTRYPOINT ["java", "-jar", "app.jar"]
