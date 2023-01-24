package com.CSCB869.MedicalRecord.modules.Visits.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Treatment {

    private String medicamentName;
    private String pharmaceuticalCompany;
    private String dosage;

}
