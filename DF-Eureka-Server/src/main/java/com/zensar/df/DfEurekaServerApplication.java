package com.zensar.df;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class DfEurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DfEurekaServerApplication.class, args);
	}

}
