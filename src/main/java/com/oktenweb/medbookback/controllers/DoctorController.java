package com.oktenweb.medbookback.controllers;

import com.oktenweb.medbookback.entity.CustomResponse;
import com.oktenweb.medbookback.entity.Doctor;
import com.oktenweb.medbookback.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/save/doctor")
    public CustomResponse save(@RequestBody Doctor doctor){
        System.out.println(doctor);
        doctor.setPassword(passwordEncoder.encode(doctor.getPassword()));
        doctorService.save(doctor);
        return new CustomResponse("ok!", true);
    }

    @GetMapping("/doctors")
    public List<Doctor> doctors(){
        return doctorService.findAll();
    }
}
