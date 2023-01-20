package com.CSCB869.MedicalRecord;

import com.CSCB869.MedicalRecord.security.auth.JWTUtility;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

//@ComponentScan({"com.CSCB869.MedicalRecord"})
@SpringBootApplication
public class MedicalRecordApplication {

	public static void main(String[] args) {
		//System.out.println(JWTUtility.generateJWT("testUser", "TEST"));
		SpringApplication.run(MedicalRecordApplication.class, args);
	}

}
