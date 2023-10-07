package com.hakim.datauploder.service;

import com.hakim.datauploder.model.ResultData;
import com.hakim.datauploder.model.Subject;
import com.hakim.datauploder.pojo.StudentResult;
import com.hakim.datauploder.repository.ResultDataRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ResultDataService {

    private final ResultDataRepo repo;
    private final SubjectService subjectService;

    public ResultData save(ResultData result) {
        return repo.save(result);
    }

    public ResultData getById(long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Could not find by id: " + id));
    }

    public List<ResultData> getByFields(long section, long department, long year, long dataType) {

        return repo.findBySectionAndDepartmentAndYearAndDataType(section, department, year, dataType);
    }

    public Map<String, Map<String, Double>> getByStudent(double student, long section, long department, long year, long dataType) {

        Map<String, Map<String, Double>> results = new HashMap<>();

        List<ResultData> resultDataList = getByFields(section, department, year, dataType);
        for (ResultData resultData : resultDataList) {

            List<StudentResult> studentResults = resultData.getSheetData().getStudentResults();
            for (StudentResult studentResult : studentResults) {

                String roll = studentResult.getStudentRoll();
                String examName = resultData.getSheetData().getExamName();
                if (Double.parseDouble(roll) == student) {

                    results.put(examName, studentResult.getResult());
                }
            }
        }

        return getResultsWithName(results);
    }

    public Map<String, Map<String, Double>> getByExam(String exam, long section, long department, long year, long dataType) {

        Map<String, Map<String, Double>> resultsWithRoll = new HashMap<>();

        List<ResultData> resultDataList = getByFields(section, department, year, dataType);
        for (ResultData resultData : resultDataList) {

            List<StudentResult> studentResults = resultData.getSheetData().getStudentResults();
            for (StudentResult studentResult : studentResults) {

                String roll = studentResult.getStudentRoll();
                String examName = resultData.getSheetData().getExamName();
                if (examName.equalsIgnoreCase(exam)) {
                    resultsWithRoll.put(roll, studentResult.getResult());
                }
            }
        }

        return getResultsWithName(resultsWithRoll);
    }

    private Map<String, Map<String, Double>> getResultsWithName(Map<String, Map<String, Double>> fees) {

        for (Map.Entry<String, Map<String, Double>> stringMapEntry : fees.entrySet()) {
            Map<String, Double> temp = new HashMap<>();
            for (Map.Entry<String, Double> entry : stringMapEntry.getValue().entrySet()) {

                Subject subject = subjectService.getById(Long.parseLong(entry.getKey()));
                temp.put(subject.getName(), entry.getValue());
            }
            fees.put(stringMapEntry.getKey(), temp);
        }

        return fees;
    }
}
