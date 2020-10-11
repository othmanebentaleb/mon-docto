FROM openjdk:8-jdk-alpine
ARG JAR_FILE=target/*.jar
EXPOSE 8081
COPY ${JAR_FILE} registration-user.jar
ENTRYPOINT ["java", "-jar", "/registration-user.jar"]