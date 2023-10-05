package com.hakim.datauploder.service;

import com.hakim.datauploder.model.Student;
import com.hakim.datauploder.repository.StudentRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepo studentRepo;

    public Student save(Student student){
        return studentRepo.save(student);
    }

    @Cacheable("student.getById")
    public Student getById(long studentRoll,long section){
        return studentRepo.findByStudentRollAndSection(studentRoll,section)
                .orElseThrow(() -> new RuntimeException("Could not find student by roll: " + studentRoll));
    }

    public List<Student> getAll(){
        return studentRepo.findAll();
    }

    public List<Student> getBySection(long section){
        return studentRepo.findAllBySection(section);
    }
}
