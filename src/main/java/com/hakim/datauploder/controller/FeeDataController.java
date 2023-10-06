package com.hakim.datauploder.controller;

import com.hakim.datauploder.model.FeeData;
import com.hakim.datauploder.service.FeeDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/fee-data")
public class FeeDataController {

    private final FeeDataService feeDataService;

    @GetMapping("/get-by-id")
    public ResponseEntity<?> getFeeData(@RequestParam long feeId) {
        FeeData feeData = feeDataService.getById(feeId);

        return ResponseEntity.ok(feeData);
    }

    @GetMapping("/get")
    public ResponseEntity<?> getBySectionAndDataType(@RequestParam long section,
                                                     @RequestParam long department,
                                                     @RequestParam long year,
                                                     @RequestParam long dataType) {
        List<FeeData> feeDataList = feeDataService.getByFields(section, department, year, dataType);

        return ResponseEntity.ok(feeDataList);
    }

    @GetMapping("/get-by-student")
    public ResponseEntity<?> getByStudent(@RequestParam double student,
                                          @RequestParam long section,
                                          @RequestParam long department,
                                          @RequestParam long year,
                                          @RequestParam long dataType) {

        return ResponseEntity.ok(feeDataService.getByStudent(student, section, department, year, dataType));
    }
}
