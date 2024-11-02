
# First stage: Build
FROM gradle:7.5.0-jdk17 AS build

# Set the working directory
WORKDIR /app

# Copy the build files and source code
COPY . .

# Run the Gradle build command, skipping tests
RUN gradle build -x test

# Second stage: Runtime
FROM openjdk:17.0.1-jdk-slim

# Optionally include a volume for temporary files
VOLUME /tmp

# Copy the built JAR file from the build stage to the runtime stage
COPY --from=build /app/build/libs/expense-tracker-spring-0.0.1-SNAPSHOT.jar app.jar

# Expose the application port
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java","-jar","/app.jar"]