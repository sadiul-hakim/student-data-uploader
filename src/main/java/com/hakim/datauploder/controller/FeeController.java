package com.hakim.datauploder.controller;

import com.hakim.datauploder.model.Fee;
import com.hakim.datauploder.service.FeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/fee")
public class FeeController {

    private final FeeService feeService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Fee fee){

        Fee save = feeService.save(fee);
        return ResponseEntity.ok(save);
    }

    @GetMapping("/{feeId}")
    public ResponseEntity<?> getById(@PathVariable long feeId){

        Fee byId = feeService.getById(feeId);
        return ResponseEntity.ok(byId);
    }

    @GetMapping("/get")
    public ResponseEntity<?> getByName(@RequestParam String name){

        Fee byId = feeService.getByName(name);
        return ResponseEntity.ok(byId);
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getAll(){

        return ResponseEntity.ok(feeService.getAll());
    }
}
