package com.oktenweb.medbookback.services;

import com.oktenweb.medbookback.entity.Doctor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.List;

public interface DoctorService extends UserDetailsService {
    void save(Doctor doctor);

    List<Doctor> findAll();

    Doctor findOneById(Integer id);
}
