package com.natura.challenge.front.nurseapp.lazyDataModels;

import com.natura.challenge.front.nurseapp.domain.Patient;
import com.natura.challenge.front.nurseapp.lazyDataModels.service.PatientListService;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public class PatientLazyDataModel extends LazyDataModel<Patient> {

    private PatientListService patientListService;

    public PatientLazyDataModel(PatientListService patientListService) {
        this.patientListService = patientListService;
    }

    @Override
    public List<Patient> load(int first, int pageSize, String sortField, SortOrder sortOrder,
                              Map<String, Object> filters) {
        //order
        Sort configOrder = Sort.unsorted();
        if (sortField != null && !sortField.isEmpty()) {
            Sort.Direction criteria = Sort.Direction.ASC;
            if (sortOrder != null)
                criteria = sortOrder.equals(SortOrder.ASCENDING) ? Sort.Direction.ASC : Sort.Direction.DESC;
            configOrder = Sort.by(criteria, sortField);
        }

        //Filters
        Optional<String> filterByName = Optional.empty();
        if (filters != null)
            filterByName = Optional.ofNullable((String) filters.get("name"));

        Optional<Integer> filterByAge = Optional.empty();
        if (filters != null)
            filterByAge = Optional.ofNullable((Integer) filters.get("age"));

        //pagination
        int pageNumber = first / pageSize;
        Pageable configPagination = PageRequest.of(pageNumber, pageSize, configOrder);
        Page<Patient> pagedResult = patientListService.listPatients(configPagination, filterByName, filterByAge);

        //total elements
        this.setRowCount((int) pagedResult.getTotalElements());

        return pagedResult.getContent();
    }
}
