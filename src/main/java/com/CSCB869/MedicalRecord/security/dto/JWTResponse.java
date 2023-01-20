package com.CSCB869.MedicalRecord.security.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JWTResponse {
    private String userId;
    private String role;
    private String jwtToken;

}
