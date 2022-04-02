package com.example.covenant.journey.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.BasicAuth;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.spi.service.contexts.SecurityContext;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	@Profile({"swagger"})
	public Docket getSwaggerApiConfigs() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("main_api")
				.pathMapping("/")
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.example.covenant.journey.api"))
				.paths(PathSelectors.ant("/**"))
				.build()
				.securityContexts(getSecurityContexts())
				.securitySchemes(getSecuritySchemes());
	}

	private List<SecurityScheme> getSecuritySchemes() {
		return List.of(new BasicAuth("auth0"));
	}

	private List<SecurityContext> getSecurityContexts() {
		SecurityReference authReference = new SecurityReference("auth0", new AuthorizationScope[0]);
		return List.of(
				SecurityContext
						.builder()
						.securityReferences(List.of(authReference))
						.build());
	}

}
