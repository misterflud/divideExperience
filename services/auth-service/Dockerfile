FROM openjdk:8-jdk-alpine

MAINTAINER Anton Oleynikov <yurolejniko@yandex.ru>

#VOLUME /tmp
#COPY target/*.jar app.jar
ADD target/auth-service.jar app.jar

#RUN bash -c 'touch /app.jar'

ENTRYPOINT java -Dspring.profiles.active=$PROFILE -jar /app.jar