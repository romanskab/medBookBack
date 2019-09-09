package com.oktenweb.medbookback.dao;

import com.oktenweb.medbookback.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDAO extends JpaRepository<User, Integer> {
    User findUserByUsername(String username);
}
