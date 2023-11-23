# Stage 1: Build the application
FROM maven:latest AS build
WORKDIR /app
# Copy pom.xml and download dependencies first to leverage Docker caching
COPY ./pom.xml ./pom.xml
RUN mvn dependency:go-offline
# Copy source code and build the application
COPY ./src ./src
RUN mvn package

# Stage 2: Deploy the application
FROM tomcat:latest
COPY --from=build /app/target/*.war /usr/local/tomcat/webapps/