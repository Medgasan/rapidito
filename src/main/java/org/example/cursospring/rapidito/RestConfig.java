package org.example.cursospring.rapidito;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class RestConfig {

    @Value("${app.api.base-url:http://localhost:8080/api}")
    private String apiBaseUrl;

    @Bean
    public RestClient restClient() {
        return RestClient.builder()
                .baseUrl(apiBaseUrl)
                .build();
    }
}
