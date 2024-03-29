package com.oktenweb.medbookback.services;

import com.oktenweb.medbookback.entity.Patient;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PatientService extends UserDetailsService {
    void save(Patient patient);

    List<Patient> findAll();

    Patient findOneById(int id);

    Patient findByUsername(String username);

}
