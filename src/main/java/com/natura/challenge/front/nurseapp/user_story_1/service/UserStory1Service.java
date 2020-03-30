package com.natura.challenge.front.nurseapp.user_story_1.service;

import com.natura.challenge.front.nurseapp.domain.Patient;
import com.natura.challenge.front.nurseapp.domain.VitalSign;

import java.util.Optional;

public interface UserStory1Service {

    void savePatient(Patient patient);

    void saveVitalSign(VitalSign vitalSign);

    void deleteVitalSign(String id);

    void deletePatient(String id);

    boolean existsPatientByDNI(String patientDNI, Optional<String> ignorePatientId);
}
