package com.oktenweb.medbookback.controllers;

import com.oktenweb.medbookback.entity.CustomResponse;
import com.oktenweb.medbookback.entity.Laboratory;
import com.oktenweb.medbookback.services.LaboratoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class LaboratoryController {

    @Autowired
    LaboratoryService laboratoryService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/save/laboratory")
    public CustomResponse save(@RequestBody Laboratory laboratory){
        System.out.println(laboratory);
        laboratory.setPassword(passwordEncoder.encode(laboratory.getPassword()));
        laboratoryService.save(laboratory);
        return new CustomResponse("ok!", true);
    }

    @GetMapping("/laboratory/current")
    public Laboratory currentLaboratory() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Laboratory laboratory = laboratoryService.findByUsername(username);
        laboratory.setPassword(null);
        return laboratory;
    }

    @GetMapping("/laboratories")
    public List<Laboratory> laboratories(){
        return laboratoryService.findAll();
    }
}
