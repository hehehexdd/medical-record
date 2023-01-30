package com.CSCB869.MedicalRecord.modules.Patient.service;

import com.CSCB869.MedicalRecord.modules.AppUser.model.AppUser;
import com.CSCB869.MedicalRecord.modules.AppUser.service.IAppUserService;
import com.CSCB869.MedicalRecord.modules.Doctor.model.Doctor;
import com.CSCB869.MedicalRecord.modules.Patient.model.EngagedParty;
import com.CSCB869.MedicalRecord.modules.Patient.model.Patient;
import com.CSCB869.MedicalRecord.modules.Patient.model.PatientRegisterDTO;
import com.CSCB869.MedicalRecord.modules.Patient.model.PatientUpdateDTO;
import com.CSCB869.MedicalRecord.modules.Patient.repo.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PatientService implements IPatientService{

    @Autowired
    private IAppUserService appUserService;
    @Autowired
    private PatientRepository patientRepository;

    @Override
    public Patient save(PatientRegisterDTO patientRegisterDTO) throws Exception {
        if(this.ucnExists(patientRegisterDTO.getUCN())) throw new Exception("UCN exists!");

        AppUser user = new AppUser(
                patientRegisterDTO.getUsername(),
                patientRegisterDTO.getPassword(),
                "PATIENT"
        );
        String gpHref = patientRegisterDTO.getGp().getHref() != null ?
                patientRegisterDTO.getGp().getHref()
                : "http://localhost:8080/user" + "/" + patientRegisterDTO.getGp().getUserId();
        EngagedParty gp = new EngagedParty(
                patientRegisterDTO.getGp().getUserId(),
                gpHref
        );
        user = this.appUserService.save(user);

        EngagedParty ep = new EngagedParty(
                user.getId(),
                "http://localhost:8080/user" + "/" + user.getId()
        );
        Patient patient = new Patient(
                patientRegisterDTO.getUCN(),
                ep,
                gp,
                patientRegisterDTO.getName()
        );
        return this.patientRepository.save(patient);
    }

    @Override
    public List<Patient> getAll() {
        return this.patientRepository.findAll();
    }

    @Override
    public Patient getById(String patientId) throws Exception {
        Optional<Patient> patient = this.patientRepository.findById(patientId);
        if (patient.isEmpty()) throw new Exception("Patient not found!");
        return patient.get();
    }

    public Patient getByUserId(String userId) throws Exception {
        Optional<Patient> patient = this.patientRepository.findByUserId(userId);
        if (patient.isEmpty()) throw new Exception("Patient not found!");
        return patient.get();
    }

    @Override
    public Patient update(String id, PatientUpdateDTO payload) throws Exception {
        Patient patient = this.patientRepository.findById(id).orElse(null);

        if(patient == null) throw new Exception("Patient not found!");

        if(payload.getName() != null) {
            patient.setName(payload.getName());
        }
        if(payload.getGp() != null) {
            patient.setGp(payload.getGp());
        }

        return this.patientRepository.save(patient);
    }

    @Override
    public void delete(String id) throws Exception {
        Patient patient = this.patientRepository.findById(id).orElse(null);

        if(patient == null) throw new Exception("Patient not found!");

        this.patientRepository.deleteById(id);
        this.appUserService.delete(patient.getEngagedParty().getUserId());
    }

    private boolean ucnExists(String ucn) {
        return this.patientRepository.findByUCN(ucn).isPresent();
    }

}
