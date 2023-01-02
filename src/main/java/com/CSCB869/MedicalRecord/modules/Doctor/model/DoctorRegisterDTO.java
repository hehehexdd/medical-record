package com.CSCB869.MedicalRecord.modules.Doctor.model;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class DoctorRegisterDTO {

    @NotBlank(message = "The username is required.")
    private String username;

    @NotBlank(message = "The password is required.")
    private String password;

    @NotBlank(message = "The NPI is required.")
    private String NPI;

    @NotBlank(message = "Name is required.")
    private String name;

    public DoctorRegisterDTO(@NotBlank String username, @NotBlank String password, @NotBlank String NPI, @NotBlank String name) {
        this.username = username;
        this.password = password;
        this.NPI = NPI;
        this.name = name;
    }
}
