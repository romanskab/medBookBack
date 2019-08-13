package com.oktenweb.medbookback.services.impl;

import com.oktenweb.medbookback.dao.PatientDAO;
import com.oktenweb.medbookback.entity.Patient;
import com.oktenweb.medbookback.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {
    @Autowired
    private PatientDAO patientDAO;

    @Override
    public void save(Patient patient) {
        if (patient != null) patientDAO.save(patient);
    }

    @Override
    public List<Patient> findAll() {
        return patientDAO.findAll();
    }

    @Override
    public Patient findOneById(Integer id) {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return patientDAO.findByUsername(username);
    }
}
