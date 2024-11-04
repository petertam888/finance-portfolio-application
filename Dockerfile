# Use a base image with JDK
FROM openjdk:21-jdk-slim

# Set the working directory
WORKDIR /app

# Copy the Maven build files
COPY target/finance-portfolio-application-0.0.1-SNAPSHOT.jar app.jar

# Expose the port the app runs on
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]