package com.CSCB869.MedicalRecord.modules.Patient.model;
import com.CSCB869.MedicalRecord.modules.AppUser.model.AppUser;
import com.CSCB869.MedicalRecord.modules.Doctor.model.Doctor;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.mongodb.lang.NonNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.codecs.pojo.annotations.BsonId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@NoArgsConstructor
@Document(collection = "Patient")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Patient {
    @Id
    private String id;

    @Indexed(unique = true)
    private EngagedParty engagedParty;

    @NonNull
    private EngagedParty gp;
    /*
    The unique civil number of the patient
     */
    @NonNull
    @Indexed(unique = true)
    private String UCN;

    @NonNull
    private String name;

    private Date healthTaxesPaidUntil;

    public Patient(
            @NonNull String UCN,
            @NonNull EngagedParty engagedParty,
            @NotNull EngagedParty gp,
            @NonNull String name) {
        this.UCN = UCN;
        this.engagedParty = engagedParty;
        this.gp = gp;
        this.name = name;
        this.healthTaxesPaidUntil = null;
    }

    public Patient(
            @NonNull String UCN,
            @NonNull EngagedParty engagedParty,
            @NonNull String name) {
        this.UCN = UCN;
        this.engagedParty = engagedParty;
        this.gp = null;
        this.name = name;
        this.healthTaxesPaidUntil = null;
    }
}
