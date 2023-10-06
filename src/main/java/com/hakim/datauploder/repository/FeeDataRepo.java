package com.hakim.datauploder.repository;

import com.hakim.datauploder.model.FeeData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FeeDataRepo extends JpaRepository<FeeData,Long> {
    List<FeeData> findBySectionAndDepartmentAndYearAndDataType(long section, long department, long year, long dataType);
}
