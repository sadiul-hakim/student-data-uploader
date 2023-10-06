package com.hakim.datauploder.controller;

import com.hakim.datauploder.model.DataImporter;
import com.hakim.datauploder.service.DataImporterService;
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

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody DataImporter dataImporter) {

        DataImporter save = dataImporterService.save(dataImporter);

        return ResponseEntity.ok(save);
    }

    @GetMapping("/get")
    public ResponseEntity<?> get(@RequestParam long importerId) {
        DataImporter dataImporter = dataImporterService.getById(importerId);

        return ResponseEntity.ok(dataImporter);
    }

    @PostMapping("/import")
    public ResponseEntity<?> importData(@RequestParam MultipartFile file,
                                        @RequestParam long importerId,
                                        @RequestParam long section,
                                        @RequestParam long department,
                                        @RequestParam long year) throws IOException {

        boolean saved = dataImporterService.importData(file.getInputStream(), importerId, section, department, year, file.getOriginalFilename());

        if (!saved) {
            return ResponseEntity.ok(Collections.singletonMap("error", "Could not import data"));
        }

        return ResponseEntity.ok(Collections.singletonMap("message", "successfully imported data!"));
    }
}
