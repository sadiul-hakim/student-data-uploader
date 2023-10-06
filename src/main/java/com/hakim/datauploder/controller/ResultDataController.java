package com.hakim.datauploder.controller;

import com.hakim.datauploder.model.ResultData;
import com.hakim.datauploder.service.ResultDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/result-data")
public class ResultDataController {

    private final ResultDataService resultDataService;

    @GetMapping("/get-by-id")
    public ResponseEntity<?> getFeeData(@RequestParam long feeId) {

        ResultData resultData = resultDataService.getById(feeId);
        return ResponseEntity.ok(resultData);
    }

    @GetMapping("/get")
    public ResponseEntity<?> getBySectionAndDataType(@RequestParam long section,
                                                     @RequestParam long department,
                                                     @RequestParam long year,
                                                     @RequestParam long dataType) {

        List<ResultData> resultData = resultDataService.getByFields(section, department, year, dataType);
        return ResponseEntity.ok(resultData);
    }

    @GetMapping("/get-by-student")
    public ResponseEntity<?> getByStudent(@RequestParam double student,
                                          @RequestParam long section,
                                          @RequestParam long department,
                                          @RequestParam long year,
                                          @RequestParam long dataType) {

        return ResponseEntity.ok(resultDataService.getByStudent(student, section, department, year, dataType));
    }

    @GetMapping("/get-by-exam")
    public ResponseEntity<?> getByExam(@RequestParam String exam,
                                       @RequestParam long section,
                                       @RequestParam long department,
                                       @RequestParam long year,
                                       @RequestParam long dataType) {

        return ResponseEntity.ok(resultDataService.getByExam(exam, section, department, year, dataType));
    }
}
