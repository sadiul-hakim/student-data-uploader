package com.hakim.datauploder.service;

import com.hakim.datauploder.model.Fee;
import com.hakim.datauploder.model.FeeData;
import com.hakim.datauploder.pojo.StudentFee;
import com.hakim.datauploder.repository.FeeDataRepo;
import com.hakim.datauploder.util.DateUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
public class FeeDataService {

    private final FeeDataRepo repo;
    private final FeeService feeService;

    public FeeData save(FeeData fee) {
        return repo.save(fee);
    }

    public FeeData getById(long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Could not find by id: " + id));
    }

    public List<FeeData> getBySectionAndDataType(long section, String dataType) {

        return repo.findBySectionAndDataType(section, dataType);
    }

    public List<FeeData> getByDataType(String dataType) {

        return repo.findByDataType(dataType);
    }

    public Map<String, Map<String, Map<String, Double>>> getByStudent(double student, long section, String dataType) {

        Map<String, Map<String, Map<String, Double>>> feesWithRoll = new HashMap<>();

        List<FeeData> feeDataList = getBySectionAndDataType(section, dataType);
        Map<String, Map<String, Double>> feeWithDate = new HashMap<>();
        for (FeeData feeData : feeDataList) {

            List<StudentFee> studentFees = feeData.getSheetData().getStudentFees();
            for (StudentFee studentFee : studentFees) {

                String roll = studentFee.getStudentRoll();
                String date = DateUtil.format(feeData.getSheetData().getDate());
                if (Double.parseDouble(roll) == student) {

                    feeWithDate.put(date, studentFee.getFees());
                    feesWithRoll.put(roll, feeWithDate);
                }
            }
        }

        for (Map.Entry<String, Map<String, Map<String, Double>>> entry : feesWithRoll.entrySet()) {

            Map<String, Map<String, Double>> feesWithName = getFeesWithName(entry.getValue());
            feesWithRoll.put(entry.getKey(),feesWithName);
        }

        return feesWithRoll;
    }

    private Map<String, Map<String, Double>> getFeesWithName(Map<String, Map<String, Double>> fees) {

        for (Map.Entry<String, Map<String, Double>> stringMapEntry : fees.entrySet()) {

            Map<String, Double> temp = new HashMap<>();
            for (Map.Entry<String, Double> entry : stringMapEntry.getValue().entrySet()) {

                Fee fee = feeService.getById(Long.parseLong(entry.getKey()));
                temp.put(fee.getName(), entry.getValue());
            }

            fees.put(stringMapEntry.getKey(),temp);
        }

        return fees;
    }
}
