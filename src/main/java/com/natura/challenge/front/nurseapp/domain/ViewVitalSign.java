package com.natura.challenge.front.nurseapp.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ViewVitalSign {

    private String id;
    private String patientId;
    @JsonFormat(pattern = "MM/dd/yyyy HH:mm")
    private Date registrationMoment;
    private Integer bloodPressureSystolic;
    private Integer bloodPressureDiastolic;
    private Integer heartRate;
    private Integer monthRegistration;
    private Integer yearRegistration;
}
