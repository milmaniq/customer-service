FROM openjdk:11-jre-slim

ENV PORT 8080

# ENV DB_HOST 127.0.0.1:3307
# ENV DB_NAME customer_service_db
# ENV DB_USERNAME root
# ENV DB_PASSWORD 123456

EXPOSE $PORT

COPY target/customer-service-0.0.1-SNAPSHOT.jar customer-service.jar

CMD ["java", "-jar", "/customer-service.jar"]