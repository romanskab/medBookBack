package com.oktenweb.medbookback.services.impl;

import com.oktenweb.medbookback.dao.VisitDAO;
import com.oktenweb.medbookback.entity.Visit;
import com.oktenweb.medbookback.services.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class VisitServiceImpl implements VisitService {

    @Autowired
    VisitDAO visitDAO;

    @Override
    public void save(Visit visit) {
        visitDAO.save(visit);
    }

    @Override
    public Visit findById(int id) {
        return visitDAO.findById(id).get();
    }

    @Override
    public List<Visit> findAllByDoctorId(int id) {
        return visitDAO.findAllByDoctorId(id);
    }

    @Override
    public List<Visit> findAllByDoctorIdAndPatientIsNull(int id) {
        return visitDAO.findAllByDoctorIdAndPatientIsNull(id);
    }

    @Override
    public List<Visit> findAllByPatientId(int id) {
        return visitDAO.findAllByPatientId(id);
    }

    @Override
    public List<Visit> findAllByDoctorIdAndDateAndPatientIsNotNullAndConclusionIsNull(int id, LocalDate localDate) {
        return visitDAO.findAllByDoctorIdAndDateAndPatientIsNotNullAndConclusionIsNull(id, localDate);
    }

    @Override
    public List<Visit> findAllByDoctorIdAndConclusionIsNullAndDateIsAfter(int id, LocalDate localDate) {
        return visitDAO.findAllByDoctorIdAndConclusionIsNullAndDateIsAfter(id, localDate);
    }

    @Override
    public List<Visit> findAllByDoctorIdAndConclusionIsNotNull(int id) {
        return visitDAO.findAllByDoctorIdAndConclusionIsNotNull(id);
    }

    @Override
    public List<Visit> findAllByPatientIdAndConclusionIsNotNull(int id) {
        return visitDAO.findAllByPatientIdAndConclusionIsNotNull(id);
    }

    @Override
    public List<Visit> findAllByDoctorIdAndPatientIsNotNullAndConclusionIsNullAndDateIsAfter(int id, LocalDate localDate) {
        return visitDAO.findAllByDoctorIdAndPatientIsNotNullAndConclusionIsNullAndDateIsAfter(id, localDate);
    }

    @Override
    public List<Visit> findAllByDoctorIdAndPatientIsNullAndDateIsAfter(int id, LocalDate localDate) {
        return visitDAO.findAllByDoctorIdAndPatientIsNullAndDateIsAfter(id, localDate);
    }


}
