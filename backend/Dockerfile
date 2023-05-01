# Build
FROM maven:3.8.6-openjdk-18 AS build
COPY src /home/app/src
COPY pom.xml /home/app
RUN mvn -f /home/app/pom.xml clean package

# Run
FROM openjdk:18-jdk-alpine
COPY --from=build /home/app/target/backend-0.0.1.jar /usr/local/lib/backend-0.0.1.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/usr/local/lib/backend-0.0.1.jar"]