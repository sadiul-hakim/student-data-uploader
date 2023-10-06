package com.hakim.datauploder.service;

import com.hakim.datauploder.model.DataTypeModel;
import com.hakim.datauploder.repository.DataTypeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DataTypeService {
    private final DataTypeRepo dataTypeRepo;

    public DataTypeModel save(DataTypeModel dataType) {
        return dataTypeRepo.save(dataType);
    }

    public DataTypeModel getById(long id) {
        return dataTypeRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Could not find dataType by id : " + id));
    }

    public DataTypeModel getByType(String type) {
        return dataTypeRepo.findByType(type)
                .orElseThrow(() -> new RuntimeException("Could not find dataType by type : " + type));
    }

    public List<DataTypeModel> getAll(){
        return dataTypeRepo.findAll();
    }
}
