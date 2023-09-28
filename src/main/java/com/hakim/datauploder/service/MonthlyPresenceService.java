package com.hakim.datauploder.service;

import com.hakim.datauploder.model.MonthlyPresence;
import com.hakim.datauploder.pojo.Presence;
import com.hakim.datauploder.repository.MonthlyPresenceRepo;
import com.hakim.datauploder.util.DateUtil;
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
public class MonthlyPresenceService {
    private final MonthlyPresenceRepo repo;

    public MonthlyPresence save(MonthlyPresence presence) {
        return repo.save(presence);
    }

    public MonthlyPresence getById(long id) {
        return repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Could not find by id: " + id));
    }

    public MonthlyPresence getBySection(long section) {
        return repo.findBySection(section)
                .orElse(null);
    }

    public List<LocalDate> getByStudent(long student, long section) {

        List<LocalDate> dateList = new ArrayList<>();
        MonthlyPresence monthlyPresence = getBySection(section);

        List<Presence> presenceList = monthlyPresence.getMonthlySheet().getPresenceList();
        if (presenceList.isEmpty()) return dateList;

        for (Presence presence : presenceList) {
            LocalDate date = presence.getDate();
            if (presence.getPresentStudentsRoll().contains(Double.parseDouble(student + ""))) {
                dateList.add(date);
            }
        }

        return dateList;
    }

    public Map<LocalDate, List<Double>> getByMonth(long section, String date) {

        Map<LocalDate, List<Double>> rollMap = new HashMap<>();
        MonthlyPresence monthlyPresence = getBySection(section);

        List<Presence> presenceList = monthlyPresence.getMonthlySheet().getPresenceList();
        if (presenceList.isEmpty()) return rollMap;

        Integer[] dateArray = DateUtil.getDateArray(date, false);
        assert dateArray != null;
        for (Presence presence : presenceList) {
            if (presence.getDate().getMonth().getValue() == dateArray[0] &&
                    presence.getDate().getYear() == dateArray[1]) {

                rollMap.put(presence.getDate(), presence.getPresentStudentsRoll());
            }
        }

        return rollMap;
    }

    public List<Double> getByDay(long section, String date) {

        List<Double> rolls = new ArrayList<>();
        MonthlyPresence monthlyPresence = getBySection(section);

        List<Presence> presenceList = monthlyPresence.getMonthlySheet().getPresenceList();
        if (presenceList.isEmpty()) return rolls;

        Integer[] dateArray = DateUtil.getDateArray(date, true);
        assert dateArray != null;
        for (Presence presence : presenceList) {
            if (presence.getDate().getDayOfMonth() == dateArray[0] &&
                    presence.getDate().getMonth().getValue() == dateArray[1] &&
                    presence.getDate().getYear() == dateArray[2]) {

                rolls.addAll(presence.getPresentStudentsRoll());
            }
        }

        return rolls;
    }

    public List<LocalDate> getByMonthAndStudent(long section, String date, long student) {

        List<LocalDate> dateList = new ArrayList<>();
        MonthlyPresence monthlyPresence = getBySection(section);

        List<Presence> presenceList = monthlyPresence.getMonthlySheet().getPresenceList();
        if (presenceList.isEmpty()) return dateList;

        Integer[] dateArray = DateUtil.getDateArray(date, false);
        assert dateArray != null;
        for (Presence presence : presenceList) {
            if (presence.getDate().getMonth().getValue() == dateArray[0] &&
                    presence.getDate().getYear() == dateArray[1] &&
                    presence.getPresentStudentsRoll().contains(Double.parseDouble(student + ""))) {
                dateList.add(presence.getDate());
            }
        }

        return dateList;
    }
}
