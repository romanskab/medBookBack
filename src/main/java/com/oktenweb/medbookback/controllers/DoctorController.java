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
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class DoctorController {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private VisitToDoctorService visitToDoctorService;

    @Autowired
    private CalendarOfVisitsService calendarOfVisitsService;

    @GetMapping("/specialities")
    public Speciality[] getSpecialities(){
        return Speciality.values();
    }

    @PostMapping("/save/doctor")
    public CustomResponse save(@RequestBody Doctor doctor) {
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

    @PostMapping("/doctor/addWorkTimes/{doctorId}/{date}")
    public CustomResponse createCalendar(@RequestBody String[] times,
                                            @PathVariable int doctorId, @PathVariable String date){
        Doctor doctor = doctorService.findOneById(doctorId);
        String[] split = date.split("-");
        int year = new Integer(split[0]);
        int month = new Integer(split[1]);
        int day = new Integer(split[2]);
        LocalDate localDate = LocalDate.of(year, month, day);
        for (String time : times) {
            CalendarOfVisits calendarOfVisits = new CalendarOfVisits();
            calendarOfVisits.setDoctor(doctor);
            calendarOfVisits.setDate(localDate);
            calendarOfVisits.setTime(time);
            calendarOfVisitsService.save(calendarOfVisits);
        }
        return new CustomResponse("createCalendar ok!", true);
    }

    @GetMapping("/doctor/calendar/{doctorId}")
    public List<CalendarOfVisits> getCalendar(@PathVariable int doctorId){
        System.out.println(doctorId);
        System.out.println(calendarOfVisitsService.findAllByDoctorId(doctorId));
        return calendarOfVisitsService.findAllByDoctorId(doctorId);
    }

    @GetMapping("/doctor/visits/{doctorId}")
    public List<VisitToDoctor> getAllVisitsByDoctor(@PathVariable int doctorId){
        System.out.println(doctorId);
        Doctor doctor = doctorService.findOneById(doctorId);
        return visitToDoctorService.findByDoctor(doctor);
    }

}
