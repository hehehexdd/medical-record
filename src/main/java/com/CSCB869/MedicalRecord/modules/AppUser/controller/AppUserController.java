package com.CSCB869.MedicalRecord.modules.AppUser.controller;
import com.CSCB869.MedicalRecord.config.ResponseError;
import com.CSCB869.MedicalRecord.modules.AppUser.model.UpdateAppUserDTO;
import com.CSCB869.MedicalRecord.modules.AppUser.service.IAppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/user")
public class AppUserController {

    @Autowired
    private IAppUserService appUserService;

    @GetMapping
    public ResponseEntity getAll() {
        return new ResponseEntity<> (this.appUserService.getAll()
                .stream()
                .map(user -> this.appUserService.convertToDTO(user))
                .collect(Collectors.toList()),
                HttpStatus.OK);
    }

    @GetMapping(path = "/{userId}")
    public ResponseEntity getById(@PathVariable String userId) {
        try {
            return new ResponseEntity<>(this.appUserService.getUserById(userId), HttpStatus.OK);
        }
        catch (Exception exc) {
            ResponseError error = new ResponseError(HttpStatus.NOT_FOUND, exc.getLocalizedMessage());
            return new ResponseEntity<>(error, error.getStatus());
        }
    }

//    @PatchMapping(path = "/{userId}")
//    public ResponseEntity update(@PathVariable String userId, @RequestBody @Valid UpdateAppUserDTO payload) {
//        try {
//            return new ResponseEntity<>(this.appUserService.update(userId, payload), HttpStatus.OK);
//        }
//        catch (Exception exc) {
//            ResponseError error = new ResponseError(HttpStatus.NOT_FOUND, exc.getLocalizedMessage());
//            return new ResponseEntity<>(error, error.getStatus());
//        }
//    }
//
//    @DeleteMapping(path = "/{userId}")
//    public ResponseEntity delete(@PathVariable String userId) {
//        try {
//            this.appUserService.delete(userId);
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//        catch (Exception exc) {
//            ResponseError error = new ResponseError(HttpStatus.NOT_FOUND, exc.getLocalizedMessage());
//            return new ResponseEntity<>(error, error.getStatus());
//        }
//    }
}
