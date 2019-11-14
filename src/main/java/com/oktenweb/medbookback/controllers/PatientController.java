package com.oktenweb.medbookback.controllers;

import com.oktenweb.medbookback.entity.*;
import com.oktenweb.medbookback.services.DoctorService;
import com.oktenweb.medbookback.services.PatientService;
import com.oktenweb.medbookback.services.TestResultService;
import com.oktenweb.medbookback.services.VisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

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
    private TestResultService testResultService;

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
    public CustomResponse save(@RequestBody Patient patient) {
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
        System.out.println(patient);
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
        LocalDate yesterday = LocalDate.now().minusDays(1);

        List<Visit> visits = visitService.findAllByDoctorIdAndPatientIsNullAndDateIsAfter(doctorId, yesterday);
        return visits;
    }

    @PostMapping("/patient/recordToDoctor/{visitId}/{patientId}")
    public CustomResponse recordToDoctor(@PathVariable int visitId, @PathVariable int patientId) {
        Visit visit = visitService.findById(visitId);
        Patient patient = patientService.findOneById(patientId);
        visit.setPatient(patient);
        visitService.save(visit);
        return new CustomResponse("saveVisit ok!", true);
    }

    @GetMapping("/patient/visit/last/{patientId}")
    public Visit getLastVisit(@PathVariable int patientId) {
        try {
            List<Visit> visits = visitService.findAllByPatientIdAndConclusionIsNotNull(patientId);
            Visit lastVisit = visits.stream().max(Comparator.comparing(Visit::getDate)).get();
            System.out.println(visits);
            System.out.println(lastVisit);
            System.out.println(patientId);
            return lastVisit;
        } catch (NoSuchElementException e) {
            System.out.println(e.getCause());
            return null;
        }
    }

    @GetMapping("/patient/visit/next/{patientId}")
    public Visit getNextVisit(@PathVariable int patientId) {
        try {
            LocalDate yesterday = LocalDate.now().minusDays(1);
            List<Visit> visits = visitService.findAllByPatientIdAndConclusionIsNullAndDateIsAfter(patientId, yesterday);
            Visit nextVisit = visits.stream().min(Comparator.comparing(Visit::getDate)).get();
            System.out.println(nextVisit);
            return nextVisit;
        } catch (NoSuchElementException e) {
            System.out.println(e.getCause());
            return null;
        }
    }

    @GetMapping("/patient/visits/finished/{patientId}")
    public List<Visit> getAllFinishedVisitsByPatientId(@PathVariable int patientId) {
        System.out.println(patientId);
        List<Visit> visits = visitService.findAllByPatientIdAndConclusionIsNotNull(patientId);
        for (Visit visit : visits) {
            System.out.println(visit);
        }
        return visits;
    }

    //    -------------------------------------------
//                    АНАЛІЗИ
//    -------------------------------------------
    @GetMapping("/patient/testResult/last/{patientId}/{title}")
    public TestResult getLastTestResult(@PathVariable String title, @PathVariable int patientId) {
        try {
            List<TestResult> results = testResultService.findAllByPatientIdAndTestTitle(patientId, title);
            TestResult testResult = results.stream().max(Comparator.comparing(TestResult::getDate)).get();
            System.out.println(testResult);
            return testResult;
        } catch (NoSuchElementException e) {
            System.out.println(e.getCause());
            return null;
        }

    }

    @GetMapping("/patient/testResults/{patientId}")
    public List<TestResult> getAllTestResults(@PathVariable int patientId) {
        List<TestResult> testResults = testResultService.findAllByPatientId(patientId);
        System.out.println(testResults);
        return testResults;
    }

    @GetMapping("/patient/testResultsByTitle/{patientId}/{title}")
    public List<TestResult> getAllTestsByTitleAndPatient(@PathVariable int patientId, @PathVariable String title) {
        List<TestResult> testResults = testResultService.findAllByPatientIdAndTestTitle(patientId, title);
        for (TestResult testResult : testResults) {
            System.out.println(testResult);
        }
        return testResults;
    }
}
