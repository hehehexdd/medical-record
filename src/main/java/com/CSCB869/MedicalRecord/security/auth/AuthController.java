package com.CSCB869.MedicalRecord.security.auth;

import com.CSCB869.MedicalRecord.config.ResponseError;
import com.CSCB869.MedicalRecord.modules.AppUser.model.AppUser;
import com.CSCB869.MedicalRecord.modules.AppUser.service.AppUserService;
import com.CSCB869.MedicalRecord.modules.AppUser.service.IAppUserService;
import com.CSCB869.MedicalRecord.security.dto.JWTRequest;
import com.CSCB869.MedicalRecord.security.dto.JWTResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/auth/token")
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private JWTUtility jwtUtility;
    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping()
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
