package com.hakim.datauploder.service;

import com.hakim.datauploder.model.*;
import com.hakim.datauploder.pojo.*;
import com.hakim.datauploder.repository.DataImporterRepo;
import com.hakim.datauploder.util.DateUtil;
import com.hakim.datauploder.util.ExcelReader;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
public class DataImporterService {

    private final DataImporterRepo dataImporterRepo;
    private final MonthlyPresenceService monthlyPresenceService;
    private final FeeDataService feeDataService;
    private final FeeService feeService;
    private final SubjectService subjectService;
    private final ResultDataService resultDataService;

    public void save(DataImporter dataImporter) {
        DataImporter save = dataImporterRepo.save(dataImporter);
    }

    public DataImporter getById(long dataImporterId) {
        return dataImporterRepo.findById(dataImporterId)
                .orElseThrow(() -> new RuntimeException("Could not get DataImporter by id : " + dataImporterId));
    }

    public boolean importData(InputStream inputStream, long importerId, long section,String fileName) throws IOException {
        ExcelReader excelReader = new ExcelReader(inputStream);

        DataImporter importer = getById(importerId);
        if (importer == null) {
            return false;
        }

        switch (importer.getDataSaving().getDataType()) {
            case FEE -> {
                Map<String, List<String>> data = excelReader.read(importer.getExcelFileDetails());

                FeeData feeData = generateFeeData(data, importer.getExcelFileDetails().getSheets().get(0).getMainColumn());
                feeData.setSection(section);
                feeData.setDataType(importer.getDataSaving().getDataType().name());

                FeeData save = feeDataService.save(feeData);
                return save != null;
            }
            case EXAM_RESULT -> {
                Map<String, List<String>> data = excelReader.read(importer.getExcelFileDetails());

                String[] fileNameArr = fileName.split("\\.");
                ResultData resultData = generateResultData(data, importer.getExcelFileDetails().getSheets().get(0).getMainColumn(),fileNameArr[0]);
                resultData.setSection(section);
                resultData.setDataType(importer.getDataSaving().getDataType().name());

                ResultData save = resultDataService.save(resultData);
                return save != null;
            }
            case PRESENCE -> {
                Map<String, List<String>> data = excelReader.read(importer.getExcelFileDetails());

                MonthlyPresence monthlyPresence = generateMonthlyPresence(data, importer.getExcelFileDetails().getSheets().get(0).getMainColumn());
                monthlyPresence.setSection(section);
                monthlyPresence.setDataType(importer.getDataSaving().getDataType().name());

                MonthlyPresence existingData = monthlyPresenceService.getBySectionAndDataType(section, importer.getDataSaving().getDataType().name());
                if (existingData == null) {
                    MonthlyPresence save = monthlyPresenceService.save(monthlyPresence);
                    return save != null;
                } else {
                    existingData.getSheetData().getPresenceList().addAll(monthlyPresence.getSheetData().getPresenceList());
                }
                MonthlyPresence save = monthlyPresenceService.save(existingData);
                return save != null;
            }
            default -> {
            }
        }

        return false;
    }

    private MonthlyPresence generateMonthlyPresence(Map<String, List<String>> data, String mainColumn) {

        MonthlyPresence monthlyPresence = new MonthlyPresence();
        MonthlySheet monthlySheet = new MonthlySheet();

        List<String> mainColumnData = data.get(mainColumn);
        List<Presence> presenceList = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : data.entrySet()) {

            if (entry.getKey().equalsIgnoreCase(mainColumn)) continue;
            Presence presence = new Presence();
            presence.setDate(DateUtil.stringToLocalDate(entry.getKey(), DateUtil.DATE_FIRST_FORMATTER));

            List<String> stringList = entry.getValue();
            for (int i = 0; i < mainColumnData.size(); i++) {
                String PRESENT = "P";
                if (stringList.get(i).equalsIgnoreCase(PRESENT)) {
                    presence.getPresentStudentsRoll().add(Double.valueOf(mainColumnData.get(i)));
                }
            }

            presenceList.add(presence);
        }

        monthlySheet.setPresenceList(presenceList);
        monthlyPresence.setSheetData(monthlySheet);
        return monthlyPresence;
    }

    private FeeData generateFeeData(Map<String, List<String>> data, String mainColumn) {

        FeeData feeData = new FeeData();
        FeeSheet feeSheet = new FeeSheet();

        List<String> rolls = data.get(mainColumn);
        List<StudentFee> studentFees = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : data.entrySet()) {

            if (entry.getKey().equals(mainColumn)) continue;
            Fee fee = feeService.getByName(entry.getKey());

            for (int i = 0; i < rolls.size(); i++) {

                StudentFee studentFee = null;
                for (StudentFee temp : studentFees) {
                    if (temp.getStudentRoll().equals(rolls.get(i))) {
                        studentFee = temp;
                    }
                }

                if (studentFee == null) {
                    studentFee = new StudentFee();
                    studentFee.setStudentRoll(rolls.get(i));
                    studentFee.getFees().put(fee.getId() + "", Double.valueOf(entry.getValue().get(i)));
                    studentFees.add(studentFee);
                } else {
                    studentFee.getFees().put(fee.getId() + "", Double.valueOf(entry.getValue().get(i)));
                }

            }
        }

        feeSheet.setStudentFees(studentFees);
        feeSheet.setDate(LocalDate.now());

        feeData.setSheetData(feeSheet);

        return feeData;
    }

    private ResultData generateResultData(Map<String, List<String>> data, String mainColumn,String examName) {

        ResultData resultData = new ResultData();
        ResultSheet resultSheet = new ResultSheet();

        List<String> rolls = data.get(mainColumn);
        List<StudentResult> studentResults = new ArrayList<>();
        for (Map.Entry<String, List<String>> entry : data.entrySet()) {

            if (entry.getKey().equals(mainColumn)) continue;
            Subject subject = subjectService.getByName(entry.getKey());

            for (int i = 0; i < rolls.size(); i++) {

                StudentResult studentResult = null;
                for (StudentResult result : studentResults) {
                    if (result.getStudentRoll().equals(rolls.get(i))) {
                        studentResult = result;
                    }
                }

                if (studentResult == null) {
                    studentResult = new StudentResult();
                    studentResult.setStudentRoll(rolls.get(i));
                    studentResult.getResult().put(subject.getId() + "", Double.valueOf(entry.getValue().get(i)));
                    studentResults.add(studentResult);
                } else {
                    studentResult.getResult().put(subject.getId() + "", Double.valueOf(entry.getValue().get(i)));
                }
            }
        }

        resultSheet.setStudentResults(studentResults);
        resultSheet.setDate(LocalDate.now());
        resultSheet.setExamName(examName);

        resultData.setSheetData(resultSheet);
        return resultData;
    }
}
