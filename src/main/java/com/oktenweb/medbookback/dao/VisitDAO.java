package com.oktenweb.medbookback.dao;

import com.oktenweb.medbookback.entity.Doctor;
import com.oktenweb.medbookback.entity.Patient;
import com.oktenweb.medbookback.entity.Visit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface VisitDAO extends JpaRepository<Visit, Integer> {
    List<Visit> findAllByDoctor(Doctor doctor);
    List<Visit> findAllByDoctorId(int id);

    List<Visit> findAllByPatient(Patient patient);
    List<Visit> findAllByPatientId(int id);

    List<Visit> findAllByDoctorIdAndConclusionIsNotNull(int id);
    List<Visit> findAllByPatientIdAndConclusionIsNotNull(int id);

    List<Visit> findAllByDoctorIdAndPatientIsNull(int id);

    List<Visit> findAllByDoctorIdAndDateAndPatientIsNotNullAndConclusionIsNull (int id, LocalDate localDate);

}
