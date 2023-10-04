package com.hakim.datauploder.repository;

import com.hakim.datauploder.model.Fee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FeeRepo extends JpaRepository<Fee,Long> {
    Optional<Fee> findByName(String name);
}
