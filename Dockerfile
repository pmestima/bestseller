FROM eclipse-temurin:17-jre-alpine

COPY /build/libs/assignment-0.0.1-SNAPSHOT.jar app.jar

WORKDIR /app

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
