package com.CSCB869.MedicalRecord.security.auth;

import com.CSCB869.MedicalRecord.security.dto.JWTRequest;
import com.CSCB869.MedicalRecord.security.dto.JWTResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;

public interface IAuthService {
    JWTResponse authenticate(JWTRequest jwtRequest,
                             JWTUtility jwtUtility,
                             AuthenticationManager authenticationManager) throws Exception;
    UserDetails loadUser(String username);
}
