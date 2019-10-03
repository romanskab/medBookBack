package com.oktenweb.medbookback.services.impl;

import com.oktenweb.medbookback.dao.VisitToDoctorDAO;
import com.oktenweb.medbookback.entity.Patient;
import com.oktenweb.medbookback.entity.VisitToDoctor;
import com.oktenweb.medbookback.services.VisitToDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
class VisitToDoctorServiceImpl implements VisitToDoctorService {
    @Autowired
    private VisitToDoctorDAO visitToDoctorDAO;

    @Override
    public void save(VisitToDoctor visitToDoctor) {
        if (visitToDoctor != null) {
            visitToDoctorDAO.save(visitToDoctor);
        }
    }

    @Override
    public List<VisitToDoctor> findAll() {
        return visitToDoctorDAO.findAll();
    }

    @Override
    public List<VisitToDoctor> findByPatient(Patient patient) {
        return visitToDoctorDAO.findByPatient(patient);
    }


}
