package com.oktenweb.medbookback.services.impl;

import com.oktenweb.medbookback.dao.CalendarOfVisitsDAO;
import com.oktenweb.medbookback.entity.CalendarOfVisits;
import com.oktenweb.medbookback.services.CalendarOfVisitsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalendarOfVisitsServiceImpl implements CalendarOfVisitsService {

    @Autowired
    CalendarOfVisitsDAO calendarOfVisitsDAO;

    @Override
    public void save(CalendarOfVisits calendarOfVisits) {
        if (calendarOfVisits != null){
            calendarOfVisitsDAO.save(calendarOfVisits);
        }
    }

    @Override
    public List<CalendarOfVisits> findAllByDoctorId(int id) {
        return calendarOfVisitsDAO.findAllByDoctorId(id);
    }

    @Override
    public List<CalendarOfVisits> findAllByDoctorIdAndPatientIsNull(int id) {
        return calendarOfVisitsDAO.findAllByDoctorIdAndPatientIsNull(id);
    }

    @Override
    public CalendarOfVisits findById(int id) {
        return calendarOfVisitsDAO.findById(id).get();
    }
}
