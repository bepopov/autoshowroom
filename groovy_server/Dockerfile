FROM openjdk:8-jre-slim
EXPOSE 8085
COPY build/libs/groovy_server-1.0.0-SNAPSHOT.jar groovy_server.jar
ENTRYPOINT ["java", "-jar", "groovy_server.jar"]