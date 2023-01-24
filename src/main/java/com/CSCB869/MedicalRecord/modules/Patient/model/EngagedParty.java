package com.CSCB869.MedicalRecord.modules.Patient.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EngagedParty {
    @NotBlank(message = "The userId is required.")
    private String userId;

    private String href;

    public EngagedParty(@NotBlank String userId) {
        this.userId = userId;
        this.href = null;
    }
}
