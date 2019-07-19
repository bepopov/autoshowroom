FROM openjdk:8-jre-slim
EXPOSE 9000
EXPOSE 6565
COPY build/libs/server-2.0-RELEASE.jar autoshowroom.jar
ENTRYPOINT ["java", "-jar", "autoshowroom.jar"]