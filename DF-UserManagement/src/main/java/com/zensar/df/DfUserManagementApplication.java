package com.zensar.df;

import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@EnableEurekaClient
public class DfUserManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(DfUserManagementApplication.class, args);
	}

	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}
	@Bean
	public Docket getDocketObj() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select().apis(RequestHandlerSelectors.basePackage("com."))
						.paths(PathSelectors.any()).build().apiInfo(getAppdetails());
	}
	@Bean ApiInfo getAppdetails() {
		Contact contact = new Contact("Dev-Forum","https://Dev-Forum.com","DevForum@gmail.com");
		return new ApiInfo(
				"Dev-Forum Application",
				"Dev-Forum Management",
				"1.0.0",
				"https://devforum.com",
				contact,
				"GPL",
				"https://gplliscense.com",
				new ArrayList<VendorExtension>()
				);
	}
}
