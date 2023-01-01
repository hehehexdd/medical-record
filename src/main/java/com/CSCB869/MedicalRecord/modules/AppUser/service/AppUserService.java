package com.CSCB869.MedicalRecord.modules.AppUser.service;

import com.CSCB869.MedicalRecord.modules.AppUser.model.AppUser;
import com.CSCB869.MedicalRecord.modules.AppUser.model.AppUserDTO;
import com.CSCB869.MedicalRecord.modules.AppUser.model.AppUserUpdateDTO;
import com.CSCB869.MedicalRecord.modules.AppUser.repo.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class AppUserService implements IAppUserService{

    @Autowired
    private AppUserRepository appUserRepository;

    @Override
    public AppUser save(AppUser appUser) throws Exception {
        if(this.usernameExists(appUser.getUsername())) throw new Exception("Username exists!");
        return this.appUserRepository.save(appUser);
    }

    @Override
    public List<AppUser> getAll() {
        return this.appUserRepository.findAll();
    }

    @Override
    public Optional<AppUser> getById(String id) {
        return this.appUserRepository.findById(id);
    }

    @Override
    public AppUserDTO getUserById(String id) throws Exception {
        Optional<AppUser> optionalUser = this.getById(id);
        if(optionalUser.isEmpty()) throw new Exception("User Not Found!");
        return this.convertToDTO(optionalUser.get());
    }

    @Override
    public AppUser update(String id, AppUserUpdateDTO payload) throws Exception {
        AppUser user = this.appUserRepository.findById(id).orElse(null);

        if(user == null) throw new Exception("User Not Found!");

        if(!payload.getPassword().isEmpty()) {
            user.setPassword(payload.getPassword());
        }
        return this.appUserRepository.save(user);
    }

    @Override
    public void delete(String id) throws Exception {
        AppUser user = this.appUserRepository.findById(id).orElse(null);

        if(user == null) throw new Exception("User Not Found!");

        this.appUserRepository.deleteById(id);
    }

    public boolean usernameExists(String username) {
        return this.appUserRepository.findByUsername(username).isPresent();
    }

    public AppUserDTO convertToDTO(AppUser appUser) {
        return new AppUserDTO(
                appUser.getId(),
                appUser.getUsername(),
                appUser.getRole()
        );
    }
}
