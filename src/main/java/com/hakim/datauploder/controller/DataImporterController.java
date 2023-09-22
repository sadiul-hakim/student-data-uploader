package com.hakim.datauploder.controller;

import com.hakim.datauploder.model.DataImporter;
import com.hakim.datauploder.model.MonthlyPresence;
import com.hakim.datauploder.service.DataImporterService;
import com.hakim.datauploder.service.MonthlyPresenceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Collections;

@RestController
@RequiredArgsConstructor
@RequestMapping("/data-importer")
public class DataImporterController {
    
    private final DataImporterService dataImporterService;
    private final MonthlyPresenceService monthlyPresenceService;

    @PostMapping("/save")
    public ResponseEntity<?> upload(@RequestBody DataImporter dataImporter){

        dataImporterService.save(dataImporter);

        return ResponseEntity.ok(Collections.singletonMap("message","DataImporter is saved successfully!"));
    }

    @GetMapping("/get")
    public ResponseEntity<?> get(@RequestParam long importerId){
        DataImporter dataImporter = dataImporterService.getById(importerId);

        return ResponseEntity.ok(dataImporter);
    }

    @PostMapping("/import")
    public ResponseEntity<?> importData(@RequestParam MultipartFile file,
                                        @RequestParam long importerId) throws IOException {

        MonthlyPresence monthlyPresence = dataImporterService.importData(file.getInputStream(), importerId);

        return ResponseEntity.ok(monthlyPresence);
    }

    @GetMapping("/get-presence")
    public ResponseEntity<?> getMonthlyPresence(@RequestParam long presenceId){
        MonthlyPresence monthlyPresence = monthlyPresenceService.getById(presenceId);

        return ResponseEntity.ok(monthlyPresence);
    }
}
