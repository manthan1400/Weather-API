FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/weather-0.0.1-SNAPSHOT.jar.jar app.jar
EXPOSE 9090
ENTRYPOINT ["java", "-jar", "app.jar"]
