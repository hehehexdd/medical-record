package com.CSCB869.MedicalRecord.modules.Doctor.repo;

import com.CSCB869.MedicalRecord.modules.Doctor.model.Doctor;
import com.CSCB869.MedicalRecord.modules.Patient.model.Patient;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoctorRepository extends MongoRepository<Doctor, String> {

    @Query("{ 'id' : ?0 }")
    Optional<Doctor> findById(String s);

    @Query("{ 'NPI' : ?0 }")
    Optional<Doctor> findByNPI(String npi);

    @Query("{ 'engagedParty.userId': ?0 } }")
    Optional<Doctor> findByUserId(String userId);
}
