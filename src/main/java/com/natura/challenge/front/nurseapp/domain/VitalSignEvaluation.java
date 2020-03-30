package com.natura.challenge.front.nurseapp.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VitalSignEvaluation {

    private String bloodPressure;
    private String heartRate;
}
