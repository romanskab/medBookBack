package com.oktenweb.medbookback.dao;

import com.oktenweb.medbookback.entity.TestResult;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestResultDAO extends JpaRepository<TestResult, Integer> {
    TestResult findById(int id);
    List<TestResult> findAllByPatientId (int id);

    List<TestResult> findAllByPatientIdAndTestTitle (int id, String title);
}
