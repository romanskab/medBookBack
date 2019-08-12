package com.oktenweb.medbookback.services;

import com.oktenweb.medbookback.entity.Patient;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface PatientService extends UserDetailsService {
    void save(Patient patient);

    List<Patient> findAll();

    Patient findOneById(Integer id);
}
