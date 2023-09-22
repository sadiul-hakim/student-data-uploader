package com.hakim.datauploder.repository;

import com.hakim.datauploder.model.MonthlyPresence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MonthlyPresenceRepo extends JpaRepository<MonthlyPresence,Long> {
}
