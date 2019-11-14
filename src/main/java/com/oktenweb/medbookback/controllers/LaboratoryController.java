package com.oktenweb.medbookback.controllers;

import com.oktenweb.medbookback.entity.*;
import com.oktenweb.medbookback.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class LaboratoryController {

    @Autowired
    LaboratoryService laboratoryService;

    @Autowired
    PatientService patientService;

    @Autowired
    TestService testService;

    @Autowired
    TestResultService testResultService;

    @Autowired
    UserService userService;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/save/laboratory")
    public CustomResponse save(@RequestBody Laboratory laboratory) {
        System.out.println(laboratory);
        laboratory.setPassword(passwordEncoder.encode(laboratory.getPassword()));
        laboratoryService.save(laboratory);
        return new CustomResponse("ok!", true);
    }

    @GetMapping("/laboratory/current")
    public Laboratory currentLaboratory() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Laboratory laboratory = laboratoryService.findByUsername(username);
        laboratory.setPassword(null);
        return laboratory;
    }

    @GetMapping("/laboratories")
    public List<Laboratory> laboratories() {
        return laboratoryService.findAll();
    }

    @GetMapping("/laboratory/getPatientByUsername/{username}")
    public Patient getPatientByUsername(@PathVariable String username) {
        System.out.println(username);
        return patientService.findByUsername(username);
    }

    @GetMapping("/laboratory/tests/titles")
    public List<Test> getTestsTitles() {
        return testService.findAll();
    }

    @PostMapping("/laboratory/test/save/{patientId}/{meterId}")
    public CustomResponse saveTest(@RequestBody TestResult[] results,
                                   @PathVariable int patientId,
                                   @PathVariable int meterId
    ) {
        System.out.println(Arrays.toString(results));
        System.out.println(patientId);
        System.out.println(meterId);
        Patient patient = patientService.findOneById(patientId);
        User user = userService.findById(meterId);
        for (TestResult result : results) {
            Test test = testService.findById(result.getTest().getId());
            result.setTest(test);
            result.setPatient(patient);
            result.setMeter(user);
//        тимчасове рішення для дати
//            result.setDate(LocalDate.now().plusDays(1));
            System.out.println(result);
            testResultService.save(result);
        }
        return new CustomResponse("saveTest ok!", true);
    }
}
