package com.hakim.datauploder.model;

import com.hakim.datauploder.model.converter.ResultSheetConverter;
import com.hakim.datauploder.pojo.ResultSheet;
import com.hakim.datauploder.pojo.StudentData;
import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "student_data")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class ResultData extends StudentData {

    @Column(columnDefinition = "JSON")
    @Convert(converter = ResultSheetConverter.class)
    private ResultSheet sheetData;
}
