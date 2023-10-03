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
public class MonthlyPresence extends StudentData implements Serializable{

    @Id
    @GeneratedValue
    private long id;

    @Column(columnDefinition = "JSON")
    @Convert(converter = MonthlySheetConverter.class)
    private MonthlySheet sheetData;
}
