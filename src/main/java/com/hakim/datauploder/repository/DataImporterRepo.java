package com.hakim.datauploder.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hakim.datauploder.model.DataImporter;

public interface DataImporterRepo extends JpaRepository<DataImporter,Long>{
    
}
