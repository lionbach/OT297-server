package com.alkemy.ong.SwaggerConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.alkemy.ong.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                .securityContexts(Arrays.asList(securityContext()))
                .securitySchemes(Arrays.asList(apiKey()));
    }

    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo(
                "ONG OT297 API",
                "API developed for Alkemy Aceleration by Guillermo Pintos, Juan Ignacio Otturi and Luis Leonardo Mumbach, with Francisco Mastucci as project mentor. This fork is maintained by Luis Leonardo Mumbach.",
                "0.0.1-SNAPSHOT",
                "https://github.com/lionbach/OT297-server",
                new Contact("Luis Leonardo Mumbach", "https://github.com/lionbach", "leomumbach@gmail.com"),
                "License of API",
                "https://github.com/lionbach/OT297-server",
                Collections.emptyList());
        return apiInfo;
    }


    private ApiKey apiKey(){ return new ApiKey("Bearer", "Authorization", "header"); }

    private SecurityContext securityContext(){
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .build();
    }

    private List<SecurityReference> defaultAuth(){
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Arrays.asList(new SecurityReference("Bearer", authorizationScopes));
    }

}
