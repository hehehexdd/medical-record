package com.CSCB869.MedicalRecord.modules.Patient.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class PatientUpdateDTO {
    private String name;
    private EngagedParty gp;
}
