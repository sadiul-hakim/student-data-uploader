package com.hakim.datauploder.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SheetDetails implements Serializable{
    
    private int skipRow;
    private String sheetName;
}
