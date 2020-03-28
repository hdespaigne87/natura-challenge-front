package com.natura.challenge.front.api_config;

import org.springframework.web.client.RestTemplate;

public class ApiRestConfig {

    public static final String API_URL_BASE = "http://localhost:8081/";

    public static RestTemplate getRestTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate;
    }
}
