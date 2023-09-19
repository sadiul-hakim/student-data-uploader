package com.hakim.datauploder.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hakim.datauploder.model.EntityObject;

public interface EntityObjectRepo extends JpaRepository<EntityObject,Long>{
    
}
