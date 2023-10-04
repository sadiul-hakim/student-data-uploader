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

    @GetMapping("/get")
    public ResponseEntity<?> getFeeData(@RequestParam long feeId){
        FeeData feeData = feeDataService.getById(feeId);

        return ResponseEntity.ok(feeData);
    }

    @GetMapping("/get-by-section-and-datatype")
    public ResponseEntity<?> getBySectionAndDataType(@RequestParam long section,@RequestParam String dataType){
        List<FeeData> feeDataList = feeDataService.getBySectionAndDataType(section, dataType);

        return ResponseEntity.ok(feeDataList);
    }

    @GetMapping("/get-by-datatype")
    public ResponseEntity<?> getByDataType(@RequestParam String dataType){
        List<FeeData> feeDataList = feeDataService.getByDataType(dataType);

        return ResponseEntity.ok(feeDataList);
    }

    @GetMapping("/get-by-student")
    public ResponseEntity<?> getByStudent(@RequestParam double student,@RequestParam long section,@RequestParam String dataType){

        return ResponseEntity.ok(feeDataService.getByStudent(student, section, dataType));
    }
}
