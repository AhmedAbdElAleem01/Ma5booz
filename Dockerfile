# syntax=docker/dockerfile:1

# Stage 1: Build the application
FROM maven:3.8.5-openjdk-11 AS build

# Set the working directory
WORKDIR /app

# Copy the pom.xml and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline

# Copy the source code
COPY src ./src

# Build the application
RUN mvn package -DskipTests

# Stage 2: Create the runtime image
FROM tomcat:10.1-jdk11-openjdk

# Set the working directory
WORKDIR /usr/local/tomcat

# Copy the built WAR file to the Tomcat webapps directory
COPY --from=build /app/target/*.war ./webapps/ROOT.war

# Expose the default Tomcat port
EXPOSE 8080

# Start Tomcat
CMD ["catalina.sh", "run"]
