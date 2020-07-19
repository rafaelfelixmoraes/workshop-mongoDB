package br.com.rafaelfelix.workshop.mongo.config;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	private static final Contact DEFAULT_CONTACT = new Contact(
			"Rafael Felix de Moraes", 
			"https://rafaelfelixmoraes.github.io/",
			"dickinson.rafael@gmail.com");

	private static final Set<String> DEFAULT_CONSUMES_AND_PRODUCES = new HashSet<String>(
			Arrays.asList("application/json", "application/xml"));

	@Bean
	public Docket api () {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(defaultApiInfo())
				.consumes(DEFAULT_CONSUMES_AND_PRODUCES)
				.produces(DEFAULT_CONSUMES_AND_PRODUCES)
				.select()
				.apis(RequestHandlerSelectors.basePackage("br.com.rafaelfelix.workshop.mongo.resources"))
				.paths(PathSelectors.any())
				.build();
	}

  private ApiInfo defaultApiInfo() {
	  String version = getClass().getPackage().getImplementationVersion();
    return new ApiInfoBuilder()
        .title("Workshop MongoDB - APIs")
        .description("\"REST APIs for praticle MongoDB workshop with Java/Spring\"")
        .version(version != null ? version : "1.0.0")
        .license("Apache License Version 2.0")
        .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0\"")
        .contact(DEFAULT_CONTACT)
        .build();
  }
}
