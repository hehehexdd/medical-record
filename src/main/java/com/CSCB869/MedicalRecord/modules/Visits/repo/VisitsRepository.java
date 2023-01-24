package com.CSCB869.MedicalRecord.modules.Visits.repo;

import com.CSCB869.MedicalRecord.modules.Patient.model.Patient;
import com.CSCB869.MedicalRecord.modules.Visits.models.Visits;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VisitsRepository extends MongoRepository<Visits, String> {
}
