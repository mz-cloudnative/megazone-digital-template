package com.megazone.springbootbackend.config;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
    @Value("${spring.rest.connect-time}")
    private int connectTimeout;
    @Value("${spring.rest.read-time}")
    private int readTimeout;
    @Value("${info.external.headerKey.key}")
    private String headerKeyKey;
    @Value("${info.external.headerKey.host}")
    private String headerKeyHost;
    @Value("${info.external.headerValue.key}")
    private String headerValueKey;
    @Value("${info.external.headerValue.host}")
    private String headerValueHost;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate(factory());
    }

    // 헤더와 바디로 이루어진 엔티티
    @Bean
    public HttpEntity<String> httpEntity() {
        HttpHeaders header = new HttpHeaders();
        header.set(headerKeyKey, headerValueKey);
        header.set(headerKeyHost, headerValueHost);

        return new HttpEntity<>(header);
    }

    // basic contract for HTTP request execution
    private HttpClient client() {
        return HttpClientBuilder.create()
                .setMaxConnTotal(50) // Assigns maximum total connection value.
                .setMaxConnPerRoute(20) //Assigns maximum connection per route value.
                .build();
    }

    private HttpComponentsClientHttpRequestFactory factory() {
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setHttpClient(client());
        factory.setReadTimeout(readTimeout);
        factory.setConnectTimeout(connectTimeout);
        return factory;
    }
}
