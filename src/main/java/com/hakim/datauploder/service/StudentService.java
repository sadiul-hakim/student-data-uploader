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

    public Student save(Student student) {
        return studentRepo.save(student);
    }

    @Cacheable("student.getById")
    public Student getByStudentRoll(long studentRoll, long section, long department, long year) {
        return studentRepo.findByStudentRollAndSectionAndDepartmentAndYear(studentRoll, section, department, year)
                .orElseThrow(() -> new RuntimeException("Could not find student by roll: " + studentRoll));
    }

    public List<Student> getAll() {
        return studentRepo.findAll();
    }

    public List<Student> getByDepartment(long section, long department, long year) {
        return studentRepo.findAllBySectionAndDepartmentAndYear(section, department, year);
    }
}
