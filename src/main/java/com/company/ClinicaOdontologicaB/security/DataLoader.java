package com.company.ClinicaOdontologicaB.security;

import com.company.ClinicaOdontologicaB.model.AppUser;
import com.company.ClinicaOdontologicaB.model.AppUserRole;
import com.company.ClinicaOdontologicaB.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    private UserRepository userRepository;

    @Autowired
    public DataLoader(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void run(ApplicationArguments args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String hashedPassword = passwordEncoder.encode("password");
        BCryptPasswordEncoder passwordEncoderB = new BCryptPasswordEncoder();
        String hashedPasswordB = passwordEncoderB.encode("passwordB");
        userRepository.save(new AppUser("UsuarioAdmin", "UsuarioAdmin", "admin@email.com", hashedPassword, AppUserRole.ROLE_ADMIN));
        userRepository.save(new AppUser("UsuarioUser", "UsuarioUser", "user@email.com", hashedPasswordB, AppUserRole.ROLE_USER));
    }
}
