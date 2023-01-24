package com.CSCB869.MedicalRecord.modules.Patient.repo;

import com.CSCB869.MedicalRecord.modules.Patient.model.Patient;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends MongoRepository<Patient, String> {
    @Query("{ 'id' : ?0 }")
    Optional<Patient> findById(String patientId);
    @Query("{ 'UCN' : ?0 }")
    Optional<Patient> findByUCN(String ucn);

    @Query("{ 'engagedParty.userId' : ?0 }")
    Optional<Patient> findByUserId(String userId);
}
