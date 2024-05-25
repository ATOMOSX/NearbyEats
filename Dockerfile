# Use an OpenJDK base image
FROM openjdk:17
# Set the working directory in the container
WORKDIR /app
# Copy the Gradle build files
COPY build.gradle .
COPY settings.gradle .
COPY gradlew .
COPY gradle/ ./gradle/
# Copy the application source code
COPY src/ ./src/
# Build the application using Gradle
RUN ./gradlew build
# Set the port to expose
EXPOSE ${PORT}
# Set the entry point to run the application
ENTRYPOINT ["java", "-jar", "nearby_eats-1.0-SNAPSHOT.jar"]