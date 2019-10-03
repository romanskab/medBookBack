package com.oktenweb.medbookback.controllers;

import com.oktenweb.medbookback.entity.*;
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

    @GetMapping("/specialities")
    public Speciality[] getSpecialities(){
        return Speciality.values();
    }

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

    @PostMapping("/doctor/saveVisitToDoctor/{doctorId}/{patientId}")
    public CustomResponse saveVisitToDoctor(@RequestBody VisitToDoctor visitToDoctor,
                                            @PathVariable int doctorId, @PathVariable int patientId){
        Doctor doctor = doctorService.findOneById(doctorId);
        Patient patient = patientService.findOneById(patientId);
        visitToDoctor.setDoctor(doctor);
        visitToDoctor.setPatient(patient);
        visitToDoctorService.save(visitToDoctor);
        return new CustomResponse("saveVisitToDoctor ok!", true);
    }

    @GetMapping("/doctor/getVisitsToDoctorByPatientId/{id}")
    public List<VisitToDoctor> getVisitToDoctorByPatientId(@PathVariable int id){
        Patient patient = patientService.findOneById(id);
        return visitToDoctorService.findByPatient(patient);
    }

    @PostMapping("/doctor/createCalendar/{doctorId}")
    public CustomResponse createCalendar(@RequestBody CalendarOfVisits calendarOfVisits,
                                            @PathVariable int doctorId){
        Doctor doctor = doctorService.findOneById(doctorId);
        calendarOfVisits.setDoctor(doctor);
        return new CustomResponse("createCalendar ok!", true);
    }

}
