package com.natura.challenge.front.nurseapp.user_story_3.service;

import com.natura.challenge.front.nurseapp.domain.Patient;
import com.natura.challenge.front.nurseapp.domain.ViewVitalSign;

import java.util.List;
import java.util.Optional;

public interface UserStory3Service {

    Optional<Patient> findPatientByDNI(String patientDNI);

    List<ViewVitalSign> findVitalSignsByMonthAndYear(String patientId, int month, int year);
}
