package com.hakim.datauploder.service;

import com.hakim.datauploder.model.DataImporter;
import com.hakim.datauploder.model.MonthlyPresence;
import com.hakim.datauploder.pojo.MonthlySheet;
import com.hakim.datauploder.pojo.Presence;
import com.hakim.datauploder.repository.DataImporterRepo;
import com.hakim.datauploder.util.DateUtil;
import com.hakim.datauploder.util.ExcelReader;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DataImporterService {
    private final String PRESENT = "P";

    private final DataImporterRepo dataImporterRepo;
    private final MonthlyPresenceService monthlyPresenceService;

    @Transactional
    public void save(DataImporter dataImporter) {
        DataImporter save = dataImporterRepo.save(dataImporter);
    }

    public DataImporter getById(long dataImporterId) {
        return dataImporterRepo.findById(dataImporterId)
                .orElseThrow(() -> new RuntimeException("Could not get DataImporter by id : " + dataImporterId));
    }

    public MonthlyPresence importData(InputStream inputStream, long importerId, long section) throws IOException {
        ExcelReader excelReader = new ExcelReader(inputStream);

        DataImporter importer = getById(importerId);

        Map<String, List<String>> data = excelReader.read(importer.getExcelFileDetails());

        MonthlyPresence monthlyPresence = generateMonthlyPresence(data,importer.getExcelFileDetails().getSheets().get(0).getMainColumn());
        monthlyPresence.setSection(section);

        MonthlyPresence existingData = monthlyPresenceService.getBySection(section);
        if (existingData == null) {
            return monthlyPresenceService.save(monthlyPresence);
        }else{
            List<Presence> presenceList = existingData.getMonthlySheet().getPresenceList();
            presenceList.addAll(monthlyPresence.getMonthlySheet().getPresenceList());
        }
        return monthlyPresenceService.save(existingData);
    }

    private MonthlyPresence generateMonthlyPresence(Map<String, List<String>> data,String mainColumn) {

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
                if (stringList.get(i).equalsIgnoreCase(PRESENT)) {
                    presence.getPresentStudentsRoll().add(Double.valueOf(mainColumnData.get(i)));
                }
            }

            presenceList.add(presence);
        }

        monthlySheet.setPresenceList(presenceList);
        monthlyPresence.setMonthlySheet(monthlySheet);
        return monthlyPresence;
    }
}
