package com.CSCB869.MedicalRecord.modules.Visits.models;

import com.CSCB869.MedicalRecord.modules.Patient.model.EngagedParty;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.mongodb.lang.NonNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Document(collection = "Visits")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Visits {

    @Id
    private String id;

    @NonNull
    private EngagedParty patient;

    @NonNull
    private EngagedParty doctor;

    @NonNull
    private String date;

    @NonNull
    private String diagnosis;

    private List<Treatment> medicaments;

    private SickLeave sickLeave;

    public Visits(
            @NonNull EngagedParty patient,
            @NonNull EngagedParty doctor,
            @NotNull String date,
            @NonNull String diagnosis,
            List<Treatment> medicaments,
            SickLeave sickLeave) {
        this.id = null;
        this.patient = patient;
        this.doctor = doctor;
        this.date = date;
        this.diagnosis = diagnosis;
        this.medicaments = medicaments;
        this.sickLeave = sickLeave;
    }

    public Visits(
            @NonNull EngagedParty patient,
            @NonNull EngagedParty doctor,
            @NotNull String date,
            @NonNull String diagnosis) {
        this.id = null;
        this.patient = patient;
        this.doctor = doctor;
        this.date = date;
        this.diagnosis = diagnosis;
        this.medicaments = new ArrayList<>();
        this.sickLeave = null;
    }

    public Visits(
            @NonNull EngagedParty patient,
            @NonNull EngagedParty doctor,
            @NotNull String date) {
        this.id = null;
        this.patient = patient;
        this.doctor = doctor;
        this.date = date;
        this.diagnosis = null;
        this.medicaments = new ArrayList<>();
        this.sickLeave = null;
    }

}
