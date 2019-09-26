FROM openjdk:8-jdk-alpine

MAINTAINER qingqingzhao

add gRpc-clent-ui-0.0.1-SNAPSHOT.jar web.jar

EXPOSE 8080

ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/web.jar"]