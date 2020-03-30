package com.natura.challenge.front.algorithms.smallest_largest_number.service;

import com.natura.challenge.front.algorithms.smallest_largest_number.dto.SmallestLargestNumberResult;

import java.util.List;

public interface SmallestLargestNumberService {

    SmallestLargestNumberResult findSmallestLargestNumber(List<Integer> listNumbers);
}
