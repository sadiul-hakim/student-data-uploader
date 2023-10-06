package com.hakim.datauploder.repository;

import com.hakim.datauploder.model.DataTypeModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DataTypeRepo extends JpaRepository<DataTypeModel,Long> {
    Optional<DataTypeModel> findByType(String type);
}
