package com.hakim.datauploder.model;

import com.hakim.datauploder.model.converter.MonthlySheetConverter;
import com.hakim.datauploder.pojo.MonthlySheet;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class MonthlyPresence implements Serializable {

    @Id
    @GeneratedValue
    private long id;
    private long section;
    @Column(columnDefinition = "JSON")
    @Convert(converter = MonthlySheetConverter.class)
    private MonthlySheet monthlySheet;
}
