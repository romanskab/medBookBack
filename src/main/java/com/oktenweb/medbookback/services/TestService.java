package com.oktenweb.medbookback.services;

import com.oktenweb.medbookback.entity.Test;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TestService {
    List<Test> findAll();

    Test findById(int id);
}
