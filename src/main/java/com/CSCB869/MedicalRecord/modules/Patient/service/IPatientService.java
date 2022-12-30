package com.CSCB869.MedicalRecord.modules.Patient.service;

import com.CSCB869.MedicalRecord.modules.AppUser.model.UpdateAppUserDTO;
import com.CSCB869.MedicalRecord.modules.Patient.model.Patient;
import com.CSCB869.MedicalRecord.modules.Patient.model.PatientRegisterDTO;
import com.CSCB869.MedicalRecord.modules.Patient.model.UpdatePatientDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface IPatientService {
    Patient save(PatientRegisterDTO patientRegisterDTO) throws Exception;
    List<Patient> getAll();
    Optional<Patient> getById(String patientId);
    Patient update(String id, UpdatePatientDTO payload) throws Exception;
    void delete(String id) throws Exception;
}
