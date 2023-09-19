package com.hakim.datauploder.model;

import java.util.ArrayList;
import java.util.List;

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
public class EntityObject {
    
    @Id
    private long id;
    private String name;
    private String description;

    @Convert(converter = StringListConverter.class)
    private List<String> properties = new ArrayList<>();
}
