package com.natura.challenge.front.algorithms.fibonacci.controller;

import com.natura.challenge.front.algorithms.fibonacci.dto.FibonacciCalculationMethod;
import com.natura.challenge.front.algorithms.fibonacci.dto.FibonacciResult;
import com.natura.challenge.front.api_config.ApiRestConfig;
import lombok.Data;
import org.primefaces.PrimeFaces;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named("fibonacciController")
@ViewScoped
@Data
public class FibonacciController implements Serializable {

    private Integer number;
    private FibonacciResult result;

    public void calculate(FibonacciCalculationMethod method) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(ApiRestConfig.API_URL_BASE)
                .path("fibonacci")
                .queryParam("number", number)
                .queryParam("method", method);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<FibonacciResult> resultEntity = restTemplate.getForEntity(builder.toUriString(), FibonacciResult.class);
        result = resultEntity.getBody();
    }
}
