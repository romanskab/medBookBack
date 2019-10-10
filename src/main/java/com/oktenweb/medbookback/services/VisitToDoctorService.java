package com.oktenweb.medbookback.services;

import com.oktenweb.medbookback.entity.Doctor;
import com.oktenweb.medbookback.entity.Patient;
import com.oktenweb.medbookback.entity.VisitToDoctor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface VisitToDoctorService{
    void save(VisitToDoctor visitToDoctor);

    List<VisitToDoctor> findAll();

    List<VisitToDoctor> findByPatient(Patient patient);

    List<VisitToDoctor> findByDoctor (Doctor doctor);
}
