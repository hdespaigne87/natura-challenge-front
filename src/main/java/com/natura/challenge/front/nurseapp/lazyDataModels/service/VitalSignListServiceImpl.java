package com.natura.challenge.front.nurseapp.lazyDataModels.service;

import com.natura.challenge.front.api_config.ApiRestConfig;
import com.natura.challenge.front.nurseapp.domain.VitalSign;
import com.natura.challenge.front.nurseapp.lazyDataModels.helper.RestPage;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.Optional;

public class VitalSignListServiceImpl implements VitalSignListService {

    @Override
    public Page<VitalSign> listVitalSigns(String patientId, Pageable configPagination, Optional<Date> filterByDate) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(ApiRestConfig.API_URL_BASE)
                .path(String.format("userStory1/patients/%s/vitalSigns", patientId))
                .queryParam("page", configPagination.getPageNumber())
                .queryParam("size", configPagination.getPageSize());
        if (configPagination.getSort().isSorted()) {
            Iterator<Sort.Order> orderIt = configPagination.getSort().iterator();
            while (orderIt.hasNext()) {
                Sort.Order order = orderIt.next();
                builder.queryParam("sort", String.format("%s,%s", order.getProperty(), order.getDirection()));
            }
        }
        if (filterByDate.isPresent()) {
            String dateStr = new SimpleDateFormat("MM/dd/yyyy").format(filterByDate.get());
            builder.queryParam("filterByDate", dateStr);
        }
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<RestPage<VitalSign>> resultEntity = restTemplate.exchange(builder.toUriString(), HttpMethod.GET,
                null, new ParameterizedTypeReference<RestPage<VitalSign>>() {
                });
        return resultEntity.getBody();
    }
}
