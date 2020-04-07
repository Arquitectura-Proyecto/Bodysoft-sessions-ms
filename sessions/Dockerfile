FROM openjdk:14-jdk-alpine
VOLUME /tmp
ADD target/sessions-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 4000
RUN sh -c 'touch /app.jar'
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar /app.jar" ]
