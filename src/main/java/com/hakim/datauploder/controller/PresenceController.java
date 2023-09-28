package com.hakim.datauploder.controller;

import com.hakim.datauploder.model.MonthlyPresence;
import com.hakim.datauploder.service.MonthlyPresenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/presence")
public class PresenceController {
    private final MonthlyPresenceService monthlyPresenceService;

    @GetMapping("/get")
    public ResponseEntity<?> getMonthlyPresence(@RequestParam long presenceId){
        MonthlyPresence monthlyPresence = monthlyPresenceService.getById(presenceId);

        return ResponseEntity.ok(monthlyPresence);
    }

    @GetMapping("/get-by-section")
    public ResponseEntity<?> getBySection(@RequestParam long section){
        MonthlyPresence monthlyPresence = monthlyPresenceService.getBySection(section);

        return ResponseEntity.ok(monthlyPresence);
    }

    @GetMapping("/get-by-student")
    public ResponseEntity<?> getByStudent(@RequestParam long student,@RequestParam long section){
        List<LocalDate> dates = monthlyPresenceService.getByStudent(student, section);

        return ResponseEntity.ok(dates);
    }

    @GetMapping("/get-by-month")
    public ResponseEntity<?> getByMonth(@RequestParam long section,@RequestParam String date){
        Map<LocalDate,List<Double>> rolls = monthlyPresenceService.getByMonth(section,date);

        return ResponseEntity.ok(rolls);
    }

    @GetMapping("/get-by-day")
    public ResponseEntity<?> getByDay(@RequestParam long section,@RequestParam String date){
        List<Double> rolls = monthlyPresenceService.getByDay(section,date);

        return ResponseEntity.ok(rolls);
    }

    @GetMapping("/get-by-month-and-student")
    public ResponseEntity<?> getByMonthAndStudent(@RequestParam long section,
                                                  @RequestParam String date,
                                                  @RequestParam long student){
        List<LocalDate> dates = monthlyPresenceService.getByMonthAndStudent(section,date,student);

        return ResponseEntity.ok(dates);
    }
}
