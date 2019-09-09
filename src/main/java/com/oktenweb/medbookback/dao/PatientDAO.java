package com.oktenweb.medbookback.dao;

import com.oktenweb.medbookback.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientDAO extends JpaRepository<Patient, Integer> {
    Patient findByUsername(String username);
}
