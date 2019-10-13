package com.oktenweb.medbookback.controllers;

import com.oktenweb.medbookback.entity.*;
import com.oktenweb.medbookback.services.DoctorService;
import com.oktenweb.medbookback.services.PatientService;
import com.oktenweb.medbookback.services.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
public class PatientController {

    @Autowired
    private PatientService patientService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private VisitService visitService;

    @Autowired
    private PasswordEncoder passwordEncoder;

//    @Autowired
//    private LocalDateCustomEditor localDateCustomEditor;
//
//    @InitBinder()
//    public void initBinder(WebDataBinder binder){
//        System.out.println(LocalDate.now());
//        binder.registerCustomEditor(LocalDate.class, localDateCustomEditor);
//        System.out.println(binder);
//        System.out.println("init!!!!!!!!patient");
//    }

    @PostMapping("/save/patient")
    public CustomResponse save(@RequestBody Patient patient){
        System.out.println(patient);
        patient.setPassword(passwordEncoder.encode(patient.getPassword()));
//        тимчасове рішення для дати
        LocalDate plus1day = patient.getDateOfBirth().plusDays(1);
        System.out.println(plus1day);
        patient.setDateOfBirth(plus1day);

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
    public List<Doctor> getDoctorsBySpeciality(@PathVariable String speciality) {
        Speciality speciality1 = Speciality.valueOf(speciality);
        return doctorService.findBySpeciality(speciality1);
    }

    @GetMapping("/patient/freeVisitToDoctor/{doctorId}")
    public List<Visit> getFreeVisitToDoctor(@PathVariable int doctorId) {
        System.out.println(doctorId);
//      тимчасове рішення для дати
        LocalDate day = LocalDate.now().plusDays(0);

        List<Visit> visits = visitService.findAllByDoctorIdAndPatientIsNullAndDateIsAfter(doctorId, day);
        return visits;
    }

    @PostMapping("/patient/recordToDoctor/{visitId}/{patientId}")
    public CustomResponse recordToDoctor(@PathVariable int visitId, @PathVariable int patientId) {
        Visit visit = visitService.findById(visitId);
        Patient patient = patientService.findOneById(patientId);
        visit.setPatient(patient);
//        тимчасове рішення для дати
        visit.setDate(visit.getDate().plusDays(1));

        visitService.save(visit);
        return new CustomResponse("saveVisit ok!", true);
    }
    
    @GetMapping("/patient/visit/last/{patientId}")
    public Visit getLastVisit(@PathVariable int patientId){
        List<Visit> visits = visitService.findAllByPatientIdAndConclusionIsNotNull(patientId);
        Visit lastVisit = visits.stream().max(Comparator.comparing(Visit::getDate)).get();
        System.out.println(visits);
        System.out.println(lastVisit);
        System.out.println(patientId);
        return lastVisit;
    }

    @GetMapping("/patient/visits/finished/{patientId}")
    public List<Visit> getAllFinishedVisitsByPatientId(@PathVariable int patientId){
        System.out.println(patientId);
        List<Visit> visits = visitService.findAllByPatientIdAndConclusionIsNotNull(patientId);
        for (Visit visit : visits) {
            System.out.println(visit);
        }
        return visits;
    }
}
