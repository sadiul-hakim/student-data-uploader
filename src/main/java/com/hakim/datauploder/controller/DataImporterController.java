package com.hakim.datauploder.controller;

import java.util.Collections;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hakim.datauploder.model.DataImporter;
import com.hakim.datauploder.service.DataImporterService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/data-importer")
public class DataImporterController {
    
    private final DataImporterService dataImporterService;

    @PostMapping("/save")
    public ResponseEntity<?> upload(@RequestBody DataImporter dataImporter){

        dataImporterService.save(dataImporter);

        return ResponseEntity.ok(Collections.singletonMap("message","DataImporter is saved successfully!"));
    }
}
