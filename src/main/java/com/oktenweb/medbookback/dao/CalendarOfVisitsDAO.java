package com.oktenweb.medbookback.dao;

import com.oktenweb.medbookback.entity.CalendarOfVisits;
import com.oktenweb.medbookback.entity.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface CalendarOfVisitsDAO extends JpaRepository<CalendarOfVisits, Integer> {
    List<CalendarOfVisits> findAllByDoctorId(int id);

    List<CalendarOfVisits> findAllByDoctorIdAndPatientIsNull(int id);

    List<CalendarOfVisits> findByDoctorAndDate (Doctor doctor, LocalDate localDate);

}
