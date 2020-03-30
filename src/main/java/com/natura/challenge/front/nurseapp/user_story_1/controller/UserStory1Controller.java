package com.natura.challenge.front.nurseapp.user_story_1.controller;

import com.natura.challenge.front.nurseapp.domain.Patient;
import com.natura.challenge.front.nurseapp.domain.VitalSign;
import com.natura.challenge.front.nurseapp.lazyDataModels.PatientLazyDataModel;
import com.natura.challenge.front.nurseapp.lazyDataModels.VitalSignsLazyDataModel;
import com.natura.challenge.front.nurseapp.lazyDataModels.service.PatientListService;
import com.natura.challenge.front.nurseapp.lazyDataModels.service.PatientListServiceImpl;
import com.natura.challenge.front.nurseapp.lazyDataModels.service.VitalSignListService;
import com.natura.challenge.front.nurseapp.lazyDataModels.service.VitalSignListServiceImpl;
import com.natura.challenge.front.nurseapp.user_story_1.service.UserStory1Service;
import com.natura.challenge.front.nurseapp.user_story_1.service.UserStory1ServiceImpl;
import lombok.Data;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@Named
@ViewScoped
@Data
public class UserStory1Controller implements Serializable {

    private enum View {PatientList, PatientForm, VitalSignList, VitalSignForm}

    private PatientLazyDataModel patients;
    private VitalSignsLazyDataModel vitalSigns;
    private Patient patient;
    private VitalSign vitalSign;
    private View currentView;
    private UserStory1Service userStory1Service;
    private PatientListService patientListService;
    private VitalSignListService vitalSignListService;

    @PostConstruct
    public void init() {
        patientListService = new PatientListServiceImpl();
        patients = new PatientLazyDataModel(patientListService);
        vitalSignListService = new VitalSignListServiceImpl();
        currentView = View.PatientList;
        userStory1Service = new UserStory1ServiceImpl();
    }

    private void showSuccessfulMessage() {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage("Result",  "Successful operation"));
    }

    public void callNewPatient() {
        patient = new Patient();
        currentView = View.PatientForm;
    }

    public void callNewVitalSign() {
        vitalSign = new VitalSign();
        vitalSign.setPatient(patient);
        currentView = View.VitalSignForm;
    }

    public void savePatient() {
        Optional<String> ignoreId = Optional.ofNullable(patient.getId());
        boolean existsDNI = userStory1Service.existsPatientByDNI(patient.getDni(), ignoreId);
        if (existsDNI) {
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",  "Another patient with the same dni was registered previously"));
            return;
        }
        userStory1Service.savePatient(patient);
        currentView = View.PatientList;
        showSuccessfulMessage();
    }

    public void saveVitalSign() {
        userStory1Service.saveVitalSign(vitalSign);
        currentView = View.VitalSignList;
        showSuccessfulMessage();
    }

    public void cancelPatientForm() {
        currentView = View.PatientList;
    }

    public void cancelVitalSignForm() {
        currentView = View.VitalSignList;
    }

    public void cancelVitalSignList() {
        currentView = View.PatientList;
    }

    public void deletePatient(String id) {
        userStory1Service.deletePatient(id);
        showSuccessfulMessage();
    }

    public void callEditPatient(Patient patient) {
        this.patient = patient;
        currentView = View.PatientForm;
    }

    public void listVitalSigns(Patient patient) {
        this.patient = patient;
        vitalSigns = new VitalSignsLazyDataModel(patient.getId(), vitalSignListService);
        currentView = View.VitalSignList;
    }

    public void callEditVitalSign(VitalSign vitalSign) {
        this.vitalSign = vitalSign;
        currentView = View.VitalSignForm;
    }

    public void deleteVitalSign(String id) {
        userStory1Service.deleteVitalSign(id);
        showSuccessfulMessage();
    }
}
