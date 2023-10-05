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
    public ResponseEntity<?> save(@RequestBody Subject subject){

        Subject save = subjectService.save(subject);
        return ResponseEntity.ok(save);
    }

    @GetMapping("/{feeId}")
    public ResponseEntity<?> getById(@PathVariable long feeId){

        Subject byId = subjectService.getById(feeId);
        return ResponseEntity.ok(byId);
    }

    @GetMapping("/get")
    public ResponseEntity<?> getByName(@RequestParam String name){

        Subject byId = subjectService.getByName(name);
        return ResponseEntity.ok(byId);
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getAll(){

        return ResponseEntity.ok(subjectService.getAll());
    }
}
