package com.megazone.springbootbackend.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;

@Configuration
@Slf4j
public class WebClientConfig {
    @Value("${spring.rest.connect-time}")
    private int connectTimeout;
    @Value("${spring.rest.read-time}")
    private int readTimeout;
    @Value("${info.external.url}")
    private String url;
    @Value("${info.external.headerKey.key}")
    private String headerKeyKey;
    @Value("${info.external.headerKey.host}")
    private String headerKeyHost;
    @Value("${info.external.headerValue.key}")
    private String headerValueKey;
    @Value("${info.external.headerValue.host}")
    private String headerValueHost;

    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl(url)
                .defaultHeader(headerKeyKey, headerValueKey)
                .defaultHeader(headerKeyHost, headerValueHost)
                .clientConnector(new ReactorClientHttpConnector(httpClient()))
                .build();
    }

    private HttpClient httpClient() {
        return HttpClient.create()
                .responseTimeout(Duration.ofSeconds(5));
    }
}
