package com.publicis.sapient.weatherpridictionapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignAutoConfiguration;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.publicis.sapient.weatherpridictionapi.client")
public class WeatherPridictionApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(WeatherPridictionApiApplication.class, args);
	}
}
