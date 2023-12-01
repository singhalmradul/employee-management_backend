FROM maven:3.8.4-openjdk-17 AS build
WORKDIR /app/

COPY pom.xml /app/pom.xml
COPY ./src/main/java/io/github/singhalmradul/empoyeemanagement/EmpoyeeManagementApplicationTests.java /app/src/main/java/io/github/singhalmradul/empoyeemanagement/EmpoyeeManagementApplicationTests.java

RUN mvn -f /app/pom.xml clean package

COPY . /app/
RUN mvn -f /app/pom.xml clean package

FROM openjdk:17-jdk-alpine
EXPOSE 8080
COPY --from=build /app/target/*.jar /app.jar
ENTRYPOINT ["java","-jar","/app.jar"]