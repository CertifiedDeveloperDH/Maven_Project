package com.company.ClinicaOdontologicaB.service;

import com.company.ClinicaOdontologicaB.repository.UserRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
public class AppUserService implements UserDetailsService {
    private final static Logger logger = Logger.getLogger(AppUserService.class);

    private final UserRepository userRepository;

    @Autowired
    public AppUserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email){
        logger.info("Usuario encontrado");

        return userRepository.findByEmail(email).get();
    }
}
