FROM gradle:7-jdk11 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build -x test --no-daemon

FROM openjdk:11
EXPOSE 8080
RUN mkdir /app
COPY --from=build /home/gradle/src/src/infrastructure/build/libs/*.jar /app/spring-boot-application.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/spring-boot-application.jar"]