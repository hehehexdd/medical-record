package com.CSCB869.MedicalRecord.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public class ResponseError {
    private HttpStatus status;
    private String message;
}
