package com.hakim.datauploder.controller;

import java.util.Collections;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hakim.datauploder.model.EntityObject;
import com.hakim.datauploder.service.EntityObjectService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/entity-object")
public class EntityObjectController {
    
    private final EntityObjectService entityObjectService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody EntityObject entityObject){
        entityObjectService.save(entityObject);

        return ResponseEntity.ok(Collections.singletonMap("message","Entity Object is saved successfully!"));
    }
}
