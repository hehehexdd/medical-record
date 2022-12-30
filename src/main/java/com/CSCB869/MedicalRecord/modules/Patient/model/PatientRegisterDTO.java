package com.CSCB869.MedicalRecord.modules.Patient.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class PatientRegisterDTO {

    @NotBlank(message = "The username is required.")
    private String username;

    @NotBlank(message = "The password is required.")
    private String password;

    @NotBlank(message = "The UCN is required.")
    private String UCN;

    @NotBlank(message = "The name is required.")
    private String name;

    public PatientRegisterDTO(@NotBlank String username, @NotBlank String password, @NotBlank String UCN, @NotBlank String name) {
        this.username = username;
        this.password = password;
        this.UCN = UCN;
        this.name = name;
    }
}
