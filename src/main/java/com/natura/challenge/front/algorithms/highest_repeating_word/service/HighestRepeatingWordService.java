package com.natura.challenge.front.algorithms.highest_repeating_word.service;

import com.natura.challenge.front.algorithms.highest_repeating_word.dto.HighestRepeatingWordResult;

import java.io.File;

public interface HighestRepeatingWordService {

    HighestRepeatingWordResult findHighestRepeatingWord(File fileObj);
}
