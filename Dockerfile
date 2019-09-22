FROM openjdk:8-jdk-alpine
RUN apk --no-cache add curl
ADD build/libs/Skyou-TestTask.jar /app/application.jar
ENTRYPOINT ["java","-jar","/app/application.jar"]
