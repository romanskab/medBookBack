package com.oktenweb.medbookback.services;

import com.oktenweb.medbookback.entity.VisitToDoctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VisitToDoctorService{
    void save(VisitToDoctor visitToDoctor);

    List<VisitToDoctor> findAll();

    List<VisitToDoctor> findByPatientId(int patientId);
}
