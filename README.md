# weather-pridiction-api

This project is created as per the requirements of the assignment provided by publicis sapient.
The purpose of this project is to consume the OpenWeatherMap api.

### Design Principles :
This api has been developed using SOLID design principles and 12 Factor app methodology. More info about SOLID
design principles can be found on https://en.wikipedia.org/wiki/`SOLID` and about 12 Factor here : https://en.wikipedia.org/wiki/Twelve-Factor_App_methodology

### Requirements:

The assignment

• Develop, test and deploy a micro service to show the output of a city's next 3 days high and low temperatures.
If rain is predicted or temperature goes above 40 degree celsius then mention 'Carry umbrella' or
'Use sunscreen lotion' respectively, in the output, for that day.



## Tech Stack

* Since I am java programmer and I've been using spring framework from past 3 years .
  The choice was quite obvious to use Spring Boot .
  Other than spring boot , maven , junit, docker , swagger, tomcat were used.

## Installation

This project is java-based. So It requires Jdk 8 (or later) and Maven 3.8.6 (or later)  to run.

```sh
$ cd weather-pridiction-api
$ mvn package
$ java -jar target/weather-pridiction-api-0.0.1-SNAPSHOT.jar 
```

Or if there is need to build new image then it can also be build and pushed to
docker hub via

```sh
$ cd weather-pridiction-api
$ docker build -t rjpraj/weather-predict-api:latest .
$ docker push rjpraj/weather-predict-api:latest 
```
And then run to start application
```sh
$ docker run -p 8080:8080 docker.io/rjpraj/weather-predict-api:latest
```

## Running tests
After changes you can run tests using Maven command:
```sh
$ cd weather-pridiction-api
$ mvn test
```

## Test Rest API

As required, this API has 1 endpoint :
1. /v1/weather/{city}

If application is started using java -jar command then access api on :
http://localhost:8080/v1/weather/pune

If application is started using docker run command, then access api at :
http://host.docker.internal:8080/v1/weather/pune

## Error Handling
1. In case of user passes a invalid city, error is displayed with message : "Weather not found for City"
2. In case OpenWeather Api is down, error message "Error fetching data, please try later" is displayed.
3. In case OpenWeather Api return null data, error message "External api sent malformed data" is displayed.

## Testing API
1. API can be tested using Postman/Soap Ui
2. Swagger is also included in api , I would suggest to use swagger then there is no need to perform extra work.


3. Swagger is included and automatic update of swagger specification is done.

## Things included in current version :
1. CI/CD is also be implemented for this api.
2. Dockerfile to create images and push to docker Hub
3. Current version  support maven/spring profiles , with dev and prod profiles.
