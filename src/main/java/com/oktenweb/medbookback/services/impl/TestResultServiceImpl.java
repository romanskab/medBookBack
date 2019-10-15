package com.oktenweb.medbookback.services.impl;

import com.oktenweb.medbookback.dao.TestResultDAO;
import com.oktenweb.medbookback.entity.TestResult;
import com.oktenweb.medbookback.services.TestResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestResultServiceImpl implements TestResultService {
    @Autowired
    TestResultDAO testResultDAO;

    @Override
    public void save(TestResult testResult) {
        testResultDAO.save(testResult);
    }

    @Override
    public TestResult findById(int id) {
        return testResultDAO.findById(id);
    }

    @Override
    public List<TestResult> findAllByPatientId(int id) {
        return testResultDAO.findAllByPatientId(id);
    }

    @Override
    public List<TestResult> findAllByPatientIdAndTestTitle(int id, String title) {
        return testResultDAO.findAllByPatientIdAndTestTitle(id, title);
    }
}
