# Use an official OpenJDK runtime as a parent image
FROM openjdk:22-slim

# Set the working directory in the container
WORKDIR /app

# Copy the packaged jar file into the container
COPY /build/libs/*.jar coDream-0.0.1-SNAPSHOT.jar

# Expose the port the app runs on
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "coDream-0.0.1-SNAPSHOT.jar"]
