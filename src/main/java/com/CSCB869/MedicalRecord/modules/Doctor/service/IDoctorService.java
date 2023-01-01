package com.CSCB869.MedicalRecord.modules.Doctor.service;

import com.CSCB869.MedicalRecord.modules.Doctor.model.Doctor;
import com.CSCB869.MedicalRecord.modules.Doctor.model.DoctorRegisterDTO;
import com.CSCB869.MedicalRecord.modules.Doctor.model.DoctorUpdateDTO;

import javax.print.Doc;
import java.util.List;
import java.util.Optional;

public interface IDoctorService {

    Doctor save(DoctorRegisterDTO doctorDTO) throws Exception;

    List<Doctor> getAll();

    Optional<Doctor> getById(String doctorId);

    Doctor update(String doctorId, DoctorUpdateDTO payload) throws Exception;

    void delete(String doctorId) throws Exception;
}
