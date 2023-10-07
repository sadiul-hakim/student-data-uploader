package com.hakim.datauploder.service;

import com.hakim.datauploder.model.Fee;
import com.hakim.datauploder.model.FeeData;
import com.hakim.datauploder.pojo.StudentFee;
import com.hakim.datauploder.repository.FeeDataRepo;
import com.hakim.datauploder.util.DateUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

    public List<FeeData> getByFields(long section, long department, long year, long dataType) {

        return repo.findBySectionAndDepartmentAndYearAndDataType(section, department, year, dataType);
    }

    /**
     * This method return Fee Data of a student. It returns
     * Map<Date,Map<Fee,Value>>
     */
    public Map<String, Map<String, Double>> getByStudent(double student, long section, long department, long year, long dataType) {

        Map<String, Map<String, Double>> fees = new HashMap<>();

        List<FeeData> feeDataList = getByFields(section, department, year, dataType);
        for (FeeData feeData : feeDataList) {

            List<StudentFee> studentFees = feeData.getSheetData().getStudentFees();
            for (StudentFee studentFee : studentFees) {

                String roll = studentFee.getStudentRoll();
                String date = DateUtil.format(feeData.getSheetData().getDate());
                if (Double.parseDouble(roll) == student) {

                    fees.put(date, studentFee.getFees());
                }
            }
        }

        return getFeesWithName(fees);
    }

    private Map<String, Map<String, Double>> getFeesWithName(Map<String, Map<String, Double>> fees) {

        for (Map.Entry<String, Map<String, Double>> stringMapEntry : fees.entrySet()) {

            Map<String, Double> temp = new HashMap<>();
            for (Map.Entry<String, Double> entry : stringMapEntry.getValue().entrySet()) {

                Fee fee = feeService.getById(Long.parseLong(entry.getKey()));
                temp.put(fee.getName(), entry.getValue());
            }

            fees.put(stringMapEntry.getKey(), temp);
        }

        return fees;
    }
}
