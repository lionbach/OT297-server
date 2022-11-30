FROM eclipse-temurin:11-jdk-alpine as build
WORKDIR /app

COPY mvnw .
COPY .mvn .mvn
COPY pom.xml .
COPY src src

RUN ./mvnw clean package -DskipTests

FROM eclipse-temurin:11-jre-alpine
COPY --from=build /app/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
# for build execute:
# docker build -t ot297server:0.1 .