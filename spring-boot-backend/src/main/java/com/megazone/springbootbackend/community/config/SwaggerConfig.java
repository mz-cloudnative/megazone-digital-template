package com.megazone.springbootbackend.community.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {

        // SecuritySecheme명
        String jwtSchemeName = "jwtAuth";
        // API 요청헤더에 인증정보 포함
        SecurityRequirement securityRequirement = new SecurityRequirement().addList(jwtSchemeName);
        // SecuritySchemes 등록
        Components components = new Components()
                .addSecuritySchemes(jwtSchemeName, new SecurityScheme()
                        .name(jwtSchemeName)
                        .type(SecurityScheme.Type.HTTP) // HTTP 방식
                        .scheme("Bearer")
                        .bearerFormat("JWT")); // 토큰 형식을 지정하는 임의의 문자(Optional)

        return new OpenAPI()
                .info(customTitle())
                .addSecurityItem(securityRequirement)
                .components(components)
                .externalDocs(customExtDoc());
    }

    @Bean
    public Info customTitle() {
        return new Info().title("CUSTOM OPEN API API")
                .description("sample application")
                .version("v0.0.1")
                .license(customLicense());
    }

    @Bean
    public License customLicense() {
        return new License()
                .name("Apache 2.0")
                .url("http://springdoc.org");
    }

    @Bean
    public ExternalDocumentation customExtDoc() {
        return new ExternalDocumentation()
                .description("spring Documentation")
                .url("https://docs.spring.io/spring-framework/docs/current/reference/html/");
    }

}
