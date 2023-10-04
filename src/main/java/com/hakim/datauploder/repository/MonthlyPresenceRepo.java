package com.hakim.datauploder.repository;

import com.hakim.datauploder.model.MonthlyPresence;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MonthlyPresenceRepo extends JpaRepository<MonthlyPresence,Long> {
    Optional<MonthlyPresence> findBySection(long section);
    Optional<MonthlyPresence> findBySectionAndDataType(long section,String dataType);
}
