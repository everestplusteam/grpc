FROM openjdk:8-jdk-alpine
MAINTAINER qingqingzhao
add gRpc-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8888
EXPOSE 9898
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]