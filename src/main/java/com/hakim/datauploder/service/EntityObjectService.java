package com.hakim.datauploder.service;

import org.springframework.stereotype.Service;

import com.hakim.datauploder.model.EntityObject;
import com.hakim.datauploder.repository.EntityObjectRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EntityObjectService {

    private final EntityObjectRepo entityObjectRepo;
    
    public void save(EntityObject entityObject){
        EntityObject save = entityObjectRepo.save(entityObject);

        if(save == null) throw new RuntimeException("Could not save entityObject.");
    }

    public EntityObject getById(long id){
        return entityObjectRepo.findById(id)
        .orElseThrow(()-> new RuntimeException("Could not get entity object by id : "+id));
    }
}
