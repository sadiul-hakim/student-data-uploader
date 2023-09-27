package com.hakim.datauploder.controller;

import com.hakim.datauploder.model.Student;
import com.hakim.datauploder.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Student student){
        Student savedStudent = studentService.save(student);

        if(savedStudent == null){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("error","Could not save Student."));
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(savedStudent);
    }

    @GetMapping("/{studentRoll}")
    public ResponseEntity<?> getStudent(@PathVariable long studentRoll){
        Student student = studentService.getById(studentRoll);

        if(student == null){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.singletonMap("error","Could not save Student."));
        }

        return ResponseEntity.status(HttpStatus.OK).body(student);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAll(){
        List<Student> allStudent = studentService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(allStudent);
    }

    @GetMapping("/get-by-section/{section}")
    public ResponseEntity<?> getAllBySection(@PathVariable long section){
        List<Student> allStudent = studentService.getBySection(section);
        return ResponseEntity.status(HttpStatus.OK).body(allStudent);
    }
}
