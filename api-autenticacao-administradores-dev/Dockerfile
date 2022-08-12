FROM openjdk:17-jdk-alpine
WORKDIR /app
ADD ./target/*.jar /app
ADD ./newrelic/newrelic.jar  /app
ADD ./newrelic/newrelic.yml  /app
ENTRYPOINT ["java","-javaagent:/app/newrelic.jar","-jar","/app/admins-0.0.1-SNAPSHOT.jar"]
