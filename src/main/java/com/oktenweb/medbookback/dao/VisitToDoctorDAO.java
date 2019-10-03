package com.oktenweb.medbookback.dao;

import com.oktenweb.medbookback.entity.Patient;
import com.oktenweb.medbookback.entity.VisitToDoctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VisitToDoctorDAO extends JpaRepository<VisitToDoctor, Integer> {
    List<VisitToDoctor> findByPatient(Patient patient);
}
