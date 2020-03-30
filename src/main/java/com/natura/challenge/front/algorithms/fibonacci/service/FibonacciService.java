package com.natura.challenge.front.algorithms.fibonacci.service;

import com.natura.challenge.front.algorithms.fibonacci.dto.FibonacciCalculationMethod;
import com.natura.challenge.front.algorithms.fibonacci.dto.FibonacciResult;

public interface FibonacciService {

    FibonacciResult calculate(Integer number, FibonacciCalculationMethod method) throws Exception;
}
