package com.oktenweb.medbookback.services;
import com.oktenweb.medbookback.entity.Laboratory;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface LaboratoryService extends UserDetailsService {
    void save(Laboratory laboratory);

    List<Laboratory> findAll();

    Laboratory findByUsername(String username);

    Laboratory findOneById(Integer id);
}
