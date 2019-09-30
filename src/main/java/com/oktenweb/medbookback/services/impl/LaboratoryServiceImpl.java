package com.oktenweb.medbookback.services.impl;

import com.oktenweb.medbookback.dao.LaboratoryDAO;
import com.oktenweb.medbookback.entity.Laboratory;
import com.oktenweb.medbookback.services.LaboratoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LaboratoryServiceImpl implements LaboratoryService {

    @Autowired
    private LaboratoryDAO laboratoryDAO;

    @Override
    public void save(Laboratory laboratory) {
        if (laboratory != null){
            laboratoryDAO.save(laboratory);
        }
    }

    @Override
    public List<Laboratory> findAll() {
        return laboratoryDAO.findAll();
    }

    @Override
    public Laboratory findByUsername(String username) {
        return laboratoryDAO.findByUsername(username);
    }

    @Override
    public Laboratory findOneById(Integer id) {
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return laboratoryDAO.findByUsername(username);
    }
}
