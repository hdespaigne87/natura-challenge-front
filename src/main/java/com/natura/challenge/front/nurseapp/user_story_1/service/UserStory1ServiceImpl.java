package com.natura.challenge.front.nurseapp.user_story_1.service;

import com.natura.challenge.front.api_config.ApiRestConfig;
import com.natura.challenge.front.nurseapp.domain.Patient;
import com.natura.challenge.front.nurseapp.domain.VitalSign;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Optional;

public class UserStory1ServiceImpl implements UserStory1Service {

    @Override
    public void savePatient(Patient patient) {
        HttpMethod httpMethod = HttpMethod.POST;
        if (patient.getId() != null) // Is editing
            httpMethod = HttpMethod.POST;
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(ApiRestConfig.API_URL_BASE)
                .path("userStory1/patients");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.exchange(builder.toUriString(), httpMethod, new HttpEntity<>(patient), Void.class);
    }

    @Override
    public void saveVitalSign(VitalSign vitalSign) {
        HttpMethod httpMethod = HttpMethod.POST;
        if (vitalSign.getId() != null) // Is editing
            httpMethod = HttpMethod.POST;
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(ApiRestConfig.API_URL_BASE)
                .path("userStory1/vitalSigns");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.exchange(builder.toUriString(), httpMethod, new HttpEntity<>(vitalSign), Void.class);
    }

    @Override
    public void deletePatient(String id) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(ApiRestConfig.API_URL_BASE)
                .path("userStory1/patients/" + id);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(builder.toUriString());
    }

    @Override
    public void deleteVitalSign(String id) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(ApiRestConfig.API_URL_BASE)
                .path("userStory1/vitalSigns/" + id);
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(builder.toUriString());
    }

    @Override
    public boolean existsPatientByDNI(String patientDNI, Optional<String> ignorePatientId) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(ApiRestConfig.API_URL_BASE)
                .path("userStory1/existsPatientByDNI/" + patientDNI);
        if (ignorePatientId.isPresent())
            builder.queryParam("ignoreId", ignorePatientId.get());
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Boolean> result = restTemplate.getForEntity(builder.toUriString(), Boolean.class);
        return result.getBody();
    }
}
