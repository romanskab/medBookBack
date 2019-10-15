package com.oktenweb.medbookback.services;
import com.oktenweb.medbookback.entity.Laboratory;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LaboratoryService extends UserDetailsService {
    void save(Laboratory laboratory);

    List<Laboratory> findAll();

    Laboratory findByUsername(String username);

    Laboratory findOneById(Integer id);
}
