package com.CSCB869.MedicalRecord.modules.Patient.controller;

import com.CSCB869.MedicalRecord.config.ResponseError;
import com.CSCB869.MedicalRecord.modules.Patient.model.Patient;
import com.CSCB869.MedicalRecord.modules.Patient.model.PatientRegisterDTO;
import com.CSCB869.MedicalRecord.modules.Patient.model.PatientUpdateDTO;
import com.CSCB869.MedicalRecord.modules.Patient.service.IPatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path = "/patient")
public class PatientController {

    @Autowired
    private IPatientService patientService;

    @PreAuthorize("permitAll()")
    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid PatientRegisterDTO patientRegisterDTO) {
        try {
            return new ResponseEntity<>(this.patientService.save(patientRegisterDTO), HttpStatus.CREATED);
        }
        catch (Exception exc) {
            ResponseError error = new ResponseError(HttpStatus.INTERNAL_SERVER_ERROR, exc.getLocalizedMessage());
            return new ResponseEntity<>(error, error.getStatus());
        }
    }

    @GetMapping
    public ResponseEntity<Object> getAll(HttpServletRequest request) {
        return new ResponseEntity<> (this.patientService.getAll(this.patientService.getFilters(request)), HttpStatus.OK);
    }

    @GetMapping(path = "/{patientId}")
    public ResponseEntity<Object> getById(@PathVariable String patientId) {
        try {
            return new ResponseEntity<>(this.patientService.getById(patientId), HttpStatus.OK);
        }
        catch (Exception exc) {
            ResponseError error = new ResponseError(HttpStatus.NOT_FOUND, exc.getLocalizedMessage());
            return new ResponseEntity<>(error, error.getStatus());
        }
    }

    @PatchMapping(path = "/{patientId}")
    public ResponseEntity<Object> update(@PathVariable String patientId, @RequestBody @Valid PatientUpdateDTO payload) {
        try {
            return new ResponseEntity<>(this.patientService.update(patientId, payload), HttpStatus.OK);
        }
        catch (Exception exc) {
            ResponseError error = new ResponseError(HttpStatus.NOT_FOUND, exc.getMessage());
            return new ResponseEntity<>(error, error.getStatus());
        }
    }

    @DeleteMapping(path = "/{patientId}")
    public ResponseEntity<Object> delete(@PathVariable String patientId) {
        try {
            this.patientService.delete(patientId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (Exception exc) {
            ResponseError error = new ResponseError(HttpStatus.NOT_FOUND, exc.getLocalizedMessage());
            return new ResponseEntity<>(error, error.getStatus());
        }
    }
}
