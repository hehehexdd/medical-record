package com.CSCB869.MedicalRecord.modules.Visits.repo;

import com.CSCB869.MedicalRecord.modules.Patient.model.Patient;
import com.CSCB869.MedicalRecord.modules.Visits.models.Visits;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface VisitsRepository extends MongoRepository<Visits, String> {
    @Query("{'$and': [?0]}")
    List<Visits> findByQuery( Map<String, String> query);
}
