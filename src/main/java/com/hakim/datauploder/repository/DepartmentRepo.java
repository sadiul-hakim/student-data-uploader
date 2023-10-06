package com.hakim.datauploder.repository;

import com.hakim.datauploder.model.Department;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DepartmentRepo extends JpaRepository<Department,Long> {
    Optional<Department> findByName(String name);
}
