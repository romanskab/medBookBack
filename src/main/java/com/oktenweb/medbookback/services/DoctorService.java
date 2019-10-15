package com.oktenweb.medbookback.services;

import com.oktenweb.medbookback.entity.Doctor;
import com.oktenweb.medbookback.entity.Speciality;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DoctorService extends UserDetailsService {
    void save(Doctor doctor);

    List<Doctor> findAll();

    Doctor findByUsername(String username);

    Doctor findOneById(int id);

    List<Doctor> findBySpeciality(Speciality speciality);
}
