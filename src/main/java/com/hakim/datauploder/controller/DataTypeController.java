package com.hakim.datauploder.controller;

import com.hakim.datauploder.model.DataTypeModel;
import com.hakim.datauploder.service.DataTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/data-type")
public class DataTypeController {
    private final DataTypeService dataTypeService;


    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody DataTypeModel dataType){
        DataTypeModel save = dataTypeService.save(dataType);

        return ResponseEntity.ok(save);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable long id){
        DataTypeModel dataType = dataTypeService.getById(id);
        return ResponseEntity.ok(dataType);
    }

    @GetMapping("/get-by-type")
    public ResponseEntity<?> getById(@RequestParam String type){
        DataTypeModel dataType = dataTypeService.getByType(type);
        return ResponseEntity.ok(dataType);
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getAll(){
        List<DataTypeModel> all = dataTypeService.getAll();
        return ResponseEntity.ok(all);
    }
}
