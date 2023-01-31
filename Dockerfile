FROM maven:3-openjdk-11-slim AS builder

WORKDIR /app

COPY commons/target/commons-0.0.1-SNAPSHOT.jar commons/target
COPY consumer-microservice/target/consumer-microservice-0.0.1-SNAPSHOT.jar consumer-microservice/target
COPY email-api/target/email-api-0.0.1-SNAPSHOT.jar email-api/target
COPY subs-api/target/subs-api-0.0.1-SNAPSHOT.jar subs-api/target

RUN echo hello-world
