FROM openjdk:11-jre-slim

ENV PORT 8000
ENV DB_HOST 127.0.0.1
ENV DB_NAME customer_service_db

EXPOSE $PORT

COPY target/customer-service-0.0.1-SNAPSHOT.jar customer-service.jar

CMD ["java", "-jar", "/customer-service.jar"]