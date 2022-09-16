package com.zensar.df;

import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;



@SpringBootApplication
@EnableEurekaClient
public class DfForumManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(DfForumManagementApplication.class, args);
	}

	@Bean
	public ModelMapper getModelMapper() {
		return new ModelMapper();
	}
	/*
	@Bean
	public Docket getDocketObj() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select().apis(RequestHandlerSelectors.basePackage("com."))
						.paths(PathSelectors.any()).build().apiInfo(getAppdetails());
	}
	
	
	@Bean ApiInfo getAppdetails() {
		Contact contact = new Contact("Dev-Forum","https://Dev-Forum.com","DevForum@gmail.com");
		ApiInfo appInfo = new ApiInfo(
				"Dev-Forum Application",
				"Dev-Forum Management",
				"1.0.0",
				"https://devforum.com",
				contact,
				"GPL",
				"https://gplliscense.com",
				new ArrayList<VendorExtension>()
				);
		return appInfo;
	}
	*/
	
	@Bean
	  public GroupedOpenApi publicApi() {
	      return GroupedOpenApi.builder()
	              .group("springshop-public")
	              .pathsToMatch("/public/**")
	              .build();
	}
	 @Bean
	      public GroupedOpenApi adminApi() {
	          return GroupedOpenApi.builder()
	                  .group("springshop-admin")
	                  .pathsToMatch("/admin/**")
	                  .build();
	 }
	 @Bean
	  public OpenAPI springShopOpenAPI() {
	      return new OpenAPI()
	              .info(new Info().title("SpringShop API")
	              .description("Spring shop sample application")
	              .version("v0.0.1")
	              .license(new License().name("Apache 2.0").url("http://springdoc.org")))
	              .externalDocs(new ExternalDocumentation()
	              .description("SpringShop Wiki Documentation")
	              .url("https://Dev-Forum.com"));
	  }
	
	@Bean
	@LoadBalanced
	public RestTemplate getRestTemplate() {
		return new RestTemplate();
	}
}
