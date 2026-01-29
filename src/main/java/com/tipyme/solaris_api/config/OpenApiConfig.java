package com.tipyme.solaris_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;

@Configuration
public class OpenApiConfig {
    private static final String SCHEME_NAME = "bearerAuth";
    private static final String BEARER_FORMAT = "JWT";
    private static final String DESCRIPTION = "JWT Bearer token authentication";

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
        .addSecurityItem(
            new SecurityRequirement().addList(SCHEME_NAME)
        )
        .components(
            new Components().addSecuritySchemes(SCHEME_NAME, 
                new SecurityScheme()
                    .name(SCHEME_NAME)
                    .type(SecurityScheme.Type.HTTP)
                    .scheme("bearer")
                    .bearerFormat(BEARER_FORMAT)
                    .in(SecurityScheme.In.HEADER)
                    .description(DESCRIPTION)
            )
        )
        .info(
            new Info()
            .title("Solaris API")
            .version("1.0")
            .description("Solaris API")
            .contact(
                new Contact()
                .name("TI PyME")
                .email("support@typyme.com.mx")
                .url("https://typyme.com.mx")
            )
        );
    }
}
