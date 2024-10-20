# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk
# Set the working directory in the container
WORKDIR /app
# Copy the current directory contents into the container at /app
ADD target/*.jar app.jar
# Make port 8081 available to the world outside this container
EXPOSE 8082
# Run the jar file
ENTRYPOINT ["java","-jar","app.jar"]