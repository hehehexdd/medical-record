package com.CSCB869.MedicalRecord.modules.Patient.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class PatientRegisterDTO {

    @NotBlank(message = "The username is required.")
    private String username;

    @NotBlank(message = "The password is required.")
    private String password;

    @NotBlank(message = "The UCN is required.")
    private String UCN;

    @NotNull(message = "The GP is required.")
    @Valid
    private EngagedParty gp;

    @NotBlank(message = "The name is required.")
    private String name;

    public PatientRegisterDTO(@NotBlank String username, @NotBlank String password, @NotBlank String UCN,
                              @NotBlank EngagedParty gp, @NotBlank String name) {
        this.username = username;
        this.password = password;
        this.UCN = UCN;
        this.gp = gp;
        this.name = name;
    }

}
