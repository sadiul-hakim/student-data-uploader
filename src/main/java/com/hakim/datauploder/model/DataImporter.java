package com.hakim.datauploder.model;

import com.hakim.datauploder.model.converter.ExcelFileDetailsConverter;
import com.hakim.datauploder.pojo.ExcelFileDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataImporter {
    
    @Id
    private long id; // 1

    private String name; // Student Attendance

    @Convert(converter = ExcelFileDetailsConverter.class)
    @Column(columnDefinition = "JSON")
    private ExcelFileDetails excelFileDetails; // Information of the excel file
}
