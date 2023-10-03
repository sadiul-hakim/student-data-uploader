package com.hakim.datauploder.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentFee {

    private String studentRoll;
    private Map<String, Double> fees = new HashMap<>();
}
