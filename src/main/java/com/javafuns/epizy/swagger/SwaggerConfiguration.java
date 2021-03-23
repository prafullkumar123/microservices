/*
 * @Copyright javaFuns Pvt Ltd .All rights Reserved 2020. You should not disclose this information
 * Otherwise terms and conditions will be applied.
 */
package com.javafuns.epizy.swagger;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import com.google.common.collect.Lists;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration   {

	@Bean
	public Docket docket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.ignoredParameterTypes(AuthenticationPrincipal.class)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.javafuns.epizy.controller"))
				.paths(PathSelectors.regex("/rest.*"))
				.build()
				.securitySchemes(Lists.newArrayList(apiKey()))
				.securityContexts(Arrays.asList(securityContext()));

	}

	private ApiKey apiKey() {
		return new ApiKey("apiKey", "Authorization", "header");
	}

	private SecurityContext securityContext() {
		return SecurityContext.builder()
				.securityReferences(defaultAuth())
				.forPaths(PathSelectors.any()).build();
	}

	private List<SecurityReference> defaultAuth() {
		AuthorizationScope authorizationScope = new AuthorizationScope(
				"global", "accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		return Arrays.asList(new SecurityReference("apiKey",authorizationScopes));
	}
	//https://stackoverflow.com/questions/48442416/cannot-send-authorization-bearer-token-using-springfox
}