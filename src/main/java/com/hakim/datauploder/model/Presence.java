package com.hakim.datauploder.model;

import com.hakim.datauploder.model.converter.NumberListConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.sql.Timestamp;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Presence {

    @Id
    private long id;

    private int studentClassNumber;
    private Timestamp currentDate;

    @Convert(converter = NumberListConverter.class)
    @Column(columnDefinition = "JSON")
    private List<Long> presentStudentsRoll;
}
