package com.oktenweb.medbookback.services.impl;

import com.oktenweb.medbookback.dao.DoctorDAO;
import com.oktenweb.medbookback.entity.Doctor;
import com.oktenweb.medbookback.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {
    @Autowired
    private DoctorDAO doctorDAO;

    @Override
    public void save(Doctor doctor) {
        if (doctor !=null){
            doctorDAO.save(doctor);
        }
    }

    @Override
    public List<Doctor> findAll() {
        return doctorDAO.findAll();
    }

    @Override
    public Doctor findByUsername(String username) {
        return doctorDAO.findByUsername(username);
    }

    @Override
    public Doctor findOneById(Integer id) {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return doctorDAO.findByUsername(username);
    }
}
