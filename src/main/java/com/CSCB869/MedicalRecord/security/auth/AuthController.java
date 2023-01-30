package com.CSCB869.MedicalRecord.security.auth;

import com.CSCB869.MedicalRecord.config.ResponseError;
import com.CSCB869.MedicalRecord.security.dto.JWTRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping(path = "/auth/token")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private JWTUtility jwtUtility;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping(consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> authenticate(@RequestBody JWTRequest jwtRequest) throws Exception {
        try {
            return new ResponseEntity<>(this.authService.authenticate(jwtRequest, jwtUtility, authenticationManager), HttpStatus.OK);
        }
        catch (Exception exc) {
            ResponseError error = new ResponseError(HttpStatus.UNAUTHORIZED, exc.getMessage());
            return new ResponseEntity<>(error, error.getStatus());
        }

    }
}
