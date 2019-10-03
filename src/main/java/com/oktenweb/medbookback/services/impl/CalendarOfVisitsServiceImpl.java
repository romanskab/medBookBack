package com.oktenweb.medbookback.services.impl;

import com.oktenweb.medbookback.dao.CalendarOfVisitsDAO;
import com.oktenweb.medbookback.entity.CalendarOfVisits;
import com.oktenweb.medbookback.services.CalendarOfVisitsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
