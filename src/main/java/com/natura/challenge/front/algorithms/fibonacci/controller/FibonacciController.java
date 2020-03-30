package com.natura.challenge.front.algorithms.fibonacci.controller;

import com.natura.challenge.front.algorithms.fibonacci.dto.FibonacciCalculationMethod;
import com.natura.challenge.front.algorithms.fibonacci.dto.FibonacciResult;
import com.natura.challenge.front.algorithms.fibonacci.service.FibonacciService;
import com.natura.challenge.front.algorithms.fibonacci.service.FibonacciServiceImpl;
import lombok.Data;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
@Data
public class FibonacciController implements Serializable {

    private Integer number;
    private FibonacciResult result;
    private FibonacciService fibonacciService;

    @PostConstruct
    public void init() {
        fibonacciService = new FibonacciServiceImpl();
    }

    public void calculate(FibonacciCalculationMethod method) throws Exception {
        result = fibonacciService.calculate(number, method);
    }
}
