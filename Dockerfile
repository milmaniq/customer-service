FROM openjdk:11-jre-slim

ENV PORT 8080
ENV CUSTOM_VARIABLE 'value set from dockerfile'

WORKDIR /app

COPY ./target/customer-service-0.0.1-SNAPSHOT.jar customer-service.jar

VOLUME ./logs

EXPOSE $PORT

CMD ["java", "-jar", "/app/customer-service.jar"]