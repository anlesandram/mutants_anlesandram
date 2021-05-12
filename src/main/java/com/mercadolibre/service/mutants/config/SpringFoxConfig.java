package com.mercadolibre.service.mutants.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

@Configuration
public class SpringFoxConfig {

    public static final String APP_NAME = "Mutant Application - Mercado Libre";
    public static final String APP_DESCRIPTION = "The app is designed to identify mutants using the DNA sequence";
    public static final String APP_OWNER = "Angela Andrade";
    public static final String MAIL = "anlesandram@gmail.com";
    public static final String PACKAGE_SERVICES = "com.mercadolibre.service.mutants.controller";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(PACKAGE_SERVICES))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                APP_NAME,
                APP_DESCRIPTION,
                "",
                "",
                new Contact(APP_OWNER, "", MAIL),
                "",
                "",
                Collections.emptyList());
    }
}

