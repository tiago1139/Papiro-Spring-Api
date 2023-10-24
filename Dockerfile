FROM maven:3.6.3-jdk-8 AS build
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:8-jdk-slim-bullseye
COPY --from=build /target/papiro-app-0.0.1-SNAPSHOT.jar papiro-app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar", "papiro-app.jar"]
