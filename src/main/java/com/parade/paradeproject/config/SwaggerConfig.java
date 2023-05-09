package com.parade.paradeproject.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author christinehsieh on 2023/5/3
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI(){

        SecurityScheme scheme = new SecurityScheme();
        scheme.type(SecurityScheme.Type.HTTP)
                .bearerFormat("JWT")
                .scheme("bearer")
                .in(SecurityScheme.In.HEADER)
                .name("Authorization");

        SecurityRequirement requirement = new SecurityRequirement();
        requirement.addList("JWT");


        return new OpenAPI()
                .components(new Components().addSecuritySchemes("JWT", scheme))
                .addSecurityItem(requirement);


    }

}
