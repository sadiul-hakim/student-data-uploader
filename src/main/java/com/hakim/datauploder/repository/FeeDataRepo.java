package com.hakim.datauploder.repository;

import com.hakim.datauploder.model.FeeData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FeeDataRepo extends JpaRepository<FeeData,Long> {
    Optional<FeeData> findBySection(long section);
    Optional<FeeData> findBySectionAndDataType(long section,String dataType);

}
