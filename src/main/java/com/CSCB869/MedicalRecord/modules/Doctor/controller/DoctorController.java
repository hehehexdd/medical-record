package com.CSCB869.MedicalRecord.modules.Doctor.controller;

import com.CSCB869.MedicalRecord.config.ResponseError;
import com.CSCB869.MedicalRecord.modules.Doctor.model.DoctorRegisterDTO;
import com.CSCB869.MedicalRecord.modules.Doctor.model.DoctorUpdateDTO;
import com.CSCB869.MedicalRecord.modules.Doctor.service.IDoctorService;
import com.CSCB869.MedicalRecord.modules.Patient.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private IDoctorService doctorService;

    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid DoctorRegisterDTO doctorRegisterDTO) {
        try {
            return new ResponseEntity<>(this.doctorService.save(doctorRegisterDTO), HttpStatus.CREATED);
        }
        catch (Exception exc) {
            ResponseError error = new ResponseError(HttpStatus.INTERNAL_SERVER_ERROR, exc.getLocalizedMessage());
            return new ResponseEntity<>(error, error.getStatus());
        }
    }

    @PreAuthorize("hasRole('ROLE_DOCTOR')")
    @GetMapping
    public ResponseEntity<Object> getAll() {
        return new ResponseEntity<> (this.doctorService.getAll(), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_DOCTOR')")
    @GetMapping(path = "/{doctorId}")
    public ResponseEntity<Object> getById(@PathVariable String doctorId) {
        try {
            return new ResponseEntity<>(this.doctorService.getById(doctorId), HttpStatus.OK);
        }
        catch (Exception exc) {
            ResponseError error = new ResponseError(HttpStatus.INTERNAL_SERVER_ERROR, exc.getLocalizedMessage());
            return new ResponseEntity<>(error, error.getStatus());
        }
    }

    @PreAuthorize("hasRole('ROLE_DOCTOR')")
    @PatchMapping(path = "/{doctorId}")
    public ResponseEntity<Object> update(@PathVariable String doctorId, @RequestBody @Valid DoctorUpdateDTO payload) {
        try {
            return new ResponseEntity<>(this.doctorService.update(doctorId, payload), HttpStatus.OK);
        }
        catch (Exception exc) {
            ResponseError error = new ResponseError(HttpStatus.NOT_FOUND, exc.getMessage());
            return new ResponseEntity<>(error, error.getStatus());
        }
    }

    @PreAuthorize("hasRole('ROLE_DOCTOR')")
    @DeleteMapping(path = "/{doctorId}")
    public ResponseEntity<Object> delete(@PathVariable String doctorId) {
        try {
            this.doctorService.delete(doctorId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (Exception exc) {
            ResponseError error = new ResponseError(HttpStatus.NOT_FOUND, exc.getLocalizedMessage());
            return new ResponseEntity<>(error, error.getStatus());
        }
    }
}
