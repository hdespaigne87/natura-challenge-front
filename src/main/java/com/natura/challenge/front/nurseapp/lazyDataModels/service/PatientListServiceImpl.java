package com.natura.challenge.front.nurseapp.lazyDataModels.service;

import com.natura.challenge.front.api_config.ApiRestConfig;
import com.natura.challenge.front.nurseapp.domain.Patient;
import com.natura.challenge.front.nurseapp.lazyDataModels.helper.RestPage;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Iterator;
import java.util.Optional;

public class PatientListServiceImpl implements PatientListService {

    @Override
    public Page<Patient> listPatients(Pageable configPagination, Optional<String> filterByName, Optional<Integer> filterByAge) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(ApiRestConfig.API_URL_BASE)
                .path("userStory1/patients")
                .queryParam("page", configPagination.getPageNumber())
                .queryParam("size", configPagination.getPageSize());
        if (configPagination.getSort().isSorted()) {
            Iterator<Sort.Order> orderIt = configPagination.getSort().iterator();
            while (orderIt.hasNext()) {
                Sort.Order order = orderIt.next();
                builder.queryParam("sort", String.format("%s,%s", order.getProperty(), order.getDirection()));
            }
        }
        if (filterByName.isPresent())
            builder.queryParam("filterByName", filterByName.get());
        if (filterByAge.isPresent())
            builder.queryParam("filterByAge", filterByAge.get());
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<RestPage<Patient>> resultEntity = restTemplate.exchange(builder.toUriString(), HttpMethod.GET,
                null, new ParameterizedTypeReference<RestPage<Patient>>() {
                });
        return resultEntity.getBody();
    }
}
