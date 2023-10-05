package com.hakim.datauploder.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentResult {
    private String studentRoll;
    private Map<String, Double> result = new HashMap<>();
}
