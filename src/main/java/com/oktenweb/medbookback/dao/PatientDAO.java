package com.oktenweb.medbookback.dao;

import com.oktenweb.medbookback.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface PatientDAO extends JpaRepository<Patient, Integer> {
    Patient findByUsername(String username);
}
