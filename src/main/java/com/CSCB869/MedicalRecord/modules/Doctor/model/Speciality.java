package com.CSCB869.MedicalRecord.modules.Doctor.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class Speciality {
    @NotNull
    private String id;
    @NotNull
    private String name;
    @NotNull
    private String type;
}
