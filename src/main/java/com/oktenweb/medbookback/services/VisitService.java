package com.oktenweb.medbookback.services;

import com.oktenweb.medbookback.entity.Doctor;
import com.oktenweb.medbookback.entity.Patient;
import com.oktenweb.medbookback.entity.Visit;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface VisitService {
    void save(Visit visit);

    Visit findById(int id);

    List<Visit> findAllByDoctorId(int id);

    List<Visit> findAllByDoctorIdAndPatientIsNull(int id);

    List<Visit> findAllByPatientId(int id);

    List<Visit> findAllByDoctorIdAndDateAndPatientIsNotNullAndConclusionIsNull (int id, LocalDate localDate);

    List<Visit> findAllByDoctorIdAndConclusionIsNotNull(int id);

    List<Visit> findAllByPatientIdAndConclusionIsNotNull(int id);
}
