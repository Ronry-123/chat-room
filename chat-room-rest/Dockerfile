FROM openjdk:8-jdk-alpine
ADD /target/*.jar /home/app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/home/app.jar"]