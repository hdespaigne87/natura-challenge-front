package com.natura.challenge.front.nurseapp.user_story_3.service;

import com.natura.challenge.front.api_config.ApiRestConfig;
import com.natura.challenge.front.nurseapp.domain.Patient;
import com.natura.challenge.front.nurseapp.domain.ViewVitalSign;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Optional;

public class UserStory3ServiceImpl implements UserStory3Service {

    @Override
    public Optional<Patient> findPatientByDNI(String patientDNI) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(ApiRestConfig.API_URL_BASE)
                .path("userStory3/findPatientByDNI/" + patientDNI);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Patient> resultEntity = restTemplate.getForEntity(builder.toUriString(), Patient.class);
        return Optional.ofNullable(resultEntity.getBody());
    }

    @Override
    public List<ViewVitalSign> findVitalSignsByMonthAndYear(String patientId, int month, int year) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(ApiRestConfig.API_URL_BASE)
                .path("userStory3/findVitalSignsByMonthAndYear")
                .queryParam("patientId", patientId)
                .queryParam("month", month)
                .queryParam("year", year);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<ViewVitalSign>> resultEntity = restTemplate.exchange(builder.toUriString(), HttpMethod.GET,
                null, new ParameterizedTypeReference<List<ViewVitalSign>>() {
                });
        return resultEntity.getBody();
    }
}
