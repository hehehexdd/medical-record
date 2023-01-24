package com.CSCB869.MedicalRecord.modules.Visits.service;

import com.CSCB869.MedicalRecord.modules.Doctor.service.DoctorService;
import com.CSCB869.MedicalRecord.modules.Patient.service.PatientService;
import com.CSCB869.MedicalRecord.modules.Visits.models.Visits;
import com.CSCB869.MedicalRecord.modules.Visits.models.VisitsCreateDTO;
import com.CSCB869.MedicalRecord.modules.Visits.models.VisitsUpdateDTO;
import com.CSCB869.MedicalRecord.modules.Visits.repo.VisitsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VisitsService implements IVisitsService{

    @Autowired
    private VisitsRepository visitsRepository;

    @Autowired
    private PatientService patientService;

    @Autowired
    private DoctorService doctorService;

    @Override
    public Visits save(VisitsCreateDTO visitDTO) throws Exception {

         Visits visit = new Visits(
                 visitDTO.getPatient(),
                 visitDTO.getDoctor(),
                 LocalDateTime.now().toString(),
                 visitDTO.getDiagnosis()
         );

        System.out.println(visit);

         if (visitDTO.getMedicaments() != null) visit.setMedicaments(visitDTO.getMedicaments());
         if (visitDTO.getSickLeave() != null) visit.setSickLeave(visitDTO.getSickLeave());

         return this.visitsRepository.save(visit);
    }

    @Override
    public List<Visits> getAll() {
        return this.visitsRepository.findAll();
    }

    @Override
    public Visits getById(String visitId) throws Exception {
        Optional<Visits> visit = this.visitsRepository.findById(visitId);
        if (visit.isEmpty()) throw new Exception("Visit not found!");
        return visit.get();
    }

    @Override
    public Visits update(String visitId, VisitsUpdateDTO payload) throws Exception {
        Visits visit = this.visitsRepository.findById(visitId).orElse(null);

        if(visit == null) throw new Exception("Visit not found!");

        if(payload.getSickLeave() != null) visit.setSickLeave(payload.getSickLeave());
        if(payload.getMedicaments() != null) visit.setMedicaments(payload.getMedicaments());

        return this.visitsRepository.save(visit);
    }

    @Override
    public void delete(String visitId) throws Exception {
        Optional<Visits> visit = this.visitsRepository.findById(visitId);

        if(visit.isEmpty()) throw new Exception("Visit not found!");

        this.visitsRepository.deleteById(visitId);
    }
}
