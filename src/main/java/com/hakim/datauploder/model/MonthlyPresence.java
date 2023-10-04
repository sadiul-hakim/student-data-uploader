package com.hakim.datauploder.model;

import com.hakim.datauploder.model.converter.MonthlySheetConverter;
import com.hakim.datauploder.pojo.MonthlySheet;
import com.hakim.datauploder.pojo.StudentData;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "student_data")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class MonthlyPresence extends StudentData implements Serializable{

    @Column(columnDefinition = "JSON")
    @Convert(converter = MonthlySheetConverter.class)
    private MonthlySheet sheetData;
}
