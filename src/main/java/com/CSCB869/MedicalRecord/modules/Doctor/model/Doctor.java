package com.CSCB869.MedicalRecord.modules.Doctor.model;

import com.CSCB869.MedicalRecord.modules.Patient.model.EngagedParty;
import com.mongodb.lang.NonNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@NoArgsConstructor
@Document(collection = "Doctor")
public class Doctor {

    @Id
    private String id;

    @NotNull
    @Indexed(unique = true)
    private String NPI;


    private EngagedParty engagedParty;

    @NonNull
    private String name;

    Set<Speciality> specialities;

    public Doctor(String NPI, EngagedParty engagedParty, @NonNull String name) {
        this.NPI = NPI;
        this.engagedParty = engagedParty;
        this.name = name;
    }

    public Doctor(String NPI, EngagedParty engagedParty, @NonNull String name, Set<Speciality> specialities) {
        this.NPI = NPI;
        this.engagedParty = engagedParty;
        this.name = name;
        this.specialities = specialities;
    }
}
