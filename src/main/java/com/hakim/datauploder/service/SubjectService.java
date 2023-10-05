package com.hakim.datauploder.service;

import com.hakim.datauploder.model.Subject;
import com.hakim.datauploder.repository.SubjectRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SubjectService {

    private final SubjectRepo subjectRepo;

    public Subject save(Subject su) {

        return subjectRepo.save(su);
    }

    @Cacheable("subject.getById")
    public Subject getById(long id) {

        return subjectRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Subject not found with id : " + id));
    }

    @Cacheable("subject.getByName")
    public Subject getByName(String name) {

        return subjectRepo.findByName(name)
                .orElseThrow(() -> new RuntimeException("Subject not found with name : " + name));
    }

    public List<Subject> getAll(){

        return subjectRepo.findAll();
    }
}
