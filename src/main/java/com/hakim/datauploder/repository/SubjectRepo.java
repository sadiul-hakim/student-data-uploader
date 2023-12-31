package com.hakim.datauploder.repository;

import com.hakim.datauploder.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SubjectRepo extends JpaRepository<Subject, Long> {
    Optional<Subject> findByName(String name);
    List<Subject> findBySectionAndDepartmentAndYear(long section, long department, long year);
}
