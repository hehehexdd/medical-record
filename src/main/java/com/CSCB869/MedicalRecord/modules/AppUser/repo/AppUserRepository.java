package com.CSCB869.MedicalRecord.modules.AppUser.repo;

import com.CSCB869.MedicalRecord.modules.AppUser.model.AppUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepository extends MongoRepository<AppUser, String> {
    @Query("{ 'username': ?0 }")
    Optional<AppUser> findByUsername(String username);

}
