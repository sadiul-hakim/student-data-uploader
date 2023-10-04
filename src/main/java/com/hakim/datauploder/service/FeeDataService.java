package com.hakim.datauploder.service;

import com.hakim.datauploder.model.FeeData;
import com.hakim.datauploder.repository.FeeDataRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
public class FeeDataService {

    private final FeeDataRepo repo;

    public FeeData save(FeeData fee) {
        return repo.save(fee);
    }

    public FeeData getById(long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Could not find by id: " + id));
    }

    public FeeData getBySection(long section) {

        return repo.findBySection(section)
                .orElse(null);
    }

    public FeeData getBySectionAndDataType(long section, String dataType) {

        return repo.findBySectionAndDataType(section, dataType)
                .orElse(null);
    }

    public List<LocalDate> getByStudent(long student, long section) {

        List<LocalDate> dateList = new ArrayList<>();


        return dateList;
    }

    public Map<LocalDate, List<Double>> getByMonth(long section, String date) {

        Map<LocalDate, List<Double>> rollMap = new HashMap<>();


        return rollMap;
    }

    public List<Double> getByDay(long section, String date) {

        List<Double> rolls = new ArrayList<>();


        return rolls;
    }

    public List<LocalDate> getByMonthAndStudent(long section, String date, long student) {

        List<LocalDate> dateList = new ArrayList<>();


        return dateList;
    }
}
