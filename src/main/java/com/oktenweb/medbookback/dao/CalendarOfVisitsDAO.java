package com.oktenweb.medbookback.dao;

import com.oktenweb.medbookback.entity.CalendarOfVisits;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CalendarOfVisitsDAO extends JpaRepository<CalendarOfVisits, Integer> {
    List<CalendarOfVisits> findAllByDoctorId(int id);

    List<CalendarOfVisits> findAllByDoctorIdAndPatientIsNull(int id);

}
