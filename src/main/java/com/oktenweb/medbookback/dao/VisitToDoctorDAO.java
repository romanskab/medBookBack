package com.oktenweb.medbookback.dao;

import com.oktenweb.medbookback.entity.Doctor;
import com.oktenweb.medbookback.entity.Patient;
import com.oktenweb.medbookback.entity.VisitToDoctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface VisitToDoctorDAO extends JpaRepository<VisitToDoctor, Integer> {
    List<VisitToDoctor> findByPatient(Patient patient);

    List<VisitToDoctor> findByDoctor (Doctor doctor);


}
