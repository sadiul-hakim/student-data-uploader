package com.hakim.datauploder.controller;

import com.hakim.datauploder.model.Section;
import com.hakim.datauploder.service.SectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/section")
public class SectionController {

    private final SectionService sectionService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Section section){
        Section save = sectionService.save(section);

        return ResponseEntity.ok(save);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable long id){
        Section dataType = sectionService.getById(id);
        return ResponseEntity.ok(dataType);
    }

    @GetMapping("/get-by-name")
    public ResponseEntity<?> getByName(@RequestParam String name){
        Section dataType = sectionService.getByName(name);
        return ResponseEntity.ok(dataType);
    }

    @GetMapping("/get-all")
    public ResponseEntity<?> getAll(){
        List<Section> all = sectionService.getAll();
        return ResponseEntity.ok(all);
    }
}
