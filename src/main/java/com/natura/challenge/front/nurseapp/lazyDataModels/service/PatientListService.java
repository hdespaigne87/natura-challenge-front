package com.natura.challenge.front.nurseapp.lazyDataModels.service;

import com.natura.challenge.front.nurseapp.domain.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface PatientListService {

    Page<Patient> listPatients(Pageable configPagination, Optional<String> filterByName, Optional<Integer> filterByAge);
}
