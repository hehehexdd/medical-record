package com.CSCB869.MedicalRecord.modules.Patient.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EngagedParty {
    @NotBlank(message = "The userId is required.")
    private String userId;

    private String href;

    public EngagedParty(@NotBlank String userId) {
        this.userId = userId;
        this.href = null;
    }
}
