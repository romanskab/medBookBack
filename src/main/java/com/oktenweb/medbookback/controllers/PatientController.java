package com.oktenweb.medbookback.controllers;

import com.oktenweb.medbookback.entity.CustomResponse;
import com.oktenweb.medbookback.entity.Doctor;
import com.oktenweb.medbookback.entity.Patient;
import com.oktenweb.medbookback.entity.Speciality;
import com.oktenweb.medbookback.services.DoctorService;
import com.oktenweb.medbookback.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class PatientController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/save/patient")
    public CustomResponse save(@RequestBody Patient patient) throws IOException {
        System.out.println(patient);
        patient.setPassword(passwordEncoder.encode(patient.getPassword()));
        patientService.save(patient);
        return new CustomResponse("save/patient ok!", true);
    }

    @GetMapping("/patients")
    public List<Patient> patients() {
        return patientService.findAll();
    }

    @GetMapping("/patient/{id}")
    public Patient getPatientById(@PathVariable int id) {
        return patientService.findOneById(id);
    }

    @GetMapping("/patient/current")
    public Patient currentPatient() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Patient patient = patientService.findByUsername(username);
        patient.setPassword(null);
        return patient;
    }

    @GetMapping("/patient/doctors/spec/{speciality}")
    public List<Doctor> getDoctorsBySpeciality(@PathVariable String speciality){
        System.out.println("GM spec...");
        Speciality speciality1 = Speciality.valueOf(speciality);
        return doctorService.findBySpeciality(speciality1);
    }
}
