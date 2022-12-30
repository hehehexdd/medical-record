package com.CSCB869.MedicalRecord.modules.Doctor.repo;

import com.CSCB869.MedicalRecord.modules.Doctor.model.Doctor;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.Optional;

public interface DoctorRepository extends MongoRepository<Doctor, String> {

    @Query("{ 'id' : ?0 }")
    Optional<Doctor> findById(String s);
}
