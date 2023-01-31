package com.CSCB869.MedicalRecord.modules.Doctor.service;

import com.CSCB869.MedicalRecord.modules.AppUser.model.AppUser;
import com.CSCB869.MedicalRecord.modules.AppUser.service.IAppUserService;
import com.CSCB869.MedicalRecord.modules.Doctor.model.Doctor;
import com.CSCB869.MedicalRecord.modules.Doctor.model.DoctorRegisterDTO;
import com.CSCB869.MedicalRecord.modules.Doctor.model.DoctorUpdateDTO;
import com.CSCB869.MedicalRecord.modules.Doctor.model.Speciality;
import com.CSCB869.MedicalRecord.modules.Doctor.repo.DoctorRepository;
import com.CSCB869.MedicalRecord.modules.Patient.model.EngagedParty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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
        Set<Speciality> specialities = new LinkedHashSet<>();
        specialities.add(doctorDTO.getSpecialty());
        Doctor doctor = new Doctor(
                doctorDTO.getNPI(),
                ep,
                doctorDTO.getName(),
                specialities
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

    public Doctor getByUserId(String userId) throws Exception {
        Optional<Doctor> doctor = this.doctorRepository.findByUserId(userId);
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

        if(payload.getSpecialities() != null) {
            for (Speciality speciality : payload.getSpecialities()) {
                try {
                    Speciality.valueOf(speciality.toString());
                } catch (Exception e) {
                    throw new Exception("One of the specialities is incorrect");
                }
            }
            doctor.setSpecialities(payload.getSpecialities());
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

    @Override
    public List<Speciality> listSpecialities() {
        return new ArrayList<>(EnumSet.allOf(Speciality.class));
    }
}
