package com.CSCB869.MedicalRecord.modules.Patient.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class EngagedParty {

//    @NotNull(message = "The userId is required.")
    @NotBlank(message = "The userId is required.")
    private String userId;
    private String href;

    private String name;

    public EngagedParty(@NotBlank String userId) {
        this.userId = userId;
        this.href = null;
    }

    public EngagedParty(@NotBlank String userId, String name) {
        this.userId = userId;
        this.href = null;
        this.name = name;
    }
}
