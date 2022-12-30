package com.CSCB869.MedicalRecord.modules.Doctor.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class DoctorRegisterDTO {

    @NotBlank(message = "The username is required.")
    private String username;

    @NotBlank(message = "The password is required.")
    private String password;

    private String NPI;

    private String name;


}
