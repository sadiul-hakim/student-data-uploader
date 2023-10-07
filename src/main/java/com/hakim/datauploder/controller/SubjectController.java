package com.hakim.datauploder.controller;

import com.hakim.datauploder.model.Subject;
import com.hakim.datauploder.service.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/subject")
public class SubjectController {

    private final SubjectService subjectService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Subject subject) {

        Subject save = subjectService.save(subject);
        return ResponseEntity.ok(save);
    }

    @GetMapping("/{subjectId}")
    public ResponseEntity<?> getById(@PathVariable long subjectId) {

        Subject byId = subjectService.getById(subjectId);
        return ResponseEntity.ok(byId);
    }

    @GetMapping("/get-by-name")
    public ResponseEntity<?> getByName(@RequestParam String name) {

        Subject byId = subjectService.getByName(name);
        return ResponseEntity.ok(byId);
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getAll() {

        return ResponseEntity.ok(subjectService.getAll());
    }

    @GetMapping("/get-by-department-and-year")
    public ResponseEntity<?> getByDepartmentAndYear(@RequestParam long section,
                                                    @RequestParam long department,
                                                    @RequestParam long year) {

        return ResponseEntity.ok(subjectService.getBySectionAndDepartmentAndYear(section, department, year));
    }
}
