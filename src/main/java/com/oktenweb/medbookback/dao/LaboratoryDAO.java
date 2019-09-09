package com.oktenweb.medbookback.dao;

import com.oktenweb.medbookback.entity.Laboratory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LaboratoryDAO extends JpaRepository<Laboratory, Integer> {
    Laboratory findByUsername(String username);
}
