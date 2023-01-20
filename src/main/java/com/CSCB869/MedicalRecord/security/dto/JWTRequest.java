package com.CSCB869.MedicalRecord.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JWTRequest {

    @NotBlank(message = "The username is required.")
    private String username;
    @NotBlank(message = "The password is required.")
    private String password;
}
