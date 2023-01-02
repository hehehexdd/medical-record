package com.CSCB869.MedicalRecord.modules.Patient.service;

import com.CSCB869.MedicalRecord.modules.Patient.model.Patient;
import com.CSCB869.MedicalRecord.modules.Patient.model.PatientRegisterDTO;
import com.CSCB869.MedicalRecord.modules.Patient.model.PatientUpdateDTO;

import java.util.List;
import java.util.Optional;

public interface IPatientService {
    Patient save(PatientRegisterDTO patientRegisterDTO) throws Exception;
    List<Patient> getAll();
    Patient getById(String patientId) throws Exception;
    Patient update(String id, PatientUpdateDTO payload) throws Exception;
    void delete(String id) throws Exception;
}
