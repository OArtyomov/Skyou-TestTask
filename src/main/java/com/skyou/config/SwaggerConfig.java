package com.skyou.config;

import com.google.common.collect.ImmutableSet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.annotations.ApiIgnore;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.Set;

import static springfox.documentation.builders.PathSelectors.any;
import static springfox.documentation.builders.RequestHandlerSelectors.basePackage;
import static springfox.documentation.spi.DocumentationType.SWAGGER_2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api() {
		return new Docket(SWAGGER_2)
				.forCodeGeneration(true)
				.apiInfo(apiInfo())
				.ignoredParameterTypes(ignoredTypes())
				.alternateTypeRules()
				.pathProvider(new AlwaysRootPathProvider())
				.select()
				.apis(basePackage("com.skyou"))
				.paths(any())
				.build();
	}

	private Set<Class> findIgnoredEntities() {
		return Collections.emptySet();
	}

	private Class[] ignoredTypes() {
		return ImmutableSet.<Class>builder()
				.add(ApiIgnore.class)
				.addAll(findIgnoredEntities())
				.build().toArray(new Class[]{});
	}

	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("ZCommerce API Description")
				.description("ZCommerce Public API")
				.termsOfServiceUrl("http://springfox.io")
				.contact(new Contact("Development Team", null, null))
				.license("Apache License Version 2.0")
				.licenseUrl("https://github.com/springfox/springfox/blob/master/LICENSE")
				.version("1.0").build();
	}

}