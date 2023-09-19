package com.hakim.datauploder.model;

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
    private long id;

    private String name;

    private long entityObject;

    @Convert(converter = ExcelFileDetailsConverter.class)
    private ExcelFileDetails excelFileDetails;
}
