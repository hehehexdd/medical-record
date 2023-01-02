package com.CSCB869.MedicalRecord.modules.Doctor.controller;

import com.CSCB869.MedicalRecord.config.ResponseError;
import com.CSCB869.MedicalRecord.modules.Doctor.model.DoctorRegisterDTO;
import com.CSCB869.MedicalRecord.modules.Doctor.model.DoctorUpdateDTO;
import com.CSCB869.MedicalRecord.modules.Doctor.service.IDoctorService;
import com.CSCB869.MedicalRecord.modules.Patient.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    @Autowired
    private IDoctorService doctorService;

    @PostMapping
    public ResponseEntity save(@RequestBody @Valid DoctorRegisterDTO doctorRegisterDTO) {
        try {
            return new ResponseEntity<>(this.doctorService.save(doctorRegisterDTO), HttpStatus.CREATED);
        }
        catch (Exception exc) {
            ResponseError error = new ResponseError(HttpStatus.INTERNAL_SERVER_ERROR, exc.getLocalizedMessage());
            return new ResponseEntity<>(error, error.getStatus());
        }
    }

    @GetMapping
    public ResponseEntity getAll() {
        return new ResponseEntity<> (this.doctorService.getAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{doctorId}")
    public ResponseEntity getById(@PathVariable String doctorId) {
        try {
            return new ResponseEntity<>(this.doctorService.getById(doctorId), HttpStatus.CREATED);
        }
        catch (Exception exc) {
            ResponseError error = new ResponseError(HttpStatus.INTERNAL_SERVER_ERROR, exc.getLocalizedMessage());
            return new ResponseEntity<>(error, error.getStatus());
        }
    }

    @PatchMapping(path = "/{doctorId}")
    public ResponseEntity update(@PathVariable String doctorId, @RequestBody @Valid DoctorUpdateDTO payload) {
        try {
            return new ResponseEntity<>(this.doctorService.update(doctorId, payload), HttpStatus.OK);
        }
        catch (Exception exc) {
            ResponseError error = new ResponseError(HttpStatus.NOT_FOUND, exc.getMessage());
            return new ResponseEntity<>(error, error.getStatus());
        }
    }

    @DeleteMapping(path = "/{doctorId}")
    public ResponseEntity delete(@PathVariable String doctorId) {
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
