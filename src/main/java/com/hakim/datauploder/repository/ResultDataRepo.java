package com.hakim.datauploder.repository;

import com.hakim.datauploder.model.ResultData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ResultDataRepo extends JpaRepository<ResultData,Long> {
    List<ResultData> findBySectionAndDepartmentAndYearAndDataType(long section, long department, long year, long dataType);
}
