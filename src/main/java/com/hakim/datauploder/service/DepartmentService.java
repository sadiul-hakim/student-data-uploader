package com.hakim.datauploder.service;

import com.hakim.datauploder.model.Department;
import com.hakim.datauploder.repository.DepartmentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartmentRepo departmentRepo;

    public Department save(Department dataType) {
        return departmentRepo.save(dataType);
    }

    public Department getById(long id) {
        return departmentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Could not find department by id : " + id));
    }

    public Department getByName(String type) {
        return departmentRepo.findByName(type)
                .orElseThrow(() -> new RuntimeException("Could not find department by type : " + type));
    }

    public List<Department> getAll(){
        return departmentRepo.findAll();
    }
}
