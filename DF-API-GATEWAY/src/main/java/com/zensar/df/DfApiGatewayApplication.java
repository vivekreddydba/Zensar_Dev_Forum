package com.zensar.df;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient

public class DfApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(DfApiGatewayApplication.class, args);
	}

}
