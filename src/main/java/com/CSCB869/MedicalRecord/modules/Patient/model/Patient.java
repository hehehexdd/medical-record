package com.CSCB869.MedicalRecord.modules.Patient.model;
import com.CSCB869.MedicalRecord.modules.AppUser.model.AppUser;
import com.CSCB869.MedicalRecord.modules.Doctor.model.Doctor;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.mongodb.lang.NonNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@Document(collection = "Patient")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Patient {
    @Id
    private String id;

    @Indexed(unique = true)
    private EngagedParty engagedParty;

    private EngagedParty gp;
    /*
    The unique civil number of the patient
     */
    @NonNull
    @Indexed(unique = true)
    private String UCN;

    @NonNull
    private String name;

    public Patient(
            @NonNull String UCN,
            @NonNull EngagedParty engagedParty,
            @NotNull EngagedParty gp,
            @NonNull String name) {
        this.UCN = UCN;
        this.engagedParty = engagedParty;
        this.gp = gp;
        this.name = name;
    }

    public Patient(
            @NonNull String UCN,
            @NonNull EngagedParty engagedParty,
            @NonNull String name) {
        this.UCN = UCN;
        this.engagedParty = engagedParty;
        this.gp = null;
        this.name = name;
    }
}