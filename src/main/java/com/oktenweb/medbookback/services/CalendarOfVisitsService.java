package com.oktenweb.medbookback.services;

import com.oktenweb.medbookback.entity.CalendarOfVisits;
import org.springframework.stereotype.Service;

@Service
public interface CalendarOfVisitsService {
    void save(CalendarOfVisits calendarOfVisits);
}
