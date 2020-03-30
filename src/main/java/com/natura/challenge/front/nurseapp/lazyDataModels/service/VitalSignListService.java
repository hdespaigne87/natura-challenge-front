package com.natura.challenge.front.nurseapp.lazyDataModels.service;

import com.natura.challenge.front.nurseapp.domain.VitalSign;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.Optional;

public interface VitalSignListService {

    Page<VitalSign> listVitalSigns(String patientId, Pageable configPagination, Optional<Date> filterByDate);
}
