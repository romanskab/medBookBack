package com.oktenweb.medbookback.services;

import com.oktenweb.medbookback.entity.CalendarOfVisits;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CalendarOfVisitsService {
    void save(CalendarOfVisits calendarOfVisits);

    List<CalendarOfVisits> findAllByDoctorId(int id);

    List<CalendarOfVisits> findAllByDoctorIdAndPatientIsNull(int id);

    CalendarOfVisits findById(int id);
}
