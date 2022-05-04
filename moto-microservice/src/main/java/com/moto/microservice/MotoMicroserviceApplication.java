package com.moto.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class MotoMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MotoMicroserviceApplication.class, args);
	}

}
