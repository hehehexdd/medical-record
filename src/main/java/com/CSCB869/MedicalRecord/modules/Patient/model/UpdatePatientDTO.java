package com.CSCB869.MedicalRecord.modules.Patient.model;

import lombok.Data;

@Data
public class UpdatePatientDTO {
    private String name;
    private EngagedParty gp;
}
