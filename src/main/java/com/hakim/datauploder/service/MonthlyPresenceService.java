package com.hakim.datauploder.service;

import com.hakim.datauploder.model.MonthlyPresence;
import com.hakim.datauploder.repository.MonthlyPresenceRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
}
