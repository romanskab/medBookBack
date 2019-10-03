package com.oktenweb.medbookback.dao;

import com.oktenweb.medbookback.entity.Doctor;
import com.oktenweb.medbookback.entity.Speciality;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoctorDAO extends JpaRepository<Doctor, Integer> {
    Doctor findByUsername (String username);

    List<Doctor> findBySpeciality(Speciality speciality);
}
