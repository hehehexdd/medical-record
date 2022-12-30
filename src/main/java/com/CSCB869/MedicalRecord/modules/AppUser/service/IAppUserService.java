package com.CSCB869.MedicalRecord.modules.AppUser.service;

import com.CSCB869.MedicalRecord.modules.AppUser.model.AppUser;
import com.CSCB869.MedicalRecord.modules.AppUser.model.AppUserDTO;
import com.CSCB869.MedicalRecord.modules.AppUser.model.UpdateAppUserDTO;

import java.util.List;
import java.util.Optional;

public interface IAppUserService {
    AppUser save(AppUser appUser);

    List<AppUser> getAll();

    Optional<AppUser> getById(String id);

    AppUserDTO getUserById(String id) throws Exception;

    AppUser update(String id, UpdateAppUserDTO payload) throws Exception;

    void delete(String id) throws Exception;

    boolean usernameExists(String username);

    AppUserDTO convertToDTO(AppUser appUser);
}
