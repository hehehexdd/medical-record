package com.CSCB869.MedicalRecord.modules.AppUser.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AppUserDTO {
    private String id;
    @NotBlank(message = "The username is required.")
    private String username;
    private String role;
}
