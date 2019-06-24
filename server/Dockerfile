FROM gradle:5.2.1-jdk8-slim as builder
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle bootJar

FROM openjdk:8-jre-slim
EXPOSE 9000
COPY --from=builder /home/gradle/src/build/libs/server-2.0-RELEASE.jar autoshowroom.jar
ENTRYPOINT ["java", "-jar", "autoshowroom.jar"]