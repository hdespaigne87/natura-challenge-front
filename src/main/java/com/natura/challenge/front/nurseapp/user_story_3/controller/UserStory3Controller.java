package com.natura.challenge.front.nurseapp.user_story_3.controller;

import com.natura.challenge.front.nurseapp.domain.Patient;
import com.natura.challenge.front.nurseapp.domain.ViewVitalSign;
import com.natura.challenge.front.nurseapp.lazyDataModels.PatientLazyDataModel;
import com.natura.challenge.front.nurseapp.lazyDataModels.VitalSignsLazyDataModel;
import com.natura.challenge.front.nurseapp.lazyDataModels.service.PatientListService;
import com.natura.challenge.front.nurseapp.lazyDataModels.service.PatientListServiceImpl;
import com.natura.challenge.front.nurseapp.lazyDataModels.service.VitalSignListService;
import com.natura.challenge.front.nurseapp.lazyDataModels.service.VitalSignListServiceImpl;
import com.natura.challenge.front.nurseapp.user_story_3.service.UserStory3Service;
import com.natura.challenge.front.nurseapp.user_story_3.service.UserStory3ServiceImpl;
import lombok.Data;
import org.primefaces.model.chart.*;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Named
@ViewScoped
@Data
public class UserStory3Controller implements Serializable {

    private enum View {PatientSearch, Dashboard}

    private PatientLazyDataModel patients;
    private VitalSignsLazyDataModel vitalSigns;
    private String patientDNI;
    private Patient patient;
    private View currentView;
    private UserStory3Service userStory3Service;
    private PatientListService patientListService;
    private VitalSignListService vitalSignListService;
    private LineChartModel chartBloodPressure;
    private LineChartModel chartHeartRate;
    private Integer month;
    private Integer year;
    private boolean showCharts;

    @PostConstruct
    public void init() {
        patientListService = new PatientListServiceImpl();
        patients = new PatientLazyDataModel(patientListService);
        vitalSignListService = new VitalSignListServiceImpl();
        currentView = View.PatientSearch;
        userStory3Service = new UserStory3ServiceImpl();
        month = LocalDate.now().getMonthValue();
        year = LocalDate.now().getYear();
    }

    public void clearDashboard() {
        patientDNI = null;
        currentView = View.PatientSearch;
        month = LocalDate.now().getMonthValue();
        year = LocalDate.now().getYear();
    }

    public void viewDashboard() {
        Optional<Patient> patientOpt = userStory3Service.findPatientByDNI(patientDNI);
        if (patientOpt.isPresent()) {
            patient = patientOpt.get();
            vitalSigns = new VitalSignsLazyDataModel(patient.getId(), vitalSignListService);
            createChartModels();
            currentView = View.Dashboard;
        } else {
            //Show message not found
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No patient with that DNI was found"));
        }
    }

    private void initLinearModel() {
        chartBloodPressure = new LineChartModel();
        chartHeartRate = new LineChartModel();
        chartBloodPressure.setAnimate(true);
        chartHeartRate.setAnimate(true);
        showCharts = false;

        List<ViewVitalSign> vitalSigns = userStory3Service.findVitalSignsByMonthAndYear(patient.getId(), month, year);

        if (vitalSigns != null) {
            ChartSeries systolic = new ChartSeries();
            systolic.setLabel("Systolic");
            ChartSeries diastolic = new ChartSeries();
            diastolic.setLabel("Diastolic");
            ChartSeries heartRate = new ChartSeries();
            heartRate.setLabel("Heart Rate");
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd");
            for (ViewVitalSign vitalSign : vitalSigns) {
                showCharts = true;
                String day = dateFormat.format(vitalSign.getRegistrationMoment());
                systolic.set(day, vitalSign.getBloodPressureSystolic());
                diastolic.set(day, vitalSign.getBloodPressureDiastolic());
                heartRate.set(day, vitalSign.getHeartRate());
            }
            chartBloodPressure.addSeries(systolic);
            chartBloodPressure.addSeries(diastolic);
            chartHeartRate.addSeries(heartRate);
        }

        if (!showCharts) {
            //Show message not found
            FacesContext context = FacesContext.getCurrentInstance();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No data was found for this period"));
        }
    }

    public void createChartModels() {
        initLinearModel();

        chartBloodPressure.setTitle("Blood Pressure");
        chartBloodPressure.setLegendPosition("e");
        chartBloodPressure.setShowPointLabels(true);
        chartBloodPressure.getAxes().put(AxisType.X, new CategoryAxis("Days"));
        Axis yAxisBlood = chartBloodPressure.getAxis(AxisType.Y);
        yAxisBlood.setLabel("Systolic / Diastolic");
        yAxisBlood.setMin(0);
        yAxisBlood.setMax(300);

        chartHeartRate.setTitle("Blood Pressure");
        chartHeartRate.setLegendPosition("e");
        chartHeartRate.setShowPointLabels(true);
        chartHeartRate.getAxes().put(AxisType.X, new CategoryAxis("Days"));
        Axis yAxisHeart = chartHeartRate.getAxis(AxisType.Y);
        yAxisHeart.setLabel("Heart Rate");
        yAxisHeart.setMin(0);
        yAxisHeart.setMax(300);
    }
}
