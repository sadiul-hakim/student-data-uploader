package com.hakim.datauploder.repository;

import com.hakim.datauploder.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentRepo extends JpaRepository<Student, Long> {
    List<Student> findAllBySectionAndDepartmentAndYear(long section, long department, long year);

    Optional<Student> findByStudentRollAndSectionAndDepartmentAndYear(long roll, long section, long department, long year);
}
