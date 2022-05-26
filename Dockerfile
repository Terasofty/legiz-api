# MAVEN
FROM maven:3.8.3-openjdk-17-slim AS MAVEN_BUILD
LABEL mantainer="alessandro"
COPY ./ ./
RUN mvn clean package
# JAVA
FROM openjdk:17
VOLUME /tmp
EXPOSE 8080
COPY --from=MAVEN_BUILD /target/legiz-api-0.0.1-SNAPSHOT.jar /app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]