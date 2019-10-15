package com.oktenweb.medbookback.services.impl;

import com.oktenweb.medbookback.dao.TestDAO;
import com.oktenweb.medbookback.entity.Test;
import com.oktenweb.medbookback.services.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestServiceImpl implements TestService {
    @Autowired
    TestDAO testDAO;

    @Override
    public List<Test> findAll() {
        return testDAO.findAll();
    }

    @Override
    public Test findById(int id) {
        return testDAO.findById(id).get();
    }


}
