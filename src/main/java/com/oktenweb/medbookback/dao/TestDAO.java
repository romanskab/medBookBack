package com.oktenweb.medbookback.dao;

import com.oktenweb.medbookback.entity.Test;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TestDAO extends JpaRepository<Test, Integer> {

}
