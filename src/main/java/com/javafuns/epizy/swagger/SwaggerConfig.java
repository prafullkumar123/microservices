package com.javafuns.epizy.swagger;
//package com.epizy.javaFuns.swagger;
//import java.util.Arrays;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import springfox.documentation.builders.ApiInfoBuilder;
//import springfox.documentation.builders.PathSelectors;
//import springfox.documentation.builders.RequestHandlerSelectors;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.service.ApiKey;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//@Configuration
//@EnableSwagger2
//public class SwaggerConfig { 
//	@Bean     
//	public Docket myApi() {  
//		return new Docket (DocumentationType.SWAGGER_2)    
//				.select()           
//				.paths(PathSelectors.regex("/rest.*")) 
//				.apis(RequestHandlerSelectors.basePackage("com.epizy.javaFuns.controller"))
//				.paths(PathSelectors.any())
//				.build()
//				.enable(true)
//				.apiInfo(apiInfo())
//				.securitySchemes(Arrays.asList(apiKey()));
//	}
//
//	private ApiInfo apiInfo() {
//		return new ApiInfoBuilder().title("REST API")
//				.description("The REST API for demo swagger.").termsOfServiceUrl("")
//				.license("Apache License Version 2.0")
//				.licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
//				.version("0.0.1")
//				.build();
//	}
//	//https://gist.github.com/RICH0423/2515552e957e608cff973310467d5db9
//	private ApiKey apiKey() {
//		return new ApiKey("authkey", "Authorization", "header");
//	}
//}
