package com.CSCB869.MedicalRecord.modules.Visits.models;

import com.CSCB869.MedicalRecord.modules.Patient.model.EngagedParty;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
public class VisitsCreateDTO {

//    @NotBlank(message = "The patient is required.")
    private EngagedParty patient;

//    @NotBlank(message = "The doctor is required.")
    private EngagedParty doctor;

//    @NotBlank(message = "The diagnosis is required.")
    private String diagnosis;

    private String date;

    private List<Treatment> medicaments;

    private SickLeave sickLeave;

    public VisitsCreateDTO(@NotBlank EngagedParty patient,
                           @NotBlank EngagedParty doctor,
                           @NotBlank String diagnosis,
                           List<Treatment> medicaments,
                           SickLeave sickLeave) {
        this.patient = patient;
        this.doctor = doctor;
        this.diagnosis = diagnosis;
        this.medicaments = medicaments;
        this.sickLeave = sickLeave;
    }

    public VisitsCreateDTO(@NotBlank EngagedParty patient,
                           @NotBlank EngagedParty doctor,
                           @NotBlank String diagnosis) {
        this.patient = patient;
        this.doctor = doctor;
        this.diagnosis = diagnosis;
        this.medicaments = new ArrayList<>();
        this.sickLeave = null;
    }

    public VisitsCreateDTO(
            String date,@NotBlank EngagedParty patient,
                           @NotBlank EngagedParty doctor
                           ) {
        this.patient = patient;
        this.doctor = doctor;
        this.diagnosis = "";
        this.date = date;
        this.medicaments = new ArrayList<>();
        this.sickLeave = null;
    }

    public VisitsCreateDTO(@NotBlank EngagedParty patient,
                           @NotBlank EngagedParty doctor) {
        this.patient = patient;
        this.doctor = doctor;
        this.diagnosis = "";
        this.date = null;
        this.medicaments = new ArrayList<>();
        this.sickLeave = null;
    }
}
