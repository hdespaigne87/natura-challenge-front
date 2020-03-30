package com.natura.challenge.front.nurseapp.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class VitalSign {

    private String id;
    private Patient patient;
    @JsonFormat(pattern = "MM/dd/yyyy HH:mm")
    private Date registrationMoment;
    private Integer bloodPressureSystolic;
    private Integer bloodPressureDiastolic;
    private Integer heartRate;
    @JsonIgnore
    private VitalSignEvaluation evaluation;
}
