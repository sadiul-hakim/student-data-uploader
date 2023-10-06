package com.hakim.datauploder.controller;

import com.hakim.datauploder.model.Department;
import com.hakim.datauploder.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Department department){
        Department save = departmentService.save(department);

        return ResponseEntity.ok(save);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable long id){
        Department dataType = departmentService.getById(id);
        return ResponseEntity.ok(dataType);
    }

    @GetMapping("/get-by-name")
    public ResponseEntity<?> getByName(@RequestParam String name){
        Department dataType = departmentService.getByName(name);
        return ResponseEntity.ok(dataType);
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getAll(){
        List<Department> all = departmentService.getAll();
        return ResponseEntity.ok(all);
    }
}
