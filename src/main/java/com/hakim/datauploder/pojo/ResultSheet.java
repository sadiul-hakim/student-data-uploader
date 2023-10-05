package com.hakim.datauploder.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultSheet {

    private String examName;
    private LocalDate date;
    private List<StudentResult> studentResults = new ArrayList<>();
}
