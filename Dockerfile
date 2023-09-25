FROM openjdk:8
EXPOSE 8080
ADD target/weather-pridiction-api-0.0.1-SNAPSHOT.jar /tmp/weather-pridiction-api-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/tmp/weather-pridiction-api-0.0.1-SNAPSHOT.jar"]