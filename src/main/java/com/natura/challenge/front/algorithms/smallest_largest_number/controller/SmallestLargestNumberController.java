package com.natura.challenge.front.algorithms.smallest_largest_number.controller;

import com.natura.challenge.front.algorithms.smallest_largest_number.dto.SmallestLargestNumberResult;
import com.natura.challenge.front.algorithms.smallest_largest_number.service.SmallestLargestNumberService;
import com.natura.challenge.front.algorithms.smallest_largest_number.service.SmallestLargestNumberServiceImpl;
import com.natura.challenge.front.api_config.ApiRestConfig;
import lombok.Data;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Named
@ViewScoped
@Data
public class SmallestLargestNumberController implements Serializable {

    private Integer number;
    private List<Integer> listNumbers;
    private SmallestLargestNumberResult result;
    private SmallestLargestNumberService smallestLargestNumberService;

    @PostConstruct
    public void init() {
        smallestLargestNumberService = new SmallestLargestNumberServiceImpl();
    }

    public void addNumberToList() {
        if (listNumbers == null)
            listNumbers = new ArrayList<>();
        if (number != null) {
            listNumbers.add(number);
        }
        number = null;
    }

    public void clearList() {
        listNumbers = new ArrayList<>();
    }

    public void findSmallestLargestNumber() {
        if (listNumbers == null || listNumbers.isEmpty()) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage("listNumbers", new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Enter almost one number"));
            return;
        }
        result = smallestLargestNumberService.findSmallestLargestNumber(listNumbers);
    }
}
