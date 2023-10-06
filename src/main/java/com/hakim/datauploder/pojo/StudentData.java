package com.hakim.datauploder.pojo;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentData {

    @Id
    @GeneratedValue
    private long id;
    @Column
    private long section;
    @Column
    private long department;
    @Column
    private long year;
    @Column
    private long dataType;
}
