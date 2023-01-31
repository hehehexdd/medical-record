package com.CSCB869.MedicalRecord.modules.Visits.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VisitsUpdateDTO {

    private List<Treatment> medicaments;
    private String diagnosis;
    private SickLeave sickLeave;
}
