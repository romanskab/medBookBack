package com.oktenweb.medbookback.services;

import com.oktenweb.medbookback.entity.TestResult;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TestResultService {
    void save(TestResult testResult);

    TestResult findById(int id);

    List<TestResult> findAllByPatientId (int id);

    List<TestResult> findAllByPatientIdAndTestTitle (int id, String title);

}
