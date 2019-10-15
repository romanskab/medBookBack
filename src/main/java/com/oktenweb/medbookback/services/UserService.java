package com.oktenweb.medbookback.services;

import com.oktenweb.medbookback.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService extends UserDetailsService {
    void save(User user);

    List<User> findAll();

    User findById(int id);

    User findUserByUsername(String username);
}
