/*******************************************************************************
 * Copyright (C) 2021
 ******************************************************************************/
package com.springboot.demo.config;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.StringVendorExtension;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spring.web.plugins.Docket;


/**
 * Swagger configuration.
 *
 * @author Priyanka Hatgine
 */
@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Spring boot demo API")
                        .description("Spring boot demo application")
                        .version("v1.0.0")
                        .license(new License().name("License").url("https://cognologix.com/")))
                        .externalDocs(new ExternalDocumentation()
                        .description("Spring Boot Demo Wiki Documentation")
                        .url("https://cognologix.com/"));
    }
}
