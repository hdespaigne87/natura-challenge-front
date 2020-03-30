package com.natura.challenge.front.nurseapp.lazyDataModels;

import com.natura.challenge.front.nurseapp.domain.VitalSign;
import com.natura.challenge.front.nurseapp.lazyDataModels.service.VitalSignListService;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class VitalSignsLazyDataModel extends LazyDataModel<VitalSign> {

    private String patientId;
    private VitalSignListService vitalSignListService;

    public VitalSignsLazyDataModel(String patientId, VitalSignListService vitalSignListService) {
        this.patientId = patientId;
        this.vitalSignListService = vitalSignListService;
    }

    @Override
    public List<VitalSign> load(int first, int pageSize, String sortField, SortOrder sortOrder,
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
        Optional<Date> filterByDate = Optional.empty();
        if (filters != null) {
            filterByDate = Optional.ofNullable((Date) filters.get("registrationMoment"));
        }


        //pagination
        int pageNumber = first / pageSize;
        Pageable configPagination = PageRequest.of(pageNumber, pageSize, configOrder);
        Page<VitalSign> pagedResult = vitalSignListService.listVitalSigns(patientId, configPagination, filterByDate);

        //total elements
        this.setRowCount((int) pagedResult.getTotalElements());

        return pagedResult.getContent();
    }
}
