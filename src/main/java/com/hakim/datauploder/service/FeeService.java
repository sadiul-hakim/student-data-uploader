package com.hakim.datauploder.service;

import com.hakim.datauploder.model.Fee;
import com.hakim.datauploder.repository.FeeRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FeeService {

    private final FeeRepo feeRepo;

    public Fee save(Fee fee) {

        return feeRepo.save(fee);
    }

    public Fee getById(long id) {

        return feeRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Fee not found with id : " + id));
    }

    public Fee getByName(String name) {

        return feeRepo.findByName(name)
                .orElseThrow(() -> new RuntimeException("Fee not found with name : " + name));
    }

    public List<Fee> getAll(){

        return feeRepo.findAll();
    }
}
