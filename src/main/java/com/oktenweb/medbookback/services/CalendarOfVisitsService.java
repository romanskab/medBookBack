package com.oktenweb.medbookback.services;

import com.oktenweb.medbookback.entity.CalendarOfVisits;
import com.oktenweb.medbookback.entity.Doctor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public interface CalendarOfVisitsService {
    void save(CalendarOfVisits calendarOfVisits);

    List<CalendarOfVisits> findAllByDoctorId(int id);

    List<CalendarOfVisits> findAllByDoctorIdAndPatientIsNull(int id);

    CalendarOfVisits findById(int id);

    List<CalendarOfVisits> findByDoctorAndDate(Doctor doctor, LocalDate localDate);

    List<CalendarOfVisits> findByDoctorAndDateAndPatientIsNotNull (Doctor doctor, LocalDate localDate);

}
