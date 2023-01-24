package com.CSCB869.MedicalRecord.modules.Visits.controller;

import com.CSCB869.MedicalRecord.config.ResponseError;
import com.CSCB869.MedicalRecord.modules.Visits.models.VisitsCreateDTO;
import com.CSCB869.MedicalRecord.modules.Visits.models.VisitsUpdateDTO;
import com.CSCB869.MedicalRecord.modules.Visits.service.IVisitsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/visits")
public class VisitsController {

    @Autowired
    private IVisitsService visitsService;

    @PreAuthorize("hasRole('ROLE_DOCTOR)")
    @PostMapping
    public ResponseEntity<Object> save(@RequestBody @Valid VisitsCreateDTO payload) {
        try {
            return new ResponseEntity<>(this.visitsService.save(payload), HttpStatus.CREATED);
        }
        catch (Exception exc) {
            ResponseError error = new ResponseError(HttpStatus.INTERNAL_SERVER_ERROR, exc.getLocalizedMessage());
            return new ResponseEntity<>(error, error.getStatus());
        }
    }

    @GetMapping
    public ResponseEntity<Object> getAll() {
        return new ResponseEntity<> (this.visitsService.getAll(), HttpStatus.OK);
    }

    @GetMapping(path = "/{visitId}")
    public ResponseEntity<Object> getById(@PathVariable String visitId) {
        try {
            return new ResponseEntity<>(this.visitsService.getById(visitId), HttpStatus.OK);
        }
        catch (Exception exc) {
            ResponseError error = new ResponseError(HttpStatus.INTERNAL_SERVER_ERROR, exc.getLocalizedMessage());
            return new ResponseEntity<>(error, error.getStatus());
        }
    }

    @PostAuthorize("hasRole('ROLE_DOCTOR')")
    @PatchMapping(path = "/{visitId}")
    public ResponseEntity<Object> update(@PathVariable String visitId, @RequestBody @Valid VisitsUpdateDTO payload) {
        try {
            return new ResponseEntity<>(this.visitsService.update(visitId, payload), HttpStatus.OK);
        }
        catch (Exception exc) {
            ResponseError error = new ResponseError(HttpStatus.NOT_FOUND, exc.getMessage());
            return new ResponseEntity<>(error, error.getStatus());
        }
    }

    @DeleteMapping(path = "/{visitId}")
    public ResponseEntity<Object> delete(@PathVariable String visitId) {
        try {
            this.visitsService.delete(visitId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (Exception exc) {
            ResponseError error = new ResponseError(HttpStatus.NOT_FOUND, exc.getLocalizedMessage());
            return new ResponseEntity<>(error, error.getStatus());
        }
    }
}
