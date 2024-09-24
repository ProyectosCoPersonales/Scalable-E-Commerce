package com.eureka_service_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServiceApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaServiceApiApplication.class, args);
	}

}
