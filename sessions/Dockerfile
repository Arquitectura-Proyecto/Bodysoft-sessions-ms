FROM openjdk:14-jdk-alpine
ARG JAR_FILE=target/sessions-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
EXPOSE 4000
ENTRYPOINT ["java","-jar","/app.jar"]
