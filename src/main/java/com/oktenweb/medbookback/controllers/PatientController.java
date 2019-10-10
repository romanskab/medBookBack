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
    private CalendarOfVisitsService calendarOfVisitsService;

    @Autowired
    private VisitToDoctorService visitToDoctorService;

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

    @GetMapping("/patient/freeTimeToDoctor/{doctorId}")
    public List<CalendarOfVisits> getFreeTimeToDoctor(@PathVariable int doctorId) {
        return calendarOfVisitsService.findAllByDoctorIdAndPatientIsNull(doctorId);
    }

    @PostMapping("/patient/saveRecordInCalendar/{calendarId}/{patientId}")
    public CustomResponse saveRecordInCalendar(@PathVariable int calendarId, @PathVariable int patientId) {
        CalendarOfVisits calendar = calendarOfVisitsService.findById(calendarId);
        Patient patient = patientService.findOneById(patientId);
        calendar.setPatient(patient);
//        тимчасове рішення для дати
        calendar.setDate(calendar.getDate().plusDays(1));

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
