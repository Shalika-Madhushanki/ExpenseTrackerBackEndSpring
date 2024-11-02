FROM openjdk:11-jre-slim
WORKDIR /app
VOLUME /tmp
COPY build/libs/expense-tracker-spring-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]