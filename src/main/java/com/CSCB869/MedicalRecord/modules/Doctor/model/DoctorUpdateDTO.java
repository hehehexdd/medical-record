package com.CSCB869.MedicalRecord.modules.Doctor.model;

import lombok.Data;

import java.util.Set;

@Data
public class DoctorUpdateDTO {
    private String name;
    private Set<Speciality> specialities;
}
