package com.oktenweb.medbookback.controllers;

import com.oktenweb.medbookback.entity.CustomResponse;
import com.oktenweb.medbookback.entity.Doctor;
import com.oktenweb.medbookback.entity.Patient;
import com.oktenweb.medbookback.entity.VisitToDoctor;
import com.oktenweb.medbookback.services.DoctorService;
import com.oktenweb.medbookback.services.PatientService;
import com.oktenweb.medbookback.services.VisitToDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private VisitToDoctorService visitToDoctorService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/save/doctor")
    public CustomResponse save(@RequestBody Doctor doctor) {
        System.out.println(doctor);
        doctor.setPassword(passwordEncoder.encode(doctor.getPassword()));
        doctorService.save(doctor);
        return new CustomResponse("save/doctor ok!", true);
    }

    @GetMapping("/doctor/current")
    public Doctor currentDoctor() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Doctor doctor = doctorService.findByUsername(username);
        doctor.setPassword(null);
        return doctor;
    }

    @GetMapping("/doctors")
    public List<Doctor> doctors() {
        return doctorService.findAll();
    }

    @PostMapping("/doctor/findPatientByUsername")
    public Patient findPatientById(@RequestBody String username){
        Patient patient = patientService.findByUsername(username);
        patient.setPassword(null);
        System.out.println(patient);
        return patient;
    }

    @PostMapping("/doctor/saveVisitToDoctor")
    public CustomResponse saveVisitToDoctor(@RequestBody VisitToDoctor visitToDoctor){
        System.out.println(visitToDoctor);
        visitToDoctorService.save(visitToDoctor);
        return new CustomResponse("saveVisitToDoctor ok!", true);
    }

    @GetMapping("/doctor/getVisitToDoctorByPatientId/{id}")
    public List<VisitToDoctor> getVisitToDoctorByPatientId(@PathVariable int id){
        System.out.println("--------------------------");
        System.out.println(visitToDoctorService.findByPatientId(id));
        System.out.println("--------------------------");
        return visitToDoctorService.findByPatientId(id);
    }
}
