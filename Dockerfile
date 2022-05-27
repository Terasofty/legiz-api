# MAVEN
FROM maven:3.8.3-openjdk-17-slim AS maven_build
LABEL mantainer="alessandro"
COPY . ./
RUN --mount=type=cache,target=/root/.m2 mvn wrapper:wrapper
# JAVA
FROM openjdk:17 AS base
WORKDIR /app
COPY . /app
COPY --from=maven_build /.mvn/* /app/.mvn/
RUN ./mvnw dependency:go-offline

FROM base as test
CMD ["./mvnw", "test"]

FROM base as development
CMD ["./mvnw", "spring-boot:run", "-Dspring-boot.run.jvmArguments='-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:8000'"]
FROM base as build
RUN ./mvnw clean package -DskipTests