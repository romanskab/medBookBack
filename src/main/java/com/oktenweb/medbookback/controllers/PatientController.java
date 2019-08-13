package com.oktenweb.medbookback.controllers;

import com.oktenweb.medbookback.dao.PatientDAO;
import com.oktenweb.medbookback.entity.CustomResponse;
import com.oktenweb.medbookback.entity.Patient;
import com.oktenweb.medbookback.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class PatientController {

//    @Autowired
//    private PatientDAO patientDAO;

    @Autowired
    private PatientService patientService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/save")
    public CustomResponse save(@RequestBody Patient patient) {
        System.out.println(patient);
        patient.setPassword(passwordEncoder.encode(patient.getPassword()));
        patientService.save(patient);
        return new CustomResponse("ok!");
    }

    @GetMapping("/patients")
    public List<Patient> patients() {
        return patientService.findAll();
    }
}
