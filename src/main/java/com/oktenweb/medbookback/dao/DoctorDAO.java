package com.oktenweb.medbookback.dao;

import com.oktenweb.medbookback.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoctorDAO extends JpaRepository<Doctor, Integer> {
    Doctor findByUsername (String username);
}
