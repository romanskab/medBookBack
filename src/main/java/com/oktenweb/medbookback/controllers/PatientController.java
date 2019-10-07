package com.oktenweb.medbookback.controllers;

import com.oktenweb.medbookback.entity.*;
import com.oktenweb.medbookback.services.CalendarOfVisitsService;
import com.oktenweb.medbookback.services.DoctorService;
import com.oktenweb.medbookback.services.PatientService;
import com.oktenweb.medbookback.services.VisitToDoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
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
    private CalendarOfVisitsService calendarOfVisitsService;

    @Autowired
    private VisitToDoctorService visitToDoctorService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/save/patient")
    public CustomResponse save(@RequestBody Patient patient) throws IOException {
        System.out.println(patient);
        patient.setPassword(passwordEncoder.encode(patient.getPassword()));
        patientService.save(patient);
        return new CustomResponse("save/patient ok!", true);
    }

//    @InitBinder("**")
//    public void initBinder(){
//        System.out.println("init!!!!!!!!!!!");
//    }

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

    @GetMapping("/patient/freeTimeToDoctor/{doctorId}")
    public List<CalendarOfVisits> getFreeTimeToDoctor(@PathVariable int doctorId) {
        return calendarOfVisitsService.findAllByDoctorIdAndPatientIsNull(doctorId);
    }

    @PostMapping("/patient/saveRecordInCalendar/{calendarId}/{patientId}")
    public CustomResponse saveRecordInCalendar(@PathVariable int calendarId, @PathVariable int patientId) {
        CalendarOfVisits calendar = calendarOfVisitsService.findById(calendarId);
        Patient patient = patientService.findOneById(patientId);
        calendar.setPatient(patient);
        calendarOfVisitsService.save(calendar);
        return new CustomResponse("saveRecordInCalendar ok!", true);
    }
    
    @GetMapping("/patient/visitsToDoctor/last/{patientId}")
    public VisitToDoctor getLastVisitToDoctor(@PathVariable int patientId){
        Patient patient = patientService.findOneById(patientId);
        List<VisitToDoctor> visits = visitToDoctorService.findByPatient(patient);
        VisitToDoctor visitToDoctor = visits.stream().max(Comparator.comparing(VisitToDoctor::getDate)).get();
        System.out.println(visitToDoctor);
        return visitToDoctor;
    }
}
