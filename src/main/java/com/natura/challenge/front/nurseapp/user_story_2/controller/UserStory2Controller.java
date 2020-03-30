package com.natura.challenge.front.nurseapp.user_story_2.controller;

import com.natura.challenge.front.nurseapp.domain.Patient;
import com.natura.challenge.front.nurseapp.domain.VitalSign;
import com.natura.challenge.front.nurseapp.lazyDataModels.PatientLazyDataModel;
import com.natura.challenge.front.nurseapp.lazyDataModels.VitalSignsLazyDataModel;
import com.natura.challenge.front.nurseapp.lazyDataModels.service.PatientListService;
import com.natura.challenge.front.nurseapp.lazyDataModels.service.PatientListServiceImpl;
import com.natura.challenge.front.nurseapp.lazyDataModels.service.VitalSignListService;
import com.natura.challenge.front.nurseapp.lazyDataModels.service.VitalSignListServiceImpl;
import com.natura.challenge.front.nurseapp.user_story_2.service.UserStory2Service;
import com.natura.challenge.front.nurseapp.user_story_2.service.UserStory2ServiceImpl;
import lombok.Data;
import org.primefaces.event.ToggleEvent;
import org.primefaces.model.Visibility;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
@Data
public class UserStory2Controller implements Serializable {

    private enum View {PatientList, VitalSignList}

    private PatientLazyDataModel patients;
    private VitalSignsLazyDataModel vitalSigns;
    private Patient patient;
    private View currentView;
    private UserStory2Service userStory2Service;
    private PatientListService patientListService;
    private VitalSignListService vitalSignListService;

    @PostConstruct
    public void init() {
        patientListService = new PatientListServiceImpl();
        patients = new PatientLazyDataModel(patientListService);
        vitalSignListService = new VitalSignListServiceImpl();
        currentView = View.PatientList;
        userStory2Service = new UserStory2ServiceImpl();
    }

    public void cancelVitalSignList() {
        currentView = View.PatientList;
    }

    public void listVitalSigns(Patient patient) {
        this.patient = patient;
        vitalSigns = new VitalSignsLazyDataModel(patient.getId(), vitalSignListService);
        currentView = View.VitalSignList;
    }

    public void onRowToggle(ToggleEvent event) {
        if (event.getVisibility().equals(Visibility.VISIBLE)) {
            VitalSign vitalSign = (VitalSign) event.getData();
            if (vitalSign.getEvaluation() == null)
                vitalSign.setEvaluation(userStory2Service.getVitalSignsEvaluation(vitalSign.getId()));
        }
    }
}
