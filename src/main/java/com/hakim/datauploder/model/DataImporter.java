package com.hakim.datauploder.model;

import com.hakim.datauploder.model.converter.ExcelFileDetailsConverter;
import com.hakim.datauploder.pojo.ExcelFileDetails;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataImporter {
    
    @Id
    @GeneratedValue
    private long id; // 1

    private String name; // Student Attendance
    @Convert(converter = ExcelFileDetailsConverter.class)
    @Column(columnDefinition = "JSON")
    private ExcelFileDetails excelFileDetails; // Information of the excel file
}
