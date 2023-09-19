package com.hakim.datauploder.service;

import org.springframework.stereotype.Service;

import com.hakim.datauploder.model.DataImporter;
import com.hakim.datauploder.repository.DataImporterRepo;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DataImporterService{
    
    private final DataImporterRepo dataImporterRepo;

    public void save(DataImporter dataImporter){
        DataImporter save = dataImporterRepo.save(dataImporter);

        if(save == null){
            throw new RuntimeException("Could not save DataImporter.");
        }
    }

    public DataImporter getById(long dataImporterId){
        return dataImporterRepo.findById(dataImporterId)
        .orElseThrow(() -> new RuntimeException("Could not get DataImporter by id : "+dataImporterId));
    }
}