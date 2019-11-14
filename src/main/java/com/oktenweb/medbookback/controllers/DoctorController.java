package com.oktenweb.medbookback.controllers;

import com.oktenweb.medbookback.entity.*;
import com.oktenweb.medbookback.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class DoctorController {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService userService;

    @Autowired
    private DoctorService doctorService;

    @Autowired
    private PatientService patientService;

    @Autowired
    private VisitService visitService;

    @Autowired
    private TestService testService;

    @Autowired
    private TestResultService testResultService;

//    @Autowired
//    private LocalDateCustomEditor localDateCustomEditor;
//
//    @InitBinder()
//    public void initBinder(WebDataBinder binder){
//        binder.registerCustomEditor(LocalDate.class, localDateCustomEditor);
//        System.out.println("init!!!!!!!!doctor");
//    }

    @GetMapping("/specialities")
    public Speciality[] getSpecialities() {
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

    @PostMapping("/doctor/getPatientByUsername")
    public Patient findPatientById(@RequestBody String username) {
        Patient patient = patientService.findByUsername(username);
        patient.setPassword(null);
        System.out.println(patient);
        return patient;
    }

    @PostMapping("/doctor/saveResultOfVisit/{visitId}")
    public CustomResponse saveResultOfVisit(@RequestBody String conclusion, @PathVariable int visitId) {
        Visit visit = visitService.findById(visitId);
        visit.setConclusion(conclusion);
        visitService.save(visit);
        return new CustomResponse("saveResultOfVisit ok!", true);
    }

    @GetMapping("/doctor/visitsByPatientId/{id}")
    public List<Visit> getVisitsByPatientId(@PathVariable int id) {
        return visitService.findAllByPatientId(id);
    }

    @GetMapping("/doctor/visitsByDoctorId/{id}")
    public List<Visit> getVisitsByDoctorId(@PathVariable int id) {
        System.out.println(id);
        return visitService.findAllByDoctorId(id);
    }

    @GetMapping("/doctor/finishedVisitsByPatientId/{id}")
    public List<Visit> getAllFinishedVisitsByPatientId(@PathVariable int id) {
        System.out.println(id);
        return visitService.findAllByPatientIdAndConclusionIsNotNull(id);
    }

    @GetMapping("/doctor/finishedVisitsByDoctorId/{id}")
    public List<Visit> getAllFinishedVisitsByDoctorId(@PathVariable int id) {
        System.out.println(id);
        return visitService.findAllByDoctorIdAndConclusionIsNotNull(id);
    }

    @GetMapping("/doctor/lastVisit/{doctorId}")
    public Visit getLastVisitForDoctor(@PathVariable int doctorId){
        System.out.println(doctorId);
        try {
            List<Visit> visits = visitService.findAllByDoctorIdAndConclusionIsNotNull(doctorId);
//        тимчасово до переходу на datetime
            Visit lastVisit = visits.get(visits.size() - 1);
            System.out.println(lastVisit);
            return lastVisit;
        } catch (IndexOutOfBoundsException e){
            System.out.println(e.getCause());
            return null;
        }

    }

    @PostMapping("/doctor/addWorkTimes/{doctorId}/{date}")
    public CustomResponse createCalendar(@RequestBody String[] times,
                                         @PathVariable int doctorId, @PathVariable String date) {
        Doctor doctor = doctorService.findOneById(doctorId);
        System.out.println(date);
        String[] split = date.split("-");
        int year = new Integer(split[0]);
        int month = new Integer(split[1]);
        int day = new Integer(split[2]);
        LocalDate localDate = LocalDate.of(year, month, day);
        for (String time : times) {
            Visit visit = new Visit();
            visit.setDoctor(doctor);
            visit.setDate(localDate);
            visit.setTime(time);
            visitService.save(visit);
        }
        return new CustomResponse("createCalendar ok!", true);
    }

    @GetMapping("/doctor/futureTodayVisits/{doctorId}")
    public List<Visit> getFutureTodayVisits(@PathVariable int doctorId) {
        System.out.println(doctorId);
        LocalDate today = LocalDate.now();
        List<Visit> visits = visitService.findAllByDoctorIdAndDateAndPatientIsNotNullAndConclusionIsNull(doctorId, today);
        for (Visit visit : visits) {
            System.out.println(visit);
        }
        return visits;
    }

    @GetMapping("/doctor/futureVisits/{doctorId}")
    public List<Visit> getFutureVisits(@PathVariable int doctorId) {
        System.out.println(doctorId);
        LocalDate yesterday = LocalDate.now().minusDays(1);
        List<Visit> visits = visitService.findAllByDoctorIdAndConclusionIsNullAndDateIsAfter(doctorId, yesterday);
        for (Visit visit : visits) {
            System.out.println(visit);
        }
        return visits;
    }

    @GetMapping("/doctor/nextVisit/{doctorId}")
    public Visit getNextVisit(@PathVariable int doctorId) {
        System.out.println(doctorId);
        LocalDate yesterday = LocalDate.now().minusDays(1);

        try {
            List<Visit> visits = visitService.findAllByDoctorIdAndPatientIsNotNullAndConclusionIsNullAndDateIsAfter(doctorId, yesterday);
//        тимчасово до переходу на datetime
            Visit nextVisit = visits.get(0);
            System.out.println(nextVisit);
            return nextVisit;
        }catch (IndexOutOfBoundsException e){
            System.out.println(e.getCause());
            return null;
        }

    }


//    --------------------------------------------
//                     АНАЛІЗИ
//    --------------------------------------------
    @GetMapping("/doctor/tests/titles")
    public List<Test> getTestsTitles(){
        return testService.findAll();
    }

    @PostMapping("/doctor/test/save/{testId}/{patientId}/{meterId}")
    public CustomResponse saveTest(@RequestBody Double result,
                                   @PathVariable int testId,
                                   @PathVariable int patientId,
                                   @PathVariable int meterId
                                   ){
        Test test = testService.findById(testId);
        Patient patient = patientService.findOneById(patientId);
        User user = userService.findById(meterId);
        TestResult testResult = new TestResult();
        testResult.setTest(test);
        testResult.setPatient(patient);
        testResult.setMeter(user);
        testResult.setResult(result);
        testResult.setDate(LocalDate.now());
        System.out.println(testResult);
        testResultService.save(testResult);
        return new CustomResponse("saveTest ok!", true);
    }
    
    @GetMapping("/doctor/testResults/patient/{patientId}")
    public List<TestResult> getTests(@PathVariable int patientId){
        System.out.println(patientId);
        List<TestResult> testResults = testResultService.findAllByPatientId(patientId);
        System.out.println(testResults);
        return testResults;
    }

}
