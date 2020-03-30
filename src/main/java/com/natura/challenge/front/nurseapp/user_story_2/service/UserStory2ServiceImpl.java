package com.natura.challenge.front.nurseapp.user_story_2.service;

import com.natura.challenge.front.api_config.ApiRestConfig;
import com.natura.challenge.front.nurseapp.domain.VitalSignEvaluation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class UserStory2ServiceImpl implements UserStory2Service {

    @Override
    public VitalSignEvaluation getVitalSignsEvaluation(String id) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(ApiRestConfig.API_URL_BASE)
                .path("userStory2/vitalSignsEvaluation/" + id);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<VitalSignEvaluation> resultEntity = restTemplate.getForEntity(builder.toUriString(), VitalSignEvaluation.class);
        return resultEntity.getBody();
    }
}
