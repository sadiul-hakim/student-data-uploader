package com.hakim.datauploder.model;

import com.hakim.datauploder.model.converter.FeeSheetConverter;
import com.hakim.datauploder.pojo.FeeSheet;
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
public class FeeData extends StudentData implements Serializable {

    @Id
    @GeneratedValue
    private long id;

    @Column(columnDefinition = "JSON")
    @Convert(converter = FeeSheetConverter.class)
    private FeeSheet sheetData;
}
