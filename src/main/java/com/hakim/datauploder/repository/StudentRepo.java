package com.hakim.datauploder.repository;

import com.hakim.datauploder.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepo extends JpaRepository<Student,Long> {
    List<Student> findAllBySection(long section);
}
