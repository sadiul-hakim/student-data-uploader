package com.hakim.datauploder.pojo;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SheetDetails implements Serializable{
    
    private int skipRow; // row number to skip from the first
    private String sheetName; // Name of the sheet
}
