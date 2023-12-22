FROM openjdk:11-jre-slim

EXPOSE 8080

COPY target/customer-service-0.0.1-SNAPSHOT.jar customer-service.jar

CMD ["java", "-jar", "/customer-service.jar"]