package com.CSCB869.MedicalRecord.modules.Doctor.service;

import com.CSCB869.MedicalRecord.modules.AppUser.model.AppUser;
import com.CSCB869.MedicalRecord.modules.AppUser.service.IAppUserService;
import com.CSCB869.MedicalRecord.modules.Doctor.model.Doctor;
import com.CSCB869.MedicalRecord.modules.Doctor.model.DoctorRegisterDTO;
import com.CSCB869.MedicalRecord.modules.Doctor.model.DoctorUpdateDTO;
import com.CSCB869.MedicalRecord.modules.Doctor.repo.DoctorRepository;
import com.CSCB869.MedicalRecord.modules.Patient.model.EngagedParty;
import com.CSCB869.MedicalRecord.modules.Patient.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DoctorService implements IDoctorService{

    @Autowired
    private IAppUserService appUserService;

    @Autowired
    private DoctorRepository doctorRepository;

    @Override
    public Doctor save(DoctorRegisterDTO doctorDTO) throws Exception {
        if(this.npiExists(doctorDTO.getNPI())) throw new Exception("Doctor with this NPI already exists!");

        AppUser user = new AppUser(
                doctorDTO.getUsername(),
                doctorDTO.getPassword(),
                "DOCTOR"
        );
        user = this.appUserService.save(user);

        EngagedParty ep = new EngagedParty(
                user.getId(),
                "http://localhost:8080/user" + "/" + user.getId()
        );
        Doctor doctor = new Doctor(
                doctorDTO.getNPI(),
                ep,
                doctorDTO.getName()
        );
        return this.doctorRepository.save(doctor);
    }

    @Override
    public List<Doctor> getAll() {
        return this.doctorRepository.findAll();
    }

    @Override
    public Doctor getById(String doctorId) throws Exception {
        Optional<Doctor> doctor = this.doctorRepository.findById(doctorId);
        if (doctor.isEmpty()) throw new Exception("Doctor not found!");
        return doctor.get();
    }

    @Override
    public Doctor update(String doctorId, DoctorUpdateDTO payload) throws Exception {
        Doctor doctor = this.doctorRepository.findById(doctorId).orElse(null);

        if(doctor == null) throw new Exception("Doctor not found!");

        if(payload.getName() != null) {
            doctor.setName(payload.getName());
        }

        return this.doctorRepository.save(doctor);
    }

    @Override
    public void delete(String doctorId) throws Exception {
        Doctor doctor = this.doctorRepository.findById(doctorId).orElse(null);

        if(doctor == null) throw new Exception("Patient not found!");

        this.doctorRepository.deleteById(doctorId);
        this.appUserService.delete(doctor.getEngagedParty().getUserId());
    }

    private boolean npiExists(String ucn) {
        return this.doctorRepository.findByNPI(ucn).isPresent();
    }
}
